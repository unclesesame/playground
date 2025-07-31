package com.abner.playground.spark;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.catalyst.expressions.*;
import org.apache.spark.sql.catalyst.plans.logical.*;
import scala.collection.Seq;

import java.util.*;
import java.util.stream.Collectors;


public class SparkDemo {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark Demo")
                .master("local[*]")
                .getOrCreate();
        Dataset<Row> people = spark.read().json("src/main/resources/com/abner/playground/spark/people.json");
        Dataset<Row> scores = spark.read().json("src/main/resources/com/abner/playground/spark/score.json");

        LogicalPlan logicalPlan = people.join(scores, "name").select("name", "age", "score")
                .queryExecution().optimizedPlan();
        // 提取血缘关系
        Map<String, Set<String>> lineageMap = extractColumnLineage(logicalPlan);
        System.out.println("!!!!!!"+lineageMap.keySet());
        System.out.println("!!!!!!"+lineageMap.values());

        // 构建DAG
        Map<String, LineageNode> dag = buildDAG(lineageMap);

        // 打印结果
        printLineage(dag);
    }

    // 主解析方法
    public static Map<String, Set<String>> extractColumnLineage(LogicalPlan plan) {
        Map<String, Set<String>> result = new HashMap<>();

        if (plan instanceof Project) {
            Project project = (Project) plan;
            Map<String, Set<String>> childResult = extractColumnLineage(project.child());

            Seq<NamedExpression> projectList = project.projectList();

            List<Expression> exprList = new ArrayList<>();
            for (int i = 0; i < projectList.size(); i++) {
                exprList.add((Expression) projectList.apply(i));
            }
            for (Expression expr : exprList) {
                processExpression(expr, childResult, result);
            }

        } else if (plan instanceof Aggregate) {
            Aggregate aggregate = (Aggregate) plan;
            Map<String, Set<String>> childResult = extractColumnLineage(aggregate.child());

            Seq<Expression> groupExpr = aggregate.groupingExpressions();
            Seq<NamedExpression> aggExpr = aggregate.aggregateExpressions();

            // 处理分组表达式
            List<Expression> exprList = new ArrayList<>();
            for (int i = 0; i < groupExpr.size(); i++) {
                exprList.add((Expression) groupExpr.apply(i));
            }
            for (Expression expr : exprList) {
                processExpression(expr, childResult, result);
            }

            // 处理聚合表达式
            exprList.clear();
            for (int i = 0; i < aggExpr.size(); i++) {
                exprList.add((Expression) aggExpr.apply(i));
            }
            for (Expression expr : exprList) {
                processExpression(expr, childResult, result);
            }

        } else if (plan instanceof Join) {
            Join join = (Join) plan;
            result.putAll(extractColumnLineage(join.left()));
            result.putAll(extractColumnLineage(join.right()));

        } else {
            // 递归处理所有子节点
            for (LogicalPlan child : scala.collection.JavaConversions.seqAsJavaList(plan.children())) {
                result.putAll(extractColumnLineage(child));
            }

            // 处理叶子节点（基础表）
            if (plan instanceof LeafNode) {
                for (Attribute attr : scala.collection.JavaConversions.seqAsJavaList(plan.output())) {
                    result.computeIfAbsent(attr.name(), k -> new HashSet<>());
                }
            }
        }

        return result;
    }

    // 处理表达式
    private static void processExpression(Expression expr,
                                          Map<String, Set<String>> childResult,
                                          Map<String, Set<String>> result) {
        if (expr instanceof Alias) {
            Alias alias = (Alias) expr;
            String outputName = alias.name();

            Set<String> inputRefs = extractAttributeReferences(alias.child())
                    .stream()
                    .map(AttributeReference::name)
                    .collect(Collectors.toSet());

            // 添加继承关系
            Set<String> allInputs = new HashSet<>();
            for (String input : inputRefs) {
                if (childResult.containsKey(input)) {
                    allInputs.addAll(childResult.get(input));
                } else {
                    allInputs.add(input);
                }
            }

            result.put(outputName, allInputs);

        } else if (expr instanceof Attribute) {
            Attribute attr = (Attribute) expr;
            result.computeIfAbsent(attr.name(), k -> new HashSet<>());
        }
    }

    // 提取属性引用
    private static List<AttributeReference> extractAttributeReferences(Expression expr) {
        List<AttributeReference> refs = new ArrayList<>();
       /* expr.collect(attr -> {
            if (attr instanceof AttributeReference) {
                refs.add((AttributeReference) attr);
            }
        });*/
        return refs;
    }

    // 构建DAG
    public static Map<String, LineageNode> buildDAG(Map<String, Set<String>> lineageMap) {
        Map<String, LineageNode> nodes = new HashMap<>();

        // 创建所有节点
        lineageMap.keySet().forEach(name -> nodes.put(name, new LineageNode(name)));

        // 建立连接关系
        for (Map.Entry<String, Set<String>> entry : lineageMap.entrySet()) {
            String output = entry.getKey();
            LineageNode outputNode = nodes.get(output);

            for (String input : entry.getValue()) {
                if (nodes.containsKey(input)) {
                    LineageNode inputNode = nodes.get(input);
                    outputNode.inputs.add(inputNode);
                    inputNode.outputs.add(outputNode);
                }
            }
        }

        return nodes;
    }

    // 打印血缘关系
    public static void printLineage(Map<String, LineageNode> dag) {
        System.out.println("Data Lineage DAG:");
        System.out.println("-----------------");

        for (LineageNode node : dag.values()) {
            System.out.print(node.name + " <- [");
            String inputs = node.inputs.stream()
                    .map(n -> n.name)
                    .collect(Collectors.joining(", "));
            System.out.println(inputs + "]");

            if (!node.outputs.isEmpty()) {
                System.out.print("  -> [");
                String outputs = node.outputs.stream()
                        .map(n -> n.name)
                        .collect(Collectors.joining(", "));
                System.out.println(outputs + "]");
            }
        }

        System.out.println("\nField-Level Lineage Details:");
        System.out.println("----------------------------");
        dag.forEach((name, node) -> {
            if (!node.inputs.isEmpty()) {
                System.out.println(name + " depends on: " +
                        node.inputs.stream().map(n -> n.name).collect(Collectors.joining(", ")));
            }
        });
    }

    static class LineageNode {
        String name;
        Set<LineageNode> inputs = new HashSet<>();
        Set<LineageNode> outputs = new HashSet<>();

        LineageNode(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}


package com.abner.playground.spark;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import static org.apache.spark.sql.functions.col;

public class SparkDemo {
    public static void main(String[] args) {
         SparkSession spark = SparkSession
                 .builder()
                 .appName("Spark Demo")
                 .config("master","local")
                 .getOrCreate();

        Dataset<Row> df = spark.read().json("src/main/resources/com/abner/playground/spark/people.json");
         df.show();
         df.printSchema();
         df.select("name").show();
         df.select(col("name"), col("age").plus(1)).show();
         df.filter(col("age").gt(21)).show();
         df.groupBy("age").count().show();

        /**
         * SQL
         */
        df.createOrReplaceTempView("people");
        Dataset<Row> sqlDF = spark.sql("SELECT * FROM people");
        sqlDF.show();
    }
}

package com.abner.playground.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkService {

    @Autowired
    private SparkSession sparkSession;

    public void dataLineageDemo(){
        Dataset<Row> userDF = sparkSession.read().json("src/main/resources/user.json"); // 示例：读取JSON文件
        userDF.createOrReplaceTempView("user_table"); // 创建临时视图以便于SQL查询
        Dataset<Row> result = sparkSession.sql("SELECT * FROM user_table"); // 执行SQL查询
        result.show(); // 显示结果
    }
}

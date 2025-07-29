package com.abner.playground.spark;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("SpringBootSparkApp")
                .master("local[*]")
                .getOrCreate();
    }
}

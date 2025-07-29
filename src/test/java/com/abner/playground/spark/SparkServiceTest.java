package com.abner.playground.spark;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SparkServiceTest {
    @Autowired
    private SparkService sparkService;

    @Test
    public void dataLineageDemo() {
        sparkService.dataLineageDemo();
    }
}
package com.abner.playground.leetcode.dp;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
public class DpSolutionTest {

    @Test
    public void minCostClimbingStairs() {
        DpSolution solution = new DpSolution();
        Assertions.assertEquals(15, solution.minCostClimbingStairs(new int[]{10,15,20}));
    }
}
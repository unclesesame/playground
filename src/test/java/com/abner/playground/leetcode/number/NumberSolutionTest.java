package com.abner.playground.leetcode.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class NumberSolutionTest {

    @Test
    public void intToRoman() {
        NumberSolution sol = new NumberSolution();
        String res = sol.intToRoman(8);
        System.out.println(res);

    }
}
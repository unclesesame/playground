package com.abner.playground.leetcode.array;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class ArraySolutionTest {

    @Test
    public void isPalindrome() {
        ArraySolution arraySolution = new ArraySolution();
       // Assertions.assertTrue(arraySolution.isPalindrome("A man, a plan, a canal: Panama"));
        Assertions.assertTrue(arraySolution.isPalindrome("0P"));
    }

    @Test
    public void canJump() {
        ArraySolution arraySolution = new ArraySolution();
        Assertions.assertFalse(arraySolution.canJump(new int[]{3, 2, 1, 0, 4}));
        Assertions.assertTrue(arraySolution.canJump(new int[]{2, 3, 1, 1, 4}));
    }
}
package com.abner.playground.leetcode.map;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class MapSolutionTest {

    @Test
    public void nearestExit() {
        MapSolution solution = new MapSolution();
        char[][] maze = new char[][]{{'+','+','.','+'},{'.','.','.','+'},{'+','+','+','.'}};
        int[] entrance = new int[]{1,2};
        Assertions.assertEquals(1, solution.nearestExit(maze, entrance));
    }
}
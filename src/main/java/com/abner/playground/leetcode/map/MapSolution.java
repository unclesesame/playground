package com.abner.playground.leetcode.map;

import com.abner.playground.leetcode.other.Solution;

public class MapSolution {

    public static void main(String[] args) {
        MapSolution solution = new MapSolution();
        solution.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
    }

    //No.547 省份数量。 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int provinces = 0;
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                dfs(isConnected, n, isVisited, i);
                provinces++;
            }
        }
        return provinces;
    }

    private void dfs(int[][] isConnected, int n, boolean[] isVisited, int i) {
        for (int j = 0; j < n; j++) {
            if (isConnected[i][j] == 1 && !isVisited[j]) {
                isVisited[j] = true;
                dfs(isConnected, n, isVisited, j);
            }
        }
    }

    //No.1926 迷宫中离入口最近的出口
    // m x n 的迷宫矩阵 maze （下标从 0 开始），矩阵中有空格子（用 '.' 表示）和墙（用 '+' 表示）。同时给你迷宫的入口 entrance ，用 entrance = [entrancerow, entrancecol] 表示你一开始所在格子的行和列
    //每一步操作，你可以往 上，下，左 或者 右 移动一个格子。你不能进入墙所在的格子，你也不能离开迷宫。你的目标是找到离 entrance 最近 的出口。出口 的含义是 maze 边界 上的 空格子。entrance 格子 不算 出口。
    //返回从 entrance 到最近出口的最短路径的 步数 ，如果不存在这样的路径，返回 -1

    public int nearestExit(char[][] maze, int[] entrance) {
        


        return -1;
    }
}

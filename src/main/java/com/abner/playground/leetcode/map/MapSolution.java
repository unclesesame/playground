package com.abner.playground.leetcode.map;

import com.abner.playground.leetcode.other.Solution;

import java.util.LinkedList;
import java.util.Queue;

public class MapSolution {

    public static void main(String[] args) {
        MapSolution solution = new MapSolution();
        //solution.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        //int count = solution.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}});
        int count = solution.orangesRotting(new int[][]{{0}});
        System.out.println(count);
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
        int m = maze.length;
        int n = maze[0].length;
        // 入口标记为墙
        maze[entrance[0]][entrance[1]] = '+';
        //每一步只能上下左右走
        int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}};
        //起点坐标转为一个int 编号
        int start = entrance[0]*n + entrance[1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0}); //起点入队

        while(!queue.isEmpty()){
            int[] front = queue.poll();
            //编号转坐标
            int row = front[0]/n;
            int column =  front[0]%n;
            int d = front[1];
            for(int[] direct: directions){
                //往四个方向走
                int nextRow = row+direct[0];
                int nextColumn = column+direct[1];
                //跳过墙和非法边界
                if(nextRow == -1 || nextRow == m || nextColumn == -1 || nextColumn == n || maze[nextRow][nextColumn] == '+'){
                    continue;
                }
                //到达出口边界
                if(nextRow == 0 || nextRow == m-1 || nextColumn == 0 || nextColumn == n-1){
                    return d+1;
                }
                int nextPosition = nextRow * n + nextColumn;// 新的坐标编号和距起点距离入队
                queue.offer(new int[]{nextPosition, d+1});
                maze[nextRow][nextColumn] = '+'; //已入队访问过，设置为墙
            }
        }

        //无法到达出口
        return -1;
    }

    //No.994 腐烂的橘子
    //给定m*n的grid，每个单元格只有三种取值， 0：空， 1：新鲜橘子 2：腐烂橘子
    //每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂
    //返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1
    public int orangesRotting(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        //只能传染上下左右四个方向
        int directions[][] = {{1,0},{-1,0},{0,1},{0,-1}};

        //定义boolean数组
        boolean isVisited[][] = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int freshOrangeCount = 0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                //腐烂的橘子入队
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i,j});
                    isVisited[i][j] = true;
                }else if(grid[i][j] == 1){
                    freshOrangeCount++;
                }
            }
        }

        if(queue.isEmpty() && freshOrangeCount != 0) return -1;

        int minute=0;

        while (!queue.isEmpty()){
            int size = queue.size(); //上轮有多少橘子感染

            while(size-- > 0){
                int[] cell = queue.poll();
                int row = cell[0];
                int column = cell[1];
                for(int[] direct: directions){
                    int nextRow = row + direct[0];
                    int nextColumn =  column + direct[1];

                    if(nextRow ==-1 || nextRow == m || nextColumn == -1 || nextColumn == n)
                        continue;

                    if(!isVisited[nextRow][nextColumn] && grid[nextRow][nextColumn] == 1){
                        queue.offer(new int[]{nextRow, nextColumn});
                        isVisited[nextRow][nextColumn] = true;
                        grid[nextRow][nextColumn] = 2;
                        freshOrangeCount --;
                    }
                }
            }
            //当轮有橘子感染，时间加1
            if(!queue.isEmpty()){
                minute ++;
            }
        }

        //最终还是有没感染的橘子，return -1
       if(freshOrangeCount > 0){
           return  -1;
       }

        return minute;
    }
}

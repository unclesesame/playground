package com.abner.playground.leetcode.matrix;

public class MatrixSolution {
    //No.36 有效的数独
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] column = new int[9][9];
        int[][][] subBoxes = new int[3][3][9];

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++ ){
                char c = board[i][j];
                if(c != '.') {
                    int index = c - '0' -1;
                    rows[i][index]++;
                    column[j][index]++;
                    subBoxes[i/3][j/3][index]++;
                    if(rows[i][index] > 1 || column[j][index] > 1 || subBoxes[i/3][j/3][index] > 1){
                        return false;
                    }
                }
            }
        }
       return true;
    }
}

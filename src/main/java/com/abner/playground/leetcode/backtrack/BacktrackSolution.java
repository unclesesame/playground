package com.abner.playground.leetcode.backtrack;

import com.ctc.wstx.osgi.WstxBundleActivator;
import com.sun.crypto.provider.PBEWithMD5AndDESCipher;

import java.util.*;

public class BacktrackSolution {

    public static void main(String[] args) {
        BacktrackSolution solution = new BacktrackSolution();
        //solution.permute(new int[]{1,2,3}); // output:[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        //solution.permuteUnique(new int[]{1,1,2}); // output: [[1,1,2],[1,2,1],[2,1,1]]
        //solution.combinationSum3(3,9); // output: [[1,2,6],[1,3,5],[2,3,4]]
        //solution.solveNQueens(4); // output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        solution.subsets(new int[]{1,2,3});
    }

    //No.46 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列
    public List<List<Integer>> permute(int[] nums) {
        Deque<Integer> state = new ArrayDeque<>();
        boolean[] selected = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        backtrack(state, nums, selected, res);
        return res;
    }

    private void backtrack(Deque<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res){
        //1）满足条件时，记录解
        if(state.size() == choices.length){
            res.add(new ArrayList<>(state));
            return;
        }

        //2）遍历所有选择
        for(int i=0; i< choices.length; i++){
            int choice = choices[i];
            //3）剪枝，选过的不能再选 boolean数组记录是否已被选
            if(!selected[i]){
                selected[i] = true;
                state.addLast(choice);
                //4）进行下一轮选择  （状态数组, 选择数组, 剪枝， 存放结果）
                backtrack(state, choices, selected, res);
                //5）回退，撤销选择，恢复到之前状态
                selected[i] = false;
                state.removeLast();
            }
        }
    }

    //No.47 给定一个可包含重复数字的序列 nums, 按任意顺序 返回所有不重复的全排列
    public List<List<Integer>> permuteUnique(int[] nums) {
        Deque<Integer> state = new ArrayDeque<>();
        boolean[] selected = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        backtrackUnique(state, nums, selected, res);
        return res;
    }

    private void backtrackUnique(Deque<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res){
        //满足条件时，记录解
        if(state.size() == choices.length){
            res.add(new ArrayList<>(state));
            return;
        }

        //遍历所有选择
        Set<Integer> hashSet = new HashSet<>();
        for(int i=0; i< choices.length; i++){
            int choice = choices[i];
            //剪枝，1)该位置选过的不能再选, 2)数字一样的不能再选
            if(!selected[i] && !hashSet.contains(choice)){
                hashSet.add(choice);
                selected[i] = true;
                state.addLast(choice);
                //进行下一轮选择
                backtrackUnique(state, choices, selected, res);
                //回退，撤销选择，恢复到之前状态
                selected[i] = false;
                state.removeLast();
            }
        }
    }

    //No.78 子集 给你一个整数数组 nums ，数组中的元素互不相同 。返回该数组所有可能的子集 解集不能包含重复的子集
    //nums = [1,2,3] ==> [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        //state存放已选的数字
        Deque<Integer> state = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrackSubsets(state, 0, nums, res);

        return res;
    }


    private void backtrackSubsets(Deque<Integer> state, int curr, int[] nums, List<List<Integer>> res){
        //记录解
        if(curr == nums.length){
            res.add(new ArrayList<>(state));
            return;
        }

        //考虑选择当前位置
        state.addLast(nums[curr]);
        backtrackSubsets(state, curr+1, nums, res);
        //考虑不选当前位置
        state.removeLast();
        backtrackSubsets(state, curr+1, nums, res);
    }


    //No.51 N皇后
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> res = new ArrayList<>();

        char[][] state = new char[n][n];
        for(int i=0; i<n; i++){
            for (int j=0; j<n;j++){
                state[i][j] = '.';
            }
        }

        boolean[] cols = new boolean[n];
        //主对角线各点(\) 满足 row1 - col1 = row2 - col2
        //次对角线各点(/) 满足 row1 + col1 = row2 + col2
        //主对角线和次对角线的数量都是2n-1
        boolean[] diags1 = new boolean[2*n-1];

        boolean[] diags2 = new boolean[2*n-1];

        backtrackForNQueen(0, n, state, res, cols, diags1, diags2);
        return res;
    }

    private void backtrackForNQueen(int row, int n, char[][] state, List<List<String>> res,
                                    boolean[] cols, boolean[] diags1, boolean[] diags2){
        //记录解 二维char数组转List<String>
        if(row == n){
            List<String> temp = new ArrayList<>();
            for(char[] charRow: state){
                StringBuilder sb = new StringBuilder();
                for(char c: charRow){
                    sb.append(c);
                }
                temp.add(sb.toString());
            }
            res.add(temp);
            return;
        }
        //遍历所有列
        for(int col = 0; col < n; col++){
            //计算该格子对应的主对角线和副对角线
            int diag1 = row - col + n - 1;
            int diag2 = row + col;

            //剪枝: 不允许该格子所在列，主对角线，副对角线存在皇后
            if(!cols[col] && !diags1[diag1] && !diags2[diag2]){

                state[row][col] = 'Q';
                cols[col] = diags1[diag1] = diags2[diag2] = true;
                //放置下一个
                backtrackForNQueen(row+1, n, state, res, cols, diags1, diags2);
                cols[col] = diags1[diag1] = diags2[diag2] = false;
                state[row][col] = '.';

            }
        }
    }

    // No.216 组合总和III. 找出所有相加之和为n的k个数的组合，只使用数字1-9，每个数字用一次
    public List<List<Integer>> combinationSum3(int k, int n) {
        Deque<Integer> state = new ArrayDeque<>();
        int start=0;
        //按顺序初始化可选择数组
        int[] choices = new int[]{1,2,3,4,5,6,7,8,9};
        boolean[] selected = new boolean[choices.length];
        List<List<Integer>> res = new ArrayList<>();
        backtrack3(state, k, n, start, choices, res);
        return res;
    }

    private void backtrack3(Deque<Integer> state, int k, int target, int start, int[] choices, List<List<Integer>> res){
        //记录解
        if(state.size() == k && target == 0){
            res.add(new ArrayList<>(state));
            return;
        }
        //从start开始遍历，避免重复解(如: [1,3,5]和[5,3,1])，保证输出结果也是按升序排列(只会输出[1,3,5]), 也不再需要selected[i]数组维护是否已经使用
        for(int i =start; i<choices.length;i++){
            if(target - choices[i] < 0){
                break;
            }

            //剪枝 state超出K的不选
            if(state.size() <= k){
                state.addLast(choices[i]);
                backtrack3(state, k, target-choices[i], i+1 , choices, res);
                state.removeLast();
            }

        }
    }



}

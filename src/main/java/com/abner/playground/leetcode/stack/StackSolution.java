package com.abner.playground.leetcode.stack;

import com.abner.playground.leetcode.other.Solution;

import java.util.Arrays;
import java.util.Stack;

public class StackSolution {

    public static void main(String[] args) {
        StackSolution solution = new StackSolution();
        solution.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});// output:[1,1,4,2,1,1,0,0]
    }

    //No.793 每日温度 整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后
    //
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int j = stack.pop();
                answer[j] = i-j;
            }
            stack.push(i);
        }
        return answer;
    }
}

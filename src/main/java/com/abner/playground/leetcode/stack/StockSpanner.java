package com.abner.playground.leetcode.stack;

import java.util.Stack;

//No.901 股票跨度价格 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）
//如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6]
public class StockSpanner {
    Stack<int[]> stack;
    int idx;
    int price;
    public StockSpanner(){
        idx=-1;
        price = Integer.MAX_VALUE;
        stack = new Stack<>();
        stack.push(new int[]{idx, price});
    }

    public int next(int price){
        idx++;
        while(price >= stack.peek()[1]){
            stack.pop();
        }
        int ret = idx - stack.peek()[0];
        stack.push(new int[]{idx, price});
        return ret;
    }
}

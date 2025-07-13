package com.abner.playground.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

//No.155 最小栈 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈
public class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        xStack.push(val);
        if(val > minStack.peek() ){
            minStack.push(minStack.peek());
        }else{
            minStack.push(val);
        }
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}

package com.abner.playground.leetcode.design;


//No.1206 设计跳表
public class SkipList {

    public SkipList(){

    }

    public boolean search(int target) {
        return false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {
        return true;
    }
}


class SkiplistNode {
    int val;
    SkiplistNode[] forward;

    public SkiplistNode(int val, int maxLevel) {
        this.val = val;
        this.forward = new SkiplistNode[maxLevel];
    }
}


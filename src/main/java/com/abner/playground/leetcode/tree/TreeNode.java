package com.abner.playground.leetcode.tree;

import org.antlr.v4.runtime.tree.Tree;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(){}

    TreeNode(int val){this.val = val;}

    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}

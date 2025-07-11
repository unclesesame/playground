package com.abner.playground.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeSolution {
    public static void main(String[] args) {
        TreeSolution solution = new TreeSolution();

    }

    //No. 1161. 树的最大层内元素和
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        int ans = 0;
        int maxLevelSum = Integer.MIN_VALUE;
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            int current = 0;
            while (size-- > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                current += node.val;
            }
            if(current > maxLevelSum){
                maxLevelSum = current;
                ans = depth;
            }
            depth++;
        }
        return ans;
    }

}

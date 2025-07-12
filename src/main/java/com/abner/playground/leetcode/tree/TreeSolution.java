package com.abner.playground.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeSolution {
    public static void main(String[] args) {
        TreeSolution solution = new TreeSolution();

    }

    //No.450 删除二叉树中的节点
    //给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        if(root.val < key){
            root.right = deleteNode(root.right, key);
            return root;
        }

        if(root.val == key){
            //叶子节点直接删除
            if(root.left == null && root.right ==null){
                return null;
            }
            //只有左子树
            if(root.right == null){
                return root.left;
            }

            //只有右子树
            if(root.left == null){
                return root.right;
            }

            //左右子树都有时，选右子树最小节点记为successor作为新的根节点替代root，并删除successor
            TreeNode successor = root.right;
            while (successor.left != null){
                successor = successor.left;
            }

            //在右子树删除successor
            root.right = deleteNode(root.right, successor.val);
            //successor 更新为新的root 并返回
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }

        return root;
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

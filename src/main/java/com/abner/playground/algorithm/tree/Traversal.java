package com.abner.playground.algorithm.tree;

public class Traversal {

    public static void main(String[] args) {

    }
    //DFS

    //root -> left -> right
    static void preorderTraversal(TreeNode root){
        if(root == null) return;
        System.out.println(root.value);// root
        preorderTraversal(root.left);//left
        preorderTraversal(root.right);//right
    }

    //left -> root -> right
    static void inorderTraversal(TreeNode root){
        if(root == null) return;

        preorderTraversal(root.left);//left
        System.out.println(root.value);// root
        preorderTraversal(root.right);//right
    }

    //left>right->root
    static void postTraversal(TreeNode root){
        if(root == null) return;

        preorderTraversal(root.left);//left
        preorderTraversal(root.right);//right
        System.out.println(root.value);// root
    }

    static void dfs(){

    }

    static void bfs(){

    }


}

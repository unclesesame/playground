package com.abner.playground.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SearchUtil {
	
	public static TreeNode createBTreeByArray(int[] array, int index){
		if(index < array.length){
			int value = array[index];
			if(value != 0){
				TreeNode node = new TreeNode(value);
				//array[index] = 0;
				node.left = createBTreeByArray(array, index*2);
				node.right = createBTreeByArray(array, index*2+1);
				return node;
			}
			
		}
		return null;
	}
	
	static void depthFirstSearch(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			System.out.print(node.value + " ");
			if(node.right != null)
				stack.push(node.right);
			if(node.left != null)
				stack.push(node.left);
		}
	}
	
	static void broadFirstSearch(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			System.out.print(node.value + " ");
			if(node.left != null){
				queue.add(node.left);
			}
			if(node.right != null){
				queue.add(node.right);
			}
		}
		
	}
	
	public static void main(String[] args) {
		int[] array = new int[]{0,1,3,5,4,7,9};
		TreeNode root = createBTreeByArray(array, 1);
		broadFirstSearch(root);
		depthFirstSearch(root);
	}
}

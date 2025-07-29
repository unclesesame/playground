package com.abner.playground.leetcode.tree;

import com.abner.playground.algorithm.tree.SearchUtil;

import java.util.*;

public class TreeSolution {

    private int ans;
    private int max;

    //用hash表记录所有节点的父节点,因为所有节点的val互不相同，可以以val作为key
    Map<Integer, TreeNode> parent = new HashMap<>();
    //记录节点是否已访问过
    Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) {
        TreeSolution solution = new TreeSolution();

        solution.isValidBST(buildTree());

    }

    static TreeNode buildTree(){
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node2.left=node3;
        node2.right=node4;

        return root;

    }

    //No.98 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {

        //中序遍历是升序的，遍历时监测当前节点是否大于前一节点
        Stack<TreeNode> stack = new Stack();
        double inorder = -Double.MAX_VALUE;
        TreeNode current = root;

        while (!stack.isEmpty() || current != null){
            //左子树依次入栈
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            //左
            current = stack.pop();
            if(current.val <= inorder){
                return false;
            }
            //根
            inorder = current.val;
            //右
            current = current.right;

        }
        return true;
    }

    //No. 102 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int currLevelSize = queue.size();
            List<Integer> currLevelList = new ArrayList<>();
            while (currLevelSize-- > 0){
                TreeNode treeNode = queue.poll();
                currLevelList.add(treeNode.val);
                if(treeNode.left != null) queue.offer(treeNode.left);
                if(treeNode.right != null) queue.offer(treeNode.right);
            }
            result.add(currLevelList);
        }

        return result;
    }

    //No.104 二叉树最大深度
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        else{
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    //No.199 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        //层序遍历标准写法，引入队列，并将root先入队
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //循环停止条件是queue为空
        while(!queue.isEmpty()){
            //记录每层右多少节点
            int levelSize = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            //将每层的节点再次入队
            for(int i=0; i< levelSize; i++){
                TreeNode node = queue.poll();
                currLevel.add(node.val);
                //从右往左层序遍历
                if(node.right != null) queue.offer(node.right);
                if(node.left != null) queue.offer(node.left);
            }
            //由于是从右向左层序遍历，每层只需要保留最右侧节点，所以这里get(0)
            result.add(currLevel.get(0));
        }

        return result;
    }

    //No.236 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //用hash表记录所有节点的父节点,因为所有节点的val互不相同，可以以val作为key
        //Map<Integer, TreeNode> parent = new HashMap<>();
        //记录节点是否已访问过
        //Set<Integer> visited = new HashSet<>();
        dfsForCommonAncestor(root);
        while (p != null){
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null){
            if(visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfsForCommonAncestor(TreeNode root){
        if(root.left != null){
            parent.put(root.left.val, root);
            dfsForCommonAncestor(root.left);
        }
        if(root.right != null){
            parent.put(root.right.val, root);
            dfsForCommonAncestor(root.right);
        }
    }


    //No.543 二叉树的直径(两个叶子节点的最长距离)
    public int diameterOfBinaryTree(TreeNode root) {
        //两个叶子节点的最长距离，不一定经过root
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root){
       if(root == null) return 0;
       int lLen = dfs(root.left);
       int rLen = dfs(root.right);
       ans = Math.max(ans, lLen+rLen);
       return Math.max(lLen, rLen) + 1;
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

    //No.872 叶子相似的树 root1和root2 从左到右的叶值序列相同返回true
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        //后续遍历root1和root2，并比较叶子
        List<Integer> seq1 = new ArrayList<>();
        List<Integer> seq2 = new ArrayList<>();
        postLeafTraversal(root1, seq1);
        postLeafTraversal(root2, seq2);
        return seq1.equals(seq2);
    }

    private void postLeafTraversal(TreeNode root, List<Integer> seq){
        if(root == null) return;
        if(root.left == null && root.right == null) seq.add(root.val);
        postLeafTraversal(root.left, seq);
        postLeafTraversal(root.right, seq);
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

    //No.1372 二叉树中的最长交错路径
    public int longestZigZag(TreeNode root) {
        if(root == null){
            return 0;
        }
        int max = 0;
        //false 向左，  true向右
        dfsForZigZag(root, false, 0);
        dfsForZigZag(root, true, 0);
        return max;
    }

    private void dfsForZigZag(TreeNode root, boolean dir, int len) {
        //每个节点记录最大长度
        max = Math.max(max, len);
        if (!dir) {
            //应该向左，且可以向左，len+1
            if (root.left != null) {
                dfsForZigZag(root.left, true, len + 1);
            }
            //应该向左，但只能向右，len重置为1
            if (root.right != null) {
                dfsForZigZag(root.right, false, 1);
            }
        } else {
            //应该向右，且可以向右，len+1
            if (root.right != null) {
                dfsForZigZag(root.right, false, len + 1);
            }
            //应该向右，但只能向左，len重置为1
            if (root.left != null) {
                dfsForZigZag(root.left, true, 1);
            }
        }
    }
}


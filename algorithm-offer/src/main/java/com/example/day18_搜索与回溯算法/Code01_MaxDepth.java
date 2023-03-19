package com.example.day18_搜索与回溯算法;

/**
 * @Date: 2023/3/19 18:16
 * 剑指 Offer 55 - I. 二叉树的深度
 */
public class Code01_MaxDepth {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
    }

//    public int maxDepth(TreeNode root) {
//        if(root == null){
//            return 0;
//        }
//        return dfs(root);
//    }
//    public static int dfs(TreeNode node){
//        if(node == null){
//            return 0;
//        }
//        int h1 = dfs(node.left);
//        int h2 = dfs(node.right);
//        return 1 + Math.max(h1,h2);
//    }


}

package com.example.day07_搜索与回溯算法;

/**
 * @Date: 2023/3/8 14:38
 * 剑指 Offer 28. 对称的二叉树
 */
public class Code03_IsSymmetric {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return process(root.left,root.right);
    }
    //判断A，B两棵树是否是镜像的
    public boolean process(TreeNode A,TreeNode B){
        if(A == null && B == null){
            return true;
        }
        if(A == null || B == null || A.val != B.val){
            return false;
        }
        return process(A.left,B.right) && process(A.right,B.left);
    }
}

package com.example.day14;

/**
 * @Date: 2023/5/6 21:25
 * 101. 对称二叉树
 *
 * https://leetcode.cn/problems/symmetric-tree/
 */
public class Code01_isSymmetric {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isMirror(root.left,root.right);
    }
    //判断两棵树是否是镜像的
    public static boolean isMirror(TreeNode A, TreeNode B){
        if(A == null && B == null){
            return true;
        }
        if(A == null || B == null || A.val != B.val){
            return false;
        }
        return isMirror(A.left,B.right) && isMirror(A.right,B.left);
    }
}

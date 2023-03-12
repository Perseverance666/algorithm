package com.example.day07_搜索与回溯算法;

/**
 * @Date: 2023/3/8 14:36
 * 剑指 Offer 26. 树的子结构
 */
public class Code01_IsSubStructure {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //判断B是不是A的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return process(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    //B是否是是以A为根节点的A的子结构
    public boolean process(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return process(A.left, B.left) && process(A.right, B.right);
    }
}

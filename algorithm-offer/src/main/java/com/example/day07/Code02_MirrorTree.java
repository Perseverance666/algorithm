package com.example.day07;

/**
 * @Date: 2023/3/8 14:37
 * 剑指 Offer 27. 二叉树的镜像
 */
public class Code02_MirrorTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode leftTree = mirrorTree(root.left);
        TreeNode rightTree = mirrorTree(root.right);
        root.left = rightTree;
        root.right = leftTree;
        return root;
    }
}

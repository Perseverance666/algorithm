package com.example.day22;

/**
 * @Date: 2023/5/23 19:24
 * 226. 翻转二叉树
 *
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class Code02_invertTree {
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

    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        root.left = invertTree(root.right);
        root.right = invertTree(root.left);
        return root;
    }
}

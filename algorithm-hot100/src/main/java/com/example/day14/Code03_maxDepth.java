package com.example.day14;

/**
 * @Date: 2023/5/6 21:27
 * 104. 二叉树的最大深度
 *
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class Code03_maxDepth {
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

    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}

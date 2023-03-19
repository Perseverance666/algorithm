package com.example.day18_搜索与回溯算法;

/**
 * @Date: 2023/3/19 18:17
 * 剑指 Offer 55 - II. 平衡二叉树
 */
public class Code02_IsBalanced {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    //返回树的深度，-1代表该树不是平衡二叉树
    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        if (left == -1) return -1;
        int right = height(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    //    public boolean isBalanced(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        return isBalanced(root.left) && isBalanced(root.right) &&
//                Math.abs(height(root.left) - height(root.right)) < 2;
//    }
//
//    //求node的深度
//    public static int height(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        return 1 + Math.max(height(node.left), height(node.right));
//    }


}

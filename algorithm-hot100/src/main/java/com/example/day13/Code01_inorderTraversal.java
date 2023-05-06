package com.example.day13;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/5/6 18:34
 * 94. 二叉树的中序遍历
 *
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class Code01_inorderTraversal {
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        inOrder(res,root);
        return res;
    }

    public static void inOrder(List<Integer> res,TreeNode node){
        if(node == null){
            return;
        }
        inOrder(res,node.left);
        res.add(node.val);
        inOrder(res,node.right);
    }
}

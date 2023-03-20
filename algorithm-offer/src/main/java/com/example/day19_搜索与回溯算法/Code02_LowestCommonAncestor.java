package com.example.day19_搜索与回溯算法;

/**
 * @Date: 2023/3/20 13:57
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 */
public class Code02_LowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root){
            return root;
        }
        //1、p,q两个节点都在根节点左边
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        //2、p,q两个节点都在跟节点右边
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        //3、p,q两个节点在根节点左右两侧
        return root;
    }
}

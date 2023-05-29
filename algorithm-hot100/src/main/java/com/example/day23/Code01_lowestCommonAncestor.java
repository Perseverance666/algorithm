package com.example.day23;

/**
 * @Date: 2023/5/28 20:24
 * 236. 二叉树的最近公共祖先
 * <p>
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class Code01_lowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null && right == null){
            return null;
        }
        //p,q两个节点都在根节点右边
        if(left == null){
            return right;
        }
        //p,q两个节点都在根节点左边
        if(right == null){
            return left;
        }
        //p,q两个节点在根节点左右两侧
        //left != null && right != null
        return root;
    }
}

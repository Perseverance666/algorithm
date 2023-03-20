package com.example.day19_搜索与回溯算法;

/**
 * @Date: 2023/3/20 13:58
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 */
public class Code03_LowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //方法返回null时，说明p，q两个节点均不在root构成的子树里
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == root || q == root){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //1、p,q两个节点都在根节点左边
        if(right == null){
            return left;
        }
        //2、p,q两个节点都在跟节点右边
        if(left == null){
            return right;
        }
        //3、p,q两个节点在根节点左右两侧
        //left != null && right != null
        return root;
    }
}

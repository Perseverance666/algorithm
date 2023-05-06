package com.example.day13;

/**
 * @Date: 2023/5/6 18:36
 * 98. 验证二叉搜索树
 *
 * https://leetcode.cn/problems/validate-binary-search-tree/
 */
public class Code03_isValidBST {
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
    //中序遍历。如果是二叉搜索树，中序遍历是单调递增的，pre记录中序遍历时该节点的前一个节点
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        //左
        if(!isValidBST(root.left)){
            return false;
        }
        //根
        if(root.val <= pre){
            return false;
        }
        pre = root.val;
        //右
        return isValidBST(root.right);
    }

//    //自己写的
//    public boolean isValidBST(TreeNode root) {
//        if(root == null){
//            return true;
//        }
//        boolean res = isValidBST(root.left) && isValidBST(root.right);
//        if(root.left != null){
//            res &= findMax(root.left).val < root.val;
//        }
//        if(root.right != null){
//            res &= findMin(root.right).val > root.val;
//        }
//        return res;
//    }
//
//    public static TreeNode findMax(TreeNode node){
//        if(node.right == null){
//            return node;
//        }
//        return findMax(node.right);
//    }
//
//    public static TreeNode findMin(TreeNode node){
//        if(node.left == null){
//            return node;
//        }
//        return findMin(node.left);
//    }
}

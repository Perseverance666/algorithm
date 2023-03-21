package com.example.day20_分治算法;

import java.util.HashMap;

/**
 * @Date: 2023/3/21 13:39
 * 剑指 Offer 07. 重建二叉树
 * <p>
 * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
 */
public class Code01_BuildTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        if (preorder.length == 0 && inorder.length == 0) {
//            return null;
//        }
//        TreeNode root = new TreeNode(preorder[0]);
//        int index = 0;  //最终i指向中序遍历的根节点的位置，即先序遍历的左子树最后一个节点的位置
//        for (; index < inorder.length; index++) {
//            if (inorder[index] == preorder[0]) break;
//        }
//        int[] leftPreorder = new int[index];
//        int[] leftInorder = new int[index];
//        int[] rightPreorder = new int[inorder.length - index - 1];
//        int[] rightInorder = new int[inorder.length - index - 1];
//        int j = 0;
//        for (int i = 1; i <= index; i++) {
//            leftPreorder[j++] = preorder[i];
//        }
//        for (int i = 0; i < index; i++) {
//            leftInorder[i] = inorder[i];
//        }
//        int k = 0;
//        for (int i = index + 1; i < inorder.length; i++) {
//            rightPreorder[k] = preorder[i];
//            rightInorder[k] = inorder[i];
//            k++;
//        }
//        TreeNode left = buildTree(leftPreorder, leftInorder);
//        TreeNode right = buildTree(rightPreorder, rightInorder);
//        root.left = left;
//        root.right = right;
//        return root;
//    }

    //用于存放key这个节点在中序遍历中的位置
    public HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int N = inorder.length;
        for (int i = 0; i < N; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, N - 1, 0, N - 1);
    }

    //根据先序遍历数组preLeft~preRight 和 中序遍历数组inLeft~inRight建造一棵树
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        int preRoot = preLeft;                  //根节点在先序遍历数组中的位置
        int inRoot = map.get(preorder[preRoot]); //根节点在中序遍历数组中的位置
        int leftSize = inRoot - inLeft;         //左子树的节点个数
        TreeNode root = new TreeNode(preorder[preRoot]);
        root.left = myBuildTree(preorder, inorder, preLeft + 1, preLeft + leftSize, inLeft, inRoot - 1);
        root.right = myBuildTree(preorder, inorder, preLeft + leftSize + 1, preRight, inRoot + 1, inRight);
        return root;
    }

}

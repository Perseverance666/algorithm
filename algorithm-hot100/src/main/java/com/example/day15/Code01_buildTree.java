package com.example.day15;

import java.util.HashMap;

/**
 * @Date: 2023/5/8 19:29
 * 105. 从前序与中序遍历序列构造二叉树
 * <p>
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Code01_buildTree {
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

    //用于存放key这个节点在中序遍历中的位置
    public HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int N = inorder.length;
        for (int i = 0; i < N; i++) {
            map.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, N - 1, 0, N - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        //base case
        if(preLeft > preRight){
            return null;
        }
        //根节点在先序遍历数组中的位置
        int preRoot = preLeft;
        //根节点在中序遍历数组中的位置
        int inRoot = map.get(preorder[preRoot]);
        //创建根节点
        TreeNode root = new TreeNode(preorder[preRoot]);
        //左子树的元素数量
        int leftSize = inRoot - inLeft;
        root.left = myBuildTree(preorder, inorder, preLeft + 1, preLeft + leftSize, inLeft, inRoot - 1);
        root.right = myBuildTree(preorder, inorder, preLeft + leftSize + 1, preRight, inRoot + 1, inRight);
        return root;
    }
}

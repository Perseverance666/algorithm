package com.example.day06_搜索与回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date: 2023/3/7 16:08
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 */
public class Code01_LevelOrder1 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int[] levelOrder(TreeNode root) {
        if(root == null){
            return new int[] {};
        }
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        int size = list.size();
        int[] ans = new int[size];
        for(int i = 0; i < size; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }
}

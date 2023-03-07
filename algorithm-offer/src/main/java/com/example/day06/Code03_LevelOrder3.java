package com.example.day06;

import java.util.*;

/**
 * @Date: 2023/3/7 16:11
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 */
public class Code03_LevelOrder3 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();
            int levelSize = queue.size();
            for(int i = 0; i < levelSize; i++){
                TreeNode node = queue.poll();
                if (ans.size() % 2 == 0) {
                    //偶数层，从左往右打印，从第0层开始。ans.size正好可以代表当前是第几层
                    level.addLast(node.val);
                }else{
                    //奇数层，从右往左打印
                    level.addFirst(node.val);
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            ans.add(level);
        }
        return ans;
    }


}

package com.example.day28_搜索与回溯算法;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/3/29 14:44
 * 剑指 Offer 37. 序列化二叉树
 * <p>
 * https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof
 */
public class Code01_Codec {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null){
                res.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }else{
                res.append("null,");
            }
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("[]")){
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(vals[index].equals("null")){
                node.left = new TreeNode(Integer.parseInt(vals[index]));
                queue.add(node.left);
            }
            index++;
            if(vals[index].equals("null")){
                node.right = new TreeNode(Integer.parseInt(vals[index]));
                queue.add(node.right);
            }
            index++;
        }
        return root;
    }
}
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

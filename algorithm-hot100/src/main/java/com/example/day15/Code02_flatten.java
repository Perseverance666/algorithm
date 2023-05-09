package com.example.day15;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/5/8 19:30
 * 114. 二叉树展开为链表
 *
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 */
public class Code02_flatten {
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
    public void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        List<TreeNode> list = new ArrayList<>();
        pre(root,list);
        for(int i = 1; i < list.size(); i++){
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }
    //先序遍历
    public static void pre(TreeNode root,List<TreeNode> list){
        if(root == null){
            return;
        }
        list.add(root);
        pre(root.left,list);
        pre(root.right,list);
    }
}

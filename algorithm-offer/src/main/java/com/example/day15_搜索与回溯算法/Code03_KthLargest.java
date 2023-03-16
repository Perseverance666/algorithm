package com.example.day15_搜索与回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/3/16 16:12
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 */
public class Code03_KthLargest {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int res = 0;
    int index = 0; //计数器，用来记录当前节点是第几大的节点
    public int kthLargest(TreeNode root, int k) {
        dfs(root,k);
        return res;
    }
    //中序遍历，不过是 右根左，保证逆序
    public void dfs(TreeNode root ,int k)
    {
        if(root == null) return;
        dfs(root.right,k); //右
        index++;
        if(k == index) res = root.val; //根
        dfs(root.left,k); //左
    }

//    public int kthLargest(TreeNode root, int k) {
//        List<Integer> list = new ArrayList<>();
//        in(list,root);
//        return list.get(list.size() - k);
//    }
//    //中序遍历到list集合中
//    public static void in(List<Integer> list,TreeNode cur){
//        if(cur == null){
//            return;
//        }
//        in(list,cur.left);
//        list.add(cur.val);
//        in(list,cur.right);
//    }
}

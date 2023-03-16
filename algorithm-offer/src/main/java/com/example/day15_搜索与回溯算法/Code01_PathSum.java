package com.example.day15_搜索与回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/3/16 14:27
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 */
public class Code01_PathSum {
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

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root,target);
        return ans;
    }
    public void dfs(TreeNode node,int target){
        if(node == null){
            return;
        }
        path.add(node.val);
        target -= node.val;
        if(target == 0 && node.left == null && node.right == null){
            //当前节点是叶子节点，且已满足target，建立副本存入ans
            ans.add(new ArrayList<>(path));
        }
        dfs(node.left, target);
        dfs(node.right, target);
        //当前节点讨论完了，在path中删除掉，不去耽误后面
        path.remove(path.size() - 1);

    }
}

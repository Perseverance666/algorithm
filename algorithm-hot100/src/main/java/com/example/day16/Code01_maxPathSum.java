package com.example.day16;

/**
 * @Date: 2023/5/9 18:18
 * 124. 二叉树中的最大路径和
 *
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 */
public class Code01_maxPathSum {
    public static class TreeNode {
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

    //最大路径和
    public int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return max;
    }
    //获取该节点的最大贡献值
    public int maxGain(TreeNode node){
        if(node == null){
            return 0;
        }

        //只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftMax = Math.max(0,maxGain(node.left));    //获取左子树的最大贡献值
        int rightMax = Math.max(0,maxGain(node.right));  //获取右子树的最大贡献值
        //以node为根节点的最大路径和
        int maxPath = node.val + leftMax + rightMax;
        //更新结果
        max = Math.max(max,maxPath);
        //返回该节点能给予的最大贡献值
        return node.val + Math.max(leftMax,rightMax);
    }
}

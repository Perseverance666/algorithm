package com.example.day20_分治算法;

/**
 * @Date: 2023/3/21 13:43
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * <p>
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class Code03_VerifyPostorder {
    public boolean verifyPostorder(int[] postorder) {
        return process(postorder, 0, postorder.length - 1);
    }
    //判断在postorder数组上left~right上是否能构成二叉搜索树
    public static boolean process(int[] postorder, int left, int right) {
        if (left >= right) {
            //只剩一个节点，或者没有节点，返回true
            return true;
        }
        int root = postorder[right];
        int i = left;
        while (postorder[i] < root) {
            i++;
        }

        int index = i;      //记录第一个大于root的节点位置。认为index之前的节点都是左子树的节点
        while (i < right) {
            if (postorder[i++] < root) {
                //1、先确保左子树节点的值都比根节点小，右子树节点的值都比根节点大
                return false;
            }
        }
        //2、再确保左右子树都是二叉搜索树
        return process(postorder, left, i - 1) && process(postorder, i, right - 1);
    }
}

package com.example.day13;

/**
 * @Date: 2023/5/6 18:35
 * 96. 不同的二叉搜索树
 *
 * https://leetcode.cn/problems/unique-binary-search-trees/
 *
 * 假设 n 个节点存在二叉排序树的个数是 G (n)，令 f(i) 为以 i 为根的二叉搜索树的个数，则G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
 * 当 i 为根节点时，其左子树节点个数为 i-1 个，右子树节点为 n-i，则f(i)=G(i−1) * G(n−i)
 * 综合两个公式可以得到 卡特兰数 公式：G(n)=G(0)∗G(n−1)+G(1)∗(n−2)+...+G(n−1)∗G(0)
 * G(n) = c(2n, n) - c(2n, n-1)
 */
public class Code02_numTrees {
    public int numTrees(int n) {
        //dp[i]表示由 i 个节点组成且节点值从 1 到 i 互不相同的 二叉搜索树的种数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                //以j为根节点，dp[j-1]表示左子树二叉搜索树种数，dp[i-j]表示右子树二叉搜索树种数，求笛卡尔积
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}

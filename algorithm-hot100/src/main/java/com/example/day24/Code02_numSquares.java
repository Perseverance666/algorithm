package com.example.day24;

/**
 * @Date: 2023/5/29 19:52
 * 279. 完全平方数
 *
 * https://leetcode.cn/problems/perfect-squares/
 */
public class Code02_numSquares {
    public int numSquares(int n) {
        //dp[i]表示最少需要多少个数的平方来表示整数i，dp[0] = 0
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, dp[i - j * j]);
            }
            dp[i] = minn + 1;
        }
        return dp[n];
    }
}

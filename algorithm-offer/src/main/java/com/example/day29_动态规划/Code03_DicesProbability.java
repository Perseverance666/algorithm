package com.example.day29_动态规划;

/**
 * @Date: 2023/3/30 14:45
 * 剑指 Offer 60. n个骰子的点数
 * <p>
 * https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof
 */
public class Code03_DicesProbability {
    public double[] dicesProbability(int n) {
        //dp[i][j]表示i个骰子，点数和为j的数量。第0行和第0列不用
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; i++) {
                //第i个骰子的点数可以为k= 1~6，这样
                //前i个骰子点数和为j = 前i-1个骰子点数和为j-k + 第i个骰子点数为k。全部累加得出dp[i][j]
                for (int k = 1; k <= 6; k++) {
                    if (j - k < 0) break;
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        //n个骰子点数和范围[n,6n],点数和可能性个数6n-n+1=5n+1
        //点数和总数6^n
        double[] res = new double[5 * n + 1];
        int index = 0;
        for (int i = n; i <= 6 * n; i++) {
            res[index++] = dp[n][i] / Math.pow(6, n);
        }
        return res;
    }
}

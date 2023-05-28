package com.example.day22;

/**
 * @Date: 2023/5/23 19:24
 * 221. 最大正方形
 * <p>
 * https://leetcode.cn/problems/maximal-square/
 *
 * dp(i,j) 的值由其上方、左方和左上方的三个相邻位置的dp值决定。
 * 具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加1，
 * 状态转移方程：dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1   记住！！！
 *
 */
public class Code01_maximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxSide = 0;
        int r = matrix.length;
        int c = matrix[0].length;
        //dp[i][j]表示以(i,j)为正方形右下角，且只包含1的正方形的边长最大值
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '1') {  //如果(i,j)为0，dp[i][j] = 0
                    if (i == 0 || j == 0) {
                        //第一行和第一列dp[i][j]都是1
                        dp[i][j] = 1;
                    }else {
                        //状态转移方程：dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
                        dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]) + 1;
                    }
                    maxSide = Math.max(maxSide,dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}

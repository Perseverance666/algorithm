package com.example.day09_动态规划;

/**
 * @Date: 2023/3/12 12:55
 * 剑指 Offer 47. 礼物的最大价值
 */
public class Code02_MaxValue {
    //    public int maxValue(int[][] grid) {
//        int M = grid.length;
//        int N = grid[0].length;
//        return process(grid, M - 1, N - 1);
//    }
//
//    //从arr[0][0]到arr[i][j]的最大价值
//    public static int process(int[][] arr, int i, int j) {
//        if (i == 0 && j == 0) {
//            return arr[0][0];
//        }
//        if (i == 0) {
//            return process(arr, i, j - 1) + arr[i][j];
//        } else if (j == 0) {
//            return process(arr, i - 1, j) + arr[i][j];
//        } else {
//            int p1 = process(arr, i, j - 1);
//            int p2 = process(arr, i - 1, j);
//            return Math.max(p1, p2) + arr[i][j];
//        }
//    }
    public int maxValue(int[][] arr) {
        int M = arr.length;
        int N = arr[0].length;
        //dp[i][j]代表从arr[0][0]出发到arr[i][j]的最大值
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = arr[i][j];
                    continue;
                }
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + arr[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + arr[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + arr[i][j];
                }
            }
        }
        return dp[M - 1][N - 1];
    }
}

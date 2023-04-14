package com.example.day10;

/**
 * @Date: 2023/4/13 14:57
 * 64. 最小路径和
 *
 * https://leetcode.cn/problems/minimum-path-sum/?favorite=2cktkvj
 */
public class Code01_minPathSum {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // return dfs(grid,0,0,m,n);

        //dp[i][j]表示从(0,0)到(i,j)的最小路径和
        int[][] dp = new int[m][n];
        //初始化第一行
        int sum = 0;
        for(int j = 0; j < n; j++){
            dp[0][j] = sum + grid[0][j];
            sum += grid[0][j];
        }
        //初始化第一列
        sum = dp[0][0];
        for(int i = 1; i < m; i++){
            dp[i][0] = sum + grid[i][0];
            sum += grid[i][0];
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] arr = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(arr));
    }
}

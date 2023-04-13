package com.example.day09;

/**
 * @Date: 2023/4/12 15:26
 * 62. 不同路径
 *
 * https://leetcode.cn/problems/unique-paths/?favorite=2cktkvj
 */
public class Code03_uniquePaths {
    //dp
    public int uniquePaths(int m, int n) {
        //dp[i][j]表示从(i,j)到(m-1,n-1)共有多少条不同路径
        int[][] dp = new int[m][n];
        //初始化最后一行
        for(int j = 0; j < n; j++){
            dp[m-1][j] = 1;
        }
        //初始化最后一列
        for(int i = 0; i < m; i++){
            dp[i][n-1] = 1;
        }

        for(int i = m-2; i >= 0; i--){
            for(int j = n-2; j >= 0; j--){
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        return dp[0][0];
    }
    // //暴力递归
    // public int uniquePaths(int m, int n) {
    //     return dfs(0,0,m,n);
    // }
    // //从(i,j)到(m-1,n-1)共有多少条不同路径
    // public static int dfs(int i,int j,int m,int n){
    //     if(i > m-1 || i < 0 || j > n-1 || j < 0){
    //         return 0;
    //     }
    //     if(i == m-1 && j == n-1){
    //         return 1;
    //     }
    //     //1、向右走
    //     int p1 = dfs(i,j+1,m,n);
    //     //2、向下走
    //     int p2 = dfs(i+1,j,m,n);
    //     return p1 + p2;
    // }
    // //从(i,j)到(m-1,n-1)共有多少条不同路径
    // public static int dfs(int i,int j,int m,int n){
    //     if(i > m-1 || i < 0 || j > n-1 || j < 0){
    //         return 0;
    //     }
    //     if(i == m-1 && j == n-1){
    //         return 1;
    //     }
    //     //1、向右走
    //     int p1 = dfs(i,j+1,m,n);
    //     //2、向下走
    //     int p2 = dfs(i+1,j,m,n);
    //     return p1 + p2;
    // }
}

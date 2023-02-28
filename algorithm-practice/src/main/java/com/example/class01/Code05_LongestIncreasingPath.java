package com.example.class01;

/**
 * @Date: 2023/2/27 17:12
 * 矩阵中的最长递增路径
 *
 * 题目：给定一个二维数组matrix，你可以从任何位置出发，走向上、下、左、右四个方向，返回能走出来的最长的递增链长度
 *
 * 测试链接：https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 */
public class Code05_LongestIncreasingPath {
    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    // 从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process1(int[][] m, int i, int j) {
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process1(m, i - 1, j) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process1(m, i + 1, j) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process1(m, i, j - 1) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process1(m, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public int longestIncreasingPath(int[][] m) {
        int M = m.length;
        int N = m[0].length;
        int ans = 0;
        //傻缓存,每个点的dp值最小是1
        int[][] dp = new int[M][N];
        //求每一个位置的最长递增路径
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                ans = Math.max(ans,process(m,i,j,dp));
            }
        }
        return ans;
    }
    //求m[i][j]的最长递增路径
    public static int process(int[][] m,int i,int j,int[][] dp){
        if(dp[i][j] != 0){
            return dp[i][j];
        }
        //往上走
        int up = i > 0 && m[i-1][j] > m[i][j] ? process(m,i-1,j,dp) : 0;
        //往下走
        int down = i < m.length - 1 && m[i+1][j] > m[i][j] ? process(m,i+1,j,dp) : 0;
        //往左走
        int left = j > 0 && m[i][j-1] > m[i][j] ? process(m,i,j-1,dp) : 0;
        //往右走
        int right = j < m[0].length -1  && m[i][j+1] > m[i][j] ? process(m,i,j+1,dp) : 0;
        //取四种可能中最长的，注意还要将自己算上
        int ans = Math.max(Math.max(up,down),Math.max(left,right)) + 1;

        dp[i][j] = ans;
        return ans;
    }
}

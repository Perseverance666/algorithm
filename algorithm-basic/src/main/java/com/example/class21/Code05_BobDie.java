package com.example.class21;

/**
 * @Date: 2023/2/2 14:34
 * 醉汉迈步
 *
 * 题目：给定5个参数，N，M，row，col，k。表示在N * M的区域上，醉汉Bob初始在(row，col)位置，
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位，任何时候Bob只要离开N * M的区域就直接死亡。
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {
    //1、暴力递归
    public static double livePosibility1(int row, int col, int k, int N, int M) {
        //醉汉不死概率 = 醉汉从(row,col)开始走剩下所有的生存点数 / 总点数4^k。
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }
    // 目前在row，col位置，还有rest步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
    public static long process(int row, int col, int rest, int N, int M) {
        //醉汉越界，这个生存点为0
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        // 醉汉还在棋盘中，且要走的步数已经走完，方法数+1；
        if (rest == 0) {
            return 1;
        }
        // 醉汉还在棋盘中，还有步数要走，求出醉汉上下左右四种可能的生存点数
        long up = process(row - 1, col, rest - 1, N, M);
        long down = process(row + 1, col, rest - 1, N, M);
        long left = process(row, col - 1, rest - 1, N, M);
        long right = process(row, col + 1, rest - 1, N, M);
        return up + down + left + right;
    }
    //2、动态规划
    public static double livePosibility2(int row, int col, int k, int N, int M) {
        //建表，dp[i][j][k]代表醉汉还有k步可以走，醉汉从(i,j)开始走剩下所有的生存点数
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //1、醉汉还在棋盘中，且要走的步数已经走完，方法数+1；
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    //2、醉汉还在棋盘中，还有步数要走，求出醉汉上下左右四种可能的生存点数
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        //3、醉汉不死概率 = 醉汉从(row,col)开始走剩下所有的生存点数 / 总点数4^k。
        return (double) dp[row][col][k] / Math.pow(4, k);
    }
    //pick函数，用来防止越界，越界返回0
    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }
}

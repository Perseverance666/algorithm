package com.example.class39;

/**
 * @Date: 2023/2/22 15:09
 * 奇怪的打印机
 * <p>
 * 题目：有台奇怪的打印机有以下两个特殊要求：
 * 1）打印机每次只能打印由同一个字符组成的序列。2）每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串s，你的任务是计算这个打印机打印它需要的最少打印次数。
 * <p>
 * 测试链接 : https://leetcode.cn/problems/strange-printer/
 */
public class Code04_StrangePrinter {
    //1、暴力递归
    public static int strangePrinter1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process1(str, 0, str.length - 1);
    }
    //刷出L~R上的样子，返回最小打印次数
    public static int process1(char[] str, int L, int R) {
        if (L == R) {
            //只有一个字符，打印1次
            return 1;
        }
        //最小打印次数最长是L~R的长度
        int ans = R - L + 1;
        //i表示L~R上字符分成两部分后右部分的第一个字符，遍历所有情况
        for (int i = L + 1; i <= R; i++) {
            //1、打印左部分
            int p1 = process1(str, L, i - 1);
            //2、打印右部分
            int p2 = process1(str, i, R);
            //3、若左部分第一个和右部分第一个相同，这两个字符同时打印，打印数-1
            int p3 = str[L] == str[i] ? -1 : 0;
            ans = Math.min(ans, p1 + p2 + p3);
        }
        return ans;
    }

    //2、动态规划
    public static int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        //dp[i][j]表示刷出i~j上的样子，返回最小打印次数
        int[][] dp = new int[N][N];
        //只有一个字符，打印1次
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int L = N - 1; L >= 0; L--) {
            for (int R = L + 1; R < N; R++) {
                //最小打印次数最长是L~R的长度
                dp[L][R] = R - L + 1;
                //k表示L~R上字符分成两部分后右部分的第一个字符，遍历所有情况
                for (int k = L + 1; k <= R; k++) {
                    //1、打印左部分
                    int p1 = dp[L][k - 1];
                    //2、打印右部分
                    int p2 = dp[k][R];
                    //3、若左部分第一个和右部分第一个相同，这两个字符同时打印，打印数-1
                    int p3 = str[L] == str[k] ? -1 : 0;
                    dp[L][R] = Math.min(dp[L][R], p1 + p2 + p3);
                }
            }
        }
        return dp[0][N - 1];
    }



    public static int strangePrinter2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        return process2(str, 0, N - 1, dp);
    }

    public static int process2(char[] str, int L, int R, int[][] dp) {
        if (dp[L][R] != 0) {
            return dp[L][R];
        }
        int ans = R - L + 1;
        if (L == R) {
            ans = 1;
        } else {
            for (int k = L + 1; k <= R; k++) {
                ans = Math.min(ans, process2(str, L, k - 1, dp) + process2(str, k, R, dp) - (str[L] == str[k] ? 1 : 0));
            }
        }
        dp[L][R] = ans;
        return ans;
    }

    public static int strangePrinter3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = R - L + 1;
                for (int k = L + 1; k <= R; k++) {
                    dp[L][R] = Math.min(dp[L][R], dp[L][k - 1] + dp[k][R] - (str[L] == str[k] ? 1 : 0));
                }
            }
        }
        return dp[0][N - 1];
    }
}

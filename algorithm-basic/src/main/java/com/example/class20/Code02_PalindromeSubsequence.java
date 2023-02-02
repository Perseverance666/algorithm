package com.example.class20;

/**
 * @Date: 2023/2/1 14:13
 * 最长回文子序列
 * <p>
 * 题目：给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列
 * <p>
 * 方法1：求字符串s1的逆序字符串s2，然后求s1和s2的最长公共子序列就是原字符串的最长回文子序列
 * 方法2：像之前动态规划题目一样先去尝试
 *
 * 测试链接：https://leetcode.cn/problems/longest-palindromic-subsequence/
 */
public class Code02_PalindromeSubsequence {
    //1、暴力递归
    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, 0, s.length() - 1);
    }

    //返回str在L到R上的最长回文子序列
    public static int process(char[] str, int L, int R) {
        if (L == R) {
            //1、当前字符串只剩一个字符了
            return 1;
        } else if (L == R - 1) {
            //2、当前字符串只剩两个字符了
            return str[L] == str[R] ? 2 : 1;
        } else {
            //3、当前字符串不止两个
            if (str[L] == str[R]) {
                //3.1、L上的字符和R上的字符一样
                return 2 + process(str, L + 1, R - 1);
            } else {
                //3.2、L上的字符和R上的字符不一样。以下两种可能中值较大的一定是对的
                //假设L上的字符不是回文子序列的一部分
                int p1 = process(str, L + 1, R);
                //假设R上的字符不是回文子序列的一部分
                int p2 = process(str, L, R - 1);
                return Math.max(p1, p2);
            }
        }
    }

    //2、动态规划
    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        //建表，填表，dp表只需填上三角形即可
        int[][] dp = new int[N][N];
        //先将右下角最后一个元素赋值，避免赋值第二条对角线时越界
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            //1、当前字符串只剩一个字符了
            dp[i][i] = 1;
            //2、当前字符串只剩两个字符了
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        //3、当前字符串不止两个，从N-3行开始往上填(N-1行和N-2行填完了)，每一行再从左往右填。
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                if (str[L] == str[R]) {
                    //3.1、L上的字符和R上的字符一样
                    dp[L][R] = 2 + dp[L + 1][R - 1];
                } else {
                    //3.2、L上的字符和R上的字符不一样。以下两种可能中值较大的一定是对的
                    //假设L上的字符不是回文子序列的一部分
                    int p1 = dp[L + 1][R];
                    //假设R上的字符不是回文子序列的一部分
                    int p2 = dp[L][R - 1];
                    dp[L][R] = Math.max(p1, p2);
                }
            }
        }
        //4、返回从0到N-1上的最长回文子序列
        return dp[0][N - 1];
    }

//    public static int lpsl1(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] str = s.toCharArray();
//        return f(str, 0, str.length - 1);
//    }
//
//    // str[L..R]最长回文子序列长度返回
//    public static int f(char[] str, int L, int R) {
//        if (L == R) {
//            return 1;
//        }
//        if (L == R - 1) {
//            return str[L] == str[R] ? 2 : 1;
//        }
//        int p1 = f(str, L + 1, R - 1);
//        int p2 = f(str, L, R - 1);
//        int p3 = f(str, L + 1, R);
//        int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));
//        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
//    }
//
//    public static int lpsl2(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] str = s.toCharArray();
//        int N = str.length;
//        int[][] dp = new int[N][N];
//        dp[N - 1][N - 1] = 1;
//        for (int i = 0; i < N - 1; i++) {
//            dp[i][i] = 1;
//            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
//        }
//        for (int L = N - 3; L >= 0; L--) {
//            for (int R = L + 2; R < N; R++) {
//                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
//                if (str[L] == str[R]) {
//                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
//                }
//            }
//        }
//        return dp[0][N - 1];
//    }

    //方法1：求字符串s1的逆序字符串s2，然后求s1和s2的最长公共子序列就是原字符串的最长回文子序列
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        char[] reverse = reverse(str);
        return longestCommonSubsequence(str, reverse);
    }

    public static char[] reverse(char[] str) {
        int N = str.length;
        char[] reverse = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            reverse[--N] = str[i];
        }
        return reverse;
    }

    public static int longestCommonSubsequence(char[] str1, char[] str2) {
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        return dp[0][N - 1];
    }
}

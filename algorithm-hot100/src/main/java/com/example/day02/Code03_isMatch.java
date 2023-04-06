package com.example.day02;

/**
 * @Date: 2023/4/4 16:31
 * 10. 正则表达式匹配
 * <p>
 * https://leetcode.cn/problems/regular-expression-matching/?favorite=2cktkvj
 */
public class Code03_isMatch {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        //dp[i][j]表示p的前j个字符是否能与s的前i个字符匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //dp[1~m][0] = false

        //初始化第一行
        for (int j = 2; j <= n; j += 2) {
            if (dp[0][j - 2] && p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) != '*') {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    //p.charAt(j) == '*'
                    if (dp[i][j - 2]) {
                        dp[i][j] = dp[i][j - 2];
                    } else if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

}

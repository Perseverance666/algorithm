package com.example.day29_动态规划;

/**
 * @Date: 2023/3/30 14:44
 * 剑指 Offer 19. 正则表达式匹配
 * <p>
 * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 */
public class Code01_IsMatch {
    public boolean isMatch(String s, String p) {
        int M = s.length();
        int N = p.length();
        //dp[i][j]表示s的前i个字符和p的前j个字符是否匹配
        boolean[][] dp = new boolean[M + 1][N + 1];
        dp[0][0] = true; //空字符串匹配
        //dp[1~M][0] = false
        //先初始化第一行，即讨论p的前j个字符是否能与空字符串匹配
        for (int j = 2; j <= N; j += 2) {
            //不用管p的奇数位字符是什么，只有p的偶数位字符全是'*'就能和空字符串匹配
            //p的字符个数要是奇数，肯定没法匹配
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (p.charAt(j - 1) != '*') {
                    //1、p的第j个字符是字母或者'.'
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        //1.1、p的第j个字符与s的第i个字符相等，或者p的第j个字符是'.'
                        //直接去看p的前j-1个字符与s的前i-1个字符匹配情况
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    //2、p的第j个字符是'*'
                    if (dp[i][j - 2]) {
                        //2.1、s的前i个字符与p的前j-2个字符匹配，不管p的j-1字符是啥，'*'表示其出现0次，匹配
                        dp[i][j] = dp[i][j - 2];
                    } else if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        //2.2、p的第j-1个字符与s的第i个字符相等，或者p的第j-1个字符是'.'
                        //此时s的第i个字符一定能处理掉。直接去看p的前j个字符与s的前i-1个字符匹配情况
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        }
        return dp[M][N];
    }
}

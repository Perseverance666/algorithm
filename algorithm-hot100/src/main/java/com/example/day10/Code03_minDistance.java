package com.example.day10;

/**
 * @Date: 2023/4/13 14:58
 * 72. 编辑距离
 * <p>
 * https://leetcode.cn/problems/edit-distance/?favorite=2cktkvj
 */
public class Code03_minDistance {
    public int minDistance(String word1, String word2) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int m = str1.length;
        int n = str2.length;
        if (m == 0 || n == 0) {
            return m == 0 ? n : m;
        }
        //dp[i][j]表示word1的前i个字符 转换成 word2的前j个字符 最少需要多少步
        //我们在建表时，针对第一行，第一列要单独考虑，我们引入空字符串，然后对第一行和第一列先初始化(方便计算，不这样定义，最终有3个例子过不去)
        int[][] dp = new int[m + 1][n + 1];
        //dp[0][0] = 0;
        //初始第一行
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }
        //初始化第一列
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //例：hor -> ro
                    int p1 = dp[i][j - 1] + 1;        //添加：(hor -> r) -> ro
                    int p2 = dp[i - 1][j] + 1;        //删除：hor -> (ho -> ro)
                    int p3 = dp[i - 1][j - 1] + 1;    //替换：(ho -> r) 然后在把最后的r -> o  最终凑成hor -> ro
                    dp[i][j] = Math.min(Math.min(p1, p2), p3);
                }
            }
        }
        return dp[m][n];
    }
}

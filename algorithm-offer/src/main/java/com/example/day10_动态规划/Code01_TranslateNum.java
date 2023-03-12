package com.example.day10_动态规划;

/**
 * @Date: 2023/3/12 12:56
 * 剑指 Offer 46. 把数字翻译成字符串
 */
public class Code01_TranslateNum {
    public int translateNum(int num) {
        char[] str = String.valueOf(num).toCharArray();//将num转为字符串
        if (str.length == 1) {
            return 1;
        }
        //dp[i]表示str[0]~str[i]结尾的翻译方法数
        int[] dp = new int[str.length];
        dp[0] = 1;
        if (str[0] - '0' > 2 || str[0] - '0' == 0 || (str[0] - '0' == 2 && str[1] - '0' > 5)) {
            dp[1] = 1;
        } else {
            dp[1] = 2;
        }
        for (int i = 2; i < str.length; i++) {
            if (str[i - 1] - '0' > 2 || str[i - 1] - '0' == 0 || (str[i - 1] - '0' == 2 && str[i] - '0' > 5)) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }

        }
        return dp[str.length - 1];
    }
}

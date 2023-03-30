package com.example.day29_动态规划;

/**
 * @Date: 2023/3/30 14:44
 * 剑指 Offer 49. 丑数
 *
 * https://leetcode.cn/problems/chou-shu-lcof
 */
public class Code02_NthUglyNumber {
    public int nthUglyNumber(int n) {
        //dp[i]表示第i个丑数是什么，dp[0]不用
        int[] dp = new int[n+1];
        dp[1] = 1;
        //用这三个变量位置上的丑数，分别乘上2,3,5，最终比较得出最小的丑数，赋予给dp[i]
        int a = 1;
        int b = 1;
        int c = 1;
        for(int i = 2; i <= n; i++){
            //丑数 = 某较小丑数 × 某因子
            int n2 = dp[a] * 2;
            int n3 = dp[b] * 3;
            int n5 = dp[c] * 5;
            //要保证dp[i]是大于dp[i-1]最小的丑数
            dp[i] = Math.min(Math.min(n2,n3),n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n];
    }
}

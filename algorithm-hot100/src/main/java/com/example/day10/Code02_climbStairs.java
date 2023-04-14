package com.example.day10;

/**
 * @Date: 2023/4/13 14:58
 * 70. 爬楼梯
 *
 * https://leetcode.cn/problems/climbing-stairs/?favorite=2cktkvj
 */
public class Code02_climbStairs {
    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        //dp[i]表示从0开始爬到i阶，有多少种不同的方法
        int[] dp = new int[n+1];  //dp[0]不用了
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}

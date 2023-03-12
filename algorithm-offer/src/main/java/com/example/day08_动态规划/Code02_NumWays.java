package com.example.day08_动态规划;

/**
 * @Date: 2023/3/10 20:14
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 */
public class Code02_NumWays {
//    public int numWays(int n) {
//        if(n == 0 || n == 1){
//            return 1;
//        }
//        return numWays(n - 1) + numWays(n - 2);
//    }

    public int numWays(int n) {
        if(n == 0 || n == 1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n;i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n] % 1000000007;
    }
}

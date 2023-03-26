package com.example.day24_数学;

/**
 * @Date: 2023/3/26 18:34
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 *
 * 约瑟夫环问题公式：f(n) = (f(n-1) + m) % n
 *
 * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 */
public class Code03_LastRemaining {
    public int lastRemaining(int n, int m) {
        // //dp[i]表示 有i个数，经过一系列删除第m个数之后，最终剩的那一个数
        // //例如dp[5] = 3  dp[4] = 0  dp[3] = 1  dp[2] = 1  dp[1] = 0
        // int[] dp = new int[n + 1];
        // //dp[1] = 0;
        // for(int i = 2; i <= n; i++){
        //     dp[i] = (dp[i-1] + m) % i;  //约瑟夫环问题公式
        // }
        // return dp[n];

        //最终dp数组可以用一个变量代替
        int ans = 0;
        for(int i = 2; i <= n; i++){
            ans = (ans + m) % i;        //约瑟夫环问题公式
        }
        return ans;
    }

}

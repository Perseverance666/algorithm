package com.example.class36;

/**
 * @Date: 2023/2/17 14:43
 * 背包放零食
 *
 * 题目：牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w，牛牛想知道在总体积不超过背包容量的情况下,
 * 一共有多少种零食放法，体积为0也算一种放法
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9，v[I] (0 <= v[i] <= 10^9）
 */
public class Code03_1_SnacksWays {
    //1、适用于背包容量w不大时
    public static int ways1(int[] arr, int w) {
        // arr[0...]
        return process(arr, 0, w);
    }
    // 讨论第index号背包一直到最后一个背包，背包还剩rest容量，返回零食放法数
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) { // 没有容量了
            // -1 无方案的意思
            return -1;
        }
        // rest>=0,
        if (index == arr.length) { // 无零食可选
            return 1;
        }
        int next1 = process(arr, index + 1, rest); // 不要
        int next2 = process(arr, index + 1, rest - arr[index]); // 要
        return next1 + (next2 == -1 ? 0 : next2);
    }
    public static int dp1(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];
        for (int j = 0; j <= w; j++) {
            dp[N][j] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][w];
    }
    //2、适用于第i袋零食体积为v[i]不大时
    public static int dp2(int[] arr, int w) {
        int N = arr.length;
        //dp[i][j]代表第0~i-1号背包经过选择后，总体积为j的零食放法数
        int[][] dp = new int[N][w + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
            }
        }
        int ans = 0;
        for (int j = 0; j <= w; j++) {
            ans += dp[N - 1][j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 2, 9 };
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(dp1(arr, w));
        System.out.println(dp2(arr, w));

    }

}

package com.example.day20;

/**
 * @Date: 2023/5/15 20:29
 * 198. 打家劫舍
 * <p>
 * https://leetcode.cn/problems/house-robber/
 */
public class Code01_rob {
    //    //自己写的
//    public int rob(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int N = nums.length;
//        int res = 0;
//        //dp[i]表示从num[i]开始偷能获得到的最高金额
//        int[] dp = new int[N];
//        dp[N - 1] = nums[N - 1];
//        for (int i = N - 2; i >= 0; i--) {
//            int max = 0;
//            for (int j = i; j < N; j++) {
//                int money = nums[i] + (j + 2 < N ? dp[j + 2] : 0);
//                max = Math.max(max, money);
//            }
//            dp[i] = max;
//            res = Math.max(res,dp[i]);
//        }
//        return res;
//    }
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        if (N == 1) {
            return nums[0];
        }
        //dp[i]表示前i间房屋能偷窃到的最高总金额
        int[] dp = new int[N];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < N; i++) {
            //偷当前
            int p1 = nums[i] + dp[i - 2];
            //不偷当前
            int p2 = dp[i - 1];
            dp[i] = Math.max(p1, p2);
        }
        return dp[N - 1];
    }
}

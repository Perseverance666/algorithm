package com.example.day08;

/**
 * @Date: 2023/4/11 15:54
 * 53. 最大子数组和
 * <p>
 * https://leetcode.cn/problems/maximum-subarray/?favorite=2cktkvj
 */
public class Code03_maxSubArray {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        //dp[i]表示必须以nums[i]结尾的最大连续子数组的和
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max((dp[i - 1] + nums[i]), nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

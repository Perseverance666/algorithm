package com.example.day25;

/**
 * @Date: 2023/5/31 20:04
 * 300. 最长递增子序列
 * <p>
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class Code03_lengthOfLIS {
    public static int lengthOfLIS(int[] nums) {
        int N = nums.length;
        if(N == 0){
            return 0;
        }
        int res = 1;
        //dp[i]表示必须以nums[i]结尾的最长递增子序列的长度
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(arr));
    }
}

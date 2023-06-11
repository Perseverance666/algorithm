package com.example.code01_array;

/**
 * @Date: 2023/6/11 13:33
 * 209. 长度最小的子数组
 * <p>
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class Code04_minSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        //滑动窗口
        int L = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (int R = 0; R < nums.length; R++) {
            sum += nums[R];
            while (sum >= target) {
                res = Math.min(res, R - L + 1);
                sum -= nums[L++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

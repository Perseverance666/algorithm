package com.example.day18;

/**
 * @Date: 2023/5/14 18:37
 * 152. 乘积最大子数组
 * <p>
 * https://leetcode.cn/problems/maximum-product-subarray/
 */
public class Code03_maxProduct {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        //dpMax[i]表示以nums[i]为结尾的最大乘积连续子数组
        int[] dpMax = new int[len];
        //dpMin[i]表示以nums[i]为结尾的最小乘积连续子数组
        int[] dpMin = new int[len];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(nums[i] * dpMax[i - 1], nums[i] * dpMin[i - 1]));
            dpMin[i] = Math.min(nums[i], Math.min(nums[i] * dpMin[i - 1], nums[i] * dpMax[i - 1]));
        }
        int res = dpMax[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dpMax[i]);
        }
        return res;
    }
}

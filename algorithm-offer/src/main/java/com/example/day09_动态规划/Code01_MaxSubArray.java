package com.example.day09_动态规划;

/**
 * @Date: 2023/3/12 12:54
 * 剑指 Offer 42. 连续子数组的最大和
 */
public class Code01_MaxSubArray {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            //求出以num[i]为最后元素的子数组最大和
            nums[i] += Math.max(0,nums[i-1]);
            max = Math.max(max,nums[i]);
        }
        return max;
    }

}

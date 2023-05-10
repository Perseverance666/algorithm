package com.example.day16;

/**
 * @Date: 2023/5/9 18:19
 * 136. 只出现一次的数字
 *
 * https://leetcode.cn/problems/single-number/
 */
public class Code03_singleNumber {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }
}

package com.example.day19;

/**
 * @Date: 2023/5/14 21:38
 * 169. 多数元素
 *
 * 摩尔投票法：假设不同数字相互抵消，那么最后剩下的数字，就是我们要找的多数元素
 *
 * https://leetcode.cn/problems/majority-element/
 */
public class Code03_majorityElement {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(candidate == nums[i]){
                count++;
            }else {
                count--;
                if(count == 0){
                    candidate = nums[i];
                    count = 1;
                }
            }
        }
        return candidate;
    }
}

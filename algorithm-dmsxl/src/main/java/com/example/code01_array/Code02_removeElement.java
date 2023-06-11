package com.example.code01_array;

/**
 * @Date: 2023/6/11 12:01
 * 27. 移除元素
 *
 * https://leetcode.cn/problems/remove-element/
 */
public class Code02_removeElement {
    public int removeElement(int[] nums, int val) {
        //双指针
        int slow = 0;
        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != val){
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}

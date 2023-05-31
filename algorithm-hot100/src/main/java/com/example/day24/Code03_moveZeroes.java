package com.example.day24;

/**
 * @Date: 2023/5/29 19:53
 * 283. 移动零
 *
 * https://leetcode.cn/problems/move-zeroes/
 */
public class Code03_moveZeroes {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        //左指针左边均为非零数；
        int left = 0;
        //右指针左边直到左指针处均为零
        int right = 0;
        while(right < n){
            if(nums[right] != 0){
                swap(nums,left,right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}

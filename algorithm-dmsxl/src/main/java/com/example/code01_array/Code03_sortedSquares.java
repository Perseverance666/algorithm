package com.example.code01_array;

/**
 * @Date: 2023/6/11 13:11
 * 977. 有序数组的平方
 *
 * https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class Code03_sortedSquares {
    public int[] sortedSquares(int[] nums) {
        //双指针
        int len = nums.length;
        int L = 0;
        int R = len - 1;
        int[] res = new int[len];
        int index = len - 1;
        while(L <= R){
            if(nums[L] * nums[L] >= nums[R] * nums[R]){
                res[index--] = nums[L] * nums[L];
                L++;
            }else{
                res[index--] = nums[R] * nums[R];
                R--;
            }
        }
        return res;
    }
}

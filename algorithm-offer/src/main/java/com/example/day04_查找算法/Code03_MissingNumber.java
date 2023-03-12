package com.example.day04_查找算法;

/**
 * @Date: 2023/3/5 19:29
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 */
public class Code03_MissingNumber {
    public int missingNumber(int[] nums) {
        int L = 0;
        int R = nums.length - 1;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            if(nums[mid] == mid){
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return L;
    }
}

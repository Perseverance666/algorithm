package com.example.day04;

/**
 * @Date: 2023/3/5 19:03
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 */
public class Code02_Search {
    //二分
    public int search(int[] nums, int target) {
        return findRight(nums,target) - findRight(nums,target-1);
    }
    //找到>target的第一个位置
    public static int findRight(int[] nums,int target){
        int L = 0;
        int R = nums.length - 1;
        while(L <= R){
            int mid = L + ((R-L) >> 1);
            if(nums[mid] <= target){
                L = mid + 1;
            }else {
                R = mid - 1;
            }
        }
        return L;
    }
}

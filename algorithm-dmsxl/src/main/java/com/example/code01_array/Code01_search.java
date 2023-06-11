package com.example.code01_array;

/**
 * @Date: 2023/6/11 11:51
 * 704. 二分查找
 *
 * https://leetcode.cn/problems/binary-search/
 */
public class Code01_search {
    public int search(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                L = mid + 1;
            }else if(nums[mid] > target){
                R = mid - 1;
            }
        }
        return -1;
    }
}

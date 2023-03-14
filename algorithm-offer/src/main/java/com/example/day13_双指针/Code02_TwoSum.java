package com.example.day13_双指针;

/**
 * @Date: 2023/3/14 19:31
 * 剑指 Offer 57. 和为s的两个数字
 */
public class Code02_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        // int[] res = new int[2];
        while(L < R){
            int sum = nums[L] + nums[R];
            if(sum > target){
                R--;
            }else if(sum < target){
                L++;
            }else {
                //nums[i] + nums[R] == target
                return new int[] { nums[L], nums[R] };
            }
        }
        return new int[0];
    }
}

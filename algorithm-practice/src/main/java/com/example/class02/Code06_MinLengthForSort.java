package com.example.class02;

/**
 * @Date: 2023/2/28 16:48
 * 最短无序连续子数组
 *
 * 题目：给定一个数组arr，只能对arr中的一个子数组排序，但是想让arr整体都有序，返回满足这一设定的子数组中最短的是多长
 *
 * 测试链接 : https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
 */
public class Code06_MinLengthForSort {
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int N = nums.length;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int min = Integer.MAX_VALUE;
        int left = N;
        for (int i = N - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }

}

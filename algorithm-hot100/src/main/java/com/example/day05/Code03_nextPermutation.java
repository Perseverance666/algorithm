package com.example.day05;

import java.util.Arrays;

/**
 * @Date: 2023/4/8 22:18
 * 31. 下一个排列
 * <p>
 * https://leetcode.cn/problems/next-permutation/?favorite=2cktkvj
 */
public class Code03_nextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i > 0; i--) {
            //1、从最后一个数开始找，只要 后面的数大于前面的数，那么前面的数nums[i-1]就是即将要交换的数
            if (nums[i] > nums[i - 1]) {
                //2、将nums[i-1]后面的数进行排序
                Arrays.sort(nums, i, len);
                for (int j = i; j < len; j++) {
                    //3、从i开始找，找到第一个大于nums[i-1]的数，然后交换得到nums的下一排列
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, j, i - 1);
                        return;
                    }
                }
            }
        }
        //若经过上面循环没找到要交换的数nums[i-1]，说明此时nums数组正好倒序，只需将nums进行排序即可
        Arrays.sort(nums);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

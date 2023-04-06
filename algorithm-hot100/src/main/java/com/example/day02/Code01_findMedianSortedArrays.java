package com.example.day02;

import java.util.Arrays;

/**
 * @Date: 2023/4/4 16:30
 * 4. 寻找两个正序数组的中位数
 * 难
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/?favorite=2cktkvj
 */
public class Code01_findMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        //这道题就是求两个数组合并后第k小的数
        //若合并后的数组长度为奇数，k = (len + 1)/2
        //若合并后的数组长度为偶数，k1 = len / 2   k2 = len/2 + 1
        if (len % 2 == 0) {
            return (getKth(nums1, 0, nums2, 0, len / 2) + getKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        } else {
            return getKth(nums1, 0, nums2, 0, (len + 1) / 2);
        }
    }

    //找到nums1[start1...]和nums2[start2..]合并后的第k小的数
    private int getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1。方便计算
        if (len1 > len2) return getKth(nums2, start2, nums1, start1, k);
        //len1大小为0，直接返回nums2中第k小的数
        if (len1 == 0) return nums2[start2 + k - 1];
        //base case
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1; //nums1数组中k/2小的数的下标。注意如果k/2已经超了当时的len1，取len1
        int j = start2 + Math.min(len2, k / 2) - 1; //nums2数组中k/2小的数的下标

        if (nums1[i] > nums2[j]) {
            //num2[j]左边的都不要了
            return getKth(nums1, start1, nums2, j + 1, k - (j - start2 + 1));
        } else {
            //num2[i]左边的都不要了
            return getKth(nums1, i + 1, nums2, start2, k - (i - start1 + 1));
        }
    }


//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int[] nums = new int[nums1.length + nums2.length];
//        for (int i = 0; i < nums1.length; i++) {
//            nums[i] = nums1[i];
//        }
//        int j = 0;
//        for (int i = nums1.length; i < nums.length; i++) {
//            nums[i] = nums2[j++];
//        }
//        Arrays.sort(nums);
//        int index = nums.length / 2;
//        if (nums.length % 2 == 1) {
//            return nums[index];
//        } else {
//            return (nums[index - 1] + nums[index]) / 2.0;
//        }
//    }
}

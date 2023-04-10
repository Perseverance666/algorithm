package com.example.day06;

/**
 * @Date: 2023/4/9 21:21
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 二分模版1。[l,r]划分成[l,mid]和[mid+1,r]。mid = (l + r)/2; l = mid + 1;
 * int bsearch_1(int l, int r)
 * {
 *     while (l < r)
 *     {
 *         int mid = (l + r)/2;
 *         if (check(mid)) r = mid;
 *         else l = mid + 1;
 *     }
 *     return l;
 * }
 *  二分模版2 [l,r]划分成[l,mid-1]和[mid,r]。mid = (l + r + 1)/2;
 *  int bsearch_2(int l, int r)
 * {
 *     while (l < r)
 *     {
 *         int mid = ( l + r + 1 ) /2;
 *         if (check(mid)) l = mid;
 *         else r = mid - 1;
 *     }
 *     return l;
 * }

 */
public class Code03_searchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        //1、找到>= target的第一个数
        int L = 0;
        int R = nums.length - 1;
        while (L < R) {
            int mid = (L + R) / 2;
            if (nums[mid] < target) {
                L = mid + 1;
            } else { //nums[mid] >= target
                R = mid;
            }
        }
        //没找到=target的数，直接返回[-1,-1]
        if (nums[L] != target) {
            return new int[]{-1, -1};
        }
        //找到=target的第一个数的下标L
        //2、找到<= target的最后一个数
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            //若mid = (l + r) / 2;  l会一直等于mid，造成死循环。这样当l~r之间元素个数位偶数时，mid取中间右侧的那个数
            int mid = (l + r + 1) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else { //nums[mid] <= target
                l = mid;
            }
        }
        //找到=target的最后一个数的下标l
        return new int[]{L, l};
    }
}

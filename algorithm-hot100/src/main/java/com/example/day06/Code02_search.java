package com.example.day06;

/**
 * @Date: 2023/4/9 21:21
 * 33. 搜索旋转排序数组
 * <p>
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/?favorite=2cktkvj
 */
public class Code02_search {
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return target == nums[0] ? 0 : -1;
        }
        int L = 0;
        int R = len - 1;
        //将数组第一次从mid分开时，一定有一部分是有序的
        while (L < R) {
            int mid = (L + R) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                //说明0 ~ mid 一定有序
                if (target >= nums[0] && target < nums[mid]) {
                    //target在mid左边，左边有序
                    R = mid;
                } else {
                    //target在mid右边
                    L = mid + 1;
                }
            } else {
                //nums[0] > nums[mid]。说明mid+1 ~ len-1 一定有序
                if (target > nums[mid] && target <= nums[len - 1]) {
                    //target在mid右边，右边有序
                    L = mid + 1;
                } else {
                    R = mid;
                }
            }
        }
        //L == R时结束循环
        return nums[L] == target ? L : -1;
    }

}

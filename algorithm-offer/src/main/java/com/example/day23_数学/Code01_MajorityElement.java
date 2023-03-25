package com.example.day23_数学;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 2023/3/25 18:50
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * <p>
 * https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 */
public class Code01_MajorityElement {
    public int majorityElement(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    //求arr[i~j]上出现次数超过一半的那个数字
    public int process(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        int mid = (i + j) / 2;
        int left = process(arr, i, mid);
        int right = process(arr, mid + 1, j);
        if (left == right) {
            return left;
        }
        int leftCount = count(arr, left, i, j);
        int rightCount = count(arr, right, i, j);
        return leftCount > rightCount ? left : right;
    }

    //返回num在arr[i~j]上出现的次数
    public int count(int[] arr, int num, int i, int j) {
        int count = 0;
        for (int k = i; k <= j; k++) {
            if (arr[k] == num) {
                count++;
            }
        }
        return count;
    }

//    public int majorityElement(int[] nums) {
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (!map.containsKey(nums[i])) {
//                map.put(nums[i], 1);
//            } else {
//                map.put(nums[i], map.get(nums[i]) + 1);
//            }
//        }
//
//        int count = Integer.MIN_VALUE;
//        int res = 0;
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > count) {
//                count = entry.getValue();
//                res = entry.getKey();
//            }
//        }
//        return res;
//    }

}

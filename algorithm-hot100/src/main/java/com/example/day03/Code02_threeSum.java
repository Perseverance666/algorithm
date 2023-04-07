package com.example.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023/4/6 22:55
 * 15. 三数之和
 * 这道题主要考察双指针，还有去重
 * https://leetcode.cn/problems/3sum/?favorite=2cktkvj
 */
public class Code02_threeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        //固定第一个数first，用双指L，R找剩下两个数
        for (int first = 0; first < len; first++) {
            //如果第一个数大于0，三数和肯定没法等于0，结束
            if (nums[first] > 0) break;
            //如果这组的第一个数 和 上一组的第一个数相等，去讨论下一组，去重
            if (first >= 1 && nums[first - 1] == nums[first]) continue;
            //双指针找剩下两个数
            int L = first + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[first] + nums[L] + nums[R];
                if (sum < 0) {
                    L++;
                    while (L < R && nums[L - 1] == nums[L]) L++; //去重
                } else if (sum > 0) {
                    R--;
                    while (L < R && nums[R] == nums[R + 1]) R--; //去重
                } else {
                    //sum == 0
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    res.add(list);

                    L++;
                    while (L < R && nums[L - 1] == nums[L]) L++; //去重
                    R--;
                    while (L < R && nums[R] == nums[R + 1]) R--; //去重
                }
            }
        }
        return res;
    }


}

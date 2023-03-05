package com.example.day04;

import java.util.HashSet;

/**
 * @Date: 2023/3/4 21:41
 * 剑指 Offer 03. 数组中重复的数字
 */
public class Code01_FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}

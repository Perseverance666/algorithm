package com.example.day01;

import java.util.HashMap;

/**
 * @Date: 2023/4/3 17:44
 * 1. 两数之和
 * <p>
 * https://leetcode.cn/problems/two-sum/?favorite=2cktkvj
 */
public class Code01_twoSum {
    //    public int[] twoSum(int[] nums, int target) {
//        for(int i = 0; i <= nums.length - 2; i++){
//            for(int j = i + 1; j <= nums.length -1; j++){
//                if(nums[i] + nums[j] == target){
//                    return new int[] {i,j};
//                }
//            }
//        }
//        return new int[0];
//    }
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }

}

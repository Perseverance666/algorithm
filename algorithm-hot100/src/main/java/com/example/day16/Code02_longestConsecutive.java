package com.example.day16;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2023/5/9 18:18
 * 128. 最长连续序列
 * 思路：用到set
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 */
public class Code02_longestConsecutive {
    public int longestConsecutive(int[] nums) {
       Set<Integer> set = new HashSet<>();  //重复元素不算结果里，去重
       for(int num : nums){
           set.add(num);
       }
       int res = 0;
       for(int num : set){
           //如果set中有num-1这个元素，就先不管num，因为后面遍历到num-1时，一定会算上num。避免重复遍历
           if(!set.contains(num - 1)){
               //cur为以num为起点的最长连续序列
               int cur = 1;
               while(set.contains(num + 1)){
                   num++;
                   cur++;
               }
               res = Math.max(res,cur);
           }
       }
       return res;
    }
}

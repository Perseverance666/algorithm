package com.example.day10_动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2023/3/12 12:57
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 */
public class Code02_LengthOfLongestSubstring {
    //将HashMap改成数组类型，能优化时间
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        //map记录key这个字符最后一次出现的位置
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int res = 1;
        //dp[i]表示以str[i]为结尾的最长子字符串的长度
        int[] dp = new int[str.length];
        dp[0] = 1;
        for (int i = 1; i < str.length; i++) {
            if (map[str[i]] == -1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                Integer preI = map[str[i]];
                //1、preI ~ i 之间没有出现重复的字母
                int p1 = i - preI;
                //2、preI ~ i 之间出现了重复的字母
                int p2 = dp[i] = dp[i - 1] + 1;
                dp[i] = Math.min(p1, p2);
            }
            map[str[i]] = i;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

//    public int lengthOfLongestSubstring(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] str = s.toCharArray();
//        //map记录key这个字符最后一次出现的位置
//        Map<Character, Integer> map = new HashMap<>();
//        map.put(str[0], 0);
//        int res = 1;
//        //dp[i]表示以str[i]为结尾的最长子字符串的长度
//        int[] dp = new int[str.length];
//        dp[0] = 1;
//        for (int i = 1; i < str.length; i++) {
//            if (!map.containsKey(str[i])) {
//                dp[i] = dp[i - 1] + 1;
//            } else {
//                Integer preI = map.get(str[i]);
//                //1、preI ~ i 之间没有出现重复的字母
//                int p1 = i - preI;
//                //2、preI ~ i 之间出现了重复的字母
//                int p2 = dp[i] = dp[i - 1] + 1;
//                dp[i] = Math.min(p1, p2);
//            }
//            map.put(str[i], i);
//            res = Math.max(res, dp[i]);
//        }
//        return res;
//    }
}

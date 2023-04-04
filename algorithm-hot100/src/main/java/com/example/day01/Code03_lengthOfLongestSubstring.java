package com.example.day01;

/**
 * @Date: 2023/4/3 17:48
 * 3. 无重复字符的最长子串
 * <p>
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/?favorite=2cktkvj
 */
public class Code03_lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //用数组能优化时间
        //map用于存放指定字符上一次出现的位置
        int[] map = new int[256];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        char[] str = s.toCharArray();
        //dp[i]表示以i位置上的字符结尾时的最长子串长度
        int[] dp = new int[str.length];
        dp[0] = 1;
        map[str[0]] = 0;
        int res = 1;
        for (int i = 1; i < str.length; i++) {
            int preI = map[str[i]];
            if (preI == -1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                //1、preI ~ i 之间没有出现重复的字母
                int p1 = i - preI;
                //2、preI ~ i 之间出现了重复的字母
                int p2 = dp[i - 1] + 1;
                dp[i] = Math.min(p1, p2);
            }
            map[str[i]] = i;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

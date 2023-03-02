package com.example.class03;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Date: 2023/2/28 21:26
 * 无重复字符的最长子串
 * 题目：求一个字符串中，最长无重复字符子串长度
 *
 * 测试链接 : https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class Code01_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        //map记录每个字符最新出现的位置
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        int pre = 1;  //以 前一个字符为结尾的最长无重复字符子串长度
        for(int i = 1; i < N; i++){
            //1、求出 当前字符上次的位置
            int p1 = i - map[str[i]];
            //2、求出 i-1位置往左推的距离
            int p2 = pre + 1;
            //3、两者可能取最小，求出以当前节点结尾的最长无重复字符子串长度
            int cur = Math.min(p1,p2);
            ans = Math.max(ans,cur);
            //4、更新pre，和map
            pre = cur;
            map[str[i]] = i;
        }
        return ans;
    }
}

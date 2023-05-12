package com.example.day17;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date: 2023/5/10 16:29
 * 139. 单词拆分
 * <p>
 * https://leetcode.cn/problems/word-break/
 */
public class Code01_wordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        //转移方程dp[i]=dp[j] && check(s[j..i−1])。check(s[j..i−1]) 表示子串s[j..i−1] 是否出现在字典中
        Set<String> set = new HashSet<>(wordDict);
        int N = s.length();
        //dp[i]表示s的前i个字符组成的子串是否能匹配字典中的单词
        boolean[] dp = new boolean[N + 1];
        //dp[0]表示空串可以匹配单词
        dp[0] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}

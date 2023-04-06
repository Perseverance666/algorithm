package com.example.day02;

/**
 * @Date: 2023/4/4 16:31
 * 5. 最长回文子串
 * <p>
 * https://leetcode.cn/problems/longest-palindromic-substring/?favorite=2cktkvj
 */
public class Code02_longestPalindrome {
//    //暴力
//    public static boolean isPalindromic(char[] str, int left, int right) {
//        while (left < right) {
//            if (str[left] != str[right]) {
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
//    }
//
//    public String longestPalindrome1(String s) {
//        char[] str = s.toCharArray();
//        int len = str.length;
//        if (len < 2) {
//            return s;
//        }
//        //由于只返回一个最长回文子串就行，maxLen记录长度，begin记录字符串开始位置，最后再subString，提升效率
//        int maxLen = 1;
//        int begin = 0;
//        for (int i = 0; i < len; i++) {
//            for (int j = i + 1; j < len; j++) {
//                if (j - i + 1 > maxLen && isPalindromic(str, i, j)) {
//                    maxLen = j - i + 1;
//                    begin = i;
//                }
//            }
//        }
//        return s.substring(begin, begin + maxLen);
//    }

    //动态规划
    public String longestPalindrome(String s) {
        char[] str = s.toCharArray();
        int len = str.length;
        if (len < 2) {
            return s;
        }
        //由于只返回一个最长回文子串就行，maxLen记录长度，begin记录字符串开始位置，最后再subString，提升效率
        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        //只剩一个字符肯定是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        //状态转移方程 dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1];
        //i < j 只填上三角即可
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                //1、子串的头和尾不等，肯定不是回文子串
                if (str[i] != str[j]) {
                    dp[i][j] = false;
                } else {
                    //考虑边界，若此时子串长度为2 或者 3，并且str[i] == str[j]，那么这个子串就是回文子串
                    if (j - i < 3) {  //此时子串长度为2 或者 3
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //如果s[i..j]是回文串，并且此时长度比maxLen大，更新maxLen和begin
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
//    public String longestPalindrome(String s) {
//        int len = s.length();
//        if (len < 2) {
//            return s;
//        }
//        int maxLen = 1;
//        int begin = 0;
//        // dp[i][j] 表示 s[i..j] 是否是回文串
//        boolean[][] dp = new boolean[len][len];
//        // 初始化：所有长度为 1 的子串都是回文串
//        for (int i = 0; i < len; i++) {
//            dp[i][i] = true;
//        }
//
//        char[] charArray = s.toCharArray();
//        // 递推开始
//        // 先枚举子串长度
//        for (int L = 2; L <= len; L++) {
//            // 枚举左边界，左边界的上限设置可以宽松一些
//            for (int i = 0; i < len; i++) {
//                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
//                int j = L + i - 1;
//                // 如果右边界越界，就可以退出当前循环
//                if (j >= len) {
//                    break;
//                }
//
//                if (charArray[i] != charArray[j]) {
//                    dp[i][j] = false;
//                } else {
//                    if (j - i < 3) {
//                        dp[i][j] = true;
//                    } else {
//                        dp[i][j] = dp[i + 1][j - 1];
//                    }
//                }
//
//                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
//                if (dp[i][j] && j - i + 1 > maxLen) {
//                    maxLen = j - i + 1;
//                    begin = i;
//                }
//            }
//        }
//        return s.substring(begin, begin + maxLen);
//    }


}

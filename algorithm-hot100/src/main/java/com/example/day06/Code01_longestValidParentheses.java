package com.example.day06;

import java.util.Stack;

/**
 * @Date: 2023/4/9 21:20
 * 32. 最长有效括号
 * <p>
 * https://leetcode.cn/problems/longest-valid-parentheses/?favorite=2cktkvj
 */
public class Code01_longestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int len = str.length;
        int res = 0;
        //dp[i]表示必须以str[i]结尾的子串的最长有效括号
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            //注意：以'('结尾的子串对应的dp = 0
            if (str[i] == ')') {
                if (str[i - 1] == '(') {
                    //1、str[i] == ')' && str[i - 1] == '('   -> dp[i] = dp[i - 2] + 2;  注意边界处理
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                }else { //str[i - 1] == ')'
                    //2、str[i] == ')' && str[i - 1] == ')'
                    //只有在与当前str[i] = ')'对称那个位置str[i - dp[i - 1] - 1] == '('时，dp[i] = dp[i - 1] + 2;否则dp为0
                    if(i - dp[i - 1] - 1 >= 0 && str[i - dp[i - 1] - 1] == '('){
                        //判断前面算的长度是否能加上，也要注意边界处理
                        int pre = i - dp[i - 1] - 2 >= 1 ? dp[i - dp[i - 1] - 2] : 0;
                        dp[i] = dp[i - 1] + 2 + pre;
                    }

                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }
//    public int longestValidParentheses(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] str = s.toCharArray();
//        return process(str, 0, str.length - 1);
//    }
//
//    public static int process(char[] str, int start, int end) {
//        if(start >= end){
//            //base case
//            return 0;
//        }
//        if (str[start] == '(') {
//            if (str[start + 1] == ')') {
//                return 1 + process(str, start + 2, end);
//            } else {
//                //str[start + 1] == '('
//                return process(str, start + 1, end);
//            }
//        } else {
//            //str[start] == ')'
//            return process(str, start + 1, end);
//        }
//    }
}

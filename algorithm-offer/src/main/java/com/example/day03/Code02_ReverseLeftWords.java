package com.example.day03;

/**
 * @Date: 2023/3/4 21:08
 * 剑指 Offer 58 - II. 左旋转字符串
 */
public class Code02_ReverseLeftWords {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

//    public String reverseLeftWords(String s, int n) {
//        StringBuilder res = new StringBuilder();
//        for(int i = n; i < s.length(); i++)
//            res.append(s.charAt(i));
//        for(int i = 0; i < n; i++)
//            res.append(s.charAt(i));
//        return res.toString();
//    }

}

package com.example.day13_双指针;

/**
 * @Date: 2023/3/14 19:32
 * 剑指 Offer 58 - I. 翻转单词顺序
 */
public class Code03_ReverseWords {
    public String reverseWords(String s) {
        //删除首尾空格
        s = s.trim();
        //双指针均指向最后一个单词的尾部
        int R = s.length() - 1;
        int L = R;
        StringBuilder res = new StringBuilder();
        while (L >= 0) {
            while (L >= 0 && s.charAt(L) != ' ') {
                //从右往左遍历，找到第一个空格位置
                L--;
            }
            //将单词插入到结果中
            res.append(s.substring(L + 1, R + 1) + " ");
            while (L >= 0 && s.charAt(L) == ' ') {
                //找到下一个单词的尾部
                L--;
            }
            //双指针均指向下一个单词的尾部
            R = L;
        }
        //最后删除尾空格
        return res.toString().trim();
    }

    //自己写的
    public String reverseWords2(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        //删除首尾空格
        str = str.trim();
        char[] s = str.toCharArray();
        int L = s.length - 1;
        int R = L;
        StringBuilder res = new StringBuilder();
        while (L >= 0) {
            if (s[L] != ' ') {
                L--;
            } else {
                res.append(str.substring(L + 1, R + 1) + " ");
                while(s[L] == ' '){
                    //找到下一个单词的尾部
                    L--;
                }
                R = L;
            }
        }
        //将最后一个单词加到res后
        res.append(str.substring(L + 1, R + 1));
        return res.toString();
    }
}

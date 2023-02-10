package com.example.class28;

/**
 * @Date: 2023/2/10 14:57
 * 后面添字符整体变回文串
 *
 * 题目：给定一个字符串str，只能在str的后面添加字符，想让str整体变成回文串，返回至少要添加几个字符
 * 思想：找到必须包含最后一个字符的最长回文子串，然后将子串前面剩下的部分翻转放到后面
 * 例：abcd123321 ->  找到123321，再将abcd翻转放到后面 -> 结果：abcd123321dcba
 */
public class Code02_AddShortestEnd {
    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (R == str.length) {
                //当某个字符的R扩到结尾，就找到包含最后一个字符的最长回文子串了，结束循环，记下这个字符处理字符串下的回文半径
                maxContainsEnd = pArr[i];
                break;
            }
        }
        //对于原字符串后面至少添加的字符串，字符个数是s.length() - maxContainsEnd + 1
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            //从后往前填res，str是处理字符串，注意下标对应
            res[res.length - 1 - i] = str[i * 2 + 1];
        }
        return String.valueOf(res);
    }
    // 将原字符串变成处理字符串。如"12132" -> "#1#2#1#3#2#"
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }
}

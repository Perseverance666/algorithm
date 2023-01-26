package com.example.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Date: 2023/1/25 21:48
 * 1、打印一个字符串的全部子序列
 * 2、打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 */
public class Code02_PrintAllSubsquences {
    //1、打印一个字符串的全部子序列
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    /**
     * 递归过程
     * @param str   固定参数，给定初始的字符串
     * @param index 来到了str[index]字符，index是当前位置
     * @param ans   将index及以后的所有生成的子序列放到ans里
     * @param path   path存放的是str[0..index-1]上的决定
     */
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) {
            //若index越界，path就是最终结果
            ans.add(path);
            return;
        }
        //每个字符只有两个可能
        //1、不要index位置的字符
        process1(str, index + 1, ans, path);
        //2、要index位置的字符
        process1(str, index + 1, ans, path + String.valueOf(str[index]));
    }

    //2、打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();  //只需将List改为Set
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }

}

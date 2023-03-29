package com.example.day28_搜索与回溯算法;

import java.util.*;

/**
 * @Date: 2023/3/29 14:46
 * 剑指 Offer 38. 字符串的排列
 * 核心思想：将每个字符都放在index位置上(index：0~str.length-1)，放的时候要剪枝，避免重复计算。每次再对index+1位置以后的字符进行全排列
 *
 * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof
 */
public class Code02_Permutation {
    public String[] permutation(String s) {
        List<String> res = new ArrayList<>();
        char[] str = s.toCharArray();
        process(str, 0, res);
        return res.toArray(new String[res.size()]);
    }

    //将字符串str中每个字符都放在index上一次进行讨论，其中每次再对index+1进行全排列
    public static void process(char[] str, int index, List<String> res) {
        if (index == str.length) {
            //index越界，表示调整好了一种可能，将调整好的str放入ans中
            res.add(String.valueOf(str));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = index; i < str.length; i++) {
            if(set.contains(str[i])){
                continue;  //之前有和str[i]重复的字符放到过index上了，剪枝
            }
            set.add(str[i]);
            //1、将index上的字符与i上的字符互换。这样确保每个字符都有在index位置的情况
            swap(str, i, index);
            //2、将str的所有字符从index+1开始全排列放到ans中
            process(str, index + 1, res);
            //3、恢复现场。准备将i+1上的字符放到index上
            swap(str, i, index);
        }
    }

    public static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}

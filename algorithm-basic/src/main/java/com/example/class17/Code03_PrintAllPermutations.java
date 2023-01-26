package com.example.class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date: 2023/1/25 22:13
 * 1、打印一个字符串的全部排列
 * 2、打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class Code03_PrintAllPermutations {
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    public static void f(ArrayList<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                char cur = rest.get(i);
                rest.remove(i);
                f(rest, path + cur, ans);
                rest.add(i, cur);
            }
        }
    }

    //1、打印一个字符串的全部排列
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }
    //将str的所有字符从index开始全排列放到ans中
    public static void g1(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            //index越界，表示调整好了一种可能，将调整好的str放入ans中
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                //1、将index上的字符与i上的字符互换。这样确保每个字符都有在index位置的情况
                swap(str, index, i);
                //2、将str的所有字符从index+1开始全排列放到ans中
                g1(str, index + 1, ans);
                //3、恢复现场。准备原index上的字符与i++上的字符互换
                //如果不恢复现场，一定会出现重复遗漏现象
                swap(str, index, i);
            }
        }
    }

    //2、打印一个字符串的全部排列，要求不要出现重复的排列
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                //str[i]字符是没有试过的，才继续，否则跳过。达到去重效果
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }
}

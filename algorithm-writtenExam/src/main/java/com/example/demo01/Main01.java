package com.example.demo01;

import java.util.Scanner;

/**
 * @Date: 2023/4/21 16:20
 * ZJ18 万万没想到之聪明的编辑
 */
public class Main01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(Main01.func(in.next()));
        }
    }

    public static String func(String str) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i >= 2 && ch == res.charAt(res.length() - 1) && ch == res.charAt(res.length() - 2)) {
                //1、排除AAA
                continue;
            } else if (i >= 3 && ch == res.charAt(res.length() - 1) &&
                    res.charAt(res.length() - 2) == res.charAt(res.length() - 3)) {
                //2、排除AABB
                continue;
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}

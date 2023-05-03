package com.example.demo01;

import java.util.Scanner;

/**
 * @Date: 2023/4/22 16:36
 * ZJ23 找零
 */
public class Main05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = 1024 - scanner.nextInt();
        int cnt = 0;
        int[] money = new int[] {64, 16, 4, 1};
        for (int i = 0; i < money.length; i++) {
            cnt += input / money[i];
            input = input % money[i];
        }
        System.out.println(cnt);
    }
}

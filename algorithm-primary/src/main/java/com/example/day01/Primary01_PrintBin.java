package com.example.day01;

/**
 * @Date: 2022/9/6 19:11
 *
 *  打印32位二进制
 */
public class Primary01_PrintBin {
    public static void print(int num){
        for(int i = 31; i >=0; i--){
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int num = 8;
        print(num);
    }
}

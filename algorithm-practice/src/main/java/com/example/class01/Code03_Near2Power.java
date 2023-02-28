package com.example.class01;

/**
 * @Date: 2023/2/27 17:11
 * 题目：给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
 */
public class Code03_Near2Power {

    //n是非负的
    public static int tableSizeFor(int n) {
        if (n == 0) {
            return 1;
        }
        //将n这个数的二进制形式将左边第一个1一直往右推成所有都是1
        //若n正好是2的倍数，为了方便起见，n--。不是2的倍数也不会影响
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;  //int类型32位，故移动这些以后就可以保证第一个1往右都是1了
        return n + 1;
    }

    public static void main(String[] args) {
        int cap = 120;
        System.out.println(tableSizeFor(cap));
    }

}

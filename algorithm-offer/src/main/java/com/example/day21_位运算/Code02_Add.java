package com.example.day21_位运算;

/**
 * @Date: 2023/3/22 13:57
 * 剑指 Offer 65. 不用加减乘除做加法
 *
 * https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 */
public class Code02_Add {
    //无进位和 m = a ^ b;  进位 n = (a & b) << 1
    //结果就是 m + n，但是不能用+。
    //将m，n作为下一组的a，b循环去求，直到n == 0
    public int add(int a, int b) {
        while(b != 0){    //当进位为0时，即n == 0时，跳出循环
            int m = a ^ b;         //记录无进位和m
            int n = (a & b) << 1;  //记录进位n
            a = m;       //无进位和m,作为下一组计算的a
            b = n;       //进位n，作为下一组计算的b
        }
        //此时 进位n 为0，无进位和m 即为所求
        return a;
    }
}

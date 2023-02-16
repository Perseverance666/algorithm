package com.example.class35;

/**
 * @Date: 2023/2/16 13:52
 * 牛羊吃草
 *
 * 题目：给定一个正整数N，表示有N份青草统一堆放在仓库里，有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜，假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定。根据唯一的参数N，返回谁会赢
 */
public class Code02_EatGrass {
    // 如果n份草，最终先手赢，返回"先手"
    // 如果n份草，最终后手赢，返回"后手"
    //1、暴力解
    public static String winner1(int n) {
        if (n < 5) {        //n<5，硬写
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        // 进到这个过程里来，当前的先手，先选
        int want = 1;
        while (want <= n) {
            //后续过程的后手 和 当前过程的先手是一个人，如果后续过程的后手赢了，说明当前过程的先手就赢了
            if (winner1(n - want).equals("后手")) {
                return "先手";
            }
            if (want <= (n / 4)) {  //这条判断防止数据*4后溢出超出int所能表示的最大范围
                want *= 4;
            } else {
                break;  //再*4就超出int所能表示的最大范围，这里就break
            }
        }
        return "后手";
    }
    //2、根据对数器找规律
    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + winner1(i));
        }
    }

}

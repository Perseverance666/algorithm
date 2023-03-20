package com.example.day19_搜索与回溯算法;

/**
 * @Date: 2023/3/19 19:46
 * 剑指 Offer 64. 求1+2+…+n
 */
public class Code01_SumNums {
    public int sumNums(int n) {
//        if(n == 1){
//            return 1;
//        }
//        return sumNums(n-1) + n;

        //利用短路效应来终止递归
        int res = 1;
        boolean x = n > 1 && (res = n + sumNums(n - 1)) > 0;
        return res;
    }
}

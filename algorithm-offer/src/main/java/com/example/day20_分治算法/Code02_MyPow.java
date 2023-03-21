package com.example.day20_分治算法;

/**
 * @Date: 2023/3/21 13:42
 * 剑指 Offer 16. 数值的整数次方
 * <p>
 * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 */
public class Code02_MyPow {
//    //x = 0.00001  n = 2147483647过不去
//    public static double myPow(double x, int n) {
//        if(n == 0){
//            return 1;
//        }
//        if(n < 0){
//            return 1/x * myPow(x,n+1);
//        }
//        if(n == 1){
//            return x;
//        }
//        return x * myPow(x,n-1);
//    }

    public static double myPow(double x, int n) {
        long N = n;
        //将负数次幂的全转换成正数去讨论
        if (N >= 0) {
            return quickMul(x, N);
        } else {
            return 1.0 / quickMul(x, -N);
        }
    }
    //快速幂：利用二分的思想，将x^n拆分成两份(注意讨论n的奇偶问题)
    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        if (N % 2 == 0) {
            return y * y;
        } else {
            return y * y * x;
        }
    }


    public static void main(String[] args) {
        System.out.println(myPow(0.00001, 2147483647));
    }
}

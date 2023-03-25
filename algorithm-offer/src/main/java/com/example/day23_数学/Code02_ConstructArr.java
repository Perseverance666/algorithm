package com.example.day23_数学;

/**
 * @Date: 2023/3/25 18:50
 * 剑指 Offer 66. 构建乘积数组
 * <p>
 * https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof
 * <p>
 * A0  A1  A2  A3  A4
 * B0   1                           = A1 * A2 * A3 * A4
 * B1       1                       = A0 * A2 * A3 * A4
 * B2           1                   = A0 * A1 * A3 * A4
 * B3               1               = A0 * A1 * A2 * A4
 * B4                   1           = A0 * A1 * A2 * A3
 */
public class Code02_ConstructArr {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        int N = a.length;
        //以对角线1为分界线，1左侧乘积放到left数组，1右侧的乘积放到right数组，最终b[i] = left[i] * right[i]
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;

        for (int i = 1; i < N; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        for (int i = N - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = left[i] * right[i];
        }
        return b;
    }

//        int N = a.length;
//        if(N == 0){
//            return new int[0];
//        }
//        int[] b = new int[N];
//        b[0] = 1;
//        //先计算下三角形乘积计入B[i]
//        for(int i = 1; i < N; i++){
//            b[i] = b[i-1] * a[i-1];
//        }
//        int temp = 1;
//        //再计算上三角乘积乘入到B[i]
//        for(int i = N - 2; i >= 0; i--){
//            temp *= a[i+1];
//            b[i] *= temp;
//        }
//        return b;
//    }


}

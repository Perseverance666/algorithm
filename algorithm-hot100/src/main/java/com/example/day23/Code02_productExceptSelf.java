package com.example.day23;

/**
 * @Date: 2023/5/28 20:25
 * 238. 除自身以外数组的乘积
 *
 * https://leetcode.cn/problems/product-of-array-except-self/
 *
 *      A0  A1  A2  A3  A4
 * B0   1                           = A1 * A2 * A3 * A4
 * B1       1                       = A0 * A2 * A3 * A4
 * B2           1                   = A0 * A1 * A3 * A4
 * B3               1               = A0 * A1 * A2 * A4
 * B4                   1           = A0 * A1 * A2 * A3
 */
public class Code02_productExceptSelf {
    public int[] productExceptSelf(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        int N = a.length;
        //以对角线1为分界线，1左侧乘积放到left数组，1右侧的乘积放到right数组，最终b[i] = left[i] * right[i]
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for(int i = 1; i < N; i++){
            left[i] = left[i - 1] * a[i - 1];
        }
        for(int j = N - 2; j >= 0; j--){
            right[j] = right[j + 1] * a[j + 1];
        }

        int[] b = new int[N];
        for(int i = 0; i < N; i++){
            b[i] = left[i] * right[i];
        }
        return b;
    }
}

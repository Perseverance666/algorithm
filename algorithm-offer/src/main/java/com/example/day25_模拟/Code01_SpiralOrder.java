package com.example.day25_模拟;

/**
 * @Date: 2023/3/26 21:20
 * 剑指 Offer 29. 顺时针打印矩阵
 *
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 */
public class Code01_SpiralOrder {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) {
            return new int[0];
        }
        int l = 0;                          //left
        int r = matrix[0].length - 1;       //right
        int t = 0;                          //top
        int b = matrix.length - 1;          //bottom
        int resIndex = 0;
        int[] res = new int[(r + 1) * (b + 1)];
        while(true) {
            // left to right.
            for(int i = l; i <= r; i++) {
                res[resIndex++] = matrix[t][i];
            }
            t++;
            if(t > b) break;

            // top to bottom.
            for(int i = t; i <= b; i++) {
                res[resIndex++] = matrix[i][r];
            }
            r--;
            if(r < l) break;;

            // right to left.
            for(int i = r; i >= l; i--) {
                res[resIndex++] = matrix[b][i];
            }
            b--;
            if(b < t) break;

            // bottom to top.
            for(int i = b; i >= t; i--) {
                res[resIndex++] = matrix[i][l];
            }
            l++;
            if(l > r) break;;
        }
        return res;
    }
}

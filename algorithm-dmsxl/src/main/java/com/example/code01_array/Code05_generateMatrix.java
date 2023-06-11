package com.example.code01_array;

/**
 * @Date: 2023/6/11 13:53
 * 59. 螺旋矩阵 II
 *
 * https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class Code05_generateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int l = 0;           //left
        int r = n - 1;       //right
        int t = 0;           //top
        int b = n - 1;       //bottom
        int num = 1;
        while (num <= n * n){
            for(int j = l; j <= r; j++){
                res[t][j] = num++;
            }
            t++;
            for(int i = t; i <= b; i++){
                res[i][r] = num++;
            }
            r--;
            for(int j = r; j >= l; j--){
                res[b][j] = num++;
            }
            b--;
            for (int i = b; i >= t; i--) {
                res[i][l] = num++;
            }
        }
        return res;
    }
}

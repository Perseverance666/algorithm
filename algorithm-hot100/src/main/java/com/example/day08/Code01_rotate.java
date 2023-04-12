package com.example.day08;

import java.util.*;

/**
 * @Date: 2023/4/11 15:52
 * 48. 旋转图像
 * <p>
 * https://leetcode.cn/problems/rotate-image/?favorite=2cktkvj
 */
public class Code01_rotate {
    //原地旋转
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int temp = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i]= temp;
            }
        }
    }
//    public void rotate(int[][] matrix) {
//        //(a,b)左上角位置，(c,d)右下角位置，用这两个点代表一圈
//        int a = 0;
//        int b = 0;
//        int c = matrix.length - 1;
//        int d = matrix[0].length - 1;
//        while (a < c) {
//            //每次调整一圈，从外到内
//            rotateEdge(matrix, a++, b++, c--, d--);
//        }
//
//    }
//
//    public static void rotateEdge(int[][] arr, int a, int b, int c, int d) {
//        int temp = 0;
//        //这一圈分为d - b组，每组调整4个点
//        for (int i = 0; i < d - b; i++) {
//            temp = arr[c - i][b];
//            arr[c - i][b] = arr[c][d - i];
//            arr[c][d - i] = arr[a + i][d];
//            arr[a + i][d] = arr[a][b + i];
//            arr[a][b + i] = temp;
//        }
//    }
}

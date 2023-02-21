package com.example.class37;

/**
 * @Date: 2023/2/17 20:11
 * 题目：给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
 */
public class Code05_RotateMatrix {
    public static void rotate(int[][] matrix) {
        //(a,b)左上角位置，(c,d)右下角位置，用这两个点代表一圈
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            //每次调整一圈，从外到内
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int tmp = 0;
        //这一圈分为d-b组，每组要修改4个点
        for (int i = 0; i < d - b; i++) {
            tmp = m[a][b + i];
            m[a][b + i] = m[c - i][b];
            m[c - i][b] = m[c][d - i];
            m[c][d - i] = m[a + i][d];
            m[a + i][d] = tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }
}

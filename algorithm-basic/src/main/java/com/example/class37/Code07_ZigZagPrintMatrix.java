package com.example.class37;

/**
 * @Date: 2023/2/17 20:11
 * 题目：给定一个正方形或者长方形矩阵matrix，实现zigzag，之字形打印
 */
public class Code07_ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        //(endR,endC)是矩阵最右下角的元素
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            //一条斜线上为一组，每次打印一组
            printLevel(matrix, tR, tC, dR, dC, fromUp);
            //(tR,tC)是每一组的右上角元素
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            //(dR,dC)是每一组的左下角元素
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            //每次打印一组后，反向调换
            fromUp = !fromUp;
        }
        System.out.println();
    }
    //一条斜线上为一组，每次打印一组
    public static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean f) {
        //f为true从上往下斜线打印，f为false从下往上斜线打印
        if (f) {
            while (tR != dR + 1) {
                System.out.print(m[tR++][tC--] + " ");
            }
        } else {
            while (dR != tR - 1) {
                System.out.print(m[dR--][dC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}

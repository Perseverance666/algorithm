package com.example.class03;

/**
 * @Date: 2023/2/28 21:31
 * 最大的以 1 为边界的正方形
 * 题目：给定一个只有0和1组成的二维数组，返回边框全是1（内部无所谓）的最大正方形面积
 * <p>
 * 测试链接 : https://leetcode.cn/problems/largest-1-bordered-square/
 */
public class Code03_Largest1BorderedSquare {
    public static int largest1BorderedSquare(int[][] m) {
        //1、初始化右表和下表
        //right[i][j] 代表m[i][j]右边有几个连续的1
        int[][] right = new int[m.length][m[0].length];
        //down[i][j] 代表m[i][j]下边有几个连续的1
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        //2、从最大面积开始试
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }

    //初始化右表和下表
    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        //从右下角这个数开始
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        //初始化最后一列的值，从倒数第二行开始
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        //初始化最后一行的值，从倒数第二列开始
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        //初始化剩下的值，从倒数m[r-2][c-2]开始
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    //返回是否有边长为size的边框全是1的正方形
    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                //检验边框是否全是1
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }
}

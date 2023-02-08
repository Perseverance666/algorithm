package com.example.class26;

/**
 * @Date: 2023/2/8 23:21
 * 铺瓷砖的方法数
 *
 * 题目：用1*2的瓷砖，把N*2的区域填满，返回铺瓷砖的方法数
 */
public class Code04_brick {
    //1、暴力递归
    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(0, n);
    }
    //这个函数代表下标为[i,n)这些区域的方法数
    public static int process(int i, int n) {
        if (i == n - 1) {
            //当讨论在最后一个位置时，砖只能竖着放
            return 1;
        }
        if (i == n) {
            //越界了，返回正在讨论的这一种方法
            return 1;
        }
        //i位置上的砖的放法只有两种可能:
        //1、把1*2的砖竖过来放在i位置，再去讨论i+1位置
        //2、把1*2的砖横过来，两块砖放在占据i和i+1位置，再去讨论i+2位置
        return process(i + 1, n) + process(i + 2, n);
    }

    //2、由于能推出f(n) = f(n-1) + f(n-2)，改成斐波那契数列O(logN)的解法
    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, tmp);
            }
            tmp = product(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length; // a的列数同时也是b的行数
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    ans[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i != 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println("===================");
        }

    }
}

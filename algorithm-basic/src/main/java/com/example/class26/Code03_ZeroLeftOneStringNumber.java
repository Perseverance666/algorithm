package com.example.class26;

/**
 * @Date: 2023/2/8 18:33
 * 有效的字符串数量
 *
 * 题目：给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串，
 * 如果某个字符串，任何0字符的左边都有1紧挨着，认为这个字符串达标。返回有多少达标的字符串
 */
public class Code03_ZeroLeftOneStringNumber {
    //1、暴力递归
    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }
    //所有达标的字符串第一个位置一定是1，说明N个字符串有N-1个位置需要考虑
    // 这个函数代表下标为[i,n)这些字符有多少个达标的
    // 1 1 _ _ _ _ _
    // 1 0 1 _ _ _ _  只有这两种可能
    public static int process(int i, int n) {
//        if (i == n - 1) {
//            return 2;
//        }
//        if (i == n) {
//            //越界了，返回1种可能
//            return 1;
//        }
        if (i >= n) {
            //越界了，返回1种可能
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    //2、由于能推出f(n) = f(n-1) + f(n-2)，改成斐波那契数列O(logN)的解法
    public static int getNum3(int n) {
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


    public static int fi(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
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
            System.out.println(getNum3(i));
            System.out.println("===================");
        }

    }
}
package com.example.class23;

/**
 * @Date: 2023/2/5 13:53
 * N皇后问题
 * <p>
 * 题目：N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。
 * 给定一个整数n，返回n皇后的摆法有多少种。
 * n=1，返回1。n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。n=8，返回92。
 */
public class Code03_NQueens {
    //1、暴力递归
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //用来记录之前每行的数据，record[i] = j 代表第i行的皇后放在了j列上
        int[] record = new int[n];
        return process1(0, record, n);
    }

    /**
     * @param i      当前来到第i行。每一行放一个皇后
     * @param record record记录着0~i-1行的数据
     * @param n      代表要放n个皇后，固定值
     * @return 返回i行往后合法的方法，不管i-1行之前的
     */
    public static int process1(int i, int[] record, int n) {
        if (i == n) {
            //前面n-1行都放完皇后了并且全部合法，方法数+1
            return 1;
        }
        int ways = 0;
        for (int j = 0; j < n; j++) {
            //如果放在i行j列合法
            if (isValid(i, j, record)) {
                record[i] = j;
                ways += process1(i + 1, record, n);
            }
        }
        return ways;
    }

    //检验这一个皇后放在i行j列后是否满足要求
    public static boolean isValid(int i, int j, int[] record) {
        //检验0~i-1行
        for (int row = 0; row < i; row++) {
            int col = record[row];
            if (col == j || Math.abs(j - col) == Math.abs(i - row)) {
                //0~i-1行中某一个皇后也在j列  或者 (i,j)在0~i-1行中某一个皇后的斜线位置上
                return false;
            }
        }
        //0~i-1行都满足条件，返回true
        return true;
    }

    //2、利用位运算
    // 请不要超过32皇后问题
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            //前面几行都放完皇后了并且全部合法，方法数+1
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            //从pos最右侧的1开始尝试
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            //递归算出下一行的结果
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 8;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}

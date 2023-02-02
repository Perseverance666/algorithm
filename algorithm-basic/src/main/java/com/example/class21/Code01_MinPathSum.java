package com.example.class21;

/**
 * @Date: 2023/2/2 14:33
 * 动态规划，空间压缩（小技巧，可以不用）
 *
 * 最小距离累加和
 *
 * 题目：给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角，
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和，返回最小距离累加和
 */
public class Code01_MinPathSum {
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        //建表，dp[i][j]代表从(0,0)到(i,j)的最小距离累加和
        int[][] dp = new int[row][col];
        //1、先填第一行和第一例
        dp[0][0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        //2、填剩下的
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + m[i][j];
            }
        }
        //3、返回从(0,0)到(row-1,col-1)的最小距离累加和
        return dp[row - 1][col - 1];

    }

    //动态规划，空间压缩
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        //空间压缩，用一个数组来装一行的数据。因为每一行都能由上一行推出，最终能得到最后一行的数据
        // dp[i]最终代表(0,0)到(row-1,j)的最小距离累加和
        int[] dp = new int[col];
        dp[0] = m[0][0];
        //1、初始化第一行的数据
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        //2、初始化剩下每一行的数据，最后dp表装的是最后一行的数据
        for (int i = 1; i < row; i++) {
            //3、初始化第i行第一列的数据
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                //dp[j-1]：第i行左边的值   dp[j]：第i-1行上边的值
                //4、第i行第j列的数据取左边和上面最小的再加m[i][j]
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        //5、此时dp表存放的是第row-1行的数据，返回col-1列的值即可
        return dp[col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));

    }
}

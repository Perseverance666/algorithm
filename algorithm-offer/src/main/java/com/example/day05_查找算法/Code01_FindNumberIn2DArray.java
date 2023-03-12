package com.example.day05_查找算法;

/**
 * @Date: 2023/3/6 21:12
 * 剑指 Offer 04. 二维数组中的查找
 */
public class Code01_FindNumberIn2DArray {
    //    //暴力
//    public boolean findNumberIn2DArray(int[][] matrix, int target) {
//        for(int[] row : matrix){
//            for(int num : row){
//                if(num == target){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    //Z字型查找,从右上角开始
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        //从右上往左下找，只能往左或往下走
        int N = matrix.length;
        if (N == 0) {
            return false;
        }
        int M = matrix[0].length;
        int x = 0;
        int y = M - 1;
        while (x < N && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                y--;
            } else {
                //matrix[x][y] < target
                x++;
            }
        }
        return false;
    }
}

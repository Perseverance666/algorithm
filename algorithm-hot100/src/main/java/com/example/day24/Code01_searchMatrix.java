package com.example.day24;

/**
 * @Date: 2023/5/29 19:52
 * 240. 搜索二维矩阵 II
 *
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 */
public class Code01_searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        //Z字型查找,从右上角(0,n-1)开始
        int m = matrix.length;
        int n = matrix[0].length;
        int x = 0;
        int y = n - 1;
        while(x < m && y >= 0){
            if(matrix[x][y] == target){
                return true;
            }else if(matrix[x][y] > target){
                y--;
            }else if(matrix[x][y] < target){
                x++;
            }
        }
        return false;
    }
}

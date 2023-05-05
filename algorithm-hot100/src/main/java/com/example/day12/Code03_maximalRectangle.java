package com.example.day12;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Date: 2023/5/3 21:05
 * 85. 最大矩形
 *
 * https://leetcode.cn/problems/maximal-rectangle/
 */
public class Code03_maximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        int[] height = new int[matrix[0].length];  //直方图数组
        //初始化以每一行为直方图底部的直方图数组
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //height[j]代表以第i行作为直方图底部时，j上的直方图有多高，即j上有几个1
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxArea,maxRecFromBottom(height));
        }
        return maxArea;
    }

    // height是直方图数组
    public static int maxRecFromBottom(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int N = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for(int i = 0; i < N; i++){
            while(!stack.isEmpty() && height[stack.peek()] > height[i]){
                int j = stack.poll();
                int chang = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea,chang * height[j]);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int j = stack.poll();
            int chang = stack.isEmpty() ? N : N - 1 - stack.peek();
            maxArea = Math.max(maxArea,chang * height[j]);
        }
        return maxArea;
    }
}

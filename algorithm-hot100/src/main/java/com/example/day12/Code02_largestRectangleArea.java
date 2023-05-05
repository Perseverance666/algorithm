package com.example.day12;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Date: 2023/5/3 21:04
 * 84. 柱状图中最大的矩形
 *
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 */
public class Code02_largestRectangleArea {
    public static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int N = heights.length;
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for(int i = 0; i < N; i++){
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                //分别讨论高度以height[j]的柱状图的最大面积是多少
                int j = stack.pop();
                //height[j]左边最近比它小的数就是height[stack.peek()],有可能没有。右边最近比它小的数就是height[i]
                int chang = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea,chang * heights[j]);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            //此时单调栈中剩的元素height[j]左边最近比它小的数就是height[stack.peek()],有可能没有。右边没有最近比它小的数
            int j = stack.pop();
            int chang = stack.isEmpty() ? N : N - 1 - stack.peek();
            maxArea = Math.max(maxArea,chang * heights[j]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(arr));
    }
}

package com.example.class25;

import java.util.Stack;

/**
 * @Date: 2023/2/7 13:50
 * 柱状图中最大的矩形
 *
 * 题目：给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积
 * 测试链接：https://leetcode.cn/problems/largest-rectangle-in-histogram
 *
 * 思路：分别讨论高度以height[0~len-1]的柱状图的最大面积是多少
 */
public class Code03_LargestRectangleInHistogram {
    public static int largestRectangleArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int N = height.length;
        int[] stack = new int[N];
        int si = -1;
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (si != -1 && height[i] <= height[stack[si]]) {
                int j = stack[si--];
                int k = si == -1 ? -1 : stack[si];
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int j = stack[si--];
            int k = si == -1 ? -1 : stack[si];
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        int N = heights.length;
        int[] stack = new int[N];
        int stackSize = 0;
        int maxArea = 0;
        for(int i = 0; i < N; i++){
            while(stackSize != 0 && heights[stack[stackSize - 1]] >= heights[i]){
                //分别讨论高度以height[j]的柱状图的最大面积是多少
                int j = stack[--stackSize];
                int chang = stackSize == 0 ? i : i - 1 - stack[stackSize-1];
                maxArea = Math.max(maxArea,chang * heights[j]);

            }
            stack[stackSize++] = i;
        }
        while(stackSize != 0){
            int j = stack[--stackSize];
            int chang = stackSize == 0 ? N : N - 1 - stack[stackSize-1];
            maxArea = Math.max(maxArea,chang * heights[j]);
        }
        return maxArea;

    }
}

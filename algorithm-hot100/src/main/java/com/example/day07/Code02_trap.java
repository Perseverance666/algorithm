package com.example.day07;

/**
 * @Date: 2023/4/10 18:58
 * 42. 接雨水
 * <p>
 * https://leetcode.cn/problems/trapping-rain-water/?favorite=2cktkvj
 */
public class Code02_trap {
    //dp
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        //leftMax[i]代表i的左边最大的柱子高度，其中leftMax[0] = 0;
        int[] leftMax = new int[n];
        int maxLeft = 0;
        for (int i = 1; i < n; i++) {
            maxLeft = Math.max(maxLeft, height[i - 1]);
            leftMax[i] = maxLeft;
        }

        //rightMax[i]代表i的右边最大的柱子高度，其中rightMax[n-1] = 0
        int[] rightMax = new int[n];
        int maxRight = 0;
        for (int i = n - 2; i >= 0; i--) {
            maxRight = Math.max(maxRight, height[i + 1]);
            rightMax[i] = maxRight;
        }
        int sum = 0;
        for(int i = 0; i < n; i++){
            //当前i位置能接的水cur = (左边所有的最高柱子,右边所有的最高数字)min  -  height[i]。
            //如果结果 <= 0，cur直接取0，不能是负数
            int cur = Math.max(Math.min(leftMax[i], rightMax[i]) - height[i], 0);
            sum += cur;
        }
        return sum;
    }
//    //开始自己写的暴力
//    public int trap(int[] height) {
//        int n = height.length;
//        int sum = 0;
//        for (int i = 1; i < n - 1; i++) {
//            //当前i位置能接的水cur = (左边所有的最高柱子,右边所有的最高数字)min  -  height[i]。
//            //如果结果 <= 0，cur直接取0，不能是负数
//            int leftMax = 0;
//            for (int left = i - 1; left >= 0; left--) {
//                leftMax = Math.max(leftMax, height[left]);
//            }
//            int rightMax = 0;
//            for (int right = i + 1; right < n; right++) {
//                rightMax = Math.max(rightMax, height[right]);
//            }
//            int cur = Math.max(Math.min(leftMax, rightMax) - height[i], 0);
//            sum += cur;
//        }
//        return sum;
//    }
}

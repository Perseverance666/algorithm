package com.example.day03;

/**
 * @Date: 2023/4/6 22:55
 * 11. 盛最多水的容器
 * <p>
 * https://leetcode.cn/problems/container-with-most-water/?favorite=2cktkvj
 */
public class Code01_maxArea {
    //双指针
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int res = 0;
        while (L < R) {
            int area = Math.min(height[L], height[R]) * (R - L);
            res = Math.max(res, area);
            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }
        return res;
    }


    //暴力
    public int maxArea1(int[] height) {
        int len = height.length;
        int res = 0;
        for (int i = 0; i < len - 1; i++) {
            if (height[i] == 0) {
                continue;
            }
            //长度从res/height[i]开始，剪枝，不用从1开始，长度小于res/height[i]的，最终得到的值肯定小于res
            for (int j = res / height[i] + i; j < len; j++) {
                if (Math.min(height[i], height[j]) * (j - i) > res) {
                    res = Math.min(height[i], height[j]) * (j - i);
                }
            }
        }
        return res;
    }
}

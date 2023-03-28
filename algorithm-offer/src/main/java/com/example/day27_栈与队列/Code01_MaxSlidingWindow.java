package com.example.day27_栈与队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date: 2023/3/28 16:06
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * <p>
 * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 */
public class Code01_MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        int resIndex = 0;
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //确保队列first端永远是当前窗口内的最大值
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
                queue.pollLast();
            }
            queue.addLast(nums[i]);
            //此时构成一个大小为k的窗口了
            if (i + 1 >= k) {
                res[resIndex++] = queue.peekFirst();
                //滑动窗口将要移动，若队列中最大元素正是要过期的元素，删除掉
                if (nums[i + 1 - k] == queue.peekFirst()) {
                    queue.pollFirst();
                }
            }
        }
        return res;
    }
}

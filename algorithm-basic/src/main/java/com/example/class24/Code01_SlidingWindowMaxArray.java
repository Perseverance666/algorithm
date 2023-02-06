package com.example.class24;

import java.util.LinkedList;

/**
 * @Date: 2023/2/6 13:55
 * 滑动窗口最大值
 * <p>
 * 题目：假设一个固定大小为W的窗口，依次划过arr，返回每一次滑出状况的最大值。
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3。返回：[5,5,5,4,6,7]
 *
 * 测试链接：https://leetcode.cn/problems/sliding-window-maximum/
 */
public class Code01_SlidingWindowMaxArray {
    // 暴力的对数器方法
    public static int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);

            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    //利用窗口内最大值更新结构
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        //1、创建双端队列，存放的是arr的下标。双端队列要确保数组值从头到尾严格递减
        //双端队列含义：假设此时让窗口依次缩小的话，哪些位置的数会依次成为窗口内的最大值
        LinkedList<Integer> maxWindow = new LinkedList<>();
        //用于存放结果，有arr.length - w + 1个元素
        int[] res = new int[arr.length - w + 1];
        int index = 0;

        //滑动窗口从0位置开始移动
        for (int R = 0; R < arr.length; R++) {
            while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                //2、若双端队列有元素，并且尾端数组值<=要放进来的元素，弹出尾端元素
                maxWindow.pollLast();
            }
            //3、该元素加入尾端
            maxWindow.addLast(R);
            //4、检验是否构成w大小的滑动窗口
            if (R - w + 1 >= 0) {
                //4.1、并丢弃双端队列中下标为R-w的元素，确保滑动窗口大小为w
                if (maxWindow.peekFirst() == R - w) {
                    maxWindow.pollFirst();
                }
                //4.2、双端队列首端元素，就是此时这个窗口下的最大值
                res[index++] = arr[maxWindow.peekFirst()];
            }

        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

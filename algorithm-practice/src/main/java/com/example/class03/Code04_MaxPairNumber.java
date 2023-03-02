package com.example.class03;

import java.util.Arrays;

/**
 * @Date: 2023/2/28 21:33
 * 题目：给定一个数组arr，代表每个人的能力值。再给定一个非负数k，如果两个人能力差值正好为k，那么可以凑在一起比赛
 * 一局比赛只有两个人，返回最多可以同时有多少场比赛
 */
public class Code04_MaxPairNumber {
    // 暴力解
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0) {
            return -1;
        }
        return process1(arr, 0, k);
    }

    public static int process1(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            //index已经越界了，说明前面已经排列完了
            for (int i = 1; i < arr.length; i += 2) {
                //就让0 1一组，2 3一组。。因为是全排列，答案一定在其中
                if (arr[i] - arr[i - 1] == k) {
                    ans++;
                }
            }
        } else {
            for (int r = index; r < arr.length; r++) {
                //1、将index上的字符与r上的字符互换。这样确保每个字符都有在index位置的情况
                swap(arr, index, r);
                //2、将剩余数组值从index+1开始全排列放到ans中
                ans = Math.max(ans, process1(arr, index + 1, k));
                //3、恢复现场。准备原index上的字符与r上的字符互换
                //如果不恢复现场，一定会出现重复遗漏现象
                swap(arr, index, r);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 时间复杂度O(N*logN)
    //贪心，窗口
    public static int maxPairNum2(int[] arr, int k) {
        int N = arr.length;
        if (k < 0 || arr == null || N <= 1) {
            return 0;
        }
        //现将数组排序
        Arrays.sort(arr);
        int ans = 0;
        int L = 0;
        int R = 0;
        //useR为true时，表示这个数已经凑过一组比赛了
        boolean[] used = new boolean[N];
        while (L < N && R < N) {
            if (L == R) {
                //窗口内只有一个数
               R++;
            } else if (used[L]) {
                //L上的数已经用过了
                L++;
            } else {
                int distance = arr[R] - arr[L];
                if (distance == k) {
                    ans++;
                    used[L++] = true;
                    used[R++] = true;
                } else if (distance < k) {
                    R++;
                } else {
                    //distance > k
                    L++;
                }
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int maxK = 5;
        int testTime = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * (maxLen + 1));
            int[] arr = randomArray(N, maxValue);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int k = (int) (Math.random() * (maxK + 1));
            int ans1 = maxPairNum1(arr1, k);
            int ans2 = maxPairNum2(arr2, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}

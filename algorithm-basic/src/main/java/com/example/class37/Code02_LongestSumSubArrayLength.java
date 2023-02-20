package com.example.class37;

import java.util.HashMap;

/**
 * @Date: 2023/2/17 20:08
 * 题目：给定一个整数组成的无序数组arr，值可能正、可能负、可能0，
 * 给定一个整数值K，找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的，返回其长度
 *
 */
public class Code02_LongestSumSubArrayLength {
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // key: 前缀和
        // value :key这个前缀和最早出现在value位置，即0~value上的前缀和是最早的
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1); // 防止以0开头合适的那些答案被错过，很重要！
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {  //以数组每个元素结尾的所有情况全部求出
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                //表中有sum-k这个累加和，就能知道往前推多远了
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                //表中没有这个累加和，就新增。表中有，啥也不做。因为表中存的是累加和的最早位置
                map.put(sum, i);
            }
        }
        return len;
    }

    // for test
    public static int right(int[] arr, int K) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, K)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    // for test
    public static boolean valid(int[] arr, int L, int R, int K) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == K;
    }

    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = maxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");

    }
}

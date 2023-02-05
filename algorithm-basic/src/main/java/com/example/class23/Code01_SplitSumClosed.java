package com.example.class23;

/**
 * @Date: 2023/2/5 13:52
 * 数组分割成累加和尽量相等的两个集合(个数随意)
 * <p>
 * 题目：给定一个正数数组arr，请把arr中所有的数分成两个集合。尽量让两个集合的累加和接近，返回最接近的情况下，较小集合的累加和
 */
public class Code01_SplitSumClosed {
    //1、暴力递归
    public static int right(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return process(arr, 0, sum / 2);
    }

    // arr[i...]可以自由选择，请返回累加和尽量接近rest，但不能超过rest的情况下，最接近的累加和是多少？
    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            //之前所有情况都讨论完了，没数了，累加和返回0
            return 0;
        }
        //1、不选当前数
        int p1 = process(arr, i + 1, rest);
        //2、选当前数。要先确保当前数可选，能选返回累加和，不能选返回0
        int p2 = 0;
        if (arr[i] <= rest) {
            p2 += arr[i] + process(arr, i + 1, rest - arr[i]);
        }
        //3、返回以上两种可能中最接近rest的，即较大的
        return Math.max(p1, p2);
    }

    //2、动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        //返回的累加和要 <= sum /2，先将sum除以2，然后建表
        sum /= 2;

        int N = arr.length;
        //dp[i][j]代表当前讨论第i个数，返回<= j累加和
        int[][] dp = new int[N + 1][sum + 1];
        //dp[N][...] = 0
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                //1、不选当前数
                int p1 = dp[i + 1][rest];
                //2、选当前数。要先确保当前数可选，能选返回累加和，不能选返回0
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 += arr[i] + dp[i + 1][rest - arr[i]];
                }
                //3、选择以上两种可能中最接近rest的，即较大的
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

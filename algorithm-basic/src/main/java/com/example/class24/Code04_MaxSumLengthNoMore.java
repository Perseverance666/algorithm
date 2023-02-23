package com.example.class24;

import java.util.LinkedList;

/**
 * @Date: 2023/2/22 15:01
 * 子数组长度不超过M的最大累加和
 * <p>
 * 题目：给定一个数组arr，和一个正数M，返回在arr的子数组在长度不超过M的情况下，最大的累加和
 */
public class Code04_MaxSumLengthNoMore {

    // O(N^2)的解法，暴力解，用作对数器
    public static int test(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int N = arr.length;
        int max = Integer.MIN_VALUE;
        for (int L = 0; L < N; L++) {
            int sum = 0;
            for (int R = L; R < N; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // O(N)的解法，最优解
    public static int maxSum(int[] arr, int M) {
        int N = arr.length;
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        //1、创建前缀和数组
        int[] sum = new int[N];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int len = Math.min(N, M); //子数组最大长度
        //2、创建双端队列，用来做滑动窗口。滑动窗口求每一部分的最大值，O(N)
        LinkedList<Integer> qMax = new LinkedList<>();
        //3、滑动窗口先放0~len-1上的前缀和
        int i = 0;
        for (; i < len; i++) {
            while (!qMax.isEmpty() && sum[qMax.peekLast()] <= sum[i]) {
                //确保双端队列左边一直是最大值
                qMax.pollLast();
            }
            qMax.addLast(i);
        }
        int max = sum[qMax.peekFirst()];
        //4、i=len，此时i指向窗口右边第一个数，开始移动滑动窗口，并求出每部分的最大值
        int L = 0;  //移动窗口后左边的值，一会前缀和要减去这部分
        for (; i < N; i++, L++) {
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();   //弹出不在滑动窗口内的值
            }
            while (!qMax.isEmpty() && sum[qMax.peekLast()] <= sum[i]) {
                //确保双端队列左边一直是最大值
                qMax.pollLast();
            }
            qMax.addLast(i);
            max = Math.max(max, sum[qMax.peekFirst()] - sum[L]);  //这里要注意减去L前面的前缀和，答案才是正确的
        }
        //5、L=N-len，此时L指向窗口左边第一个数,窗口内的数不够len个了
        for (; L < N - 1; L++) {
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();   //弹出不在滑动窗口内的值
            }
            max = Math.max(max, sum[qMax.peekFirst()] - sum[L]);
        }
        return max;
    }
//    public static int maxSum(int[] arr, int M) {
//        if (arr == null || arr.length == 0 || M < 1) {
//            return 0;
//        }
//        int N = arr.length;
//        int[] sum = new int[N];
//        sum[0] = arr[0];
//        for (int i = 1; i < N; i++) {
//            sum[i] = sum[i - 1] + arr[i];
//        }
//        LinkedList<Integer> qmax = new LinkedList<>();
//        int i = 0;
//        int end = Math.min(N, M);
//        for (; i < end; i++) {
//            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
//                qmax.pollLast();
//            }
//            qmax.add(i);
//        }
//        int max = sum[qmax.peekFirst()];
//        int L = 0;
//        for (; i < N; L++, i++) {
//            if (qmax.peekFirst() == L) {
//                qmax.pollFirst();
//            }
//            while (!qmax.isEmpty() && sum[qmax.peekLast()] <= sum[i]) {
//                qmax.pollLast();
//            }
//            qmax.add(i);
//            max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
//        }
//        for (; L < N - 1; L++) {
//            if (qmax.peekFirst() == L) {
//                qmax.pollFirst();
//            }
//            max = Math.max(max, sum[qmax.peekFirst()] - sum[L]);
//        }
//        return max;
//    }

    // 用作测试
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // 用作测试
    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = test(arr, M);
            int ans2 = maxSum(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}

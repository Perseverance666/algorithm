package com.example.class24;

import java.util.LinkedList;

/**
 * @Date: 2023/2/6 13:55
 * 最大值减最小值小于等于num的子数组数量
 * <p>
 * 题目：给定一个整型数组arr，和一个整数num。某个arr中的子数组sub，如果想达标，必须满足：sub中最大值 – sub中最小值 <= num，返回arr中达标子数组的数量
 */
public class Code02_AllLessNumSubArray {
    // 暴力的对数器方法
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        LinkedList<Integer> maxWindow = new LinkedList<>();  //记录滑动窗口内的最大值
        LinkedList<Integer> minWindow = new LinkedList<>();  //记录滑动窗口内的最小值
        int counts = 0;  //记录结果
        //R用来记录 以L为开始下标的子数组 的滑动窗口的右边界，R不会回退。[L,R)：[i,i)代表滑动窗口内没有值
        int R = 0;
        //1、依次求出以L为开始下标的子数组数量。一直到N-1，所有情况全部讨论完毕
        for (int L = 0; L < N; L++) {
            while (R < N) {       //R一越界就停止
                //2、初始化最大值双端队列
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                //3、初始化最小值双端队列
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                //4、若数组[L,R)内max - min > sum，就停止，去讨论[L+1,R)。
                // 若满足max - min <= sum，R++，去讨论[L,R+1)
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            //5、此时 以L为开始下标的子数组 满足条件的子数组数量为R-L个。
            counts += R - L;
            //6、准备讨论以L+1为下标的子数组。不过先要把两个双端队列过期的元素，即下标为L的元素先淘汰掉，不能让L的元素影响L+1的判断
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        //7、所有子数组全部讨论完，返回符合条件的数量
        return counts;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = num(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}

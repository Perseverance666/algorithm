package com.example.class25;

import java.util.Stack;

/**
 * @Date: 2023/2/7 13:49
 * 子数组最小乘积的最大值
 *
 * 题目：给定一个只包含正数的数组arr，arr中任何一个子数组sub，一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 *
 * 测试链接：https://leetcode.cn/problems/maximum-subarray-min-product/
 *
 * 思路：这个题的答案一定是 以原数组某一个值作为sub中的最小值，此时(sub累加和 )* (sub中的最小值)最大。
 * 这样，求原数组以0~len-1每一个位置的数作为sub最小值时的(sub累加和 )* (sub中的最小值)最大值，
 */
public class Code02_AllTimesMinToMax {
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        //1、先求前缀和数组，用来以后 求从i到j位置的累加和
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                //注意这里跟之前不同，这里是>=。如果栈顶元素=arr[i]，弹出并记录
                // 但是记录的信息可能是错的，不过没关系，最终原数组最后一个与栈顶元素值相等的信息一定是正确的，
                int j = stack.pop();
                //获取以栈顶元素作为sub中的最小值，此时(sub累加和 )* (sub中的最小值)，然后与之前的max作比较取较大的
                //若栈为空，j元素左边没有比它小的最近的数
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }

    // 本题可以在leetcode上找到原题
    // 测试链接 : https://leetcode.com/problems/maximum-subarray-min-product/
    // 注意测试题目数量大，要取模，但是思路和课上讲的是完全一样的
    // 注意溢出的处理即可，也就是用long类型来表示累加和
    // 还有优化就是，你可以用自己手写的数组栈，来替代系统实现的栈，也会快很多
    public static int maxSumMinProduct(int[] arr) {
        int size = arr.length;
        long[] sums = new long[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        long max = Long.MIN_VALUE;
        int[] stack = new int[size];
        int stackSize = 0;
        for (int i = 0; i < size; i++) {
            while (stackSize != 0 && arr[stack[stackSize - 1]] >= arr[i]) {
                int j = stack[--stackSize];
                max = Math.max(max,
                        (stackSize == 0 ? sums[i - 1] : (sums[i - 1] - sums[stack[stackSize - 1]])) * arr[j]);
            }
            stack[stackSize++] = i;
        }
        while (stackSize != 0) {
            int j = stack[--stackSize];
            max = Math.max(max,
                    (stackSize == 0 ? sums[size - 1] : (sums[size - 1] - sums[stack[stackSize - 1]])) * arr[j]);
        }
        return (int) (max % 1000000007);
    }
}

package com.example.class01;

import java.util.Arrays;

/**
 * @Date: 2022/12/28 20:11
 * 二分法
 * 在一个有序数组中，找到 >= 某个数最左侧的位置 (<= 最右侧位置 问题类似)
 */
public class Code05_BSNearLeft {
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;     //记录最左满足的数
        while(L <= R){
            mid = L + ((R - L) >> 1);
            if(arr[mid] >= value){
                //若当前数 >= 指定数，记录当前位置，并将R移到mid左边
                index = mid;
                R = mid - 1;
            }else{
                //若当前数 < 指定数，不记录当前位置，并将L移到mid右边
                L = mid + 1;

            }
        }
        return index;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                return i;
            }
        }
        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != nearestIndex(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}

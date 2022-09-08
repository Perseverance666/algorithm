package com.example.day03;

import java.util.Arrays;

/**
 * @Date: 2022/9/8 19:08
 *
 * 二分法，有序数组中找到 >= num 最左的位置
 */
public class Primary09_BSNearLeft {

    //在arr上，找到满足 >= num的最左位置
    public static int nearestIndex(int[] arr,int num){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;     //记录满足条件的下标，-1为没找到
        while (L <= R){
            int mid = (L + R) / 2;
            if(arr[mid] >= num){
                index = mid;
                R = mid -1;
            }else{ //arr[mid] < num
                L = mid + 1;
            }
        }
        return index;
    }

    //for test
    public static int test(int[] arr,int num){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= num){
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

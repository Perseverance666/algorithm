package com.example.class05;

import java.util.Arrays;

/**
 * @Date: 2023/3/2 15:57
 * 单边循环快排 和 双边循环快排
 */
public class Code04_QuickSort {
    //1、单边循环快排
    public static void quickSort1(int[] arr){
        process1(arr,0,arr.length-1);
    }
    public static void process1(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int p = partition1(arr, L, R);
        process1(arr,L,p-1);
        process1(arr,p+1,R);
    }
    //单边循环快排，选最右边元素为基准点pivot
    public static int partition1(int[] arr,int L,int R){
        int pivot = arr[R];
        int i = L;  //i往左都是 <= pivot的数
        for(int j = i; j < R; j++){  //j负责找到 <=pivot的，一旦找到就和i交换
            if(arr[j] <= pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,R);
        return i;
    }

    //2、双边循环快排
    public static void quickSort2(int[] arr) {
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int p = partition2(arr, L, R);
        process2(arr, L, p - 1);
        process2(arr, p + 1, R);
    }

    //双边循环快排，选最左边元素为基准点pivot
    public static int partition2(int[] arr, int L, int R) {
        int pivot = arr[L];
        int i = L;
        int j = R;
        while (i < j) {
            //先让j从右往左找 <= pivot的
            while (i < j && arr[j] > pivot) {     //注意:i<j，避免交换错误
                j--;
            }
            //再让i从左往右找 > pivot的
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            swap(arr, i, j);
        }
        //此时i == j
        swap(arr, L, i);
        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 对比两个数组（用于测试）
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

    // 打印数组（用于测试）
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 跑大样本随机测试（对数器）
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            Arrays.sort(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr1, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }
}

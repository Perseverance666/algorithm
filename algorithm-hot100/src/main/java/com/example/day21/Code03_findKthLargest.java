package com.example.day21;

/**
 * @Date: 2023/5/16 15:35
 * 215. 数组中的第K个最大元素
 * <p>
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 */
public class Code03_findKthLargest {
    public int findKthLargest(int[] arr, int k) {
        return quickSort(arr, 0, arr.length - 1, arr.length - k);
    }

    public int quickSort(int[] arr, int L, int R, int index) {
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int p = partition(arr, L, R);
        if (p == index) {
            return arr[p];
        } else {
            return p > index ? quickSort(arr, L, p - 1, index) : quickSort(arr, p + 1, R, index);
        }
    }
    //单边循环快排，选最右边元素为基准点pivot
    public int partition(int[] arr, int L, int R) {
        int pivot = arr[R];
        int i = L;  //i往左都是 <= pivot的数
        for (int j = i; j < R; j++) {
            if (arr[j] <= pivot) {  //j负责找到 <=pivot的，一旦找到就和i交换
                swap(arr, j, i);
                i++;
            }
        }
        swap(arr, R, i);
        return i;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

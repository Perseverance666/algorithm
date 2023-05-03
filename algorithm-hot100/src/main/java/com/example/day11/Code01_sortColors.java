package com.example.day11;

/**
 * @Date: 2023/4/14 18:15
 * 75. 颜色分类
 * <p>
 * https://leetcode.cn/problems/sort-colors/
 */
public class Code01_sortColors {
    public static void sortColors(int[] arr) {
        int L = 0;                  //[0,L)是0    [L,R]是1   (R,len - 1]是2
        int R = arr.length - 1;
        int i = 0;
        while (i <= R) {
            if (arr[i] == 0) {
                swap(arr, i, L);
                L++;
                i++;
            } else if (arr[i] == 1) {
                i++;
            } else if (arr[i] == 2) {
                swap(arr, i, R);
                R--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        sortColors(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

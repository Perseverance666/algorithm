package com.example.day17_排序;

/**
 * @Date: 2023/3/18 19:29
 * 剑指 Offer 40. 最小的k个数
 */
public class Code01_GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        quickSort(arr,0,arr.length);
        for(int i = 0; i < k; i++){
            res[i] = arr[i];
        }
        return res;
    }
    public static void quickSort(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        int p = partition(arr,L,R);
        quickSort(arr,L,p-1);
        quickSort(arr,p,R);
    }
    public static int partition(int[] arr,int L,int R){
        int pivot = arr[R];
        int i = L;
        for(int j = i; j < R; j++){
            if(arr[j] <= pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,R);
        return i;
    }
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

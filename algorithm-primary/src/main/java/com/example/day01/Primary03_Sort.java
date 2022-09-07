package com.example.day01;

/**
 * @Date: 2022/9/6 19:33
 *
 * 选择排序、冒泡排序(从小到大)
 */
public class Primary03_Sort {
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //选择排序
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        //0~n-1 1~n-1  2~n-1 ... i~n-1
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr,i,minValueIndex);
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }

        //0~n-1  0~n-2 ...
        for(int end = arr.length - 1; end > 0; end--){
            //0 1    1 2    .... end-1 end
            for(int i = 1; i <= end; i++){
                if(arr[i] < arr[i-1]){
                    swap(arr,i,i-1);
                }
            }
        }
    }

    //插入排序
    public static void insertSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }

        //0~1 0~2 0~3 ... 0~n-1
        for(int end = 1; end < arr.length; end++){
            for(int curNumIndex = end; curNumIndex-1 >= 0  && arr[curNumIndex-1] > arr[curNumIndex]; curNumIndex--){
                swap(arr,curNumIndex-1,curNumIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7,3,2,6,1,5,2,4};
        printArray(arr);
//        selectSort(arr);
//        bubbleSort(arr);
        insertSort(arr);
        printArray(arr);

    }
}

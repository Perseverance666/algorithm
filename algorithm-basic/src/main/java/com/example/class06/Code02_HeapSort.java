package com.example.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Date: 2023/1/6 19:46
 * 堆排序
 */
public class Code02_HeapSort {
    // 堆排序额外空间复杂度O(1)
//    public static void heapSort(int[] arr) {
//        if (arr == null || arr.length < 2) {
//            return;
//        }
//        // O(N*logN)
////		for (int i = 0; i < arr.length; i++) { // O(N)
////			heapInsert(arr, i); // O(logN)
////		}
//        // O(N)
//        for (int i = arr.length - 1; i >= 0; i--) {
//            heapify(arr, i, arr.length);
//        }
//        int heapSize = arr.length;
//        swap(arr, 0, --heapSize);
//        // O(N*logN)
//        while (heapSize > 0) { // O(N)
//            heapify(arr, 0, heapSize); // O(logN)
//            swap(arr, 0, --heapSize); // O(1)
//        }
//    }
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length <= 1){
            return;
        }

//        //1.1、先把数组从0开始依次heapInsert，最后变成大根堆
//        // O(N*logN)
//        for(int i = 0; i < arr.length; i++){
//            heapInsert(arr,i);
//        }
        //或者1.2、从最底层开始，把这一层都变成大根堆，然后再处理上一层，依次变成大根堆
        // O(N)
        for(int i = arr.length-1; i >=0 ;i--){
            heapify(arr,i,arr.length);
        }

        //2、然后把第一个元素也就是最大的元素与最后一个元素交换，同时heapSize有效值减一，逻辑删除最大值，最大值排好序了
        int heapSize = arr.length;      //heapSize以内的数满足大根堆
        swap(arr,0,heapSize - 1);
        heapSize--;

        while(heapSize > 0){
            //3、对新换上来的根节点向下heapify
            heapify(arr,0,heapSize);
            //4、heapify后又变成大根堆，再把第一个元素和最后一个元素交换，这样又一个第二大的排好序了，直到heapSize变为0
            swap(arr,0,heapSize - 1);
            heapSize--;
        }
    }

    // arr[index]刚来的数，往上移动
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // arr[index]位置的数，能否往下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大的孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
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

    // for test
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

    // for test
    public static void main(String[] args) {

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

}

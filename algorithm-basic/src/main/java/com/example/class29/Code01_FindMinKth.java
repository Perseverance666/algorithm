package com.example.class29;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date: 2023/2/11 20:22
 * 无序数组中求第K小的数
 *
 * 题目：在无序数组中求第K小的数
 */
public class Code01_FindMinKth {
    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    //1、改写快排，递归形式，O(N)
     public static int minKth2(int[] arr, int k) {   // k >= 1，第k小，从k=1开始
        //在0~arr.length-1上，假如排序的话，找下标为k-1的值，即求无序数组中第K小的数
        return process2(arr, 0, arr.length - 1, k - 1);
    }
    // 快排是左右两侧都进递归，而这里就有一侧进入递归，O(N)。因为递归只走一侧，可以改成迭代
    // 这个方法代表 arr[L..R]  范围上，如果排序的话(不是真的去排序)，找 位于index的数
    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) { // L = =R ==INDEX
            //数组中就剩一个数了
            return arr[L];
        }
        //1、在arr[L~R]上等概率随机出一个数
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        //2、将原数组分拨了，L~range[0]-1是<pivot的数，range[0]~range[1]是=pivot的数，range[1]+1~R是>pivot的数。（注：左右两侧并没有排序）
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            //3.1、index在中间区域，随机的这个数就是所求
            return pivot;
        } else if (index < range[0]) {
            //3.2、index在左侧，递归调用L~range[0]-1
            return process2(arr, L, range[0] - 1, index);
        } else {
            //3.3、index在右侧，递归调用range[1]+1~R
            return process2(arr, range[1] + 1, R, index);
        }
    }
    public static int[] partition(int[] arr, int L, int R, int pivot) {
        //arr[0~less]是<pivot  arr[less+1 ~ more-1]是=pivot，arr[more~R]是>pivot
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }
    public static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    //2、利用bfprt算法，O(N)。(了解即可，平常不用，就用改写快排)
    // bfprt算法就是把改写快排中1、在arr[L~R]上等概率随机出一个数 改成 讲究的拿出一个数
    public static int minKth3(int[] arr, int k) {
        return bfprt(arr, 0, arr.length - 1, k - 1);
    }
    // arr[L..R]  如果排序的话，位于index位置的数，是什么，返回
    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        //1、讲究的拿出一个数
        // L...R  每五个数一组
        // 每一个小组内部排好序
        // 小组的中位数组成新数组
        // 这个新数组的中位数返回
        int pivot = medianOfMedians(arr, L, R);
        //2、将原数组分拨了，L~range[0]-1是<pivot的数，range[0]~range[1]是=pivot的数，range[1]+1~R是>pivot的数。（注：左右两侧并没有排序）
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            //3.1、index在中间区域，随机的这个数就是所求
            return pivot;
        } else if (index < range[0]) {
            //3.2、index在左侧，递归调用L~range[0]-1
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            //3.3、index在右侧，递归调用range[1]+1~R
            return bfprt(arr, range[1] + 1, R, index);
        }
    }
    //讲究的拿出一个数
    public static int medianOfMedians(int[] arr, int L, int R) {
        // 1、arr[L...R]  五个数一组
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            //每个小组的第一个数
            int teamFirst = L + team * 5;
            // L ... L + 4
            // L +5 ... L +9
            // L +10....L+14
            // 2、每个小组内部排序
            // 3、每个小组中位数领出来，组成marr
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        // 4、marr中，找到中位数
        // marr(0, marr.len - 1,  mArr.length / 2 )
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    //获取有序数组的中位数
    public static int getMedian(int[] arr, int L, int R) {
        //每个小组内部进行插入排序
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

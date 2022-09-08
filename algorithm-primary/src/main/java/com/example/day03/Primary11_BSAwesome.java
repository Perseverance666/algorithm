package com.example.day03;

/**
 * @Date: 2022/9/8 19:37
 * <p>
 * 二分法，局部最小值问题（找一个局部最小即可）  条件：arr数组整体无序， 相邻的数不相等
 */
public class Primary11_BSAwesome {

    //找到一个局部最小
    public static int oneMinIndex(int[] arr) {
        int len = arr.length;
        if (arr == null || len == 0) {
            return -1;
        }
        if (len == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[len - 1] < arr[len - 2]) {
            return len - 1;
        }

        int L = 0;
        int R = len - 1;
        while (L < R - 1) {     //若L<=R,可能会出现越界问题，故当L到R只有两个数时，不再循环，即防止mid-1或mid+1越界
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {   //mid比左右都下，取mid
                return mid;
            } else {
                if (arr[mid - 1] < arr[mid]) {         //mid左边更小，砍掉右边
                    R = mid - 1;
                } else {
                    L = mid + 1;                //mid右边更小,砍掉左边
                }
            }
        }
        return arr[L] < arr[R] ? L : R;       //特殊情况，最后只剩俩数
    }

    //生成随机数组，且相邻数不相等
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);   //先生成第一个数
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);                //确保相邻数不相等
            }
        }
        return arr;

    }

    //for test
    public static boolean check(int[] arr,int minIndex){
        if(arr.length == 0){
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        //若左边没超界，正常比较，否则返回true，不干扰检验
        boolean leftCheck = left >= 0 ? arr[minIndex] < arr[left] : true;
        //若右边没超界，正常比较，否则返回true，不干扰检验
        boolean rightCheck = right <= arr.length - 1 ? arr[minIndex] < arr[right] : true;
        return leftCheck && rightCheck;    //左右检验都合格后，返回true

    }
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int index = oneMinIndex(arr);
            if (!check(arr, index)) {
                printArray(arr);
                System.out.println(index);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

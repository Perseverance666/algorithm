package com.example.class08;

import java.util.Arrays;

/**
 * @Date: 2023/1/8 20:45
 * 桶排序 --> 基数排序
 */
public class Code04_RadixSort {
    // 只适用于非负数
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        radixSort(arr, 0, arr.length - 1, maxbits(arr));
        radixSort(arr, maxbits(arr));
    }

    //求最大数十进制的最高位数是多少
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    // 基数排序, 十进制的最大位数digit
    public static void radixSort(int[] arr, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[arr.length];
        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次。  1->个位  2->十位...
            //现在count[i]表示d位数字=i的一共有多少个
            int[] count = new int[radix]; // 规定count数组大小是10，10个空间，
            for (i = 0; i < arr.length; i++) {
                //1、获取数组元素第d位上的数，添加到count数组。现在count[i]表示d位数字=i的一共有多少个
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++) {
                //2、现在count[i]表示d位数字<=i的一共有多少个，后面就这样用
                count[i] = count[i] + count[i - 1];
            }
            for (i = arr.length - 1; i >= 0; i--) {
                //3、从右往左遍历获取d位上的数，count[j]表示d位<=j的个数，所以新数组0~count[j]-1上放的都是d位<=j的数，将它放在最右边就是count[j]-1上，核心就是从右往左放
                //只减count数组j下标的数量即可，>j下标的数量不用减，不耽误后面计算
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = 0; i < arr.length; i++) {
                //将d位排好序的数组更新到arr中。省了10个桶
                arr[i] = help[i];
            }
        }
    }
//    // arr[L..R]排序  ,  最大值的十进制位数digit
//    public static void radixSort(int[] arr, int L, int R, int digit) {
//        final int radix = 10;
//        int i = 0, j = 0;
//        // 有多少个数准备多少个辅助空间
//        int[] help = new int[R - L + 1];
//        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次。  1->个位  2->十位...
//            // count[0] 当前位(d位)是0的数字有多少个
//            // count[1] 当前位(d位)是(0和1)的数字有多少个
//            // count[i] 当前位(d位)是(0~i)的数字有多少个
//            int[] count = new int[radix]; // 规定count数组大小是10，10个空间，
//            for (i = L; i <= R; i++) {
//                //1、获取数组元素第d位上的数，添加到count数组。现在count[i]表示d位数字=i的一共有多少个
//                j = getDigit(arr[i], d);
//                count[j]++;
//            }
//            for (i = 1; i < radix; i++) {
//                //2、现在count[i]表示d位数字<=i的一共有多少个
//                count[i] = count[i] + count[i - 1];
//            }
//            for (i = R; i >= L; i--) {
//                //3、从右往左遍历获取d位上的数，count[j]表示d位<=j的个数，所以新数组0~count[j]-1上放的都是d位<=j的数，将它放在最右边就是count[j]-1上，核心就是从右往左放
//                //只减count数组j下标的数量即可，>j下标的数量不用减，不耽误后面计算
//                j = getDigit(arr[i], d);
//                help[count[j] - 1] = arr[i];
//                count[j]--;
//            }
//            for (i = L, j = 0; i <= R; i++, j++) {
//                //将d位排好序的数组更新到arr中。省了10个桶
//                arr[i] = help[j];
//            }
//        }
//    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}

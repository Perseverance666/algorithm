package com.example.class01;

/**
 * @Date: 2022/12/28 20:58
 * 二分法
 * 局部最小值问题，任意相邻的两个数不等，找一个局部最小即可
 */
public class Code06_BSAwesome {
    public static int getLessIndex(int[] arr) {
        if(arr == null || arr.length <= 0){
            return -1; //不存在
        }
        if(arr.length == 1 || arr[0] < arr[1]){
            return 0; //0位置就是局部最小
        }
        if(arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;  //arr.length -1 位置就是局部最小
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while(L < R){
            mid = L + ((R - L) >> 1);
            if(arr[mid] > arr[mid -1]){
                //说明局部最小在左边
                R = mid - 1;
            }else if(arr[mid] > arr[mid + 1]){
                //说明局部最小在右边
                L = mid + 1;
            }else{
                //arr[mid] < arr[mid -1] && arr[mid] < arr[mid + 1]
                return mid;
            }
        }
        //此时L = R,这个数一定是局部最小
        return L;
    }

    // 验证得到的结果，是不是局部最小
    public static boolean isRight(int[] arr, int index) {
        if (arr.length <= 1) {
            return true;
        }
        if (index == 0) {
            return arr[index] < arr[index + 1];
        }
        if (index == arr.length - 1) {
            return arr[index] < arr[index - 1];
        }
        return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
    }

    // 为了测试
    // 生成相邻不相等的数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    // 为了测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int ans = getLessIndex(arr);
            if (!isRight(arr, ans)) {
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

}

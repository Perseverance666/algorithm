package com.example.day30_分治算法;

/**
 * @Date: 2023/3/31 14:21
 * 剑指 Offer 51. 数组中的逆序对
 * <p>
 * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 */
public class Code02_ReversePairs {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    //将arr[L~R]进行升序排序，并且返回逆序对数量
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            //只有一个数，没有逆序对
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return process(arr, L, M) + process(arr, M + 1, R) + merge(arr, L, M, R);
    }

    //arr[L~M]和arr[M+1~R]上升序，返回两部分合并后的逆序对数量，合并有数组还要升序
    public static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];  //装最后合并后的数组
        int index = help.length - 1;   //数组从后往前装
        //两个指针从后往前开始
        int p1 = M;
        int p2 = R;
        int res = 0;  //合并途中产生的逆序对数量
        while (p1 >= L && p2 > M) {
            res += arr[p1] > arr[p2] ? p2 - M : 0;
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[index--] = arr[p1--];
        }
        while (p2 > M) {
            help[index--] = arr[p2--];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }
}

package com.example.day05;

/**
 * @Date: 2022/9/10 19:06
 *
 *  如何不用额外变量交换两个数
 */

public class Basic01_Swap {
    public static void main(String[] args) {
        int a = 16;
        int b = 603;
        System.out.println(a);
        System.out.println(b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);

        int[] arr = {3,1,100};
        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 0);

        //若交换相同位置，该位置为出错，变成0
        System.out.println(arr[0]);
        System.out.println(arr[2]);
    }


    //不推荐用这个交换，知道就好
    public static void swap (int[] arr, int i, int j) {
        // arr[0] = arr[0] ^ arr[0];
        arr[i]  = arr[i] ^ arr[j];
        arr[j]  = arr[i] ^ arr[j];
        arr[i]  = arr[i] ^ arr[j];
    }
}

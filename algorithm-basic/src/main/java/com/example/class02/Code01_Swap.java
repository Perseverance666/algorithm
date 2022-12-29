package com.example.class02;

/**
 * @Date: 2022/12/29 19:16
 * 如何不用额外变量交换两个数
 */
public class Code01_Swap {

    //异或运算，不用额外变量交换两个数
    public static void swap (int[] arr, int i, int j) {
        arr[i]  = arr[i] ^ arr[j];
        arr[j]  = arr[i] ^ arr[j];
        arr[i]  = arr[i] ^ arr[j];
    }

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
        int i = 0;
        int j = 1;

        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

        System.out.println(arr[i] + " , " + arr[j]);

        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 2);

        System.out.println(arr[0]);
        System.out.println(arr[2]);
    }

}

package com.example.class02;

/**
 * @Date: 2022/12/29 20:28
 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class Code02_EvenTimesOddTimes {
    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for(int i = 0; i < arr.length; i++){
            eor ^= arr[i];
        }
        System.out.println(eor);  //eor = a ^ b;   a != b  eor != 0

        //取eor最右边的1最为条件，然后分成两组
        int rightOne = eor & (-eor);   //eor & (~eor + 1) 等同于 eor & (-eor) ，可以得到最右边的1，例如：eor = 1011000 rightOne = 0001000
        int eor2 = 0;
        for(int i = 0; i < arr.length; i++){
            //这次只异或那一位是1的
            if((arr[i] & rightOne) == rightOne){
                eor2 ^= arr[i];
            }

        }

        int a = eor2; //eor2的异或结果就是其中一个数
        int b = eor ^ a;   //b = eor ^ a = a ^ b ^ a;
        System.out.println(a + " " + b);

    }


    public static int bit1counts(int N) {
        int count = 0;

        //   011011010000
        //   000000010000     1

        //   011011000000
        while(N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
            // N -= rightOne
        }

        return count;

    }


    public static void main(String[] args) {

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);

    }
}

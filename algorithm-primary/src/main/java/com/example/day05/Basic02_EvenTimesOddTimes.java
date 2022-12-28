package com.example.day05;

/**
 * @Date: 2022/9/10 20:59
 *
 *  1. 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这些数
 *  2. 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这些数
 *
 *  怎么把一个int类型的数，提取出最右侧的1来   (N & (~N)+1)
 */
public class Basic02_EvenTimesOddTimes {
    // arr中，只有一种数，出现奇数次，其他数都出现了偶数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);       //只用eor一直亦或，因为出现偶数次的最后都亦或成0，只剩最后一个出现奇数次的，即为所求
    }

    // arr中，有两种数，出现奇数次，其他数都出现了偶数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor = a ^ b != 0，eor必然有一个位置上是一

        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor); // 提取出最右的1  -eor = ~eor + 1

        //该位置rightOne上，a与b不同，然后以此分为两组，用新的变量onlyOne来亦或
        int onlyOne = 0; // eor'
        for (int i = 0 ; i < arr.length;i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {    //以最右边的1来分成两组,例：第5位是1 和 第5位不是1的 分成两组
                onlyOne ^= arr[i];      //最后onlyOne不是等于a，就是等于b，其他数为偶数次亦或后为0
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne)); //eor ^ onlyOne是另一个数, eor = a ^ b,eor ^ a = b
    }


    //输出2进制中1的个数
    public static int bit1counts(int N) {
        int count = 0;

        //   011011010000
        //   000000010000     1

        //   011011000000

        while(N != 0) {
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;      //将最右的1抹掉
        }

        return count;

    }

    public static void main(String[] args) {
        int a = 5;
        int b = 7;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);

    }
}

package com.example.class35;

/**
 * @Date: 2023/2/16 13:52
 * 验证某数是否能表示成若干连续正数和
 *
 * 题目：定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如，5=2+3，5就是这样的数；12=3+4+5，12就是这样的数。2=1+1，2不是这样的数，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 */
public class Code03_MSumToN {
    //1、暴力解
    public static boolean isMSum1(int num) {
        for (int start = 1; start <= num; start++) {
            //num第一个加数从1开始试，把所有可能都试一遍，不行的话在从2开始试，以此类推
            int sum = start;
            for (int j = start + 1; j <= num; j++) {
                //从两个数开始试(1+2)，不行的话1+2+3，以此类推，直到超出num，start换2开始
                if (sum + j > num) {
                    //超出num，换start
                    break;
                }
                if (sum + j == num) {
                    //满足条件，返回true
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    //2、根据对数器找规律
    public static boolean isMSum2(int num) {
        //只要num是2的倍数，即num的二进制表示只有一个1，就返回false
        //提取num最右侧的1,num减去后，看是否是0，是0，num就是2的倍数，返回false
		return num - (num & (~num + 1)) != 0;  //num & (~num + 1) 也可写成 num & (-num)
    }

    public static void main(String[] args) {
        for (int num = 1; num < 200; num++) {
            System.out.println(num + " : " + isMSum1(num));
        }
        System.out.println("test begin");
        for (int num = 1; num < 5000; num++) {
            if (isMSum1(num) != isMSum2(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");

    }
}

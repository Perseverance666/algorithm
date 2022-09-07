package com.example;

import org.junit.Test;

/**
 * @Date: 2022/9/7 20:22
 */
public class test05_Random {
    @Test
    public void testRandom(){
        System.out.println("测试开始");

        // Math.random() -> double -> [0,1)
        System.out.println("= Math.random()等概率返回[0,1)之间的数 =");
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.75) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);


        System.out.println("= 等概率返回[0,8)之间的数 =");
        // [0,1) -> [0,8)
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println((double) 5 / (double) 8);


        System.out.println("= 等概率返回[0,8]之间的整数 =");
        // [0,K) -> [0,8]
        int K = 9;
        int[] counts = new int[9];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) (Math.random() * K); // [0,K-1]
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }


        System.out.println("= x平方的概率 =");
        count = 0;
        double x = 0.12;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));
    }


    // 返回[0,1)的一个小数
    // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x调整成x平方
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }
}

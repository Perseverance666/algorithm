package com.example.day01;

/**
 * @Date: 2022/9/6 19:01
 *
 *  给定一个参数N，返回：1! + 2! + 3! + ... = N! 的结果
 */
public class Primary02_SumOfFactorial {

    public static long func(int N){
        int sum = 0;
        int num = 1;
        for(int i = 1; i <= N; i++){
            num *= i;
            sum += num;
        }
        return sum;
    }
    public static void main(String[] args) {
        int N = 10;
        System.out.println(func(N));
    }
}

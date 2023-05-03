package com.example.demo01;

import java.util.Scanner;

/**
 * @Date: 2023/4/22 16:37
 * ZJ16 数列的和
 */
public class Main06 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();
            double res = func(n,m);
            System.out.printf("%.2f\n",res);
        }
    }

    public static double func(int n, int m){
        double res = 0;
        double temp = n;
        for(int i = 0; i < m; i++){
            res += temp;
            temp = Math.sqrt(temp);
        }
        return res;
    }
}

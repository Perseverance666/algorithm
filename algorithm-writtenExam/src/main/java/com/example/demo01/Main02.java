package com.example.demo01;

import java.util.Scanner;

/**
 * @Date: 2023/4/21 16:22
 * ZJ19 万万没想到之抓捕孔连顺
 */
public class Main02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int D = in.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(func(arr,D));

    }

    public static long func(int[] arr,int D){
        int len = arr.length;
        if(len < 3){
            return 0;
        }
        long res = 0L;
        int L = 0;
        for(int R = 2; R < len; R++){
            while(arr[R] - arr[L] > D){
                L++;
            }
            //固定R，然后从L ~ R-1中挑两个，这3个数凑成一组
            long n = R - L;   //L ~ R-1有n个数
            res += n * (n - 1)/2;
        }
        return res % 99997867;
    }
}

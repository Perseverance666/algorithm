package com.example.demo02;

import java.util.Scanner;

/**
 * @Date: 2023/4/22 19:01
 */
public class Main01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int L = 0;
        int minL = 0;
        int R = n - 1;
        int minR = n - 1;
        int sumL = arr[L];
        int sumR = arr[R];
        int abs = Math.abs(sumR - sumL);
        while(L < R){
            if(abs == 0){
                break;
            }else if(sumR > sumL){
                L++;
                if(L == R){
                    break;
                }
                sumL += arr[L];
                int abs2 = Math.abs(sumR - sumL);
                if(abs2 > abs){
                    continue;
                }else{
                    abs = abs2;
                    minL = L;
                }
            }else if(sumR < sumL){
                R--;
                if(L == R){
                    break;
                }
                sumR += arr[R];
                int abs2 = Math.abs(sumR - sumL);
                if(abs2 > abs){
                    continue;
                }else{
                    abs = abs2;
                    minR = R;
                }
            }
        }
        System.out.println(abs + " " + (minL+1) + " " + (minR+1));
    }
}

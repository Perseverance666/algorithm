package com.example.demo01;

import java.util.Scanner;

/**
 * @Date: 2023/4/22 16:38
 * ZJ17 水仙花数
 */
public class Main07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            int r = 0;
            for(int i = left; i <= right; i++){
                if(func(i)){
                    r++;
                    System.out.print(i + " ");
                }
            }
            if(r == 0){
                System.out.println("no");
                continue;
            }
            System.out.println();
        }
    }
    //检验n这个数是否是水仙花数
    public static boolean func(int n){
        int temp = n;
        int num = 0;
        while(temp != 0){
            int ge = temp % 10;
            num += (int)Math.pow(ge,3);
            temp /= 10;
        }
        return num == n;
    }
//    public static boolean func(int n){
//        int bai = n / 100;
//        int shi = (n - bai * 100) / 10;
//        int ge = n % 10;
//        int num = (int)(Math.pow(bai,3) + Math.pow(shi,3) + Math.pow(ge,3));
//        return num == n;
//    }
}

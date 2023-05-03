package com.example.demo02;

import java.util.Scanner;

/**
 * @Date: 2023/4/22 20:00
 */
public class Main02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] w = new int[n];
        int[] s = new int[n];
        for(int i = 0; i < n; i++){
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
        System.out.println(func(w,s,n));
    }

    public static int func(int[] w,int[] s,int n){
//         int max = 0;
//         for(int i = 0; i < n; i++){
//             max = Math.max(max,dfs(w,s,n,i,0));
//         }
//         return max;
        return dfs(w,s,n,0,0);
    }

    public static int dfs(int[] w,int[] s,int n,int index,int sum){
        if(index >= n){
            return sum;
        }
        //选
        int p1 = dfs(w,s,n,index + 1 + s[index],sum + w[index]);
        //不选
        int p2 = dfs(w,s,n,index + 1,sum);
        return Math.max(p1,p2);
    }
}

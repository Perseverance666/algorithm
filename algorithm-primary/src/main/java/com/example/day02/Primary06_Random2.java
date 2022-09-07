package com.example.day02;

/**
 * @Date: 2022/9/7 20:44
 *
 * 01不等概率随机 到 01等概率随机   0的概率为p，1的概率为1-p
 *
 * 00 不要    11 不要     01 概率为p(1-p) 要   10 概率为p(1-p) 要   做到01等概率
 */
public class Primary06_Random2 {
    public static void main(String[] args) {
        int count =0;
        int testTimes = 10000000;

        System.out.println("= 等概率返回0,1 =");
        count = 0;
        for(int i = 0; i < testTimes; i++){
            if(g() == 0){
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
    }

    //0的概率0.84   1的概率0.16
    public static int f(){
        return Math.random() < 0.84 ? 0 : 1;
    }

    //等概率返回0,1
    public static int g(){
        int ans = 0;
        do {
            ans = f();
        }while(ans == f());   //01 和 10 才返回
        return ans;
    }

}

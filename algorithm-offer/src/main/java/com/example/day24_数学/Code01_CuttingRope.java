package com.example.day24_数学;

/**
 * @Date: 2023/3/26 18:32
 * 剑指 Offer 14- I. 剪绳子
 * <p>
 * https://leetcode.cn/problems/jian-sheng-zi-lcof
 */
public class Code01_CuttingRope {
    //贪心
    public int cuttingRope(int n) {
        if (n <= 3) {
            //n = 0或1 : 没法切 返回0。这个题从n = 2开始
            //n = 3 : 1+2   ->  1*2
            //n = 2 : 1+1   ->  1*1
            return n - 1;
        }
        //贪心策略：按3进行拆分，即拆出来的数要尽可能有很多的3，这样得到的乘积最大
        int a = n / 3;
        int b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        } else if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        } else { //b == 2
            return (int) Math.pow(3, a) * 2;
        }
    }

//    //动态规划
//    public int cuttingRope(int n) {
//        //dp[i]表示将i拆分成至少两个数的和之后，这些数的最大乘积
//        int[] dp = new int[n+1];
//        //dp[0] = dp[1] = 0
//        for(int i = 2; i <= n; i++){
//            int curMax = 0;
//            for(int j = 1; j < i; j++){
//                //1、将i拆分成j和i-j两组，且i-j不再拆分
//                int p1 = j * (i - j);
//                //2、将i拆分成j和i-j两组，然后i-j继续拆分
//                int p2 = j * dp[i - j];
//                //3、绳子第一段砍成j后的最大乘积
//                int curMaxByJ = Math.max(p1,p2);
//                //4、通过比较得到i的最大乘积
//                curMax = Math.max(curMax,curMaxByJ);
//            }
//            dp[i] = curMax;
//        }
//        return dp[n];
//    }
}

package com.example.class22;

/**
 * @Date: 2023/2/3 22:00
 * 英雄砍怪兽
 * <p>
 * 题目：给定3个参数，N，M，K，怪兽有N滴血，等着英雄来砍自己，英雄每一次打击，
 * 都会让怪兽流失[0~M]的血量到底流失多少？每一次在[0~M]上等概率的获得一个值，
 * 求K次打击之后，英雄把怪兽砍死的概率。
 */
public class Code01_KillMonster {
    //1、暴力递归
    public static double right(int N, int M, int K) {
        if (N < 1 || K < 1 || M < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        long kill = process(K, M, N);
        return kill / all;
    }

    /**
     * @param times 还有times次可以砍
     * @param M     每次的伤害在[0~M]范围上
     * @param hp    怪兽还剩hp点血
     * @return 返回砍死的情况数
     */
    public static long process(int times, int M, int hp) {
        if (times == 0) {
            //都砍完了
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            //怪兽没血了，但是还可以砍
            return (long) Math.pow(M + 1, times);
        }
        //怪兽有血，并且还可以砍
        int ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    //2、动态规划
    public static double dp(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        double all = Math.pow(M + 1, K);
        //dp[i][j]代表怪兽还有j滴血，还有i次可以砍，这个条件下砍死怪兽次数
        long[][] dp = new long[K + 1][N + 1];
        //1、怪兽没血(血为负就不列表里了)，并且砍的次数都用完了
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            //2、怪兽没血(血为负就不列表里了),但是砍得次数还有
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
//                //for循环遍历
//                long ways = 0;
//                for (int i = 0; i <= M; i++) {
//                    if (hp - i > 0) {
//                        //3、如果砍完当前之后怪兽还有血
//                        ways += dp[times - 1][hp - i];
//                    } else {
//                        ways += (long) Math.pow(M + 1, times - 1);
//                    }
//                }
//                dp[times][hp] = ways;
                //通过观察
                if(hp - M - 1 > 0){
                    dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp] - dp[times - 1][hp - M - 1];
                }else {
                    dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp] - (long) Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return kill / all;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

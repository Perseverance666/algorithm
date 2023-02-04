package com.example.class22;

/**
 * @Date: 2023/2/3 22:00
 * 换钱的最少货币数
 * <p>
 * 题目：arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim，每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Code02_MinCoinsNoLimit {
    //1、暴力递归
    public static int minCoins(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    //目标rest钱，当前讨论index号钱，前面的已经讨论完了，返回最小货币数
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            //钱都用完了。若目标钱已经达成rest==0，说明不再需要货币了，返回0。若rest != 0，说明无法凑成rest，返回Integer.MAX_VALUE;
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            //获取从index+1往后返回的最小货币数
            int next = process(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE) {
                //index+1后面能够凑成rest，更新ans，选择较小的。若后面不能凑成，不更新
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    //2、动态规划
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        //dp[i][j]代表讨论第i张货币，此时还需要j元钱，这个情况下的最小货币数
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
//                dp[index][rest] = Integer.MAX_VALUE;
//                for(int zhang = 0; zhang * arr[index] <= rest; zhang++){
//                    int next = dp[index + 1][rest - zhang * arr[index]];
//                    if(next != Integer.MAX_VALUE){
//                        dp[index][rest] = Math.min(dp[index][rest], next + zhang);
//                    }
//                }
                //通过观察,dp[index][rest]依赖index行中一堆元素中较小的，
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    //只有+1了，dp[index][rest-1]下面依赖的才能根dp[index][rest]下面依赖的最小货币数相等
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, zhang + next);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }
}

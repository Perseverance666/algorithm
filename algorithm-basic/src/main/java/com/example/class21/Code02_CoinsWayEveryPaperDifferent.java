package com.example.class21;

/**
 * @Date: 2023/2/2 14:33
 * 换钱的方法数--题目1
 * <p>
 * 题目：arr是货币数组，其中的值都是正数，再给定一个正数aim。
 * 每个值都认为是一张货币，即便是值相同的货币也认为每一张都是不同的，返回组成aim的方法数。
 * 例如：arr = {1,1,1}，aim =2。第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2，一共就3种方法，所以返回3
 */
public class Code02_CoinsWayEveryPaperDifferent {
    //1、暴力递归
    public static int coinWays(int[] arr, int aim) {
        if (aim == 0) {
            //目标钱数为0，所有货币都不选即可，方法数为1
            return 1;
        }
        return process(arr, 0, aim);
    }
    // 当前讨论第index号货币，之前的已经讨论完了，此时还需要rest
    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            // 此时货币都讨论完了，没钱了。检验是否完成rest了
            return rest == 0 ? 1 : 0;
        } else {
            //第index号货币只有两种选择，要还是不要，将两种可能相加
            //不要
            int p1 = process(arr, index + 1, rest);
            //要
            int p2 = process(arr, index + 1, rest - arr[index]);
            return p1 + p2;
        }
    }

    //2、动态规划
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            //目标钱数为0，所有货币都不选即可，方法数为1
            return 1;
        }
        int N = arr.length;
        //建表，dp[i][j]表示目标j元钱，从第i号货币开始选择的方法数
        int[][] dp = new int[N + 1][aim + 1];
        //1、初始化表，(N,0)为1，剩下N行的所有列都是0。此时货币都讨论完了，没钱了，只有目标前为0，方法数才+1
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                //2、第index号货币只有两种选择，要还是不要，将两种可能相加
                //不要
                int p1 = dp[index + 1][rest];
                //要
                int p2 = rest - arr[index] < 0 ? 0 : dp[index + 1][rest - arr[index]];
                dp[index][rest] = p1 + p2;
            }
        }
        //3、返回目标aim元钱，从第0号货币开始选择的方法数
        return dp[0][aim];
    }


    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

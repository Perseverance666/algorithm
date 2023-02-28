package com.example.class02;

import java.util.Arrays;

/**
 * @Date: 2023/2/28 16:46
 * 题目：现有司机N*2人，调度中心会将所有司机平分给A、B两区域，i号司机去A可得收入为income[i][0]，去B可得收入为income[i][1]
 * 返回能使所有司机总收入最高的方案是多少钱?
 */
public class Code04_Drive {
    //1、暴力递归
    //A、B两区域的司机数量必须是相等的，故司机数量必须是偶数
    public static int maxMoney1(int[][] income) {
        int N = income.length;
        if (income == null || N == 0 || (N & 1) != 0) {
            return 0;
        }
        int M = N / 2; //A,B两区域的司机数量
        return process(income, 0, M);
    }

    //返回第i~N-1号司机的最高收入，此时A区域还剩rest个位置(只要A确定了，B就确定了，故参数就一个A即可)
    public static int process(int[][] arr, int i, int rest) {
        if (i == arr.length) {
            //越界了，所有司机都讨论完了
            return 0;
        }
        //1、剩下的司机必须去A区域
        if (rest == arr.length - i) {
            return arr[i][0] + process(arr, i + 1, rest - 1);
        }
        //2、剩下的司机都必须去B区域
        if (rest == 0) {
            return arr[i][1] + process(arr, i + 1, rest);
        }
        //3、剩下的司机A、B区域都得去
        int p1 = arr[i][0] + process(arr, i + 1, rest - 1);
        int p2 = arr[i][1] + process(arr, i + 1, rest);
        return Math.max(p1, p2);
    }

    //2、动态规划
    public static int maxMoney2(int[][] income) {
        int N = income.length;
        if (income == null || N == 0 || (N & 1) != 0) {
            return 0;
        }
        int M = N / 2; //A,B两区域的司机数量
        //dp[i][j]表示A区域还剩j个位置，此时i~N-1号司机的最高收入
        int[][] dp = new int[N + 1][M + 1];
        //dp[N][..] = 0  越界了，所有司机都讨论完了
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= M; rest++) {
                if (rest == N - i) {
                    //1、剩下的司机都必须去A区域
                    dp[i][rest] = income[i][0] + dp[i + 1][rest - 1];
                } else if (rest == 0) {
                    //2、剩下的司机都必须去B区域
                    dp[i][rest] = income[i][1] + dp[i + 1][rest];
                } else {
                    //3、剩下的司机A、B区域都得去
                    int p1 = income[i][0] + dp[i + 1][rest - 1];
                    int p2 = income[i][1] + dp[i + 1][rest];
                    dp[i][rest] = Math.max(p1, p2);
                }

            }
        }
        return dp[0][M];
    }


    // 这题有贪心策略 :
    // 假设一共有10个司机，思路是先让所有司机去A，得到一个总收益
    // 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益
    // 这道题有贪心策略，打了我的脸
    // 但是我课上提到的技巧请大家重视
    // 根据数据量猜解法可以省去大量的多余分析，节省时间
    // 这里感谢卢圣文同学
    public static int maxMoney3(int[][] income) {
        int N = income.length;
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(arr);
        int M = N >> 1;
        for (int i = N - 1; i >= M; i--) {
            sum += arr[i];
        }
        return sum;
    }

    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}

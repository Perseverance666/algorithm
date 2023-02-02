package com.example.class21;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @Date: 2023/2/2 14:34
 * 换钱的方法数--题目3
 *
 * arr是货币数组，其中的值都是正数，再给定一个正数aim。每个值都认为是一张货币，认为值相同的货币没有任何不同，返回组成aim的方法数。
 * 例如：arr = {1,2,1,1,2,1,2}，aim =4。方法：1+1+1+1、1+1+1、2+2，一共就3种方法，所以返回3
 *
 * 这道题就相当于把题目2中的每张面值数量无限改成有限，面值数量再用一个数组存放
 */
public class Code04_CoinsWaySameValueSamePapper {

    //存放两个数组，一个coins[]类似题目2中的数组，另一个zhangs存放每种面值的钱有多少张
    public static class Info {
        public int[] coins;     //每张钱的面值，正数且去重
        public int[] zhangs;    //每种面值的钱有多少张

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }
    //将原问题的数组arr转化成封装好的Info
    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }

    //1、暴力递归
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.zhangs, 0, aim);
    }
    // 当前讨论第index号钱，之前的已经讨论完了，此时还需要rest
    public static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        //！！！相比题目2，加了条件zhang <= zhangs[index]，确保张数不能越界
        for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
            ways += process(coins, zhangs, index + 1, rest - (zhang * coins[index]));
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
                    ways += dp[index + 1][rest - (zhang * coins[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }
    //2、动态规划
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
//                //1、直接3层循环填表，dp[index][rest]依赖dp[index + 1][rest - (zhang * arr[index])]满足条件下的所有位置
//                int ways = 0;
//                //！！！相比题目2，加了条件zhang <= zhangs[index]，确保张数不能越界
//                for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
//                    ways += dp[index + 1][rest - (zhang * coins[index])];
//                }
//                dp[index][rest] = ways;

                //2、通过列表观察！！发现dp[index][rest]依赖dp[index + 1][rest] 和 dp[index][rest - arr[index]](确保不越界)。避免了第3层for循环
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    //确保不越界
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                //！！！相比题目2，这里要减去当前rest不需要的部分(若存在的话)，即dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                // 因为dp[index][rest]依赖的dp[index][rest - arr[index]]其中有一个值是当前rest不需要的部分，要减去
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}

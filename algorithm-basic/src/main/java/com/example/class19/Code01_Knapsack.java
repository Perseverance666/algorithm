package com.example.class19;

/**
 * @Date: 2023/1/27 16:50
 * <p>
 * 题目：给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量。返回你能装下最多的价值是多少？
 */
public class Code01_Knapsack {
    /**
     * 1、暴力递归
     *
     * @param w   i号物品的重量，没有负数
     * @param v   i号物品的价值，没有负数
     * @param bag 背包容量，装的物品不能超过这个重量
     * @return 返回不超重情况下，能够得到的最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }
    //此时背包还能装rest重量的物品，返回从index物品开始往后剩下物品的最大价值
    //从0到w.length递归调用
    public static int process(int[] w, int[] v, int index, int rest) {
        if (index == w.length) {
            return 0;
        }
        //1、不选当前物品
        int p1 = process(w, v, index + 1, rest);
        //2、选当前物品
        //注意：判断选完当前物品后是否超重，若超重，从index开始往后剩下物品的最大价值为0；若不超重，v[index] + 从index+1开始往后剩下物品的最大价值
        int p2 = rest - w[index] < 0 ? 0 : v[index] + process(w, v, index + 1, rest - w[index]);
        //3、返回价值较大的选择
        return Math.max(p1, p2);

    }

    //2、动态规划
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        //1、建立dp表，行为当前物品的下标0~index，列为背包的剩余容量0~bag，bag为负数时单独处理
        int[][] dp = new int[N + 1][bag + 1];
        //2、初始化dp表，N行全部为0，已经初始化好了
        //3、填表，从下往上填，因为index行依赖index+1行
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                //4、不选当前物品
                int p1 = dp[index + 1][rest];
                //5、选当前物品。注意：判断选完当前物品后是否超重
                int p2 = rest - w[index] < 0 ? 0 : v[index] + dp[index + 1][rest - w[index]];
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        //6、返回背包容量为bag，从0号物品开始的最大价值
        return dp[0][bag];

    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}

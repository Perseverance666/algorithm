package com.example.class36;

/**
 * @Date: 2023/2/16 13:53
 * 贿赂怪兽
 * <p>
 * 题目：int[] d，d[i]：i号怪兽的能力。int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；
 * 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上
 * 返回通过所有的怪兽，需要花的最小钱数
 */
public class Code01_MoneyProblem {

    // int[] d d[i]：i号怪兽的武力
    // int[] p p[i]：i号怪兽要求的钱
    //1、适用于怪兽能力值小，dp时以怪兽能力值为列，花的钱多少无所谓
    public static long func1(int[] d, int[] p) {
        //从第0号怪兽开始谈论，此时能力值为0
        return process1(d, p, 0, 0);
    }
    //适用于怪兽能力值小，dp时以怪兽能力值为列，花的钱多少无所谓
    //当前讨论第index号怪兽，此时我现在的能力是ability，返回通过所有怪兽需要花的最小钱数
    public static long process1(int[] d, int[] p, int index, int ability) {
        if (index == d.length) {
            //怪兽都讨论完了,不需要花钱了
            return 0;
        }
        //1、只有当前有的能力 >= 怪兽的能力 才能选择贿赂不贿赂。
        if (ability >= d[index]) {
            //1.1、不贿赂index号怪兽
            long p1 = process1(d, p, index + 1, ability);
            //1.2、贿赂index号怪兽
            long p2 = p[index] + process1(d, p, index + 1, ability + d[index]);
            return Math.min(p1, p2);
        } else {
            //2、当前有的能力 < 怪兽的能力只能贿赂，不贿赂就打不过
            return p[index] + process1(d, p, index + 1, ability + d[index]);
        }
    }
    public static long dp1(int[] d, int[] p) {
        int N = d.length; //怪兽的数量
        int maxAbility = 0; //最大的能力值
        for (int i = 0; i < N; i++) {
            maxAbility += d[i];
        }
        //dp[i][j]代表当前讨论第i号怪兽，此时我的能力值是j时，通过所有怪兽需要花的最小钱数
        long[][] dp = new long[N + 1][maxAbility + 1];
        //dp[N][...] = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= maxAbility; j++) {
                if(j + d[i] > maxAbility){
                    //如果 当前能力+怪兽能力 越界了，这一行后面的值不用填了，没意义了
                    break;
                }
                //1、只有当前有的能力 >= 怪兽的能力 才能选择贿赂不贿赂。
                if (j >= d[i]) {
                    //1.1、不贿赂index号怪兽
                    long p1 = dp[i + 1][j];
                    //1.2、贿赂index号怪兽
                    long p2 = p[i] + dp[i + 1][j + d[i]];
                    dp[i][j] = Math.min(p1, p2);
                } else {
                    //2、当前有的能力 < 怪兽的能力只能贿赂，不贿赂就打不过
                    dp[i][j] = p[i] + dp[i + 1][j + d[i]];
                }
            }
        }
        return dp[0][0];
    }
    //2、适用于怪兽花的钱少，dp时以怪兽花的钱为列,能力值多少无所谓
    public static int func2(int[] d, int[] p) {
        int N = d.length; // 怪兽数量
        int sumMoney = 0;
        for (int i = 0; i < N; i++){
            sumMoney += p[i];
        }
        for(int money = 0; money < sumMoney; money++){
            //讨论最后一个怪兽，第一个合格的钱就是需要花的最小钱数
            if(process2(d,p,N-1,money) != -1){
                return money;
            }
        }
        //如果都不行，就返回所有怪兽的钱数
        return sumMoney;
    }
    //适用于怪兽花的钱少，dp时以怪兽花的钱为列,能力值多少无所谓
    //当前讨论第index号怪兽，要求之前花money钱，返回通过所有怪兽的最大能力值，能力越大后面才能选择贿赂还是不贿赂所以返回最大能力值
    public static long process2(int[] d, int[] p, int index, int money) {
        if(index == -1){
            //还没遇见怪兽呢
            //返回-1就是之前无法满足花money
            return money == 0 ? 0 : -1;
        }
        //1、不贿赂index号怪兽
        long preMaxAbility1 = process2(d,p,index-1,money);
        long p1 = -1;
        //只有之前有的能力 >= 怪兽的能力 才能选择贿赂不贿赂。
        if(preMaxAbility1 != -1 && preMaxAbility1 >= d[index]){
            p1 = preMaxAbility1;
        }
        //2、贿赂index号怪兽
        long preMaxAbility2 = process2(d,p,index-1,money-p[index]);
        long p2 = -1;
        if(preMaxAbility2 != -1){
            //确保讨论index-1怪兽时，花了money-p[index]
             p2 = d[index] + preMaxAbility2;
        }
        return Math.max(p1,p2);
    }
    public static long dp2(int[] d, int[] p) {
        int sum = 0;
        for (int num : p) {
            sum += num;
        }
        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 如果dp[i][j]==-1，表示经过0～i的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到正好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        // 经过0～i的怪兽，花钱数一定为p[0]，达到武力值d[0]的地步。其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // 可能性一，为当前怪兽花钱
                // 存在条件：
                // j - p[i]要不越界，并且在钱数为j - p[i]时，要能通过0～i-1的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                // 可能性二，不为当前怪兽花钱
                // 存在条件：
                // 0~i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    // 两种可能性中，选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过0～N-1的怪兽，且花钱为j（花钱的严格等于j）时的武力值最大是多少？
        // 那么最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int j = 0; j <= sum; j++) {
            if (dp[d.length - 1][j] != -1) {
                ans = j;
                break;
            }
        }
        return ans;
    }

    public static int[][] generateTwoRandomArray(int len, int value) {
        int size = (int) (Math.random() * len) + 1;
        int[][] arrs = new int[2][size];
        for (int i = 0; i < size; i++) {
            arrs[0][i] = (int) (Math.random() * value) + 1;
            arrs[1][i] = (int) (Math.random() * value) + 1;
        }
        return arrs;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 20;
        int testTimes = 10000;
        for (int i = 0; i < testTimes; i++) {
            int[][] arrs = generateTwoRandomArray(len, value);
            int[] d = arrs[0];
            int[] p = arrs[1];
            long ans1 = func1(d, p);
            long ans2 = dp1(d, p);
            long ans3 = dp2(d, p);
            long ans4 = func2(d, p);
            if (ans1 != ans2 || ans2 != ans3 || ans1 != ans4) {
                System.out.println("oops!");
            }
        }

    }
}

package com.example.class22;

/**
 * @Date: 2023/2/3 22:00
 * 题目：给定一个正数1，裂开的方法有一种（1）。给定一个正数2，裂开的方式有两种（1、1）（2）。
 * 给定一个正数3，裂开的方法有三种（1、1、1）（1、2）（3）。给定一个正数4，裂开的方法有五种（1、1、1、1）（1、1、2）（1、3）（2、2）（4）。
 * 给定一个正数n，其中裂开的后面的数要大于等于前面的数，求裂开的方法数
 */
public class Code03_SplitNumber {
    //1、暴力递归
    public static int ways(int n) {
        // n为正数，若为负，返回0
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //n拆出来的数肯定是在[1,n]，这里pre初始值为1，就可以不耽误n的拆分
        return process(1, n);
    }

    /**
     * @param pre  上一个拆出来的数是pre
     * @param rest 还剩rest需要去拆
     * @return 返回拆解的方法数
     */
    public static int process(int pre, int rest) {
        if (rest == 0) {
            //说明之前的拆分策略可以，方法数+1
            return 1;
        }
        if (pre > rest) {
            //此时已经不满足条件，这个方法不行，返回0
            return 0;
        }
        int ways = 0;
        //从rest中再拆出一个数，这个数要>=pre
        for (int one = pre; one <= rest; one++) {
            ways += process(one, rest - one);
        }
        return ways;

    }

    //2、动态规划
    public static int dp(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        //dp[i][j]代表上一个拆出来的数是i，此时还剩j需要去拆，这个情况下的裂开的方法数
        int[][] dp = new int[n + 1][n + 1];
        //第0行不用管
        for (int i = 1; i <= n; i++) {
            //第0列都是1，说明之前的拆分策略可以，方法数+1
            dp[i][0] = 1;
            //对角线pre == rest，拆分方法只有一种
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
//                int ways = 0;
//                for (int one = pre; one <= rest; one++) {
//                    ways += dp[one][rest - one];
//                }
//                dp[pre][rest] = ways;
                //通过观察,dp[pre][rest]依赖这一行rest - pre这个值以及往左下斜着途中的几个值。
                //而,dp[pre+1][rest]依赖也如此，所以dp[pre][rest]= dp[pre+1][rest]再加上pre这一行的这个dp[pre][rest - pre]
                dp[pre][rest] = dp[pre+1][rest] +  dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 5;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
        System.out.println(dp(test));
    }

}

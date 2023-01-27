package com.example.class18;

/**
 * @Date: 2023/1/26 11:38
 * 从暴力递归到动态规划
 * 动态规划就是把原来调用递归的过程改成直接获取dp表中的值
 *
 * 机器人达到指定位置方法数
 *
 * 题目：假设有排成一行的N个位置，记为1~N，N一定大于或等于2，开始时机器人在其中的M位置上（M一定是1~N中的一个），
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；规定机器人必须走K步，最终能来到P位置（P也是1~N中的一个）的方法有多少种。
 * 给定四个删除N、M、K、P，返回方法数
 */
public class Code01_RobotWalk {
    //1、暴力递归
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    public static int process1(int cur, int rest, int aim, int N) {
        //base case
        if (rest == 0) {
            //若已经走完K步了，cur与aim位置一样，方法数+1，否则为0
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            //1、当前机器人在1位置，它只能往2位置走
            return process1(2, rest - 1, aim, N);
        } else if (cur == N) {
            //2、当前机器人在N位置，它只能往N-1位置走
            return process1(N - 1, rest - 1, aim, N);
        } else {
            //3、当前机器人在中间位置，可以往左走，也可以往右走
            return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
        }
    }

    //从暴力递归到动态规划
    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        // dp就是缓存表
        // dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
        // dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
        // N+1 * K+1
        return process2(start, K, aim, N, dp);
    }
    // cur 范: 1 ~ N
    // rest 范：0 ~ K
    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 之前没算过！
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;

    }

    //2、动态规划
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        //建立dp表，第0行不参与赋值。行代表当前机器人位置，列代表机器人还剩几步可以走
        int[][] dp = new int[N + 1][K + 1];
        //1、将第一列初始化，只需将dp[aim][0] = 1，其他为0
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            //2、处理第一行
            dp[1][rest] = dp[2][rest - 1];
            //3、处理中间行
            for (int cur = 2; cur <= N - 1; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            //4、处理最后一行
            dp[N][rest] = dp[N-1][rest-1];
        }
        //5、返回dp表中的位置dp[start][K]为结果
        return dp[start][K];
    }


    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}

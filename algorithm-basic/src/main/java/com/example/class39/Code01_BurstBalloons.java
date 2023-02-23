package com.example.class39;

/**
 * @Date: 2023/2/22 14:55
 * 戳气球
 * <p>
 * 题目：有n个气球，编号为0到n-1，每个气球上都标有一个数字，这些数字存在数组nums中
 * 现在要求你戳破所有的气球。戳破第i个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币
 * 这里的i-1和i+1代表和i相邻的、没有被戳爆的！两个气球的序号
 * 如果i-1或i+1超出了数组的边界，那么就当它是一个数字为1的气球
 * 求所能获得硬币的最大数量
 * <p>
 * 测试链接：https://leetcode.cn/problems/burst-balloons/
 */
public class Code01_BurstBalloons {
    //1、暴力递归
    public static int maxCoins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        // [3,2,1,3]
        // [1,3,2,1,3,1]
        int N = arr.length;
        //将原数组的左右边界分别加上1，新数组大小为N+2
        int[] help = new int[N + 2];
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        help[0] = 1;
        help[N + 1] = 1;
        //返回打爆1~N位置气球的最大得分，就是原来的0~N-1位置
        return func(help, 1, N);
    }

    // 由于原数组左右边界都加了1，所以L-1位置，和R+1位置，永远不越界
    // 潜台词：[L-1] 和 [R+1]位置的气球一定没爆。这道题的关键就是这个潜台词，还有要讨论最后打爆某个气球
    // 返回，arr[L...R]打爆所有气球，最大得分是什么
    public static int func(int[] arr, int L, int R) {
        if (L == R) {
            //只剩一个气球了
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        int ans = 0;
        //1、最后打爆L位置上的气球。先打爆L+1~R，再加上arr[L-1] * arr[L] * arr[R+1]
        int p1 = func(arr, L + 1, R) + arr[L - 1] * arr[L] * arr[R + 1];
        //2、最后打爆R位置上的气球。先打爆L~R-1，再加上arr[L-1] * arr[L] * arr[R+1]
        int p2 = func(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1];
        ans = Math.max(p1, p2);
        //3、最后打爆i位置上的气球。遍历L+1 ~ R-1
        for (int i = L + 1; i < R; i++) {
            //打爆L~i-1，然后打爆i+1~R，最后加上arr[L-1] * arr[i] * arr[R+1]
            int p3 = func(arr, L, i - 1) + func(arr, i + 1, R) + arr[L - 1] * arr[i] * arr[R + 1];
            ans = Math.max(ans, p3);
        }
        return ans;
    }

    //2、动态规划
    public static int dp(int[] arr) {
        int N = arr.length;
        if (arr == null || N == 0) {
            return 0;
        }
        if (N == 1) {
            return arr[0];
        }
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = arr[i];
        }
        //dp[i][j]表示打爆i~j位置气球的最大得分
        int[][] dp = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            //只剩一个气球
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
        }
        for (int L = N; L >= 1; L--) {
            for (int R = L + 1; R <= N; R++) {  //L<R
                //1、最后打爆L位置上的气球
                int p1 = dp[L + 1][R] + help[L - 1] * help[L] * help[R + 1];
                //2、最后打爆R位置上的气球
                int p2 = dp[L][R - 1] + help[L - 1] * help[R] * help[R + 1];
                dp[L][R] = Math.max(p1, p2);
                //3、最后打爆i位置上的气球
                for (int k = L + 1; k < R; k++) {
                    int p3 = dp[L][k - 1] + dp[k + 1][R] + help[L - 1] * help[k] * help[R + 1];
                    dp[L][R] = Math.max(dp[L][R], p3);
                }
            }
        }
        //返回打爆1~N位置气球的最大得分，就是原来的0~N-1位置
        return dp[1][N];
    }

}

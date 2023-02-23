package com.example.class39;

/**
 * @Date: 2023/2/22 14:57
 * 移除盒子
 * <p>
 * 题目：给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色，你将经过若干轮操作去去掉盒子
 * 直到所有的盒子都去掉为止，每一轮你可以移除具有相同颜色的连续k个盒子（k >= 1）
 * 这样一轮之后你将得到 k * k 个积分，当你将所有盒子都去掉之后，求你能获得的最大积分和
 * <p>
 * 测试链接：https://leetcode.cn/problems/remove-boxes/
 */
public class Code02_RemoveBoxes {
    // arr[L...R]消除，而且前面跟着K个arr[L]这个数
    // 返回：所有东西都消掉，最大得分
    public static int func1(int[] arr, int L, int R, int K) {
        if (L > R) {
            return 0;
        }
        int ans = func1(arr, L + 1, R, 0) + (K + 1) * (K + 1);

        // 前面的K个X，和arr[L]数，合在一起了，现在有K+1个arr[L]位置的数
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                ans = Math.max(ans, func1(arr, L + 1, i - 1, 0) + func1(arr, i, R, K + 1));
            }
        }
        return ans;
    }

    public static int removeBoxes1(int[] boxes) {
        int N = boxes.length;
        int[][][] dp = new int[N][N][N];
        int ans = process1(boxes, 0, N - 1, 0, dp);
        return ans;
    }

    public static int process1(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][K] > 0) {
            return dp[L][R][K];
        }
        int ans = process1(boxes, L + 1, R, 0, dp) + (K + 1) * (K + 1);
        for (int i = L + 1; i <= R; i++) {
            if (boxes[i] == boxes[L]) {
                ans = Math.max(ans, process1(boxes, L + 1, i - 1, 0, dp) + process1(boxes, i, R, K + 1, dp));
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }

    public static int removeBoxes2(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        if (boxes.length == 1) {
            return 1;
        }
        int N = boxes.length;
        //傻缓存，dp[i][j][k]代表前面有k个盒子与i上的盒子颜色相同，消除i到j上盒子的最大积分和
        int[][][] dp = new int[N][N][N];
        //前面有0个盒子与0上的盒子颜色相同，消除0到N-1上的盒子
        return process(boxes, 0, N - 1, 0, dp);
    }

    //前面有K个盒子与L上的盒子颜色相同，消除L到R上的盒子，返回最大积分和
    public static int process(int[] boxes, int L, int R, int K, int[][][] dp) {
        if (L > R) {
            //越界了，积分和为0
            return 0;
        }
        if (dp[L][R][K] > 0) {
            //有缓存就用缓存
            return dp[L][R][K];
        }
        //1、如果L紧后面有好多与L上盒子颜色一样的连续的盒子，前面K个盒子与这些盒子组合，最后要记得剩一个盒子留在数组里
        int last = L;
        while (last + 1 <= R && boxes[last + 1] == boxes[L]) {
            last++;
            //最后last指向最后一个与前面颜色相同的盒子
        }
        //这样前面有pre个相同颜色的盒子
        int pre = last - L + K;
        //2、前面pre个盒子与last上的盒子先一块消除，再消除剩下的
        int p1 = (pre + 1) * (pre + 1) + process(boxes, last + 1, R, 0, dp);
        int ans = p1;
        //3、前面pre个盒子与last上的盒子组合，然后找到下一组与last上盒子颜色一样的连续的盒子的第一个位置
        //要从last+2开始找，因为last+1肯定颜色不与last一样
        for (int i = last + 2; i <= R; i++) {
            if (boxes[i] == boxes[last] && boxes[i - 1] != boxes[last]) {
                //找到下一组与last上盒子颜色一样的连续的盒子的第一个位置
                //4、先消除中间的，然后再消除i到R，前面有pre+1(算上last)和盒子和i上的盒子颜色一样
                int p2 = process(boxes, last + 1, i - 1, 0, dp) + process(boxes, i, R, pre + 1, dp);
                ans = Math.max(ans, p2);
            }
        }
        dp[L][R][K] = ans;
        return ans;
    }
}

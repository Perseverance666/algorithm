package com.example.class36;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @Date: 2023/2/17 14:41
 * 子序列中累加和%m之后的最大值
 * <p>
 * 题目：给定一个非负数组arr，和一个正数m，返回arr的所有子序列中累加和%m之后的最大值
 */
public class Code02_SubsquenceMaxModM {
    public static int max1(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        //set装所有的累加和情况
        process(arr, 0, 0, set);
        int max = 0;
        for (Integer sum : set) {
            max = Math.max(max, sum % m);
        }
        return max;
    }

    //当前讨论第0~index号数，之前的累加和是sum，set装所有可能的累加和
    public static void process(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            //所有数都讨论完了，set装之前的累加和sum
            set.add(sum);
        } else {
            //1、不要这个数
            process(arr, index + 1, sum, set);
            //2、要这个数
            process(arr, index + 1, sum + arr[index], set);
        }
    }

    //1、适用于arr的值都不大数，即累加和不大，dp表以子序列的累加和为列。m很大
    public static int max2(int[] arr, int m) {
        int sum = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }
        //dp[i][j]当前讨论第0~i号数，是否能搞定累加和j
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            //累加和为0时，就是每个数都不选，一定能搞出来
            dp[i][0] = true;
        }
        //只讨论第一个数，累加和能搞出arr[0]，就是选他，
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                //1、不选当前数
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {  //防止数组越界
                    //2、选当前数
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        int ans = 0;
        for (int j = 0; j <= sum; j++) {
            //讨论第0~N-1号数,把所有可能的累加和都试一遍，找出最大符合的
            if (dp[N - 1][j]) {
                ans = Math.max(ans, j % m);
            }
        }
        return ans;
    }
    //2、适用于m不大，dp表以子序列的累加和%m的余数为列。arr的值大，即累加和大。
    public static int max3(int[] arr, int m) {
        int N = arr.length;
        // 0...m-1
        //dp[i][j]代表0~i-1之间的数子序列累加和%m的余数是否是j
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            //子序列累加和%m的余数为0时，就是每个数都不选，一定能搞出来余数0
            dp[i][0] = true;
        }
        //只讨论第一个数，累加和%m余数是arr[0]%m
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                //1、不选当前数
                dp[i][j] = dp[i - 1][j];
                //2、选当前数
                int cur = arr[i] % m;
                if (cur <= j) {
                    //当前数%m余数 <= 当前要求的余数j，只需0~i-2之间的数子序列累加和%m的余数是j-cur
                    dp[i][j] |= dp[i - 1][j - cur];
                } else {
                    //当前数%m余数 > 当前要求的余数j，需0~i-2之间的数子序列累加和%m的余数是m + j - cur,要大一圈最终才能余数为j
                    dp[i][j] |= dp[i - 1][m + j - cur];
                }
            }
        }
        int ans = 0;
        for (int i = m - 1; i > 0; i--) {
            if (dp[N - 1][i]) {
                //讨论第0~N-1号数,把所有可能的子序列累加和%m的余数都试一遍，找出最大符合的
                ans = i;
                break;
            }
        }
        return ans;
    }

    // 3、适用于arr的长度相对不大。arr的累加和很大，m也很大。采用分治法
    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        //sortSet1装左边子序列累加和%m的余数的所有可能值
        process4(arr, 0, 0, mid, m, sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        //sortSet2装右边子序列累加和%m的余数的所有可能值
        process4(arr, mid + 1, 0, arr.length - 1, m, sortSet2);
        int ans = 0;
        for (Integer leftMod : sortSet1) {
            //左边右边结合求出最大的余数，左右结合的值要<=m
            ans = Math.max(ans, leftMod + sortSet2.floor(m - 1 - leftMod));
        }
        return ans;
    }
    //讨论从第index号数到end号数之间，子序列累加和%m的余数的所有可能值，之前的累加和是sum
    public static void process4(int[] arr, int index, int sum, int end, int m, TreeSet<Integer> sortSet) {
        if (index == end + 1) {
            //都讨论完了
            sortSet.add(sum % m);
        } else {
            //1、不要当前数
            process4(arr, index + 1, sum, end, m, sortSet);
            //2、要当前数
            process4(arr, index + 1, sum + arr[index], end, m, sortSet);
        }
    }

    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        int m = 76;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = max1(arr, m);
            int ans2 = max2(arr, m);
            int ans3 = max3(arr, m);
            int ans4 = max4(arr, m);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}

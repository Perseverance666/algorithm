package com.example.class24;

import java.util.LinkedList;

/**
 * @Date: 2023/2/6 13:56
 * 加油站的良好出发点问题
 *
 * 题目：在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。你有一辆油箱容量无限的的汽车，
 * 从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则保证它是唯一的。
 *
 * 测试链接：https://leetcode.cn/problems/gas-station
 */
public class Code03_GasStation {
    // 这个方法的时间复杂度O(N)，额外空间复杂度O(N)
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] g, int[] c) {
        int N = g.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = g[i] - c[i];
            arr[i + N] = g[i] - c[i];
        }
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }
        LinkedList<Integer> w = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
                w.pollLast();
            }
            w.addLast(i);
        }
        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[w.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (w.peekFirst() == i) {
                w.pollFirst();
            }
            while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
                w.pollLast();
            }
            w.addLast(j);
        }
        return ans;
    }

//    public static int canCompleteCircuit(int[] gas, int[] cost) {
//        int n = gas.length;
//        //从第i号加油站开始讨论
//        int i = 0;
//        while(i < n){
//            int gasSum = 0;     //总共加的油
//            int costSum = 0;    //总共消费的油
//            int count = 0;      //记录能走过几个站点
//            int j = i;        //j代表从i号站点出发沿途的那些站点
//            //要确保能绕一圈
//            while(count < n){
//                gasSum += gas[j];
//                costSum += cost[j];
//                if(gasSum  - costSum < 0){
//                    //这个站点发现油不够了
//                    break;
//                }else {
//                    //这个站点发现油够，准备检验下一个站点
//                    count++;
//                    j = (j + 1) % n;
//                }
//            }
//            // 如果能环绕一圈
//            if(count == n){
//                return i;
//            }else {
//                //从油不够的那个站点开始检查(重要优化)
//                i = i + count + 1;
//            }
//        }
//        //所有站点都讨论完了，没有复合要求的
//        return -1;
//    }

}

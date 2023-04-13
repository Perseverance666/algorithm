package com.example.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Date: 2023/4/12 15:25
 * 56. 合并区间
 * <p>
 * https://leetcode.cn/problems/merge-intervals/?favorite=2cktkvj
 */
public class Code02_merge {
    public int[][] merge(int[][] intervals) {
        //1、先将intervals中的数组 按左端点从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        //2、准备开始合并
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int L = intervals[i][0];        //当前数组的左端点
            int R = intervals[i][1];        //当前数组的右端点
            int resR = res.get(res.size() - 1)[1];   //res结果集中最后一个数组的右端点R
            if (L <= resR) {
                //合并区间，例：(0,2) (0,1)  -> (0,2)   (0,2) (1,3) -> (0,3)
                res.get(res.size() - 1)[1] = Math.max(R, resR);
            } else {
                //不合并区间，当前数组直接加入结果集res
                res.add(new int[]{L, R});
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}

package com.example.day24_数学;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/3/26 18:33
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * <p>
 * https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 */
public class Code02_FindContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        int mid = target / 2;
        //左指针到mid就够了，不用再往后了
        for (int L = 1; L <= mid; L++) {
            int sum = L;
            int R = L + 1;
            while (sum + R < target) {
                sum += R;
                R++;
            }
            if (sum + R == target) {
                int[] arr = new int[R - L + 1];
                int k = L;
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = k++;
                }
                res.add(arr);
            }
            //sum + R > target，说明以L开头的数组不行，换下一组
        }
        return res.toArray(new int[res.size()][]);
    }
}

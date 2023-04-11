package com.example.day07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/4/10 18:57
 * 39. 组合总和
 * <p>
 * https://leetcode.cn/problems/combination-sum/?favorite=2cktkvj
 */
public class Code01_combinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, candidates, 0, target);
        return res;
    }
    //当前讨论arr[index]，此时还需要rest
    public static void dfs(List<List<Integer>> res, List<Integer> list, int[] arr, int index, int rest) {
        if (index == arr.length) {
            return;
        }
        if (rest == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //1、不选当前index数
        dfs(res, list, arr, index + 1, rest);
        //2、选当前index数，确保能选
        if (rest - arr[index] >= 0) {
            list.add(arr[index]);
            dfs(res, list, arr, index, rest - arr[index]);
            list.remove(list.size() - 1); //恢复现场
        }
    }
}

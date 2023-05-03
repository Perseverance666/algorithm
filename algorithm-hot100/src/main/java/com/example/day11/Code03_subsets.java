package com.example.day11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/4/14 18:16
 * 78. 子集
 *
 * https://leetcode.cn/problems/subsets/
 */
public class Code03_subsets {
    public List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(arr,0,res,list);
        return res;
    }

    public static void dfs(int[] arr,int index,List<List<Integer>> res,List<Integer> list){
        if(index == arr.length){
            res.add(new ArrayList<>(list));
            return;
        }
        //1、要当前元素
        list.add(arr[index]);
        dfs(arr,index + 1, res,list);

        //2、不要当前元素
        list.remove(list.size() - 1);
        dfs(arr,index + 1, res,list);
    }
}

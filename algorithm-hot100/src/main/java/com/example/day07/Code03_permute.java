package com.example.day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Date: 2023/4/10 18:59
 * 46. 全排列
 * <p>
 * https://leetcode.cn/problems/permutations/?favorite=2cktkvj
 */
public class Code03_permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res,list,nums,0);
        return res;
    }

    public static void dfs(List<List<Integer>> res,List<Integer> list,int[] arr,int index){
        if(index == arr.length){
            //都讨论完了
            res.add(new ArrayList<>(list));
            return;
        }
        //将index上的数与i上数互换，确保每个数都能在index上
        for(int i = index; i < arr.length; i++){
            swap(arr,i,index);
            //该组合index位置确定，讨论index位置
            list.add(arr[index]);
            dfs(res,list,arr,index + 1);
            //恢复现场
            list.remove(list.size() - 1);
            swap(arr,i,index);
        }
    }
    public static void swap(int[] arr,int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

//    //官方答案
//    public static List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
//        List<Integer> list = new ArrayList<>();
//        //先将第一种排列赋给list，然后dfs在list上修改排列
//        for (int num : nums) {
//            list.add(num);
//        }
//        dfs(res, list, 0);
//        return res;
//    }
//
//    public static void dfs(List<List<Integer>> res, List<Integer> list, int index) {
//        if (index == list.size()) {
//            //都讨论完了
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        //将index上的数与i上数互换，确保每个数都能在index上
//        for (int i = index; i < list.size(); i++) {
//            Collections.swap(list, i, index);
//            dfs(res, list, index + 1);
//            Collections.swap(list, i, index); //恢复现场
//        }
//    }

}

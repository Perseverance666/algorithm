package com.example.class02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Date: 2023/2/28 16:40
 * 题目：给定数组hard和money，长度都为N，hard[i]表示i号工作的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力，每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班。返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class Code01_ChooseWork {
    public static class Job{
        public int hard;
        public int money;
        public Job(int h,int m){
            hard = h;
            money = m;
        }
    }
    public static class JobComparator implements Comparator<Job>{
        @Override
        public int compare(Job o1, Job o2) {
            //将工作按难度从小到大排序，难度一样按收入从大到小排序
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money - o1.money);
        }
    }
    public static int[] getMoneys(Job[] job, int[] ability) {
        Arrays.sort(job,new JobComparator());
        TreeMap<Integer,Integer> map = new TreeMap<>(); //有序表
        map.put(job[0].hard,job[0].money);
        //pre是指上一份进入map的工作
        Job pre = job[0];
        for(int i = 1; i < job.length; i++){
            if(job[i].hard != pre.hard && job[i].money > pre.money){
                map.put(job[i].hard,job[i].money);
                pre = job[i];
            }
        }

        int M = ability.length;
        int[] ans = new int[M];
        for(int i = 0; i < M; i++){
            //找到难度 <= ability[i] 且离它最近的工作
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0; //注意key可能为空
        }
        return ans;
    }
}

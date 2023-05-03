package com.example.demo01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Date: 2023/4/22 15:28
 * ZJ21 特征提取
 */
public class Main04{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        for(int i = 0; i < N; i++){
            int M = in.nextInt();
            //记录上一行键值对的 map
            HashMap<String , Integer> lastMap = new HashMap<>();
            //记录本行键值对的 map
            HashMap<String , Integer> curMap = new HashMap<>();
            int max = 1;
            //讨论每一行
            for(int j = 0; j < M; j++){
                //这一行有len个特征
                int len = in.nextInt();
                curMap.clear();
                for(int k = 0; k < len; k++){
                    String s = in.nextInt() + " " + in.nextInt();
                    curMap.put(s,curMap.getOrDefault(s,0) + 1);
                }
                for(Map.Entry<String,Integer> entry : lastMap.entrySet()){
                    String key = entry.getKey();
                    if(curMap.containsKey(key)){
                        curMap.put(key,curMap.get(key) + lastMap.get(key));
                        max = Math.max(max,curMap.get(key));
                    }
                }
                lastMap.clear();
                lastMap.putAll(curMap);
            }
            System.out.print(max);
        }
    }
}
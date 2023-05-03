package com.example.demo01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Date: 2023/4/21 18:02
 * ZJ20 雀魂启动
 */

public class Main03 {
    public static boolean flag;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //存放手中拥有每张牌的数量
        int[] card = new int[10];
        for (int i = 0; i < 13; i++) {
            card[in.nextInt()]++ ;
        }
        ArrayList<Integer> res = new ArrayList<>();
        //讨论第14张牌从1~9抓到哪张能胡
        for (int i = 1; i <= 9; i++) {
            if (card[i] <= 3) {
                card[i]++;
                if (dfs(card, 1, 14)) {
                    res.add(i);
                }
                //恢复现场
                card[i]--;
            }
        }
        if(res.size() == 0){
            System.out.println(0);
        }else{
            for(int num : res){
                System.out.print(num + " ");
            }
        }

    }
    //当前牌大小为start，还剩rest张牌
    public static boolean dfs(int[] card, int start, int rest) {
        if (rest == 0) {
            return true;
        }
        //1、当前牌没了
        if (card[start] == 0) {
            return dfs(card, start + 1, rest);
        }
        //2、选当前牌做为雀头
        if (card[start] >= 2 && rest % 3 != 0) {
            card[start] -= 2;
            if (start + 1 <= 9 && card[start] == 0) {
                flag = dfs(card, start + 1, rest - 2);
            } else {
                flag = dfs(card, start, rest - 2);
            }
            //恢复现场
            card[start] += 2;
            if(flag){
                return flag;
            }
        }
        //3、选当前牌作为刻子
        if (card[start] >= 3) {
            card[start] -= 3;
            if (start + 1 <= 9 && card[start] == 0) {
                flag = dfs(card, start + 1, rest - 3);
            } else {
                flag = dfs(card, start, rest - 3);
            }
            //恢复现场
            card[start] += 3;
            if(flag){
                return flag;
            }
        }
        //4、选当前牌作为顺子
        if (start + 2 <= 9 && card[start] > 0 && card[start + 1] > 0 &&
                card[start + 2] > 0) {
            card[start]--;
            card[start+1]--;
            card[start+2]--;
            if(card[start] == 0){
                flag = dfs(card, start + 1, rest - 3);
            }else{
                flag = dfs(card, start, rest - 3);
            }
            //恢复现场
            card[start]++;
            card[start+1]++;
            card[start+2]++;
            if(flag){
                return flag;
            }
        }
        //没法胡了
        return false;
    }
}
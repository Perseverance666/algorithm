package com.example.day16_排序;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Date: 2023/3/17 12:15
 * 面试题45. 把数组排成最小的数
 */
public class Code01_MinNumber {
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            //s1 + s2 <= s2 + s1  就把s1放前面
            return (s1 + s2).compareTo(s2 + s1);
        }
    }
    public String minNumber(int[] nums) {
        String[] strArr = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            strArr[i] = String.valueOf(nums[i]);
        }
//        Arrays.sort(strArr,new MyComparator());
        quickSort(strArr,0,strArr.length - 1);
        StringBuilder res = new StringBuilder();
        for(String str : strArr){
            res.append(str);
        }
        return res.toString();
    }

    public static void quickSort(String[] strArr,int L,int R){
        if(L >= R){
            return;
        }
        int p = partition(strArr,L,R);
        quickSort(strArr,L,p-1);
        quickSort(strArr,p,R);
    }
    public static int partition(String[] strArr, int L, int R) {
        String pivot = strArr[R];
        int i = L;  //i往左都是<=pivot的数
        for(int j = i; j < R; j++){      //j负责找到 <=pivot的，一旦找到就和i交换
            if(new MyComparator().compare(strArr[j],pivot) <= 0){
                swap(strArr,i,j);
                i++;
            }
        }
        //最后把strArr[R]上元素交换，这样i往左都是 <= pivot的数了
        swap(strArr,i,R);
        return i;
    }
    public static void swap(String[] strArr,int i,int j){
        String temp = strArr[i];
        strArr[i] = strArr[j];
        strArr[j] = temp;
    }


}

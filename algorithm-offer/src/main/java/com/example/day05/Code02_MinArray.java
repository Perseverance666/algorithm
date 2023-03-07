package com.example.day05;

import java.util.HashMap;

/**
 * @Date: 2023/3/6 21:11
 * 剑指 Offer 11. 旋转数组的最小数字
 */
public class Code02_MinArray {
    public int minArray(int[] numbers) {
        int L = 0;
        int R = numbers.length - 1;
        while(L < R){
            int mid = L + ((R - L) >> 1);
            if(numbers[mid] > numbers[R]){
                //大于往右找
                L = mid + 1;
            }else if(numbers[mid] < numbers[R]){
                //小于往左找
                R = mid;
            }else {
                //numbers[mid] = numbers[mid + 1]，往左找
                R--;
            }
        }
        return numbers[L];
    }
}

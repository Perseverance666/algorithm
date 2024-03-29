package com.example.day21_位运算;

/**
 * @Date: 2023/3/22 13:56
 * 剑指 Offer 15. 二进制中1的个数
 *
 * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 */
public class Code01_HammingWeight {
    // you need to treat n as an unsigned value
//    public int hammingWeight(int n) {
//        int count = 0;
//        for(int i = 0; i < 32; i++){
//            if((n & 1) == 1){
//                count++;
//            }
//            n >>= 1;
//        }
//        return count;
//    }
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}

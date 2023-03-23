package com.example.day22_位运算;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 2023/3/23 13:54
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 *
 * 这道题也可也改成：数组中所有的数都出现了M次，只有一种数出现了K次，1 <= K < M 返回这种数
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 */
public class Code02_SingleNumber {
    public int singleNumber(int[] nums) {
        //创建32大小的数组，用于记录所有数字的各二进制位的1的出现次数
        int[] counts = new int[32];
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < 32 ; j++){
                //检验num[i]这个数二进制下哪一位是1
                if(((nums[i] >> j) & 1) == 1){
                    counts[j]++;
                }
            }
        }
        int res = 0;
        int m = 3;  //其他数都出现3次
        for(int i = 0; i < 32; i++){
            if(counts[i] % m != 0){
                //这一位1出现的次数不是3的整数倍，说明出现1次的那个数二进制下在这一位上一定是1
                res |= (1 << i);
            }
        }
        return res;
    }

//     public int singleNumber(int[] nums) {
//         int ones = 0, twos = 0;
//         for(int num : nums){
//             ones = ones ^ num & ~twos;
//             twos = twos ^ num & ~ones;
//         }
//         return ones;
//     }

//    public int singleNumber(int[] nums) {
//        HashMap<Integer,Integer> map = new HashMap<>();
//        for(int i = 0; i < nums.length; i++){
//            if(!map.containsKey(nums[i])){
//                map.put(nums[i],1);
//            }else{
//                map.put(nums[i],map.get(nums[i]) + 1);
//            }
//        }
//        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
//            if(entry.getValue() == 1){
//                return entry.getKey();
//            }
//        }
//        return -1;
//    }
}

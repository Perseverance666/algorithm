package com.example.day22_位运算;

/**
 * @Date: 2023/3/23 13:53
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * <p>
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 */
public class Code01_SingleNumbers {
    public static int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        //a,b用于存最终两个不同的数。res = a ^ b
        int a = 0;
        int b = 0;
        //取res最右侧的1，则a和b二进制下的这一位 一定不等，要不然异或不可能是1
        int rightOne = res & (-res);   //rightOne = res & (~res + 1)
        //然后以最右侧的1 flag作为分界，将原数组进行分组，然后分别异或，最终得到答案
        for (int num : nums) {
            if ((num & rightOne) == 0) {
                //num这个数的rightOne这一位是0
                a ^= num;
            } else {
                //num这个数的rightOne这一位是1
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        int[] nums = {1,2,5,2};
        int[] res = singleNumbers(nums);
        for(int num : res){
            System.out.println(num);
        }
    }
}

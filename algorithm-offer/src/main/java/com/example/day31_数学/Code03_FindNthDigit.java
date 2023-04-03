package com.example.day31_数学;

/**
 * @Date: 2023/4/2 20:44
 * 剑指 Offer 44. 数字序列中某一位的数字
 *
 * https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
 *
 *            数字范围     位数    数字数量    数位数量
 *             1 - 9      1        9          9
 *            10 - 99     2        90        180
 *           100 - 999    3        900       2700
 *          1000 - 9999   4        9000      36000
 *              ...      ...       ...        ...
 *         start - end   digit    9*start   9*start*dight
 *
 *
 *     例如 2901 = 9 + 180 + 2700 + 12 即一定是4位数,第12位   n = 12;
 *     数据为 = 1000 + (n - 1) / 4  = 1000 + 2 = 1002
 *     定位1002中的位置 = (n - 1) % 4 = 3
 *     s[3] - '0' = 2;
 */
public class Code03_FindNthDigit {
    public int findNthDigit(int n) {
        int digit = 1;     //此时数字的位数
        long start = 1;    //该位数下的第一个数字
        long count = 9;    //该位数下的数位的数量
        //1、先确定n对应的数字有多少位 digit
        while(n > count){
            n -= count;
            digit++;
            start *= 10;
            count = 9 * digit * start;
        }
        //2、循环结束后根据此时的n可以求出 原n对应的数字 num
        long num =  start + (n - 1) / digit;   //n对应的 数字
        //3、找到原n在数字num的哪位上 index
        int index = (n - 1) % digit;   //index返回0 ~ digit-1
        int res = Long.toString(num).charAt(index) - '0';
        return res;
    }
}

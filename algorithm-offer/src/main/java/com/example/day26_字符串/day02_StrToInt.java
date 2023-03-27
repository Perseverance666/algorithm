package com.example.day26_字符串;

/**
 * @Date: 2023/3/27 13:13
 * 面试题67. 把字符串转换成整数
 *
 * https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
 */
public class day02_StrToInt {
    public static int strToInt(String str) {
        char[] c = str.trim().toCharArray(); //去掉前后空格
        if(c.length == 0){
            return 0;
        }
        int res = 0;
        int bndry = Integer.MAX_VALUE / 10;  //用于防止越界
        int i = 0;  //去掉空格后的字符数组下标
        int sign = 1;   //sign = -1，表示开头有-， sign = 1表示没有-
        //1、先处理符号位
        if(c[0] == '-') {
            sign = -1;
            i = 1;
        } else if(c[0] == '+') {
            i = 1;
        }
        for(int j = i; j < c.length; j++) {
            //2、只要某一位不是数字，直接停，返回res * sign
            if(c[j] < '0' || c[j] > '9') {
                break;
            }
            //3、讨论如果加上了j这个字符后，是否会越界
            //情况一：此时res > 214748364，若执行拼接res * 10 + (c[j] - '0')，res必越界
            //情况二：此时res == 214748364，若拼接后是2147483648或2147483649，res越界
            if(res > bndry || res == bndry && c[j] > '7'){
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            //4、将结果res拼接上讨论的这个字符j
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }

    public static void main(String[] args) {
        System.out.println(strToInt("-91283472332"));
    }
}

package com.example.day31_数学;

/**
 * @Date: 2023/4/2 20:44
 * 剑指 Offer 43. 1～n 整数中 1 出现的次数
 *
 * https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
 *
 * 将n拆分为两部分，最高一位的数字high和其他位的数字last，分别判断情况后将结果相加
 *
 * 1、例子如n=1234，high=1, pow=1000, last=234
 * 可以将数字范围分成两部分1~999和1000~1234
 *
 * 1~999这个范围1的个数是f(pow-1)
 * 1000~1234这个范围1的个数需要分为两部分：
 * 千分位是1的个数：千分位为1的个数刚好就是234+1(last+1)，注意，这儿只看千分位，不看其他位
 * 其他位是1的个数：即是234中出现1的个数，为f(last)
 * 所以全部加起来是f(pow-1) + last + 1 + f(last);
 *
 * 2、例子如3234，high=3, pow=1000, last=234
 * 可以将数字范围分成两部分1~999，1000~1999，2000~2999和3000~3234
 *
 * 1~999这个范围1的个数是f(pow-1)
 * 1000~1999这个范围1的个数需要分为两部分：
 * 千分位是1的个数：千分位为1的个数刚好就是pow，注意，这儿只看千分位，不看其他位
 * 其他位是1的个数：即是999中出现1的个数，为f(pow-1)
 * 2000~2999这个范围1的个数是f(pow-1)
 * 3000~3234这个范围1的个数是f(last)
 * 所以全部加起来是pow + high*f(pow-1) + f(last);
 *
 */
public class Code02_CountDigitOne {
    public int countDigitOne(int n) {
        if(n == 0){
            return 0;
        }
        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int) Math.pow(10, s.length()-1);
        int last = n - high * pow;
        if(high == 1){
            //例如1234。res = 0~999 + 1000~1234
            //         res = 0~999 + (千位是1的个数 + 0~234)
            int p1 = countDigitOne(pow - 1);
            int p2 = last + 1 + countDigitOne(last);
            return p1 + p2;
        }else{
            //例如3234。res =  0~999 + 2000~2999 + 1000~1999 + 3000~3234
            //         res = (0~999 + 2000~2999)+(千位是1的个数 + 0~999) + 3000~3234
            //         res = (0~999 + 2000~2999 + 0~999) + 千位是1的个数 + 3000~3234
            int p1 = countDigitOne(pow-1) * high;
            int p2 = pow;
            int p3 = countDigitOne(last);
            return p1 + p2 + p3;
        }
    }
}

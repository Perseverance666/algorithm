package com.example.day31_数学;

/**
 * @Date: 2023/4/2 20:43
 * 剑指 Offer 14- II. 剪绳子 II
 * <p>
 * https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
 */
public class Code01_CuttingRope {
    //贪心策略：按3进行拆分，即拆出来的数要尽可能有很多的3，这样得到的乘积最大
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long res = 1;
        if (n % 3 == 1) {
            // 有 (n / 3 - 1)个3 和 1个4，先把4减去。 res = 3 ^ (n / 3 - 1) * 4  这样结果最大
            res = 4;
            n = n - 4;
        }
        if (n % 3 == 2) {
            // 有 (n / 3)个3 和 1个2， 先把2减去。 res = 3 ^ (n / 3) * 2 这样结果最大
            res = 2;
            n = n - 2;
        }
        while (n > 0) {
            //多出来的1 或者 2 处理完了。现在用 循环求余 解题
            res = res * 3 % 1000000007;
            n = n - 3;
        }
        return (int) res;
    }
}

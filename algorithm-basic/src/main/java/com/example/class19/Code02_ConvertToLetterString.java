package com.example.class19;

/**
 * @Date: 2023/1/30 12:17
 * 数字字符串转换为字母组合的种数
 * <p>
 * 题目：规定1和数字A对应、2和B对应、...26和Z对应。那么一个数字字符串比如“111”就可以转化为：AAA、KA和AK。
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_ConvertToLetterString {
    /**
     * 1、暴力递归
     *
     * @param str str只含有数字字符0~9
     * @return 返回多少种转化方案
     */
    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }
    //0~i-1位置已经设置好，设置i位置以及往后的转化方案
    public static int process(char[] c, int i) {
        if (i == c.length) {
            //此时所有字符串已经转化好了，转化方案数+1
            return 1;
        }
        if (c[i] == '0') {
            //如果当前字符是0，说明这个方案不行。0只能和10或者20才能符合题意
            return 0;
        }
        //1、选当前一个数字字符来转化。再获取i+1以后的方案数
        int num = process(c, i + 1);
        //2、选两个数字字符来转化。要先判断i+1是否越界以及这两个数字字符是否能转化为字母，再获取i+2以后的方案数
        if (i + 1 < c.length && (c[i] - '0') * 10 + c[i + 1] - '0' <= 26) {
            num += process(c, i + 2);
        }
        return num;
    }

    //2、动态规划
    public static int dp1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] cs = str.toCharArray();
        int N = cs.length;
        //1、建立dp表。列代表当前字符的位置0~N
        int[] dp = new int[N + 1];
        //2、初始化dp表。0~N-1位置字符串已经设置好了，转化方案数+1
        dp[N] = 1;
        //3、填表
        for (int i = N - 1; i >= 0; i--) {
            //只有当前字符不是0才能继续，否则说明这个方案不行
            if (cs[i] != '0') {
                //4、选当前一个数字字符来转化。再获取i+1以后的方案数
                dp[i] = dp[i + 1];
                //5、选两个数字字符来转化。要先判断i+1是否越界以及这两个数字字符是否能转化为字母，再获取i+2以后的方案数
                if (i + 1 < cs.length && (cs[i] - '0') * 10 + cs[i + 1] - '0' <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        //6、返回从0位置开始的转化方案数
        return dp[0];
    }

    // 从左往右的动态规划
    // dp[i]表示：str[0...i]有多少种转化方式
    public static int dp2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if (str[0] == '0') {
            return 0;
        }
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            if (str[i] == '0') {
                // 如果此时str[i]=='0'，那么他是一定要拉前一个字符(i-1的字符)一起拼的，
                // 那么就要求前一个字符，不能也是‘0’，否则拼不了。
                // 前一个字符不是‘0’就够了嘛？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了。
                // 这就够了嘛？还不够，你们拼完了，还得要求str[0...i-2]真的可以被分解！
                // 如果str[0...i-2]都不存在分解方案，那i和i-1拼成了也不行，因为之前的搞定不了。
                if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
                    return 0;
                } else {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
                }
            } else {
                dp[i] = dp[i - 1];
                if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' <= 26) {
                    dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
                }
            }
        }
        return dp[N - 1];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp1(s);
            int ans2 = dp2(s);
            if (ans0 != ans1 || ans0 != ans2) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}

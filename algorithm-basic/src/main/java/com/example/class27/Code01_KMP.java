package com.example.class27;

/**
 * @Date: 2023/2/9 14:06
 * KMP算法实现
 */
public class Code01_KMP {
    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        //xy都可代表字符串数组下标
        int x = 0;
        int y = 0;
        // O(M) m <= n
        int[] next = getNextArray(str2);
        // O(N)·    若跳出循环是因为y越界说明已经从str1中找到str2的片段，若是因为x越界说明没找到
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { //此时y=0
                // abcabcd x=7 ->  abcabcd     x=7 ->  abcabcd         x=7 -> abcabcd_          x=8
                // abcabct y=7 ->     abcabct  y=3 ->        abcabct   y=0 ->        abcabct    y=0
                x++;
            } else {
                //x不变，y来到str[next[y]]位置，此时str1 x位置的字符与str2 y位置的字符比较
                // abcabcd x=7 ->  abcabcd       x=7        xy都可代表字符串数组下标
                // abcabct y=7 ->     abcabct    y=3
                y = next[y];
            }
        }
        //如果y越界，返回str1中包含s tr2片段的字符串开始下标，否则没找到返回-1
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[] { -1 };
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组的值
        int cn = 0; // 当前是哪个位置的值再和i-1位置的字符比较
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) { // 配成功的时候
                //           cn             i-1  i    next[i-1] = 9  next[i]= 10
                // acdbstacd b f e acdbstacd b   k
                next[i++] = ++cn;
            } else if (cn == 0) {
                //  next[cn]=0                                                                 next[i]=0，准备讨论i+1
                //     cn                       i-1  i        cn                             i-1  i
                // acd b s t acd t f e acdbstacd t   k    ->  a c d b s t acd t f e acdbstacd t   k
                next[i++] = 0;
            } else {
                //       next[cn]=3     next[i-1] = 9
                //           cn             i-1  i   ->         cn                       i-1  i
                // acdbstacd t f e acdbstacd b   k   ->     acd b s t acd t f e acdbstacd b   k
                cn = next[cn];
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

package com.example.class28;

/**
 * @Date: 2023/2/10 14:56
 * Manacher算法实现
 * <p>
 * 题目：给定一个字符串str，返回str中最长回文子串的长度
 */
public class Code01_Manacher {
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 将原字符串变成处理字符串。如"12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        //  处理字符串的回文半径的大小
        int[] pArr = new int[str.length];
        // coding：最右的扩成功位置的，再下一个位置。讲述中：R代表最右的扩成功的位置
        int R = -1;
        // C是对于回文直径L~R的中点位置
        int C = -1;
        int max = Integer.MIN_VALUE;
        //1、求处理字符串每一个位置的pArr[i]回文半径的大小
        for (int i = 0; i < str.length; i++) { // 0 1 2
            //1、若i在R外，先pArr[i]=1，因为它至少是1
            //2、若i在R内，综合以下三种可能，i的回文半径 = Math.min(pArr[2 * C - i], R - i)
            //   1）、i‘的回文直径在L~R内，即i'的L'在L右边，i的回文半径=i'的回文半径，pArr[i]=pArr[2*C-i]。i'的回文半径要小
            //   a b c d e d e b a t s t a b e d e d c f      注意：这里面其实有#，为了方便看，就省略了
            //     L L'  i'  R'      C           i     R    pArr[i] = pArr[i']=pArr[2*C-i]=2，
            //   2）、i'的回文直径一部分在L~R外，即i'的L'在L左边，i的回文半径=R-i，pArr[i]=R-i。i'的回文半径要大
            //   a b c d e d c b a t s t a b c d e d c f      注意：这里面其实有#，为了方便看，就省略了
            // L'  L     i'  R'      C(R')       i     R    pArr[i] = R-i=3，
            //   3）、i'的回文直径压在L~R上，即即i'的L'在L位置上，i的回文半径 至少 = i'的回文半径(等于R-i)。具体i的回文半径还要往后比较
            //   a b c d e d c a c t s t c a c d e d c a      注意：这里面其实有#，为了方便看，就省略了
            //     L(L') i'  R'      C           i     R  pArr[i] 至少= pArr[i']=pArr[2*C-i]=R-i=3，这个例子最后就是4

            // R第一个违规的位置，i>= R
            // pArr[i]是i位置扩出来的答案，目前i位置扩的区域，至少是多大。
            //2.1、pArr[i]至少是多少
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;   //重点要记住！！！
            while (i + pArr[i] < str.length && i - pArr[i] > -1) { //i往左和往右的最新的位置不越界
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    //只有上面的2、3）才会走到这里
                    //2.2、结束循环后得到最终的pArr[i]
                    pArr[i]++;
                else {
                    //上面的2、1）和2）会直接结束循环
                    break;
                }
            }
            //2.3、把R位置推得更往右了，更新R，更新C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            //2.4、记录处理串的最大回文半径
            max = Math.max(max, pArr[i]);
        }
        //3、最终求的是处理串的最大回文半径，max-1后就是原字符串的最大回文长度（最大回文直径）
        return max - 1;
    }
    // 将原字符串变成处理字符串。如"12132" -> "#1#2#1#3#2#"
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];  //返回处理字符串
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
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
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

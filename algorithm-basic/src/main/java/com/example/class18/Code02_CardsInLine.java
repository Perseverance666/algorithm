package com.example.class18;

/**
 * @Date: 2023/1/26 11:38
 * 排成一条线的纸牌博弈问题
 *
 * 题目：给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数
 */
public class Code02_CardsInLine {
    //1、暴力递归
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //1、先手玩家最终获得的分数
        int first = f1(arr, 0, arr.length - 1);
        //2、后手玩家最终获得的分数
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }
    //先手从L到R上选择最优的解
    public static int f1(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return arr[L];
        }
        //1、先手挑最左边的牌后得到最优解
        int p1 = arr[L] + g1(arr, L + 1, R);
        //2、先手挑最右边的牌后得到最优解
        int p2 = arr[R] + g1(arr, L, R - 1);
        //3、先手选两种可能中最优的
        return Math.max(p1, p2);
    }
    //后手从L到R上选择最优的解
    public static int g1(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return 0;
        }
        //1、先手的玩家先挑走了最左边的牌
        int p1 = f1(arr, L + 1, R);
        //2、先手的玩家先挑走了最右边的牌
        int p2 = f1(arr, L, R - 1);
        //3、后手玩家只能选两种可能中最差的，因为先手玩家一定是给后手剩下最差的
        return Math.min(p1, p2);
    }

    //从暴力递归到动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }
    // arr[L..R]，先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }
    // // arr[L..R]，后手获得的最好分数返回
    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
            int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    //2、动态规划
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        //建立两张dp表相互依赖。行代表L最左边的值，列代表R最右边的值
        int fDp[][] = new int[N][N];
        int gDp[][] = new int[N][N];
        //1、先将f表对角线初始化。g表对角线都是0，默认初始化好了
        for (int i = 0; i < N; i++) {
            fDp[i][i] = arr[i];
        }
        //2、开始从对角线上面一条对角线开始往上面对角线填，两张表交替填写对角线上的值
        for (int col = 1; col < N; col++) {
            int L = 0;
            int R = col;
            while (R < N) {  //列c 比行r 先越界
                //fDp表和gDp表用来替代之前的递归过程
                fDp[L][R] = Math.max(arr[L] + gDp[L + 1][R], arr[R] + gDp[L][R - 1]);
                gDp[L][R] = Math.min(fDp[L + 1][R], fDp[L][R - 1]);
                //准备填写下一个位置两个表的值
                L++;
                R++;
            }
        }
        //3、返回先手最优和后手最优中值较大的数
        return Math.max(fDp[0][N - 1], gDp[0][N - 1]);
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}

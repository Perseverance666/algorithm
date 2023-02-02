package com.example.class20;

/**
 * @Date: 2023/2/1 14:14
 * <p>
 * 题目：请自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置，
 * 那么整个棋盘就是横坐标上9条线，纵坐标上10条线的区域。
 * 给你三个参数x,y,k，返回“马”从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数有多少种
 */
public class Code03_HorseJump {
    //1、暴力递归
    public static int jump(int x, int y, int k) {
        //(x,y)是目的地。x-> 0~9  y-> 0~8
        //目的地越界 返回0
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return process(0, 0, k, x, y);
    }

    //当前马在(a,b)位置，目的地是(x,y)位置，还剩rest步可以走
    public static int process(int a, int b, int rest, int x, int y) {
        //1、当前位置已经越界
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        //2、所有步数已经走完。第0层只有(x,y)这个点为1，剩下的点都为0
        if (rest == 0) {
            return (a == x && b == y) ? 1 : 0;
        }
        //3、马走日，所以马只有8种走法。将这8中走法累加，其中无效的为0
        int num = process(a + 2, b + 1, rest - 1, x, y);
        num += process(a + 1, b + 2, rest - 1, x, y);
        num += process(a - 1, b + 2, rest - 1, x, y);
        num += process(a - 2, b + 1, rest - 1, x, y);
        num += process(a + 2, b - 1, rest - 1, x, y);
        num += process(a + 1, b - 2, rest - 1, x, y);
        num += process(a - 1, b - 2, rest - 1, x, y);
        num += process(a - 2, b - 1, rest - 1, x, y);

        return num;
    }

    //2、动态规划
    public static int dp(int x, int y, int k) {
        //(x,y)是目的地。x-> 0~9  y-> 0~8
        //目的地越界 返回0
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        //建表，行是马此时的横坐标，列是马此时的纵坐标，高是马剩余可用的步数
        int[][][] dp = new int[10][9][k + 1];
        //1、所有步数已经走完。第0层只有(x,y)这个点为1，剩下的点都为0
        dp[x][y][0] = 1;
        //2、马走日，所以马只有8种走法。将这8中走法累加，其中无效的为0
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    //对于越界的那些点，没法一一赋值，建一个方法getDp()用来获取dp表中当前位置的信息
                    dp[i][j][rest] = getDp(dp, i + 2, j + 1, rest - 1);
                    dp[i][j][rest] += getDp(dp, i + 1, j + 2, rest - 1);
                    dp[i][j][rest] += getDp(dp, i - 1, j + 2, rest - 1);
                    dp[i][j][rest] += getDp(dp, i - 2, j + 1, rest - 1);
                    dp[i][j][rest] += getDp(dp, i + 2, j - 1, rest - 1);
                    dp[i][j][rest] += getDp(dp, i + 1, j - 2, rest - 1);
                    dp[i][j][rest] += getDp(dp, i - 1, j - 2, rest - 1);
                    dp[i][j][rest] += getDp(dp, i - 2, j - 1, rest - 1);
                }
            }
        }
        //3、返回马从(0,0)走k步到(x,y)的方法数
        return dp[0][0][k];

    }
    //获取dp表中当前位置的信息，若当前位置越界返回0
    public static int getDp(int[][][] dp, int a, int b, int rest) {
        //若当前位置已经越界，返回0
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        //若不越界，返回dp表中值
        return dp[a][b][rest];
    }

    // 当前来到的位置是（x,y）
    // 还剩下rest步需要跳
    // 跳完rest步，正好跳到a，b的方法数是多少？
    // 10 * 9
//    public static int jump(int a, int b, int k) {
//        return process(0, 0, k, a, b);
//    }
//
//    public static int process(int x, int y, int rest, int a, int b) {
//        if (x < 0 || x > 9 || y < 0 || y > 8) {
//            return 0;
//        }
//        if (rest == 0) {
//            return (x == a && y == b) ? 1 : 0;
//        }
//        int ways = process(x + 2, y + 1, rest - 1, a, b);
//        ways += process(x + 1, y + 2, rest - 1, a, b);
//        ways += process(x - 1, y + 2, rest - 1, a, b);
//        ways += process(x - 2, y + 1, rest - 1, a, b);
//        ways += process(x - 2, y - 1, rest - 1, a, b);
//        ways += process(x - 1, y - 2, rest - 1, a, b);
//        ways += process(x + 1, y - 2, rest - 1, a, b);
//        ways += process(x + 2, y - 1, rest - 1, a, b);
//        return ways;
//    }
//
//    public static int dp(int a, int b, int k) {
//        int[][][] dp = new int[10][9][k + 1];
//        dp[a][b][0] = 1;
//        for (int rest = 1; rest <= k; rest++) {
//            for (int x = 0; x < 10; x++) {
//                for (int y = 0; y < 9; y++) {
//                    int ways = pick(dp, x + 2, y + 1, rest - 1);
//                    ways += pick(dp, x + 1, y + 2, rest - 1);
//                    ways += pick(dp, x - 1, y + 2, rest - 1);
//                    ways += pick(dp, x - 2, y + 1, rest - 1);
//                    ways += pick(dp, x - 2, y - 1, rest - 1);
//                    ways += pick(dp, x - 1, y - 2, rest - 1);
//                    ways += pick(dp, x + 1, y - 2, rest - 1);
//                    ways += pick(dp, x + 2, y - 1, rest - 1);
//                    dp[x][y][rest] = ways;
//                }
//            }
//        }
//        return dp[0][0][k];
//    }
//
//    public static int pick(int[][][] dp, int x, int y, int rest) {
//        if (x < 0 || x > 9 || y < 0 || y > 8) {
//            return 0;
//        }
//        return dp[x][y][rest];
//    }

    public static int ways(int a, int b, int step) {
        return f(0, 0, step, a, b);
    }

    public static int f(int i, int j, int step, int a, int b) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (step == 0) {
            return (i == a && j == b) ? 1 : 0;
        }
        return f(i - 2, j + 1, step - 1, a, b) + f(i - 1, j + 2, step - 1, a, b) + f(i + 1, j + 2, step - 1, a, b)
                + f(i + 2, j + 1, step - 1, a, b) + f(i + 2, j - 1, step - 1, a, b) + f(i + 1, j - 2, step - 1, a, b)
                + f(i - 1, j - 2, step - 1, a, b) + f(i - 2, j - 1, step - 1, a, b);

    }

    public static int waysdp(int a, int b, int s) {
        int[][][] dp = new int[10][9][s + 1];
        dp[a][b][0] = 1;
        for (int step = 1; step <= s; step++) { // 按层来
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
                            + getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
                            + getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
                            + getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
                }
            }
        }
        return dp[0][0][s];
    }

    // 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
    public static int getValue(int[][][] dp, int i, int j, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        return dp[i][j][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));
        System.out.println(dp(x, y, step));

        System.out.println(jump(x, y, step));
    }
}

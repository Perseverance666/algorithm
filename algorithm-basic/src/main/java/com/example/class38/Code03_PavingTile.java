package com.example.class38;

/**
 * @Date: 2023/2/21 14:58
 * 铺砖问题
 *
 * 题目：铺砖问题（最优解其实是轮廓线dp，但是这个解法对大厂刷题来说比较难，掌握课上的解法即可）
 * 你有无限的1*2的砖块，要铺满M*N的区域，不同的铺法有多少种?
 */
public class Code03_PavingTile {
    /*
     * 2*M铺地的问题非常简单，这个是解决N*M铺地的问题
     */

    public static int ways1(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int[] pre = new int[M]; // pre代表-1行的状况
        for (int i = 0; i < pre.length; i++) {
            pre[i] = 1;
        }
        return process(pre, 0, N);
    }

    // pre 表示level-1行的状态
    // level表示，正在level行做决定
    // N 表示一共有多少行 固定的
    // level-2行及其之上所有行，都摆满砖了
    // level做决定，让所有区域都满，方法数返回
    public static int process(int[] pre, int level, int N) {
        if (level == N) { // base case
            for (int i = 0; i < pre.length; i++) {
                if (pre[i] == 0) {
                    return 0;
                }
            }
            return 1;
        }

        // 没到终止行，可以选择在当前的level行摆瓷砖
        int[] op = getOp(pre);
        return dfs(op, 0, level, N);
    }

    // op[i] == 0 可以考虑摆砖
    // op[i] == 1 只能竖着向上
    public static int dfs(int[] op, int col, int level, int N) {
        // 在列上自由发挥，玩深度优先遍历，当col来到终止列，i行的决定做完了
        // 轮到i+1行，做决定
        if (col == op.length) {
            return process(op, level + 1, N);
        }
        int ans = 0;
        // col位置不横摆
        ans += dfs(op, col + 1, level, N); // col位置上不摆横转
        // col位置横摆, 向右
        if (col + 1 < op.length && op[col] == 0 && op[col + 1] == 0) {
            op[col] = 1;
            op[col + 1] = 1;
            ans += dfs(op, col + 2, level, N);
            op[col] = 0;
            op[col + 1] = 0;
        }
        return ans;
    }

    public static int[] getOp(int[] pre) {
        int[] cur = new int[pre.length];
        for (int i = 0; i < pre.length; i++) {
            cur[i] = pre[i] ^ 1;
        }
        return cur;
    }

    // Min (N,M) 不超过 32
    public static int ways2(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int max = Math.max(N, M);
        int min = Math.min(N, M);
        int pre = (1 << min) - 1;
        return process2(pre, 0, max, min);
    }

    // 上一行的状态，是pre，limit是用来对齐的，固定参数不用管
    // 当前来到i行，一共N行，返回填满的方法数
    public static int process2(int pre, int i, int N, int M) {
        if (i == N) { // base case
            return pre == ((1 << M) - 1) ? 1 : 0;
        }
        int op = ((~pre) & ((1 << M) - 1));
        return dfs2(op, M - 1, i, N, M);
    }

    public static int dfs2(int op, int col, int level, int N, int M) {
        if (col == -1) {
            return process2(op, level + 1, N, M);
        }
        int ans = 0;
        ans += dfs2(op, col - 1, level, N, M);
        if ((op & (1 << col)) == 0 && col - 1 >= 0 && (op & (1 << (col - 1))) == 0) {
            ans += dfs2((op | (3 << (col - 1))), col - 2, level, N, M);
        }
        return ans;
    }

    // 记忆化搜索的解
    // Min(N,M) 不超过 32
    public static int ways3(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int max = Math.max(N, M);   //max作为行数
        int min = Math.min(N, M);   //min作为列数
        int pre = (1 << min) - 1;   //pre用二进制形式表示上一行的状态，有min位
        //dp[i][j]代表上一层的状态为i，当前讨论该层的j行
        int[][] dp = new int[1 << min][max + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(pre, 0, max, min, dp);
    }
    //pre是上一行的状态，i是当前行，N是总共有多少行，M是总共有多少列，dp是缓存
    public static int process3(int pre, int i, int N, int M, int[][] dp) {
        if (dp[pre][i] != -1) {
            //缓存里有直接拿
            return dp[pre][i];
        }
        int ans = 0;
        if (i == N) {
            //所有层都讨论完了，若上层满了，铺法+1，若上层不满，铺法+0
            ans = pre == ((1 << M) - 1) ? 1 : 0;
        } else {
            //讨论第i层，op二进制为0的位置表示 需要去深度优先遍历去讨论怎么放砖。为1的位置就代表不用讨论了，必须竖着放
            int op = ((~pre) & ((1 << M) - 1));
            //从最后一列往第0列开始讨论
            ans = dfs3(op, M - 1, i, N, M, dp);
        }
        dp[pre][i] = ans;
        return ans;

    }
    //op能表示哪些列可能考虑摆砖，col是当前列，level是当前行
    public static int dfs3(int op, int col, int level, int N, int M, int[][] dp) {
        if (col == -1) {
            //这一行的所有列讨论完了，准备讨论下一行
            return process3(op, level + 1, N, M, dp);
        }
        int ans = 0;
        //1、砖竖着放
        ans += dfs3(op, col - 1, level, N, M, dp);
        if (col > 0 && (op & (3 << (col - 1))) == 0) { //3的二进制11左移col-1位
            //2、砖能横着放
            ans += dfs3((op | (3 << (col - 1))), col - 2, level, N, M, dp);
            //注：由于是值传递，op最终不会改变，不用恢复现场
        }
        return ans;
    }

    // 严格位置依赖的动态规划解
    public static int ways4(int N, int M) {
        if (N < 1 || M < 1 || ((N * M) & 1) != 0) {
            return 0;
        }
        if (N == 1 || M == 1) {
            return 1;
        }
        int big = N > M ? N : M;
        int small = big == N ? M : N;
        int sn = 1 << small;
        int limit = sn - 1;
        int[] dp = new int[sn];
        dp[limit] = 1;
        int[] cur = new int[sn];
        for (int level = 0; level < big; level++) {
            for (int status = 0; status < sn; status++) {
                if (dp[status] != 0) {
                    int op = (~status) & limit;
                    dfs4(dp[status], op, 0, small - 1, cur);
                }
            }
            for (int i = 0; i < sn; i++) {
                dp[i] = 0;
            }
            int[] tmp = dp;
            dp = cur;
            cur = tmp;
        }
        return dp[limit];
    }

    public static void dfs4(int way, int op, int index, int end, int[] cur) {
        if (index == end) {
            cur[op] += way;
        } else {
            dfs4(way, op, index + 1, end, cur);
            if (((3 << index) & op) == 0) { // 11 << index 可以放砖
                dfs4(way, op | (3 << index), index + 1, end, cur);
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        int M = 6;
        System.out.println(ways1(N, M));
        System.out.println(ways2(N, M));
        System.out.println(ways3(N, M));
        System.out.println(ways4(N, M));

        N = 10;
        M = 10;
        System.out.println("=========");
        System.out.println(ways3(N, M));
        System.out.println(ways4(N, M));
    }

}

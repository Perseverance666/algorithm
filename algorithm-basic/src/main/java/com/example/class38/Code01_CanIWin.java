package com.example.class38;

/**
 * @Date: 2023/2/21 14:50
 * 我能赢么
 *
 * 题目：在"100 game"这个游戏中，两名玩家轮流选择从1到10的任意整数，累计整数和
 * 先使得累计整数和达到或超过100的玩家，即为胜者，如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从1到15的整数（不放回），直到累计整数和 >= 100
 * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和）
 * 判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）
 * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300
 *
 * 谁选完之后，累计和 >=desiredTotal，谁就赢，此时下一个人还可用的累计和<=0
 *
 * 测试链接：https://leetcode.cn/problems/can-i-win/
 */
public class Code01_CanIWin {
    // 1~choose 拥有的数字
    // total 一开始的剩余
    // 返回先手会不会赢
    public static boolean canIWin0(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] arr = new int[choose];
        for (int i = 0; i < choose; i++) {
            arr[i] = i + 1;
        }
        // arr[i] != -1 表示arr[i]这个数字还没被拿走
        // arr[i] == -1 表示arr[i]这个数字已经被拿走
        // 集合，arr，1~choose
        return process(arr, total);
    }

    // 当前轮到先手拿，
    // 先手只能选择在arr中还存在的数字，
    // 还剩rest这么值，
    // 返回先手会不会赢
    public static boolean process(int[] arr, int rest) {
        if (rest <= 0) {
            return false;
        }
        // 先手去尝试所有的情况
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                int cur = arr[i];
                arr[i] = -1;
                boolean next = process(arr, rest - cur);
                arr[i] = cur;
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }

    // 这个是暴力尝试，思路是正确的，超时而已
    public static boolean canIWin1(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        return process1(choose, 0, total);
    }

    // 当前轮到先手拿，
    // 先手可以拿1~choose中的任何一个数字
    // status   i位如果为0，代表没拿，当前可以拿
    //          i位为1，代表已经拿过了，当前不能拿
    // 还剩rest这么值，
    // 返回先手会不会赢
    public static boolean process1(int choose, int status, int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 1; i <= choose; i++) {
            if (((1 << i) & status) == 0) { // i 这个数字，是此时先手的决定！
                if (!process1(choose, (status | (1 << i)), rest - i)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 暴力尝试改动态规划而已
    public static boolean canIWin2(int choose, int total) {
        if (total == 0) {
            //累计和为0，算先手赢
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            //1~choose所有数加起来都不能到total，算先手输
            return false;
        }
        //用位信息来代表每个数是否被选，为了方便第0位不用
        //例choose=4，此时选数的所有可能就是 0000_ ~ 1111_，共有10000，即16种可能
        int[] dp = new int[1 << (choose + 1)];  //傻缓存
        //status=0代表此时什么数都可以选，讨论这种可能
        return process2(choose, 0, total, dp);
    }

    // 为什么明明status和rest是两个可变参数，却只用status来代表状态(也就是dp)
    // 因为选了一批数字之后，得到的和一定是一样的，所以rest是由status决定的，所以rest不需要参与记忆化搜索
    // status代表此时的状态，即哪些数可选和不可选，rest代表累计和还剩多少
    public static boolean process2(int choose, int status, int rest, int[] dp) {
        if (dp[status] != 0) {
            //算完了， dp[status] == 1  true 代表先手赢了。dp[status] == -1  false 代表先手输了
            return dp[status] == 1 ? true : false;
        }
        // dp[status] == 0  process(status) 没算过！去算！
        boolean ans = false;
        if (rest > 0) {
            //准备讨论先手选数的所有可能，选完1~choose其中一个数后，是否能赢
            //用位信息来代表每个数是否被选，为了方便第0位不用，从第1位开始到第choose位
            for (int i = 1; i <= choose; i++) {
                if (((1 << i) & status) == 0) {
                    //第i位位0，才能去选。先手选择第i位上的数
                    if (!process2(choose, (status | (1 << i)), rest - i, dp)) {
                        //只要后手输了，先手这次就赢了，找到先手赢的情况结束循环
                        ans = true;
                        break;
                    }
                }
            }
        }
        //这个status的请款讨论完了
        dp[status] = ans ? 1 : -1;
        return ans;
    }
}

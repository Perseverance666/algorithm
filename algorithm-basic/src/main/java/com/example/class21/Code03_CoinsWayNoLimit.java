package com.example.class21;

/**
 * @Date: 2023/2/2 14:34
 * 换钱的方法数--题目2
 * <p>
 * 题目：arr是面值数组，其中的值都是正数且没有重复，再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的，返回组成aim的方法数。
 * 例如：arr = {1,2}，aim =4。方法如下：1+1+1+1、1+1+2、2+2，一共就3种方法，所以返回
 */
public class Code03_CoinsWayNoLimit {
    //1、暴力递归
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }
    // 当前讨论第index号钱，之前的已经讨论完了，此时还需要rest
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            //index越界了，没钱了，若rest=0，方法数+1
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        //每张钱都可以选择0,1,2.。。。张， 其中面值 * 张数 <= rest
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            //由于rest - zhang * arr[index] >= 0，即每次递归调用，rest >=0，肯定不越界，故不用在做判断了
            ways += process(arr, index + 1, rest - zhang * arr[index]);
        }
        return ways;
    }


    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    //2、动态规划
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        //建表，dp[i][j]表示目标j元钱，从第i号货币开始选择的方法数
        int[][] dp = new int[N + 1][aim + 1];
        //1、初始化表，(N,0)为1，剩下N行的所有列都是0。此时货币都讨论完了，没钱了，只有目标前为0，方法数才+1
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
//                //2.1、填表。直接3层循环填表，dp[index][rest]依赖dp[index + 1][rest - (zhang * arr[index])]满足条件下的所有位置
//                //每张钱都可以选择0,1,2.。。。张， 其中面值 * 张数 <= rest
//                int ways = 0;
//                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//                    ways += dp[index+1][rest - zhang * arr[index]];
//                }
//                dp[index][rest] = ways;
                //2.2、填表。通过列表观察！！发现dp[index][rest]依赖dp[index + 1][rest] 和 dp[index][rest - arr[index]](确保不越界)。避免了第3层for循环
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] < 0 ? 0 : dp[index][rest - arr[index]]);
            }
        }
        //3、返回目标aim元钱，从第0号货币开始选择的方法数
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}

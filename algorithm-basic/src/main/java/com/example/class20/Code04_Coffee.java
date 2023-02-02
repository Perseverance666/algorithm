package com.example.class20;

import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date: 2023/2/1 14:14
 * 喝咖啡问题，难
 * <p>
 * 题目：数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * 四个参数：arr, n, a, b
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 */
public class Code04_Coffee {
    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // 以下为贪心+优良暴力
    //咖啡机信息
    public static class Machine {
        public int timePoint;          //这个咖啡机可以开始冲咖啡的时间点
        public int workTime;           //这个咖啡机冲一杯咖啡所需要的时间

        //(1,3)代表1这个时间点可以开始冲咖啡，冲咖啡需要3
        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    //比较器，冲完一杯咖啡时间点最早的排在前面
    public static class MachineComparator implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    // 优良一点的暴力尝试的方法
    //核心思想：首先求出每个人喝完咖啡的最优时间，即每个杯子可以开始洗的时间(小根堆)，再求出所有杯子变干净的最优时间(动态规划)
    //1、暴力递归
    public static int minTime1(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
        for (int i = 0; i < arr.length; i++) {
            //初始化小根堆，此时每台咖啡机都可以使用
            heap.add(new Machine(0, arr[i]));
        }
        //drink[i]代表第i号人喝完咖啡的时间，即第i号杯子可以开始洗的时间。利用小根堆，这个时间是最优的
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            //选择最合适的咖啡机
            Machine cur = heap.poll();
            //更新这台咖啡机下一次可以冲咖啡的时间
            cur.timePoint += cur.workTime;
            heap.add(cur);
            //第i个人喝完咖啡的时间点，即第i号杯子可以开始洗的时间
            drinks[i] = cur.timePoint;
        }
        //从第0号杯子开始洗，此时洗咖啡机free=0,可以开始洗
        return bestTime(drinks, a, b, 0, 0);
    }

    /**
     * 洗完index及剩下杯子最早的结束时间
     *
     * @param drinks 所有杯子可以开始洗的时间
     * @param wash   单杯洗干净的时间（串行）
     * @param air    挥发干净的时间(并行)
     * @param index  第几号杯子
     * @param free   洗咖啡机什么时候可用
     * @return drinks[index.....]所有杯子都变干净，最早的结束时间（返回）
     */
    public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            //说明所有杯子都洗完了,就不用洗了
            return 0;
        }
        //1、index号杯子用洗咖啡机洗
        //index杯子洗完的时间
        int cleanIndex1 = Math.max(drinks[index], free) + wash;
        //剩余杯子变干净时间
        int cleanOther1 = bestTime(drinks, wash, air, index + 1, cleanIndex1);
        //所有杯子变干净的时间
        int p1 = Math.max(cleanIndex1, cleanOther1);

        //2、index号杯子挥发
        //index杯子挥发完的时间
        int cleanIndex2 = drinks[index] + air;
        //剩余杯子变干净时间
        int cleanOther2 = bestTime(drinks, wash, air, index + 1, free);
        //所有杯子变干净的时间
        int p2 = Math.max(cleanIndex2, cleanOther2);

        //3、就上面这两种可能,返回时间最早的
        return Math.min(p1, p2);
    }

    //2、动态规划
    public static int minTime2(int[] arr, int n, int a, int b) {
        //1、建立小根堆，用来获取并排序每个杯子最早可以开始洗的时间
        PriorityQueue<Machine> heap = new PriorityQueue<>(new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
            }
        });
        //初始化小根堆
        for (int i = 0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        //每个杯子最早可以开始洗的时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            //选择最合适的咖啡机
            Machine cur = heap.poll();
            //更新这台咖啡机的信息，并重新放回小根堆
            cur.timePoint += cur.workTime;
            heap.add(cur);
            //获取这个杯子可以开始洗的时间
            drinks[i] = cur.timePoint;
        }
        //2、动态规划，用来求所有杯子变干净的最优时间
        int maxFree = 0;
        for (int i = 0; i < n; i++) {
            //2.1、获取洗碗机能使用的最晚时间
            maxFree = Math.max(maxFree, drinks[i]) + a;
        }
        //建表，行是第几个杯子，列是洗咖啡机可以使用的时间点。因为列的范围不好规定，用最差情况来作为列的边界
        //dp[i][j]代表 在洗咖啡机j时间点可以使用时 第i号及剩下的所有杯子变干净的最早时间
        //dp[n][...] = 0; 建表时已经初始化好了，就不用在写了。第n行代表所有杯子都洗完了，就不用洗了
        int[][] dp = new int[n + 1][maxFree + 1];
        for (int index = n - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                //2.2、index杯子就下面面这两种可能,返回时间最早的
                //2.2.1、index号杯子用洗咖啡机洗
                //index杯子洗完的时间
                int cleanIndex1 = Math.max(drinks[index], free) + a;
                if(cleanIndex1 > maxFree){
                    //越界了，index号杯子后面的也不用填了
                    break;
                }
                //剩余杯子变干净时间
                int cleanOther1 = dp[index + 1][cleanIndex1];
                //所有杯子变干净的时间
                int p1 = Math.max(cleanIndex1, cleanOther1);
                //2.2.2、index号杯子挥发
                //index杯子挥发完的时间
                int cleanIndex2 = drinks[index] + b;
                //剩余杯子变干净时间
                int cleanOther2 = dp[index + 1][free];
                //所有杯子变干净的时间
                int p2 = Math.max(cleanIndex2, cleanOther2);
                //2.2.3、就上面这两种可能,选择时间最早的
                dp[index][free] = Math.min(p1,p2);
            }
        }
        //2.3、返回在洗咖啡机0时间点可以使用时 第0号及剩下的所有杯子变干净的最早时间
        return dp[0][0];
    }


//    // 贪心+优良尝试改成动态规划
//    public static int minTime2(int[] arr, int n, int a, int b) {
//        PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
//        for (int i = 0; i < arr.length; i++) {
//            heap.add(new Machine(0, arr[i]));
//        }
//        int[] drinks = new int[n];
//        for (int i = 0; i < n; i++) {
//            Machine cur = heap.poll();
//            cur.timePoint += cur.workTime;
//            drinks[i] = cur.timePoint;
//            heap.add(cur);
//        }
//
//        return bestTimeDp(drinks, a, b);
//    }
//
//    public static int bestTimeDp(int[] drinks, int wash, int air) {
//        int N = drinks.length;
//        int maxFree = 0;
//        for (int i = 0; i < drinks.length; i++) {
//            maxFree = Math.max(maxFree, drinks[i]) + wash;
//        }
//        int[][] dp = new int[N + 1][maxFree + 1];
//        for (int index = N - 1; index >= 0; index--) {
//            for (int free = 0; free <= maxFree; free++) {
//                int selfClean1 = Math.max(drinks[index], free) + wash;
//                if (selfClean1 > maxFree) {
//                    break; // 因为后面的也都不用填了
//                }
//                // index号杯子 决定洗
//                int restClean1 = dp[index + 1][selfClean1];
//                int p1 = Math.max(selfClean1, restClean1);
//                // index号杯子 决定挥发
//                int selfClean2 = drinks[index] + air;
//                int restClean2 = dp[index + 1][free];
//                int p2 = Math.max(selfClean2, restClean2);
//                dp[index][free] = Math.min(p1, p2);
//            }
//        }
//        return dp[0][0];
//    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }
}

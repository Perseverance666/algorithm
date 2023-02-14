package com.example.class32;

/**
 * @Date: 2023/2/14 14:50
 *
 * 一维IndexTree实现：关键在于利用指定数最右侧的1
 * 1、随便一个二进制的数，需要在index上+d，index不断加上最右侧的1，这些index的数组值都需要+d
 * 2、随便一个二进制的数，求1~这个数的累加和，index不断减去最右侧的1，这些index的数组值的累加和即为所求
 */
public class Code01_IndexTree {
    // 下标从1开始！
    public static class IndexTree {
        private int[] tree;
        private int N;

        // 0位置弃而不用！
        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        // 1~index 累加和是多少
        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                //删去最右侧的1，就是上一个需要累加的位置
                index -= index & -index;
            }
            return ret;
        }

        // index & -index : 提取出index最右侧的1出来
        // index :           0011001000
        // index & -index :  0000001000
        //在index位置加d。还会影响后面的某些位置
        public void add(int index, int d) {
            while (index <= N) {
                tree[index] += d;
                //加上最右侧的1，就是下一个受牵连的位置
                index += index & -index;
            }
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }
}

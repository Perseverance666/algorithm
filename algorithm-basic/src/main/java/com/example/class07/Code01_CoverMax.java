package com.example.class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date: 2023/1/7 14:05
 * 最大线段重合问题
 */
public class Code01_CoverMax {
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            //检查每一个0.5 有多少重合线段
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static int maxCover2(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        //1、首先把所有线段按左端点从小到大排序
        Arrays.sort(lines, new StartComparator());
        //2、创建小根堆，用于存放每一个线段的右端点
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        //3、依次检验每个线段的 以这个线段左端点开头的重合线段的个数
        int max = 0;  //最大线段重合数
        for (int i = 0; i < lines.length; i++) {
            // lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
            //4、若小根堆不为空 并且 小跟堆顶值小于当前线段的左端点，说明之前的这个线段不与当前线段重合，从小根堆中把它弹出。
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            //5、此时小跟堆中线段都是与当前线段重合的，把当前线段的右端点也放进小根堆中
            heap.add(lines[i].end);
            //6、小根堆中存放线段右端点的数量就是与当前线段重合的线段数
            //若当前线段的重合线段数比之前的线段重合数大，更新max，否则继续遍历下一个线段
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }

    }

    // 和maxCover2过程是一样的
    // 只是代码更短
    // 不使用类定义的写法
    public static int maxCover3(int[][] m) {
        // m是二维数组，可以认为m内部是一个一个的一维数组
        // 每一个一维数组就是一个对象，也就是线段
        // 如下的code，就是根据每一个线段的开始位置排序
        // 比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }

        //1、首先把所有线段按左端点从小到大排序
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
        //2、创建小根堆，用于存放每一个线段的右端点
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //3、依次检验每个线段的 以这个线段左端点开头的重合线段的个数
        int max = 0;
        for (int[] line : m) {
            //4、若小根堆不为空 并且 小跟堆顶值小于当前线段的左端点，说明之前的这个线段不与当前线段重合，从小根堆中把它弹出。
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            //5、此时小跟堆中线段都是与当前线段重合的，把当前线段的右端点也放进小根堆中
            heap.add(line[1]);
            //6、小根堆中存放线段右端点的数量就是与当前线段重合的线段数
            //若当前线段的重合线段数比之前的线段重合数大，更新max，否则继续遍历下一个线段
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}

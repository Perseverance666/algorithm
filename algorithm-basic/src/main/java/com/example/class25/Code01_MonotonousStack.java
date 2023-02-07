package com.example.class25;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @Date: 2023/2/7 13:47
 * 单调栈结构
 * 这里的单调栈从底到上单调递增
 */
public class Code01_MonotonousStack {
    // arr = [ 3, 1, 2, 3]
    //         0  1  2  3
    //  res =
    //  [
    //     0 : [-1,  1]
    //     1 : [-1, -1]
    //     2 : [ 1, -1]
    //     3 : [ 2, -1]
    //  ]
    //单调栈，原数组元素不重复
    public static int[][] getNearLessNoRepeat(int[] arr) {
        // res二维数组存的是原数组下标，没有存-1。
        // res[i][0]代表原数组第i个位置的这个数左边比它小的最近的数的下标。res[i][1]代表原数组第i个位置的这个数右边比它小的最近的数的下标
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) { // 当遍历到i位置的数，arr[i]
            //1、当前数arr[i]无法加入到栈中
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                //2、弹出记录栈顶元素的相关信息
                int j = stack.pop();
                //3、若栈为空，j元素左边没有比它小的最近的数，返回-1。栈不为空，弹出j后的栈顶元素就是j元素左边比它小的最近的数
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[j][0] = leftLessIndex;
                //4、arr[i]就是j元素右边比它小的最近的数
                res[j][1] = i;
            }
            stack.push(i);
        }
        //5、arr数组已经全部放入到栈中过了，此时栈中还有元素，依次弹出并记录相关信息
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[j][0] = leftLessIndex;
            //已经没有arr[i]了都讨论完了，所以j元素右边没有比它小的最近的数
            res[j][1] = -1;
        }
        return res;
    }

    //单调栈，原数组元素有重复的
    public static int[][] getNearLess(int[] arr) {
        // res二维数组存的是原数组下标，没有存-1。
        // res[i][0]代表原数组第i个位置的这个数左边比它小的最近的数的下标。res[i][1]代表原数组第i个位置的这个数右边比它小的最近的数的下标
        int[][] res = new int[arr.length][2];
        //单调栈结构，存的是一组原数组值相等的那些下标
        Stack<List<Integer>> stack = new Stack<>();
        //1、开始从数组第一个元素开始讨论
        for (int i = 0; i < arr.length; i++) {
            //2、栈顶元素对应的值严格 > arr[i]，弹出栈顶元素并记录所有下标的信息
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                //3、若栈为空，这组下标所有的数左边没有比它小的最近的数，返回-1。
                //栈不为空，弹出后的栈顶元素中一组下标最右边的下标所对应的值就是这组下标所有的数左边比它小的最近的数
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popI : popIs) {
                    res[popI][0] = leftLessIndex;
                    //4、arr[i]就是popIs所有数的右边比它小的最近的数
                    res[popI][1] = i;
                }
            }
            //5、检验栈顶元素的所对应的值是否与arr[i]相等
            //5.1、若相等，加入到集合中
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                //5.2、若不相等，创建新的集合并加入栈中
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //6、arr数组已经全部放入到栈中过了，此时栈中还有元素，依次弹出并记录相关信息
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            for (Integer popI : popIs) {
                res[popI][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                //已经没有arr[i]了都讨论完了，popIs所有数的右边都没有比它小的最近的数
                res[popI][1] = -1;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}

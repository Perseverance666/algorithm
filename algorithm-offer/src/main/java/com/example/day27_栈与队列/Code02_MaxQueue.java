package com.example.day27_栈与队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/3/28 16:06
 * 面试题59 - II. 队列的最大值
 * <p>
 * https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof
 */
public class Code02_MaxQueue {
    public class MaxQueue {
        Queue<Integer> queue;   //正常的双端队列
        Deque<Integer> deque;   //存放最大值的双端队列。deque的first端永远是queue中的的最大值

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            return deque.isEmpty() ? -1 : deque.peekFirst();
        }

        public void push_back(int value) {
            queue.add(value);
            //确保deque的first端永远是queue中的的最大值
            while (!deque.isEmpty() && value > deque.peekLast()) {
                deque.pollLast();
            }
            deque.addLast(value);

        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            //要弹出的元素正好是队列中最大的元素，deque中要删除
            if (queue.peek().equals(deque.peekFirst())) {
                deque.pollFirst();
            }
            return queue.poll();
        }
    }

    /**
     * Your MaxQueue object will be instantiated and called as such:
     * MaxQueue* obj = new MaxQueue();
     * int param_1 = obj->max_value();
     * obj->push_back(value);
     * int param_3 = obj->pop_front();
     */
}

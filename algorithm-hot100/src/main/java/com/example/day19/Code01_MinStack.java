package com.example.day19;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Date: 2023/5/14 21:36
 * 155. 最小栈
 * <p>
 * https://leetcode.cn/problems/min-stack/
 */
public class Code01_MinStack {
    public class MinStack {
        Deque<Integer> stack;
        Deque<Integer> minStack;

        public MinStack() {
            stack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(val, minStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}

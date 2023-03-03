package com.example.day01;

import java.util.Stack;

/**
 * @Date: 2023/3/3 14:34
 * 剑指 Offer 30. 包含min函数的栈
 * 思路：用两个栈，一个栈是数据栈，另一个栈栈顶元素一直保持着数据栈的最小值，两个栈元素个数一样
 */
public class Code02_MinStack {
    class MinStack {
        public Stack<Integer> dataStack;
        public Stack<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            dataStack = new Stack<Integer>();
            minStack = new Stack<Integer>();
        }

        public void push(int x) {
            dataStack.push(x);
            if(minStack.isEmpty()){
                minStack.push(x);
            }else {
                minStack.push(Math.min(x,minStack.peek()));
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }
}

package com.example.day01_栈与队列;

import java.util.Stack;

/**
 * @Date: 2023/3/2 21:00
 * 剑指 Offer 09. 用两个栈实现队列
 * 思路：准备两个栈，入队列时元素进pushStack，出队列时元素从popStack弹出，
 * 若出栈为空，将入栈元素添加到出栈。若入栈为空，说明队列中没有元素
 */
public class Code01_CQueue {
    class CQueue {

        public Stack<Integer> pushStack;
        public Stack<Integer> popStack;

        public CQueue() {
            pushStack = new Stack<Integer>();
            popStack = new Stack<Integer>();
        }

        public void appendTail(int value) {
            pushStack.push(value);
        }

        public int deleteHead() {
            if(popStack.isEmpty()){
                pushToPop();
            }
            return popStack.isEmpty() ? -1 : popStack.pop();
        }
        public void pushToPop(){
            while(!pushStack.isEmpty()){
                popStack.push(pushStack.pop());
            }
        }
    }

}

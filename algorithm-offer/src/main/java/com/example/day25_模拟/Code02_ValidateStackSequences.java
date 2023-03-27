package com.example.day25_模拟;

import java.util.Stack;

/**
 * @Date: 2023/3/26 21:20
 * 剑指 Offer 31. 栈的压入、弹出序列
 *
 * https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 */
public class Code02_ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed){
            stack.add(num);
            while(!stack.isEmpty() && stack.peek() == popped[i]){
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}

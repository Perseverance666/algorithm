package com.example.class17;

import java.util.Stack;

/**
 * 使用递归函数逆序栈
 * 题目：给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 * 思想：拿出一个元素，剩余元素递归，再把拿出的元素放到最后的位置
 * @Date: 2023/1/25 22:13
 */
public class Code04_ReverseStackUsingRecursive {
    //将栈中元素翻转
    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        //1、获取栈底元素
        int last = f(stack);
        //2、将栈中剩余元素翻转
        reverse(stack);
        //3、在将栈底元素放回栈顶
        stack.push(last);
    }
    //将栈底元素移除并返回
    //递归过程中到最后一层才知道栈底元素last，然后一层一层返回，这样每层都知道last了。然后将每层弹出的栈顶元素result在放回去
    public static int f(Stack<Integer> stack) {
        //1、弹出栈顶元素
        int result = stack.pop();
        if(stack.isEmpty()){
            //弹出元素后栈为空，说明这个元素是栈底元素，返回
            return result;
        } else{
            //2、获取此时栈底元素
            int last = f(stack);
            //3、将之前弹出的元素再放回栈中
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}

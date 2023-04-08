package com.example.day04;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Date: 2023/4/8 20:37
 * 20. 有效的括号
 * <p>
 * https://leetcode.cn/problems/valid-parentheses/?favorite=2cktkvj
 */
public class Code02_isValid {
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        if (str.length % 2 == 1) {
            return false;
        }

        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            if (map.containsKey(str[i])) {
                //当前字符是 右半部分
                if (stack.isEmpty() || stack.peek() != map.get(str[i])) {
                    return false;
                }
                stack.pop();
            } else {
                //当前字符是 左半部分
                stack.push(str[i]);
            }
        }
        return stack.isEmpty();
    }

//    //自己写的
//    public boolean isValid(String s) {
//        char[] str = s.toCharArray();
//        Stack<Character> stack = new Stack<>();
//        for (int i = 0; i < str.length; i++) {
//            if (str[i] == '(' || str[i] == '[' || str[i] == '{') {
//                stack.push(str[i]);
//            } else {
//                if(stack.isEmpty()) return false;
//                if (stack.peek() == '(' && str[i] == ')' ||
//                        stack.peek() == '[' && str[i] == ']' ||
//                        stack.peek() == '{' && str[i] == '}') {
//                    stack.pop();
//                }else{
//                    return false;
//                }
//            }
//        }
//        return stack.isEmpty();
//    }

}

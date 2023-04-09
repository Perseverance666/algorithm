package com.example.day05;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/4/8 22:17
 * 22. 括号生成
 * <p>
 * https://leetcode.cn/problems/generate-parentheses/?favorite=2cktkvj
 */
public class Code01_generateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        getParenthesis(new StringBuilder(), n, n, res);
        return res;
    }

    //str表示之前拼接的结果，left表示左括号还有几个可以使用，right表示右括号还有几个可以使用，res表示最终结果集
    public void getParenthesis(StringBuilder str, int left, int right, List<String> res) {
        //要想符合条件，left <= right
        if (left == 0 && right == 0) {
            //所有括号都用完了，将结果放进结果集
            res.add(str.toString());
            return;
        }
        if (left == right) {
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str.append("("), left - 1, right, res);
        } else if(left < right){
            //剩余左括号小于右括号
            StringBuilder copy = new StringBuilder(str);
            if(left > 0){
                //左括号还有剩余时可以用左括号
                getParenthesis(copy.append("("), left - 1, right, res);
            }
            //肯定可以用右括号
            getParenthesis(str.append(")"), left, right - 1, res);
        }
    }
}

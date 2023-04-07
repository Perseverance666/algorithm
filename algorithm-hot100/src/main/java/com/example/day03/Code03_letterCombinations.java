package com.example.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 2023/4/6 22:56
 * 17. 电话号码的字母组合
 *
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/?favorite=2cktkvj
 */
public class Code03_letterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits.length() == 0) {
            return res;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        char[] str = digits.toCharArray();
        backtrack(res, phoneMap, str, 0, new StringBuffer());
        return res;
    }

    //从digits[index]开始讨论组合
    public void backtrack(List<String> res, Map<Character, String> phoneMap, char[] digits, int index, StringBuffer sb) {
        //base case
        if(index == digits.length){
            //所有数字字符都讨论完了，将组合好的sb放到结果集res中
            res.add(sb.toString());
        }else{
            //讨论当前index字符
            String s = phoneMap.get(digits[index]);
            char[] sArr = s.toCharArray();
            for(int i = 0; i < sArr.length; i++){
                //当前index字符选择sArr[i]
                sb.append(sArr[i]);
                //讨论digits[index+1]往后的字符
                backtrack(res,phoneMap,digits,index + 1, sb);
                //恢复现场
                sb.deleteCharAt(index);
            }
        }
    }
}

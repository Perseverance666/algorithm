package com.example.day03;

/**
 * @Date: 2023/3/4 19:55
 * 剑指 Offer 05. 替换空格
 */
public class Code01_ReplaceSpace {
    public String replaceSpace(String s) {
        //' ' 换成'%20'，新数组最长为 s.length() * 3
        char[] ch = new char[s.length() * 3];
        int j = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' '){
                ch[j++] = '%';
                ch[j++] = '2';
                ch[j++] = '0';
            }else {
                ch[j++] = c;
            }
        }
        return new String(ch,0,j);
    }

}

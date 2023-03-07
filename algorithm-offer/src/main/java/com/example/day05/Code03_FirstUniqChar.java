package com.example.day05;

import java.util.HashMap;

/**
 * @Date: 2023/3/6 21:13
 * 剑指 Offer 50. 第一个只出现一次的字符
 */
public class Code03_FirstUniqChar {
    //    public char firstUniqChar(String s) {
//        HashMap<Character,Integer> map = new HashMap<>();
//        for(int i = 0; i < s.length(); i++){
//            char c = s.charAt(i);
//            if(!map.containsKey(c)){
//                map.put(c,1);
//            }else {
//                map.put(c,map.get(c) + 1);
//            }
//        }
//        for(int i = 0; i < s.length(); i++){
//            if(map.get(s.charAt(i)) == 1){
//                return s.charAt(i);
//            }
//        }
//        return ' ';
//    }
    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> map = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc)
            map.put(c, !map.containsKey(c));
        for (char c : sc)
            if (map.get(c)) return c;
        return ' ';
    }


}

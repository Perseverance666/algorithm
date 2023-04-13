package com.example.day08;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * @Date: 2023/4/11 15:53
 * 49. 字母异位词分组
 * <p>
 * https://leetcode.cn/problems/group-anagrams/?favorite=2cktkvj
 */
public class Code02_groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);  //排序
            String key = new String(str);
            List<String> list = new ArrayList<>();
            if (map.containsKey(key)) {
                list = map.get(key);
            }
            list.add(strs[i]);
            map.put(key, list);
        }

        for (Map.Entry<String, List<String>> entries : map.entrySet()) {
            res.add(entries.getValue());
        }
        return res;
    }
}

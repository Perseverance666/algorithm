package com.example.class08;

/**
 * @Date: 2023/1/8 18:52
 *  测试链接 : https://leetcode.cn/problems/implement-trie-ii-prefix-tree/
 *  提交Trie类可以直接通过
 *  原来代码是对的，但是既然找到了直接测试的链接，那就直接测吧
 *  这个链接上要求实现的功能和课上讲的完全一样
 *  该前缀树的路用数组实现
 *
 *  前缀树
 */

public class Code01_TrieAsArray {
    class Trie {
        //前缀树节点类型
        class Node {
            public int pass;    //有几个字符经过该节点
            public int end;      //有几个字符串以该结点结束
            public Node[] nexts;  //以数组下标为路的下一个节点。  数组下标0代表a  1代表b ...

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        //头结点
        private Node root;

        public Trie() {
            root = new Node();
        }

        //往前缀树中加入字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            //字符串拆成字符
            char[] str = word.toCharArray();
            Node node = root;       //node先指向根节点
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) { // 从左往右遍历字符
                path = str[i] - 'a'; // 由字符，对应成走向哪条路      a走0号路，b走1号路...
                //如果当前节点后面没有 这个字符的路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                //node指向新的结点
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        //删除前缀树中的word
        public void erase(String word) {
            if (countWordsEqualTo(word) != 0) {
                char[] chs = word.toCharArray();
                Node node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

        //word这个单词出现了几次
        public int countWordsEqualTo(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    //如果没路了，就是出现0次
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        //pre前缀出现了几次
        public int countWordsStartingWith(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
}

package com.example.day21;

/**
 * @Date: 2023/5/16 15:34
 * 208. 实现 Trie (前缀树)
 * <p>
 * https://leetcode.cn/problems/implement-trie-prefix-tree/
 */
public class Code02_Trie {
    public class Trie {
        //子节点的数组。children[0]对应小写字母a, children[25]对应小写字母z
        public Trie[] children;
        //该节点是否为字符串的结尾
        public boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;   // 得到根结点
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            //该节点不为空，且以这个点结尾
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie node = searchPrefix(prefix);
            //该节点不为空即可
            return node != null;
        }

        //查找前缀，并返回最后一个节点
        public Trie searchPrefix(String prefix) {
            Trie node = this;   // 得到根结点
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    //没有匹配到prefix
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }
}

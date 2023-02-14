package com.example.class32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date: 2023/2/14 14:52
 */
public class Code04_AC2 {
    // 前缀树的节点
    public static class Node {
        // 如果一个node，end为空，不是结尾
        // 如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        // 只有在上面的end变量不为空的时候，endUse才有意义
        // 表示，这个字符串之前有没有加入过答案
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node() {
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        // 将每个敏感词加入到前缀树中。有多少个匹配串，就调用多少次insert
        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        public void build() {
            //广度优先遍历
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                //当前讨论节点作为父亲的姿态，然后把它的所有子节点的fail指针填好
                cur = queue.poll(); // 弹出某个父亲
                for (int i = 0; i < 26; i++) { // 检验每条路是否有孩子
                    if (cur.nexts[i] != null) { // 该路下有子节点
                        //1、初始时孩子的fail先设置root
                        cur.nexts[i].fail = root;
                        //2、找父亲的fail
                        cfail = cur.fail;
                        while (cfail != null) { // cur不是头节点
                            if (cfail.nexts[i] != null) {
                                //3、父亲的fail指向的那个结点有i号路，子节点的fail指向父亲的fail，停
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            //4、父亲的fail指向的那个结点没有i号路，找父亲的fail的fail(只要没找到，就找fail)
                            cfail = cfail.fail;
                        }
                        //5、这个孩子节点加入队列
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        // 大文章：content
        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a'; // 路
                // 如果当前字符在这条路上没配出来，就随着fail方向走向下条路径
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                // 1) 现在来到的路径，是可以继续匹配的
                // 2) 现在来到的节点，就是前缀树的根节点
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    //沿着follow的fai的转一圈到root，检验途中是否有 follow.end != null(每个敏感词的最后一个字符follow.end!=null)，有就记录，找到一个敏感词
                    if (follow.endUse) {
                        break;
                    }
                    // 不同的需求，在这一段之间修改
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    // 不同的需求，在这一段之间修改
                    follow = follow.fail;
                }
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }
}

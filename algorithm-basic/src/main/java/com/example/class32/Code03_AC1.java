package com.example.class32;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/2/14 14:52
 * AC自动机的实现
 */
public class Code03_AC1 {
    public static class Node {
        public int end; // 有多少个字符串以该节点结尾
        public Node fail;
        public Node[] nexts;

        public Node() {
            end = 0;
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
                    Node next = new Node();
                    cur.nexts[index] = next;
                }
                cur = cur.nexts[index];
            }
            cur.end++;
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

        public int containNum(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            int ans = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.end == -1) {
                        break;
                    }
                    { // 不同的需求，在这一段{ }之间修改
                        ans += follow.end;
                        follow.end = -1;
                    } // 不同的需求，在这一段{ }之间修改
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
        ac.insert("c");
        ac.build();
        System.out.println(ac.containNum("cdhe"));
    }

}

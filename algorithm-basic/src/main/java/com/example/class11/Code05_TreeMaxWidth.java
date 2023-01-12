package com.example.class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/1/12 21:24
 * 求二叉树最宽的层有多少节点
 */
public class Code05_TreeMaxWidth {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        //1、根节点入队列
        queue.add(head);
        Node curEnd = head; // 当前层，最右节点是谁
        Node nextEnd = null; // 下一层，最右节点是谁
        int max = 0;           //最宽层的节点数
        int curLevelNodes = 0; // 当前层的节点数
        while (!queue.isEmpty()) {
            //2、弹出队列元素，并检查是否有左右孩子。若有，将它们依次设置为下一层的最右节点，随时记录
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                //为下一层做准备
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                //为下一层做准备
                nextEnd = cur.right;
            }
            //3、弹出元素后，当前层节点数+1
            curLevelNodes++;
            //4、检验该节点是否为当前层最后一个节点。若是开始准备下一层，若不是继续弹出这层后面的节点
            if (cur == curEnd) {
                //5、当前层节点数与max比较
                max = Math.max(max, curLevelNodes);
                //6、为下一层做准备，下一层节点置为0，下一层的curEnd置为之前记录好的nextEnd
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}

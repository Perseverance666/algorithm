package com.example.class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/1/11 19:33
 * 层次遍历二叉树
 */
public class Code01_LevelTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    //层次遍历利用队列
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        //1、先将根节点入队列
        queue.add(head);
        while (!queue.isEmpty()) {
            //2、弹出队列元素
            Node cur = queue.poll();
            System.out.println(cur.value);
            //3、弹出一个元素后，如果有的话，将它左孩子和右孩子依次进入队列
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }

}

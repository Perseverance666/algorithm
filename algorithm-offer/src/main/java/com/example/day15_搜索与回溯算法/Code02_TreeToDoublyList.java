package com.example.day15_搜索与回溯算法;

/**
 * @Date: 2023/3/16 14:29
 * 剑指 Offer 36. 二叉搜索树与双向链表
 */
public class Code02_TreeToDoublyList {
    public class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node head = null;
    Node pre = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        in(root);
        //最终head指向第一个节点，pre指向最后一个节点
        head.left = pre;
        pre.right = head;
        return head;
    }
    //中序遍历，节点值大小从左往右依次递增
    //将node进行中序遍历
    public void in(Node cur) {
        if (cur == null) {
            return;
        }
        in(cur.left);

        cur.left = pre;
        if (pre != null) {
            pre.right = cur;
        } else {
            //说明当前节点是值最小的节点，head指向它
            head = cur;
        }
        pre = cur;

        in(cur.right);
    }
}

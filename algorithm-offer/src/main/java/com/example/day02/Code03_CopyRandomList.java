package com.example.day02;

/**
 * @Date: 2023/3/3 20:15
 */
public class Code03_CopyRandomList {
    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        //1、先在第一个节点和第二个节点之间添加第一个节点的克隆，后面以此类推
        Node cur = head;     //记录老链表的当前节点
        Node next = null;    //记录老链表的下一节点
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        //2、然后根据老节点的random找到新节点的random
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        //3、分离老链表和新链表，即更新老链表的next和新链表的next
        cur = head;
        Node res = cur.next;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next == null ? null : next.next;
            cur = next;
        }
        return res;
    }
}

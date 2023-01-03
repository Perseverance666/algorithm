package com.example.class03;

/**
 * @Date: 2023/1/2 20:17
 * 移除链表元素
 */
public class Code02_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        //1、首先让head指向第一个不需要删除的节点
        while(head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        //2、然后进行删除
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if(cur.value == num){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}

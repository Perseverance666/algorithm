package com.example.day04;

/**
 * @Date: 2023/4/8 20:36
 * 19. 删除链表的倒数第 N 个结点
 *
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?favorite=2cktkvj
 */
public class Code01_removeNthFromEnd {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //双指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //创建一个伪头节点
        ListNode node = new ListNode(-1,head);
        ListNode first = head;
        ListNode second = node;
        //先让first走到第n个节点
        for(int i = 0; i < n; i++){
            first = first.next;
        }
        //然后双指针同时往下走
        while (first != null){
            first = first.next;
            second = second.next;
        }
        //最后second指向的就是 倒数第n+1个节点
        second.next = second.next.next;
        return node.next;
    }

//    //自己写的，翻转链表
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode list = reverseList(head);
//        if(n == 1){
//            list = list.next;
//            return reverseList(list);
//        }
//        ListNode pre = null;
//        ListNode cur = list;
//        int count = 1;
//        while (count < n){
//            pre = cur;
//            cur = cur.next;
//            count++;
//        }
//        pre.next = cur.next;
//        return reverseList(list);
//    }
//
//    public static ListNode reverseList(ListNode list){
//        ListNode pre = null;
//        ListNode next = null;
//        ListNode cur = list;
//        while(cur != null){
//            next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
//    }
}

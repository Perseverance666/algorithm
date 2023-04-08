package com.example.day04;

/**
 * @Date: 2023/4/8 20:38
 * 21. 合并两个有序链表
 *
 * https://leetcode.cn/problems/merge-two-sorted-lists/?favorite=2cktkvj
 */
public class Code03_mergeTwoLists {
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //建一个伪头结点
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return head.next;
    }
}

package com.example.day18;

/**
 * @Date: 2023/5/14 18:35
 * 148. 排序链表
 *
 * 思路：归并排序
 * https://leetcode.cn/problems/sort-list/
 */
public class Code02_sortList {
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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //找到链表中间节点
        ListNode mid = middleNode(head);
        ListNode rightHead = mid.next;  //右边链表的头节点
        //以mid为中点，切分成两个链表
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        //合并两个有序链表并返回
        return mergeTwoLists(left,right);
    }

    //找到链表中间节点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }
}

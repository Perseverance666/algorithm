package com.example.day19;

/**
 * @Date: 2023/5/14 21:37
 * 160. 相交链表
 *
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class Code02_getIntersectionNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode A = headA;
        ListNode B = headB;
        while (A != B){
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }
}

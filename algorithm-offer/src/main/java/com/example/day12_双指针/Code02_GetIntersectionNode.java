package com.example.day12_双指针;

/**
 * @Date: 2023/3/13 14:32
 * 剑指 Offer 52. 两个链表的第一个公共节点
 */
public class Code02_GetIntersectionNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode A = headA;
        ListNode B = headB;
        while(A != B){
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
            //最终A会指向链表B，B会指向链表A，此时AB两个链表等长
            //如果有交点，最终AB都会指向交点，如果没有交点，最终AB都会指向null
        }
        return A;
    }
}

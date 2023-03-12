package com.example.day11_双指针;

/**
 * @Date: 2023/3/12 12:59
 * 剑指 Offer 22. 链表中倒数第k个节点
 */
public class Code02_GetKthFromEnd {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //双指针
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head;
        ListNode latter = head;
        for (int i = 0; i < k; i++) {
            former = former.next;
        }
        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

//    public ListNode getKthFromEnd(ListNode head, int k) {
//        ListNode cur = head;
//        int count = 0;
//        while (cur != null){
//            count++;
//            cur = cur.next;
//        }
//        cur = head;
//        for(int i = 1; i <= count - k; i++){
//            cur = cur.next;
//        }
//        return cur;
//    }
}

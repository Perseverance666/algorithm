package com.example.day12_双指针;

/**
 * @Date: 2023/3/13 14:10
 * 剑指 Offer 25. 合并两个排序的链表
 */
public class Code01_MergeTwoLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if(l1 == null){
//            return l2;
//        }
//        if(l2 == null){
//            return l1;
//        }
//        ListNode p = null;
//        if (l1.val <= l2.val) {
//            p = l1;
//            l1 = l1.next;
//        } else {
//            p = l2;
//            l2 = l2.next;
//        }
//        ListNode head = null;
//        head = p;
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                p.next = l1;
//                l1 = l1.next;
//            } else {
//                p.next = l2;
//                l2 = l2.next;
//            }
//            p = p.next;
//        }
//        if (l1 == null) {
//            p.next = l2;
//        }
//        if (l2 == null) {
//            p.next = l1;
//        }
//        return head;
//    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //创建伪头节点
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

package com.example.day05;

/**
 * @Date: 2023/4/8 22:18
 * 23. 合并 K 个升序链表
 * <p>
 * https://leetcode.cn/problems/merge-k-sorted-lists/?favorite=2cktkvj
 */
public class Code02_mergeKLists {
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


//    //暴力
//    public ListNode mergeKLists(ListNode[] lists) {
//        if (lists.length == 0) {
//            return null;
//        }
//        ListNode res = lists[0];
//        if (lists.length == 1) {
//            return res;
//        }
//
//        for (int i = 1; i < lists.length; i++) {
//            res = mergeTwoLists(res, lists[i]);
//        }
//        return res;
//    }

    //分治
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        return merge(lists, 0, lists.length - 1);

    }

    //将lists[start...end]上的链表合并
    public ListNode merge(ListNode[] lists, int start, int end) {
        if(start == end){
            return lists[start];
        }
        int mid = (start + end) / 2;
        return mergeTwoLists(merge(lists, start, mid), merge(lists, mid + 1, end));
    }

    //合并list1和list2两个链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //创建伪头结点
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 == null ? list2 : list1;
        return head.next;
    }
}

package com.example.day11_双指针;

/**
 * @Date: 2023/3/12 12:58
 * 剑指 Offer 18. 删除链表的节点
 */
public class Code01_DeleteNode {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val){
            //如果链表第一个就和val相等
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                break;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}

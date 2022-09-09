package com.example.day04;

/**
 * @Date: 2022/9/9 21:25
 *
 * 两个有序链表的合并：给定两个有序链表的头结点head1和head2，返回合并之后的大链表，要求依然有序
 * 例： 1->3->3->5->7     2->2->3->3->7
 * 返回： 1->2->2->3->3->3->3->5->7
 */
public class Primary17_MergeTwoSortedLinkedList {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if(head1 == null || head2 == null){
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.val <= head2.val ? head1 : head2;         //谁小head指向谁
        ListNode cur1 = head.next;             //cur1指向head.next
        ListNode cur2 = head == head1 ? head2 : head1;          //cur2指向头大的那个开始结点
        ListNode pre = head;
        while(cur1 != null && cur2 != null){
            if(cur1.val <= cur2.val){
                pre.next = cur1;
                cur1 = cur1.next;
            }else{
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }


}

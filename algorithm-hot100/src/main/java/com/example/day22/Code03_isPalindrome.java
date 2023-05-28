package com.example.day22;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/5/23 19:25
 * 234. 回文链表
 * <p>
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
public class Code03_isPalindrome {
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

    //时间复杂度O(n)，空间复杂度O(n)
    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int p1 = 0;
        int p2 = list.size() - 1;
        while (p1 < p2) {
            if (list.get(p1) != list.get(p2)) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }

    //进阶：时间复杂度O(n)，空间复杂度O(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode mid = findMid(head);
        //翻转后半部分的链表
        ListNode secondHalf = reverseList(mid.next);

        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean res = true;
        while(p1 != null && p2 != null){
            if(p1.val != p2.val){
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //将链表反转回来
        mid.next = reverseList(secondHalf);
        return res;
    }
    //找到head链表的中间节点
    public ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    //反转链表
    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}

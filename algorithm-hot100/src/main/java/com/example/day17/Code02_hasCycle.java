package com.example.day17;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2023/5/10 16:30
 * 141. 环形链表
 *
 * https://leetcode.cn/problems/linked-list-cycle/
 */
public class Code02_hasCycle {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    //哈希
    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(!set.add(head)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    //双指针
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

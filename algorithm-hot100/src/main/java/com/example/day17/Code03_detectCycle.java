package com.example.day17;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2023/5/10 16:31
 * 142. 环形链表 II
 *
 * https://leetcode.cn/problems/linked-list-cycle-ii/
 */
public class Code03_detectCycle {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //哈希表
    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode cur = head;
        Set<ListNode> set = new HashSet<>();
        while(cur != null){
            if(!set.add(cur)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }
    //双指针
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(true){
            if(fast == null || fast.next == null){
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        //此时slow == fast
        ListNode ptr = head;
        while (ptr != slow) {
            ptr = ptr.next;
            slow = slow.next;
        }
        return ptr;
    }
}

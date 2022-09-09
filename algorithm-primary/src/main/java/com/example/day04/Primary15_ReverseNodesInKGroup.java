package com.example.day04;

/**
 * @Date: 2022/9/9 18:35
 *
 * hard
 * K个结点的组内逆序调整
 * 给定一个一个单链表的头结点head，和一个正数k，实现k个节点的小组内部逆序，如果最后一组不够k个就不调整
 * 例：调整前：1->2->3->4->5->6->7->8,k=3
 *    调整后：3->2->1->6->5->4->7->8
 */

// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
public class Primary15_ReverseNodesInKGroup {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;
    }


    //返回一组中反转后的开始结点，若不够一组返回空
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while(--k != 0 && start != null){     //若为空，则这组不够k个
            start = start.next;
        }
        return start;
    }

    //翻转结点，反转后尾结点指向下一组头结点
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;   //先让end指向下一结点
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;
        while(cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;        //将这一组翻转后最后一个(没翻转前第一个)结点指向下一组第一个结点
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start,k);
        if(end == null){    //不够k个,不够一组
            return head;
        }
        //凑齐第一组
        reverse(start,end);
        head = end;         //head固定
        //上一组的结尾结点
        ListNode lastEnd = start;
        while (true){
            start = lastEnd.next;
            end = getKGroupEnd(start,k);
            if(end == null){            //这一组不够k个
                return head;
            }
            reverse(start,end);
            lastEnd.next = end;        //上一组结尾尾点指向这一组翻转后的开始结点
            lastEnd = start;           //对下一组来说，lastEnd为该组翻转后的尾结点start
        }
    }
}

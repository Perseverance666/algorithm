package com.example.day01;

/**
 * @Date: 2023/4/3 17:46
 * 2. 两数相加
 * <p>
 * https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
 */
public class Code02_addTwoNumbers {
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

    //    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        int flag = 0;
//        ListNode list = new ListNode();
//        ListNode p = list;
//        ListNode p1 = l1;
//        ListNode p2 = l2;
//        while(p1 != null && p2 != null){
//            int res = flag + p1.val + p2.val;
//            p.val = res % 10;
//            flag = res / 10;
//
//            p1 = p1.next;
//            p2 = p2.next;
//            if(p1 != null && p2 != null){
//                p.next = new ListNode();
//                p = p.next;
//            }
//
//        }
//        while(p1 != null){
//            p.next = new ListNode();
//            p = p.next;
//            int res = flag + p1.val;
//            p.val = res % 10;
//            flag = res / 10;
//            p1 = p1.next;
//        }
//        while(p2 != null){
//            p.next = new ListNode();
//            p = p.next;
//            int res = flag + p2.val;
//            p.val = res % 10;
//            flag = res / 10;
//            p2 = p2.next;
//        }
//        if(flag != 0){
//            p.next = new ListNode(1);
//        }
//        return list;
//    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //创建一个伪头结点
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        int carry = 0;  //进位
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            carry = sum / 10;
            int num = sum % 10;
            cur.next = new ListNode(num);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            //最后一位还有进位，只能是1
            cur.next = new ListNode(1);
        }
        return head.next;
    }

}

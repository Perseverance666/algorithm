package com.example.day04;

/**
 * @Date: 2022/9/9 20:50
 *
 * 两个链表相加：给定两个链表的头结点head1和head2,认为从左到右是某个数字从低位到高位，返回相加之后的链表
 * 例：4->3->6  2->5->3       返回6->8->9       解释 634+352=986
 *
 * 分三个阶段： L,S都有 -> L有，S没有 -> L,S都没有。  临时变量存进位
 */
public class Primary16_AddTwoNumbers {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 求链表长度
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode l = len1 >= len2 ? head1 : head2;   //长链表
        ListNode s = l == head1 ? head2 : head1;    //短链表

        int carry = 0;     //存放进位
        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;      //last一直跟着长链表，为了最后需要加进位时 知道加在哪里
        int curNum = 0;
        while(curS != null){        //第一阶段：L,S都有
            curNum = curL.val + curS.val + carry;
            carry = curNum / 10;
            curL.val = curNum % 10;
            last = curL;            //保存当前长链表位置
            curL = curL.next;
            curS = curS.next;
        }
        while(curL != null){        //第二阶段：L没有，S有
            curNum = curL.val + carry;
            carry = curNum / 10;
            curL.val = curNum % 10;
            last = curL;                //保存当前长链表位置
            curL = curL.next;
        }
        if(carry != 0){          //第三阶段： L,S都没有
            last.next = new ListNode(1);      //特殊情况：L,S都没有后，还有进位，进位一定是1
        }
        return l;    //返回修改后的长链表
    }

}

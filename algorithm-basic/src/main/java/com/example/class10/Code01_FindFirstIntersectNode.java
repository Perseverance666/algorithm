package com.example.class10;

/**
 * @Date: 2023/1/11 14:13
 * 链表题目：给定两个可能有环也可能无环的单链表，头节点head1和head2。请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null。
 * 要求：如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 *
 * 一个有环的链表最后不会指向null，一个无环的节点最后一定指向null
 * 两个无环的链表可能相交，两个有环的链表可能相交，一个有环一个无环的链表一定不会相交
 */
public class Code01_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    //如果两个链表相交，请返回相交的第一个节点。如果不相交，返回null
    public static Node getIntersectNode(Node head1, Node head2) {
        //若某个链表为null，肯定不相交
        if (head1 == null || head2 == null) {
            return null;
        }
        //1、获取两个链表的入环节点，若无环返回null
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        //2、两个无环节点，求相交的第一个节点
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        //3、两个有环节点，求相交的第一个节点
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        //4、一个有环和一个无环的链表一定不会相交
        return null;
    }

    // 利用快慢指针找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        //先将0，1,2节点的情况写出来，防止后面fast为空
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        //1、定义快慢指针，一直遍历到两个指针指向同一个位置
        Node slow = head.next; // n1 -> slow
        Node fast = head.next.next; // n2 -> fast
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                //若存在null说明链表肯定无环
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //2、让快指针回到头节点位置，慢指针位置不变。让快指针也一次走一个节点，两个指针相遇的位置就是第一个入环节点的位置
        fast = head; // n2 -> walk again from head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 1、两个无环的链表可能相交
    // 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2) {
        //如果两个链表其中一个为null，肯定不相交
        if (head1 == null || head2 == null) {
            return null;
        }
        //1、先统计一下两个链表的长度。为了后面把长部分走完，再一起走相同的部分，然后找到第一个相交节点
        Node cur1 = head1;
        Node cur2 = head2;
        int len1 = 0;
        int len2 = 0;
        while (cur1.next != null) {
            len1++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            len2++;
            cur2 = cur2.next;
        }
        //2、如果两个链表的最后一个节点不是同一个，说明他俩肯定没相交
        if (cur1 != cur2) {
            return null;
        }
        //3、让cur1指向长链表的头结点，cur2指向另一个链表的头节点
        cur1 = len1 > len2 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        //4、先让长链表把长的那部分走完
        int n = Math.abs(len1 - len2);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        //5、然后长链表和短链表再各自走完后面的部分，此时他俩后面的部分长度相等。直到走到他俩相交的第一个节点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 2、一个有环一个无环的链表一定不会相交

    // 3、两个有环的链表可能相交
    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    // 两个有环链表可能有3种情况：
    //  1.两个链表不相交。loop1 != loop2
    //  2.两个链表相交之后才进入入环节点   loop1 == loop2  相交的节点在loop1前面
    //  3.先进入入环节点，在环的途中两个节点相交 loop1 != loop2。loop1或者loop2都可作为第一个相交节点
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            //1、loop1 == loop2，两个链表相交之后才进入入环节点，相交的节点在loop1前面
            //方法和求两个无环节点的第一个相交节点类似，只是这次只需遍历里到loop1即可，之前是遍历到null
            cur1 = head1;
            cur2 = head2;
            //1.1、先统计一下两个链表从头节点到loop1的长度
            int len1 = 0;
            int len2 = 0;
            while (cur1 != loop1) {
                len1++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                len2++;
                cur2 = cur2.next;
            }
            //1.2、cur1指向长链表，cur2指向短链表
            cur1 = len1 > len2 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            //1.3、让长链表先把长的那部分走完
            int n = Math.abs(len1 - len2);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            //1.4、长短链表一块遍历，直到第一个交点
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //2、loop1 != loop2，开始从其中一个链表的入环节点开始遍历
            cur1 = loop1.next;
            while (cur1 != loop1) {
                //2.1、准备开始从loop1遍历一圈
                if (cur1 == loop2) {
                    //2.2、若在遍历一圈的过程中找到了loop2，说明是先进入入环节点，在环的途中两个节点相交
                    return loop2;  //返回loop1或loop2都可以。返回loop1是相对于loop2链表，返回loop2是相对于loop1链表
                }
                cur1 = cur1.next;
            }
            //2.3、此时遍历了一圈，都没有找到loop2，说明两个链表不相交，是两个独立的有环链表
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->7->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}

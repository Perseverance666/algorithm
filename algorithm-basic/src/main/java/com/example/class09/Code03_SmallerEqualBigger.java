package com.example.class09;

/**
 * @Date: 2023/1/9 21:58
 * 链表题目：将单向链表按某值划分成左边小，中间相等，右边大的形式
 */
public class Code03_SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == pivot) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static Node listPartition2(Node head, int pivot) {
        //1、准备6个指针
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node mH = null; // big head
        Node mT = null; // big tail

        Node next = null;  //用来获取链表下一个元素
        while(head != null){
            //2、记录下一个节点，并把当前节点与其他节点分离开
            next = head.next;
            head.next = null;
            //3、若这个元素比pivot小
            if(head.value < pivot){
                if(sH == null){
                    //3.1、说明现在sH和sT之间没有元素，直接添加
                    sH = head;
                    sT = head;
                } else {
                    //3.2、说明现在sH和sT之间有元素
                    sT.next = head;
                    sT = head;
                }
            } else if(head.value == pivot){
                //4、若这个元素和pivot一样大
                if(eH == null){
                    //4.1、说明现在eH和eT之间没有元素，直接添加
                    eH = head;
                    eT = head;
                } else {
                    //4.2、说明现在eH和eT之间有元素
                    eT.next = head;
                    eT = head;
                }
            } else{
                //5、若这个元素比pivot大
                if(mH == null){
                    //5.1、说明现在mH和mT之间没有元素，直接添加
                    mH = head;
                    mT = head;
                } else {
                    //5.2、说明现在mH和mT之间有元素
                    mT.next = head;
                    mT = head;
                }

            }
            //6、继续看下一个元素
            head = next;
        }

        //7、开始连起来，不用管右区域，只关心左中区域
        if(sH != null){
            //7.1、左有中无
            if(eH == null){
                sT.next = mH;
            }else{
                //7.2、左有中有
                sT.next = eH;
                eT.next = mH;
            }
            return sH;
        } else {
            //7.3、左无中无
            if(eH == null){
                return mH;
            }else {
                //7.4、左无中有
                eT.next = mH;
                return eH;
            }
        }
//        if (sT != null) { // 如果有小于区域
//            sT.next = eH;
//            eT = eT == null ? sT : eT; // 下一步，谁去连大于区域的头，谁就变成eT
//        }
//        // 下一步，一定是需要用eT 去接 大于区域的头
//        // 有等于区域，eT -> 等于区域的尾结点
//        // 无等于区域，eT -> 小于区域的尾结点
//        // eT 尽量不为空的尾巴节点
//        if (eT != null) { // 如果小于区域和等于区域，不是都没有
//            eT.next = mH;
//        }
//        return sH != null ? sH : (eH != null ? eH : mH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }

}

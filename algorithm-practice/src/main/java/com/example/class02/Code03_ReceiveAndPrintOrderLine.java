package com.example.class02;

import java.util.HashMap;

/**
 * @Date: 2023/2/28 16:44
 * 题目：已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i， 那么当i+1出现时
 * 请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构
 */
public class Code03_ReceiveAndPrintOrderLine {
    public static class Node {
        public String info;
        public Node next;

        public Node(String str) {
            info = str;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;     //装头区间的map
        private HashMap<Integer, Node> tailMap;     //装尾区间的map
        private int waitPoint;                      //此时应该被打印的点的编号，初始化为1

        public MessageBox() {
            headMap = new HashMap<Integer, Node>();
            tailMap = new HashMap<Integer, Node>();
            waitPoint = 1;
        }

        // 消息的编号，info消息的内容, 消息一定从1开始
        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            // num~num
            // 建立了num~num这个连续区间的头和尾
            headMap.put(num, cur);
            tailMap.put(num, cur);
            // 查询有没有某个连续区间以num-1结尾。有就链表相连，并更新头表和尾表
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            // 查询有没有某个连续区间以num+1开头的。有就链表相连，并更新头表和尾表
            if (headMap.containsKey(num + 1)) {
                cur.next = headMap.get(num + 1);
                tailMap.remove(num);
                headMap.remove(num + 1);
            }
            //如果当前数就是等待要打印的数，打印，否则不打印
            if (num == waitPoint) {
                print();
            }
        }

        private void print() {
            //获取要打印的节点。后面要将头表和尾表相应的节点信息删除
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                //打印链表上的内容
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint-1);
            System.out.println();
        }

    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2,"B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1,"A"); // 1 2 -> print, trigger is 1
        box.receive(4,"D"); // - 4
        box.receive(5,"E"); // - 4 5
        box.receive(7,"G"); // - 4 5 - 7
        box.receive(8,"H"); // - 4 5 - 7 8
        box.receive(6,"F"); // - 4 5 6 7 8
        box.receive(3,"C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9,"I"); // 9 -> print, trigger is 9
        box.receive(10,"J"); // 10 -> print, trigger is 10
        box.receive(12,"L"); // - 12
        box.receive(13,"M"); // - 12 13
        box.receive(11,"K"); // 11 12 13 -> print, trigger is 11

    }
}

package com.example.day04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Date: 2022/9/9 16:27
 * <p>
 * 用单链表实现队列和栈
 * <p>
 * 队列：添加数据tail移动，弹出数据head移动，队列先进先出
 * 栈：添加弹出数据都是head移动，栈先进后出
 */
public class Primary13_LinkedListToQueueAndStack {
    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V v) {
            value = v;
            next = null;
        }
    }

    //队列
    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        //向队列添加数据
        public void offer(V value) {
            Node<V> cur = new Node<>(value);
            if (tail == null) {       //队列中没有数据,tail和head均指向该结点
                tail = cur;
                head = cur;
            } else {                //队列中有数据
                tail.next = cur;     //连接结点
                tail = cur;         //tail指向新结点
            }
            size++;
        }

        //队列弹出数据
        public V poll() {
            V ans = null;
            if (head == null) {    //队列为空
                return ans;
            }

            //队列不为空
            ans = head.value;
            size--;
            if (head == tail) {  //队列只有一个结点时，弹出后head为null,让tail为null，jvm会自动释放所有数据，
                //若tail不为null，此时指向最后一个结点，该结点数据是可达的，jvm无法删除该结点，成为脏数据
                head = null;
                tail = null;
            } else {         //队列中超过一个结点
                //弹出时，不必关注弹出结点的next，因为程序只有head和tail两个入口，弹出结点数据是不可达的，jvm会自动释放（C++需要自己解决）
                head = head.next;
            }
            return ans;
        }

        //只查看，不弹出数据
        public V peek() {
            V ans = null;
            if (head != null) {     //队列中有数据
                ans = head.value;
            }
            return ans;
        }
    }

    //栈
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        //向栈添加元素
        public void push(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {   //栈中没有数据，head指向它
                head = cur;
            } else {
                cur.next = head;    //栈中有数据，新结点指向前一个结点
                head = cur;         //head指向新结点
            }
            size++;
        }

        //栈弹出数据
        public V pop() {
            V ans = null;
            if (head == null) {           //栈中没有数据
                return ans;
            }

            //栈中有数据
            ans = head.value;
            head = head.next;     //head指向下一结点
            size--;
            return ans;
        }

        //只查看，不弹出数据
        public V peek() {
            return head != null ? head.value : null;
        }
    }

    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        testQueue();
        testStack();
    }
}

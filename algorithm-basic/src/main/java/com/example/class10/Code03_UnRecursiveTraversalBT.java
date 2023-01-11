package com.example.class10;

import java.util.Stack;

/**
 * @Date: 2023/1/11 16:45
 * 非递归方式实现二叉树的先序、中序、后序遍历
 */
public class Code03_UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    //先序
    //确保一个节点的右孩子先进栈左孩子后进栈，这样弹出的时候就能先弹出左孩子再弹出右孩子
    public static void pre(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            //1、根节点进栈
            stack.add(head);
            while (!stack.isEmpty()) {
                //2、只要栈中有元素就弹出
                head = stack.pop();
                //3、弹出这个节点后，先将这个节点的右孩子进栈，再将这个节点的左孩子进栈
                //这样弹出的时候就能保证 根->左->右 这个顺序了
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //中序
    public static void in(Node head) {
        System.out.print("in-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            //1、先将根节点一直遍历左孩子到底并将其都压入栈中
            stack.push(head);
            while(head.left != null){
                head = head.left;
                stack.push(head);
            }
            while(!stack.isEmpty()){
                //2、弹出元素栈顶元素并打印
                head = stack.pop();
                System.out.print(head.value + " ");
                //3、然后将弹出元素的右孩子一直遍历它的左孩子到底并将其都压入栈中。这样从栈弹出的顺序就是 左根右
                if(head.right != null){
                    head = head.right;
                    stack.push(head);
                    while(head.left != null){
                        head = head.left;
                        stack.push(head);
                    }
                }
            }

        }
        System.out.println();
    }

//    public static void in(Node cur) {
//        System.out.print("in-order: ");
//        if (cur != null) {
//            Stack<Node> stack = new Stack<Node>();
//            while (!stack.isEmpty() || cur != null) {
//                if (cur != null) {
//                    stack.push(cur);
//                    cur = cur.left;
//                } else {
//                    cur = stack.pop();
//                    System.out.print(cur.value + " ");
//                    cur = cur.right;
//                }
//            }
//        }
//        System.out.println();
//    }

    //后序1，使用两个栈
    public static void pos1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            //1、根节点进栈
            s1.push(head);
            while (!s1.isEmpty()) {
                //2、只要栈中有元素就弹出，弹出不是打印，而是放入另一个栈
                head = s1.pop();
                s2.push(head);
                //3、弹出这个节点后，先将这个节点的左孩子进栈，再将这个节点的右孩子进栈
                //这样弹出的时候就能保证 根->右->左 这个顺序了
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            //4、再弹出另一个栈，因为进这个栈时是根右左，弹出时就是左右根了
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }


    //后序2，使用一个栈，难
    public static void pos2(Node h) {
        System.out.print("pos-order: ");
        if (h != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }

}

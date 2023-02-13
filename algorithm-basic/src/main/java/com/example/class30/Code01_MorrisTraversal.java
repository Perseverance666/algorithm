package com.example.class30;

/**
 * @Date: 2023/2/13 11:50
 * Morris遍历的实现
 *
 * 假设来到当前节点cur，开始时cur来到头节点位置
 * 1）如果cur没有左孩子，cur向右移动(cur = cur.right)
 * 2）如果cur有左孩子，找到左子树上最右的节点mostRight：
 * 	a.如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur = cur.left)
 * 	b.如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur = cur.right)
 * 3）cur为空时遍历停止
 */
public class Code01_MorrisTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void process(Node root) {
        if (root == null) {
            return;
        }
        // 1
        process(root.left);
        // 2
        process(root.right);
        // 3
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;            //标记当前节点
        Node mostRight = null;      //标记当前节点左子树的最右节点的右指针
        while (cur != null) {       //当前节点为空，说明全部节点都讨论完
            mostRight = cur.left;
            //1、当前节点有左子树
            if (mostRight != null) {
                //1.1、找到当前节点左子树的最右节点。最右节点的右指针要么指向空，要么指向当前节点(说明这个节点来过一次了)
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    //1.2、最右节点的右指针指向空，说明是第一次讨论当前节点，最右节点的右指针指向当前节点，做标记。并准备讨论该节点的左子树
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    //1.3、最右节点的右指针指向当前节点，说明是第二次讨论当前节点，最右节点的右指针指向空，取消标记。并准备讨论该节点的右子树
                    mostRight.right = null;
                }
            }
            //2.1、当前结点没有左子树，该节点讨论完，准备去讨论该节点的右子树
            //2.2、或者当前节点的左子树都讨论完了，准备去讨论该节点的右子树
            cur = cur.right;
        }
    }
    //先序morris遍历
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    //第一次讨论该节点，打印
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                //该节点没有左树，说明该节点只会经过一次，直接打印
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }
    //中序morris遍历
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    //第一次讨论该节点，打印
                    System.out.print(cur.value + " ");
                }
            } else {
                //该节点没有左树，说明该节点只会经过一次，直接打印
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }
    //后序morris遍历
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    //第二次讨论该节点，逆序打印该节点左树一直到右边界
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        //经过两次的节点的左树右边界都打印完了，在逆序打印整棵树一直到右边届
        printEdge(head);
        System.out.println();
    }
    //逆序打印该节点一直到右边界
    public static void printEdge(Node head) {
        //翻转该节点左树一直到右边界。类似翻转链表
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            //逆序打印
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        //再翻转回来
        reverseEdge(tail);
    }
    //翻转链表
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.value) {
                ans = false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        printTree(head);

    }
}

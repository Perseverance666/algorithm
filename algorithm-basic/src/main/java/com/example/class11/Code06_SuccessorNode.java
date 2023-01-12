package com.example.class11;

/**
 * @Date: 2023/1/12 22:03
 * 在二叉树中找到一个节点的后继节点
 * 中序遍历中一个节点的下一个节点就是这个节点的后继节点
 */
public class Code06_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        //1、该节点有右子树，它右子树的最左节点就是该节点的后继节点
        if (node.right != null) {
            //指针指向它的右孩子，然后开始一直向左遍历
            node = node.right;
            while(node.left != null){
                node = node.left;
            }
            return node;
        } else {
            //2、该节点无右子树，去找它爹
            // 该节点若作为它爹的左孩子，它爹就是该节点的后继节点；
            // 该节点若作为它爹的右孩子，那就找它爹的爹，一直往上遍历直到某个节点作为它爹的左孩子，这个爹即为所求。若到根节点都没找到，说明该节点没有后继节点
            Node parent = node.parent;
            while (parent != null && parent.right == node) {   //parent为null说明遍历到根节点了
                // 当前节点若是它父亲节点右孩子，就一直往上遍历
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

//    public static Node getSuccessorNode(Node node) {
//        if (node == null) {
//            return node;
//        }
//        if (node.right != null) {
//            return getLeftMost(node.right);
//        } else { // 无右子树
//            Node parent = node.parent;
//            while (parent != null && parent.right == node) { // 当前节点是其父亲节点右孩子
//                node = parent;
//                parent = node.parent;
//            }
//            return parent;
//        }
//    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}

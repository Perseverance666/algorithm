package com.example.class13;

import java.util.ArrayList;

/**
 * @Date: 2023/1/14 17:15
 * 找到二叉树中的最大搜索二叉子树并返回头节点  --- 说明整棵树不一定是搜索二叉树
 * 题目：给定一棵二叉树的头节点head，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉子树，并返回这棵子树的头节点
 */
public class Code01_MaxSubBSTHead {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static Node maxSubBSTHead1(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = maxSubBSTHead1(head.left);
        Node rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static Node maxSubBSTHead2(Node head) {
        if(head == null){
            return null;
        }
        return process(head).maxSubBSTHead;
    }
    public static class Info{
        public Node maxSubBSTHead;   //该节点构成树中的最大搜索二叉子树的节点
        public int maxSubBSTSize;    //该节点构成树中的最大搜索二叉子树的节点个数
        public int max;             //该节点构成树的节点数值的最大值
        public int min;             //该节点构成树的节点数值的最小值
        public Info(Node mh,int ms,int ma,int mi){
            maxSubBSTHead = mh;
            maxSubBSTSize = ms;
            max = ma;
            min = mi;
        }
    }
    public static Info process(Node x){
        if(x == null){
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.value;
        int min = x.value;
        int maxSubBSTSize = 0;
        Node maxSubBSTHead = null;
        if(leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            //1、先把左树的maxSubBSTHead作为整棵树的maxSubBSTHead
            maxSubBSTHead = leftInfo.maxSubBSTHead;
            maxSubBSTSize = leftInfo.maxSubBSTSize;
        }
        if(rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if(rightInfo.maxSubBSTSize > maxSubBSTSize){
                //2、若右树的maxSubBSTSize大于左树，就先把右树的maxSubBSTHead作为整棵树的maxSubBSTHead
                maxSubBSTHead = rightInfo.maxSubBSTHead;
                maxSubBSTSize = rightInfo.maxSubBSTSize;
            }
        }
        //3、假如整棵树是搜索二叉树
        boolean leftIsBST = leftInfo == null ? true : leftInfo.maxSubBSTHead == x.left;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.maxSubBSTHead == x.right;
        //3.1、确保左右树都是搜索二叉树
        if(leftIsBST && rightIsBST){
            //3.2、然后判断该节点的左树的最大值是否 < 该节点，右树的最小值是否 > 该节点
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
            boolean rightMinMoreX = rightInfo == null ? true : (rightInfo.min > x.value);
            if(leftMaxLessX && rightMinMoreX){
                //这样这棵树才是搜索二叉树
                int leftSize = leftInfo == null ? 0 : leftInfo.maxSubBSTSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.maxSubBSTSize;
                maxSubBSTSize = leftSize + rightSize + 1;
                maxSubBSTHead = x;
            }
        }
        return new Info(maxSubBSTHead,maxSubBSTSize,max,min);
    }
//    public static Node maxSubBSTHead2(Node head) {
//        if (head == null) {
//            return null;
//        }
//        return process(head).maxSubBSTHead;
//    }
//
//    // 每一棵子树
//    public static class Info {
//        public Node maxSubBSTHead;
//        public int maxSubBSTSize;
//        public int min;
//        public int max;
//
//        public Info(Node h, int size, int mi, int ma) {
//            maxSubBSTHead = h;
//            maxSubBSTSize = size;
//            min = mi;
//            max = ma;
//        }
//    }
//
//    public static Info process(Node X) {
//        if (X == null) {
//            return null;
//        }
//        Info leftInfo = process(X.left);
//        Info rightInfo = process(X.right);
//        int min = X.value;
//        int max = X.value;
//        Node maxSubBSTHead = null;
//        int maxSubBSTSize = 0;
//        if (leftInfo != null) {
//            min = Math.min(min, leftInfo.min);
//            max = Math.max(max, leftInfo.max);
//            maxSubBSTHead = leftInfo.maxSubBSTHead;
//            maxSubBSTSize = leftInfo.maxSubBSTSize;
//        }
//        if (rightInfo != null) {
//            min = Math.min(min, rightInfo.min);
//            max = Math.max(max, rightInfo.max);
//            if (rightInfo.maxSubBSTSize > maxSubBSTSize) {
//                maxSubBSTHead = rightInfo.maxSubBSTHead;
//                maxSubBSTSize = rightInfo.maxSubBSTSize;
//            }
//        }
//        if ((leftInfo == null ? true : (leftInfo.maxSubBSTHead == X.left && leftInfo.max < X.value))
//                && (rightInfo == null ? true : (rightInfo.maxSubBSTHead == X.right && rightInfo.min > X.value))) {
//            maxSubBSTHead = X;
//            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
//                    + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
//        }
//        return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
//    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}

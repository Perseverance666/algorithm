package com.example.class12;

import java.util.ArrayList;

/**
 * @Date: 2023/1/13 19:19
 * 判断二叉树是否是搜索二叉树
 * 搜索二叉树的条件：
 *      1、每个节点的左树和右树都是搜索二叉树
 *      2、每个节点左树的最大值 < 该节点，右树的最小值 > 该节点
 */
public class Code02_IsBST {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    //存放某个节点构成树中的最大值和最小值以及是否是搜索二叉树
    public static class Info {
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean i,int ma,int mi){
            isBST = i;
            max = ma;
            min = mi;
        }
    }

    //主函数，判断二叉树是否是搜索二叉树
    public static boolean isBST2(Node head){
        if(head == null){
            return true;
        }
        return process(head).isBST;
    }

    //返回指定节点的树的信息
    public static Info process(Node x){
        if(x == null){
            return null;
        }
        //获取该节点左右树的信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //先设置该节点的构成树的最大值和最小值，值都先设置为该节点，然后和左右树的去比较
        //这里其实没太大用，主要用于递归传递信息
        int max = x.value;
        int min = x.value;
        if(leftInfo != null){
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min,min);
        }
        if(rightInfo != null){
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min,min);
        }

        boolean isBST = true;
        //1、检验该节点的左右树是否是搜索二叉树
        if(leftInfo != null && !leftInfo.isBST || rightInfo != null && !rightInfo.isBST){
            isBST = false;
        }

        //2、检验该节点的左树的最大值是否 < 该节点，右树的最小值是否 > 该节点
        if(leftInfo != null && leftInfo.max >= x.value || rightInfo != null && rightInfo.min <= x.value){
            isBST = false;
        }
        return new Info(isBST,max,min);
    }


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
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

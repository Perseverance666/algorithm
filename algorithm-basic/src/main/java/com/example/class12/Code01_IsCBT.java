package com.example.class12;

import java.util.LinkedList;

/**
 * @Date: 2023/1/13 15:14
 * 判断二叉树是否是完全二叉树 --- 递归套路和非递归套路
 *
 * 测试链接 : https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
 */
public class Code01_IsCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //非递归套路
    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (  //1、遇到了不双全的节点之后，leaf置为true，又发现下一个节点不是叶节点，不满足完全二叉树。遇到了不双全的节点，后面的节点应该都是叶子节点
                    (leaf && (l != null || r != null))
                            ||
                            //2、某个节点有右孩子没有左孩子，不满足完全二叉树
                            (l == null && r != null)
            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                //3、该节点是不双全的，leaf置为true。之后判断后面的节点是否为叶子节点
                leaf = true;
            }
        }
        return true;
    }

    //递归套路
    public static boolean isCBT2(Node head){
        return process(head).isCBT;
    }
    //封装指定节点构成树的信息
    public static class Info{
        public boolean isFull; //该节点构成的树是否是满二叉树
        public boolean isCBT;  //该节点构成的树是否是完全二叉树
        public int height; //该节点构成的树的高度
        public Info(boolean iF,boolean iC,int h){
            isFull = iF;
            isCBT = iC;
            height = h;
        }
    }
    public static Info process(Node x){
        if(x == null){
            return new Info(true,true,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        ////这里其实没太大用，主要用于递归传递信息
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        //当左右树为满二叉树，并且左右树的高等相等时，该树才是满二叉树
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

        //该二叉树是否是完全二叉树，只有4种可能
        boolean isCBT = false;
        if(isFull){
            //1、这棵树是满二叉树
            isCBT = true;
        }else if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            //2、左树是完全二叉树，右树是满二叉树，且左树高度 = 右树高度 + 1
            isCBT = true;
        }else if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
            //3、左树是满二叉树，右树是满二叉树，且左树高度 = 右树高度 + 1
            isCBT = true;
        }else if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
            //4、左树是满二叉树，右树是完全二叉树，且左树高度 = 右树高度
            isCBT = true;
        }

        return new Info(isFull,isCBT,height);
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
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

package com.example.class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Date: 2023/1/14 19:41
 * 在二叉树中找到两个节点的最近公共祖先
 * 题目：给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先
 */
public class Code02_lowestAncestor {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static Node lowestAncestor2(Node head, Node a, Node b){
        return process(head,a,b).ans;
    }
    public static class Info{
        public boolean findA;  //在该节点构成树中找到了a
        public boolean findB;  //在该节点构成树中找到了b
        public Node ans;       //在该节点构成树中找到了最低公共祖先
        public Info(boolean fa,boolean fb,Node a){
            findA = fa;
            findB = fb;
            ans = a;
        }
    }
    public static Info process(Node x,Node a,Node b){
        if(x == null){
            return new Info(false,false,null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        //当前节点为a，或者在左树右树中找到了a，说明在该节点构成树中找到了a
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        //当前节点为b，或者在左树右树中找到了b，说明在该节点构成树中找到了b
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        Node ans = null;
        //在这棵树中只有找到了a和b，这棵树中才存在最低公共祖先
        if(findA && findB){
            if(leftInfo.ans != null){
                //1、在左树找到了答案
                ans = leftInfo.ans;
            }else if(rightInfo.ans != null){
                //2、在右树找到了答案
                ans = rightInfo.ans;
            }else{
                //3、左右树都没找到答案，并且在这颗树中已经找到了a和b，那就说明x就是最低公共祖先
                ans = x;
            }
        }
        return new Info(findA,findB,ans);
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

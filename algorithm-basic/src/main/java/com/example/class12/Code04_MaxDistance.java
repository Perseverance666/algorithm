package com.example.class12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Date: 2023/1/13 20:44
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整颗二叉树的最大距离
 * 最大距离只有如下可能：
 *      1、当最大距离不经过根节点时，最大距离 = 根节点的左子树最大距离 或者 根节点的右子树最大距离
 *      2、当最大距离经过根节点时，最大距离 = 根节点左子树的高度 + 根节点右子树的高度 + 1
 */
public class Code04_MaxDistance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = getPrelist(head);
        HashMap<Node, Node> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static ArrayList<Node> getPrelist(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static HashMap<Node, Node> getParentMap(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
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

    public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
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
        Node lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }

    //存放某个节点构成树的节点间最大距离 和 这棵树的高度
    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int md,int h){
            maxDistance = md;
            height = h;
        }
    }
    //主函数，返回二叉树节点间的最大距离
    public static int maxDistance2(Node head){
        return process(head).maxDistance;
    }
    //返回指定节点的树的信息
    public static Info process(Node x){
        if(x == null){
            return new Info(0,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //这里其实没太大用，主要用于递归传递信息
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        //1、最大距离不经过x节点，最大距离 = 根节点的左子树最大距离 或者 根节点的右子树最大距离
        int maxDistance = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        //2、最大距离经过x节点，最大距离 = 根节点左子树的高度 + 根节点右子树的高度 + 1
        //maxDistance取3种结果中较大的那一个
        maxDistance = Math.max(maxDistance, leftInfo.height + rightInfo.height + 1);

        return new Info(maxDistance,height);
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
            if (maxDistance1(head) != maxDistance2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

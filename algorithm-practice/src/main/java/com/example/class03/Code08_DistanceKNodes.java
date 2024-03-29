package com.example.class03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date: 2023/2/28 21:41
 * 题目：给定三个参数，二叉树的头节点head，树上某个节点target，正数K。从target开始，可以向上走或者向下走，
 * 返回与target的距离是K的所有节点
 */
public class Code08_DistanceKNodes {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root,null);
        createParentMap(root,parents);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();  //用来记录哪些节点曾经进过队列
        List<Node> ans = new ArrayList<>();
        queue.add(target);
        visited.add(target);
        int level = 0; //第几层，这里的宽度优先遍历，要一层的节点一起进出队列
        while(!queue.isEmpty()){
            int size = queue.size(); //每一层的节点个数
            while(size > 0){
                Node poll = queue.poll();
                if(level == K){
                    ans.add(poll);
                }
                if(poll.left != null && !visited.contains(poll.left)){
                    queue.add(poll.left);
                    visited.add(poll.left);
                }
                if(poll.right != null && !visited.contains(poll.right)){
                    queue.add(poll.right);
                    visited.add(poll.right);
                }
                if(parents.get(poll) != null && !visited.contains(parents.get(poll))){
                    queue.add(parents.get(poll));
                    visited.add(parents.get(poll));
                }
                size--;
            }
            level++;
            if(level > K){
                break;
            }
        }
        return ans;
    }
    //设置cur作为谁的父节点
    public static void createParentMap(Node cur, HashMap<Node, Node> parents) {
        if(cur.left != null){
            parents.put(cur.left,cur);
            createParentMap(cur.left,parents);
        }
        if(cur.right != null){
            parents.put(cur.right,cur);
            createParentMap(cur.right,parents);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }
}

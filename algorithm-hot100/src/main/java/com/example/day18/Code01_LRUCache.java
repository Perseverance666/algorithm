package com.example.day18;

import java.util.HashMap;

/**
 * @Date: 2023/5/14 18:34
 * 146. LRU 缓存
 * 数据结构：哈希表 + 双向链表
 * https://leetcode.cn/problems/lru-cache/
 * 注：双向链表中尾部节点是最近最久未使用的节点
 */
public class Code01_LRUCache {
    public class LRUCache {
        //双向链表中的节点信息
        private class Node {
            int key;
            int value;
            Node pre;
            Node next;

            public Node() {
            }

            public Node(int k, int v) {
                key = k;
                value = v;
            }
        }

        //哈希表，用来找到指定key的Node节点位置
        private HashMap<Integer, Node> map = new HashMap<>();
        private int size;           //LRU缓存的当前节点数量
        private int capacity;       //LRU缓存的容量
        private Node head;          //伪头结点
        private Node tail;           //伪尾结点

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            //1、先判断该节点是否存在
            if (node == null) {
                return -1;
            }
            //2、存在，将该节点移到头部，并返回该节点的value
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                //该节点存在，更新value，并将该节点移到头部
                node.value = value;
                moveToHead(node);
            } else {
                //该节点不存在，在头部插入新节点
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                if (size > capacity) {
                    //超出容量，删除尾结点
                    map.remove(tail.pre.key);
                    removeNode(tail.pre);
                    size--;
                }
            }
        }

        public void moveToHead(Node node) {
            //1、先删除该节点在原来的位置
            removeNode(node);
            //2、将该节点插入到头部
            addToHead(node);

        }

        //删除该节点在原来的位置
        public void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        //将该节点插入到头部
        public void addToHead(Node node) {
            node.pre = head;
            node.next = head.next;
            head.next = node;
            node.next.pre = node;
        }
    }
}

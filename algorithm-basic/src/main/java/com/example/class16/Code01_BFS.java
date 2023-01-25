package com.example.class16;

import com.example.class16.other.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2023/1/23 19:28
 * 图的宽度优先遍历 BFS
 * 用队列
 */
public class Code01_BFS {
    // 从node出发，进行宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();    //用来防止重复节点继续入队列
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    //set里面没有才能进队列
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}

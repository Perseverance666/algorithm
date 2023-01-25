package com.example.class16;

import com.example.class16.other.Graph;
import com.example.class16.other.Node;

import java.util.*;

/**
 * @Date: 2023/1/23 20:47
 * 图的拓扑排序
 *
 * 返回入度为0的点，然后将这个点和相关边删除，再返回入度为0的点，依次类推，形成拓扑排序
 * 注意：拓扑排序一般不唯一
 */
public class Code03_TopologySort {
    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        // key->某个节点  value->这个节点的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 只有入度为0的点，才进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            //1、将所有节点和入度信息存入inMap
            inMap.put(node, node.in);
            if (node.in == 0) {
                //2、若节点入度为0，存入队列
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            //3、弹出队列第一个节点，并加入result
            Node cur = zeroInQueue.poll();
            result.add(cur);
            //4、删除该节点，就是把它所有邻接节点的入度都减1
            for (Node next : cur.nexts) {
                //邻接节点入度减1
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }
}

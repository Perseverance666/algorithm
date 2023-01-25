package com.example.class16;

import com.example.class16.other.Edge;
import com.example.class16.other.Graph;
import com.example.class16.other.Node;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Date: 2023/1/24 21:38
 * 最小生成树算法Prim
 */
public class Code05_Prim {
    //边权重从小到大排序
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        // 依次挑选的的边在result里
        Set<Edge> result = new HashSet<>();
        // 随便挑了一个点，node 是开始点
        for (Node node : graph.nodes.values()) {    //for循环的目的是防止森林，要把所有节点都考虑到
            if (!nodeSet.contains(node)) {
                //1、这个点被解锁了
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    //2、解锁这个点相连的边，按边的权重放入小根堆中
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    //3、弹出解锁的边中权重最小的
                    Edge edge = priorityQueue.poll();
                    //4、检查这条边的to节点
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)) { // 点集合中没有，说明这是新的点
                        //5、这个点被解锁了，这条边加入result集合
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            //6、解锁这个点相连的边
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            // break;
        }
        return result;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visit = new boolean[size];
        visit[0] = true;
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }
        int sum = 0;
        for (int i = 1; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("hello world!");
    }

}

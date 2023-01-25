package com.example.class16;

import com.example.class16.other.Edge;
import com.example.class16.other.Node;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
/**
 * @Date: 2023/1/24 22:12
 */
public class Code06_Dijkstra {
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        //存放from节点到其他节点的距离(以下简称距离)
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 存放已经确定最短路径的节点
        HashSet<Node> selectedNodes = new HashSet<>();
        //1、先获取没有确定最短路径节点中距离最小的节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            //2、获取当前map中记录的距离
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                //3、考察这个节点的所有边的to节点
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    //4、若to节点在map中没有数据，说明距离是正无穷，给to节点赋新值distance + edge.weight
                    distanceMap.put(toNode, distance + edge.weight);
                } else { // toNode
                    //5、若to节点在map中有数据，to节点距离取 map中数据和这次数据distance + edge.weight的较小值
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            //6、这个节点minNode已经确定，以后不在考虑，准备下一个没有确定最短路径节点中距离最小的节点
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            //update
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(heapIndexMap.get(node));
            }
            //add
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            //将堆顶元素与堆最后一个元素交换位置，然后删除最后一个元素，再进行heapify，实现堆顶元素弹出
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        //将index上的数进行向上调整
        private void insertHeapify(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //将index上的数进行向下调整
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                //选左右孩子中较小的
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                //选孩子和爹中较小的
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            //只要反向索引表中有数据，不管是-1还是其他的，说明这个节点已经进入过堆了
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            //若反向索引表中的值为-1，说明不在堆中，已经弹出了
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size); //加强堆
        //1、将head节点添加到加强堆中
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>(); //用于收集最后的结果
        while (!nodeHeap.isEmpty()) {
            //2、弹出加强堆中的节点，它的距离是最小的
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                //3、处理这个节点的所有边的to节点
                //若加强堆中没有，add。若此时的距离edge.weight + distance < 加强堆中的距离，update。若此时距离 >=加强堆中距离，ignore
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            //4、该节点处理完成，以后不在讨论，放入到结果集
            result.put(cur, distance);
        }
        return result;
    }

}

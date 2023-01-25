package com.example.class16.other;


/**
 * @Date: 2023/1/23 19:28
 *  由已知二维数组matrix转换为自己定义的图
 *
 * 	 二维数组matrix是一个 N*3 的矩阵，matrix记录着所有边的信息
 * 	一行matrix的结构为： [weight, from节点上面的值，to节点上面的值]
 * 	例：	[ 5 , 0 , 7]	代表一条边的权重是5，来自值为0的节点，去往值为7的节点
 */
public class GraphGenerator {
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			 // 拿到每一条边， matrix[i] 
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			//初始化图中点的信息
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}

			Node fromNode = graph.nodes.get(from);
			Node toNode = graph.nodes.get(to);
			Edge newEdge = new Edge(weight, fromNode, toNode);

			fromNode.nexts.add(toNode);
			fromNode.out++;
			fromNode.edges.add(newEdge);
			toNode.in++;
			//初始化图中边的信息
			graph.edges.add(newEdge);
		}
		return graph;
	}

}

package com.example.class16.other;


/**
 * @Date: 2023/1/23 19:28
 * 边结构的描述
 */
public class Edge {
	public int weight;		//边的权重
	public Node from;		//这条边来自哪个节点
	public Node to;			//这条边去往哪个节点

	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

}

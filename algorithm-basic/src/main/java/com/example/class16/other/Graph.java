package com.example.class16.other;

import java.util.HashMap;
import java.util.HashSet;


/**
 * @Date: 2023/1/23 19:28
 * 图结构的描述
 */
public class Graph {
	public HashMap<Integer, Node> nodes;	//点集
	public HashSet<Edge> edges;				//边集
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}

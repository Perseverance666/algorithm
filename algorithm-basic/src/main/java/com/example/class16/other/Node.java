package com.example.class16.other;

import java.util.ArrayList;


/**
 * @Date: 2023/1/23 19:28
 * 点结构的描述
 */
public class Node {
	public int value;	//点的值
	public int in;		//入度
	public int out;		//出度
	public ArrayList<Node> nexts;	//从这个点出发的直接点
	public ArrayList<Edge> edges;	//从这个点出发的直接边

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}

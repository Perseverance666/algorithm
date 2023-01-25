package com.example.class16;

import com.example.class16.other.Node;

import java.util.HashSet;
import java.util.Stack;

/**
 * @Date: 2023/1/23 19:54
 * 图的深度优先遍历 DFS
 * 用栈（迭代方式），也可以递归
 */
public class Code02_DFS {
    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();      //栈里面存的是目前的整条路径
        HashSet<Node> set = new HashSet<>();    //用来记录已经打印过的节点，避免重复打印
        stack.add(start);
        set.add(start);
        //压入栈就打印
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            //准备遍历当前弹出节点的所有邻接节点
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    //只要set集合中没有记录该邻接节点，就将当前节点和该邻接节点压入栈，并且结束循环不去检查其他邻接节点了
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    //压入栈就打印
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}

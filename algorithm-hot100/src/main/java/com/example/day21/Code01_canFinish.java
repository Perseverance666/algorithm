package com.example.day21;

import java.util.*;

/**
 * @Date: 2023/5/16 15:34
 * 207. 课程表
 *
 * https://leetcode.cn/problems/course-schedule/
 */
public class Code01_canFinish {
    //1、BFS。 拓扑排序
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //用于记录某一个节点的入度大小
        int[] inDegrees = new int[numCourses];
        //用于记录所有节点的所有邻接节点
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            inDegrees[cp[0]]++;
            list.get(cp[1]).add(cp[0]);
        }
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            //将入度为0的节点加入到队列
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        // BFS TopSort.
        while (!queue.isEmpty()) {
            int i = queue.poll();
            //讨论完一个节点课程数减一，最后看是否课程数为0
            numCourses--;
            for (int j : list.get(i)) {
                //将i节点的所有邻接节点的入度都减1
                inDegrees[j]--;
                //将入度为0的节点加入到队列
                if (inDegrees[j] == 0) {
                    queue.add(j);
                }
            }
        }
        //若课程安排图中存在环，一定有节点的入度始终不为0，
        return numCourses == 0;
    }

    //2、DFS
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>();

        //未被 DFS 访问：i == 0；
        //已被其他节点启动的 DFS 访问：i == -1；
        //已被当前节点启动的 DFS 访问：i == 1
        int[] flags = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            list.get(cp[1]).add(cp[0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (dfs(list, flags, i)) return false;
        }
        return true;
    }

    //DFS遍历检验是否有环，有环返回true
    private boolean dfs(List<List<Integer>> list, int[] flags, int i) {
        if (flags[i] == -1) {
            //当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，此时无环返回false
            return false;
        }
        if (flags[i] == 1) {
            //在本轮 DFS 搜索中节点 i 被第2次访问，即 课程安排图有环 ，直接返回 true
            return true;
        }
        //标记当前节点被本轮 DFS 访问过
        flags[i] = 1;
        //递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回true
        for (Integer j : list.get(i)) {
            if (dfs(list, flags, j)) return true;
        }
        //准备讨论其他节点，其他节点将不会讨论该节点
        flags[i] = -1;
        return true;
    }
}

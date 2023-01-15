package com.example.class13;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/1/14 20:36
 * 派对的最大快乐值问题
 *
 * 题目：公司的每个员工都符合Employee类的描述。整个公司的人员结构可以看作是一棵标准的、没有环的多叉树。
 * 树的头节点是公司唯一的老板，除老板外，每个员工都有唯一的直接上级。叶节点是没有任何下属的基层员工，除基层员工外，每个员工都有一个或多个直接下级。
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下规则：
 *  1、如果某个员工来了，那么这员工的所有直接下级都不能来。2、派对的整体快乐值是所有到场员工快乐值的累加。3、你的目标是让派对的整体快乐值尽量大。
 *
 * 给定一个头节点boss，请返回派对的最大快乐值。
 */
public class Code03_MaxHappy {
    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    public static int maxHappy2(Employee head){
        //取boss来或者不来中快乐值较大的
        return Math.max(process(head).yes,process(head).no);
    }
    public static class Info{
        public int yes;  //该节点来的时候的最大快乐值
        public int no;   //该节点不来的时候的最大快乐值
        public Info(int y,int n){
            yes = y;
            no = n;
        }
    }
    public static Info process(Employee x) {
        if (x == null) {
            return new Info(0, 0);
        }
        int yes = x.happy;
        int no = 0;
        for (Employee e : x.nexts) {
            Info info = process(e);
            //1、当前节点来，它的直接下级一定不来。当前节点来时的最大快乐值 = 所有直接下级不来时的最大快乐值
            yes += info.no;
            //2、当前节点不来，它的直接下级可能来可能不来。当前节点来时的最大快乐值 = max（所有直接下级不来时的最大快乐值，所有直接下级来时的最大快乐值）
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes, no);
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

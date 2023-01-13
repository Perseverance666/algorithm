package com.example.class12;

import java.util.ArrayList;

/**
 * @Date: 2023/1/13 20:43
 * 在线测试链接 : https://leetcode.cn/problems/largest-bst-subtree
 *
 * 找到二叉树中的最大搜索二叉子树的节点个数  --- 说明整棵树不一定是搜索二叉树
 * 题目：给定一棵二叉树的头节点head，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉子树，并返回这棵子树的节点个数
 */
public class Code06_MaxSubBSTSize {

    // 提交时不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }


    //节点构成树的相关信息
    public static class Info {
        public int maxBSTSubtreeSize;  //当前节点构成树的最大搜索二叉子树的节点个数
        public int size; //当前节点构成树的所有节点个数。
        //注意：当 maxBSTSubtreeSize == size时，说明该节点构成树是搜索二叉树，这里我们不用单定义另一个变量isBST了
        public int max; //当前节点构成树的节点数值的最大值
        public int min; //当前节点构成树的节点数值的最小值

        public Info(int ms,int s,int ma,int mi){
            maxBSTSubtreeSize = ms;
            size = s;
            max = ma;
            min = mi;
        }
    }
    //主函数，返回二叉树中的最大搜索二叉子树的节点个数
    public static int largestBSTSubtree(TreeNode head) {
        if(head == null){
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }
    //返回指定节点构成树的信息
    public static Info process(TreeNode x) {
        if (x == null) {
            //因为涉及到max和min，这里返回null而不返回对象，后面的分析过程中要注意空指针异常
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //这里其实没太大用，主要用于递归传递信息
        int size = (leftInfo == null ? 0 : leftInfo.size) + (rightInfo == null ? 0 : rightInfo.size) + 1;
        int max = x.val;
        int min = x.val;
        if (leftInfo != null) {
            max = Math.max(max,leftInfo.max);
            min = Math.min(min,leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max,rightInfo.max);
            min = Math.min(min,rightInfo.min);
        }
        //该节点构成树的maxBSTSubtreeSize只有3种可能
        //1、整棵树不是搜索二叉树，取左树的maxBSTSubtreeSize
        int p1 = -1;  //给个默认值-1，如果左树不存在，后面3种可能取最大时，-1会被舍弃
        if(leftInfo != null){
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        //2、整棵树不是搜索二叉树，取右树的maxBSTSubtreeSize
        int p2 = -1;
        if(rightInfo != null){
            p2 = rightInfo.maxBSTSubtreeSize;
        }
        //3、整棵树是搜索二叉树
        int p3 = -1;
        //3.1、先判断左右子树是否是搜索二叉树
        boolean leftIsBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.size);
        boolean rightIsBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.size);
        if(leftIsBST && rightIsBST){
            //3.2、然后判断该节点的左树的最大值是否 < 该节点，右树的最小值是否 > 该节点
            boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
            boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
            if(leftMaxLessX && rightMinMoreX){
                //这样这棵树才是搜索二叉树
                int leftSize = leftInfo == null ? 0 : leftInfo.size;
                int rightSize = rightInfo == null ? 0 : rightInfo.size;
                p3 = leftSize + rightSize + 1;
            }

        }
        //4、该节点构成树的maxBSTSubtreeSize是上面3种可能的最大值
        int maxBSTSubtreeSize = Math.max(Math.max(p1,p2),p3);
        return new Info(maxBSTSubtreeSize, size, max, min);
    }

    // 为了验证
    // 对数器方法
    public static int right(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(right(head.left), right(head.right));
    }

    // 为了验证
    // 对数器方法
    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    // 为了验证
    // 对数器方法
    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // 为了验证
    // 对数器方法
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // 为了验证
    // 对数器方法
    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (largestBSTSubtree(head) != right(head)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}

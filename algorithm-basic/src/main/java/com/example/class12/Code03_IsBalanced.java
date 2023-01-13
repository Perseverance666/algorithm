package com.example.class12;

/**
 * @Date: 2023/1/13 19:18
 * 判断二叉树是否是平衡二叉树
 * 平衡二叉树的条件：
 *      1、每个节点的左树和右树都是平衡二叉树
 *      2、每个节点左右树的高度之差<2
 */
public class Code03_IsBalanced {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //存放某个节点构成树的高度以及是否是平衡二叉树
    public static class Info{
        public boolean isBalanced;
        public int height;

        public Info(boolean i,int h){
            isBalanced = i;
            height = h;
        }
    }
    //主函数，判断二叉树是否是平衡二叉树
    public static boolean isBalanced2(Node head){
        return process(head).isBalanced;
    }

    //返回指定节点的树的信息
    public static Info process(Node x){
        if(x == null){
            return new Info(true,0);
        }
        //获取左右子树的信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        //该节点树的高度
        //这里其实没太大用，主要用于递归传递信息
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = true;
        //1、要满足左树和右树都是平衡二叉树
        if(!leftInfo.isBalanced || !rightInfo.isBalanced){
            isBalanced = false;
        }
        //2、该节点左右树的高度之差<2。满足这两条该节点的树才是平衡二叉树
        if(Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalanced = false;
        }

        return new Info(isBalanced,height);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

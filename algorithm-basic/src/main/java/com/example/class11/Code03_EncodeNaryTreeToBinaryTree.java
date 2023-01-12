package com.example.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/1/12 20:22
 * 本题测试链接：https://leetcode.cn/problems/encode-n-ary-tree-to-binary-tree
 *
 * 将N叉树编码为二叉树
 * 将N叉树某个节点的所有孩子 在转为二叉树时都遍历到这个节点的左数一直往右遍历，这样转换后保证唯一，转换后的二叉树所有节点的右孩子都是null
 *
 */
public class Code03_EncodeNaryTreeToBinaryTree {
    // 多叉树结构
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    // 二叉树结构
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 只提交这个类即可
    class Codec {
        // 编码为二叉树
        public TreeNode encode(Node root) {
            if (root == null) {
                return null;
            }
            //1、将多叉树的根节点放到二叉树的根节点上
            TreeNode head = new TreeNode(root.val);
            //2、递归调用将多叉树的根节点的孩子放到二叉树的左孩子一直往右遍历
            head.left = en(root.children);
            //二叉树的所有节点的右孩子一直为null
            return head;
        }

        private TreeNode en(List<Node> children) {
            TreeNode head = null;
            TreeNode cur = null;
            for (Node child : children) {
                TreeNode tNode = new TreeNode(child.val);
                if (head == null) {
                    //1、head指向第一个孩子。整个for循环只执行一次，最终返回head
                    head = tNode;
                } else {
                    //2、将传来的所有孩子一直往右遍历
                    cur.right = tNode;
                }
                cur = tNode;
                //3、这些孩子中也有孩子，递归调用将它们的孩子放到二叉树的左孩子一直往右遍历
                cur.left = en(child.children);
            }
            return head;
        }

        // 解码为多叉树
        public Node decode(TreeNode root) {
            if (root == null) {
                return null;
            }
            return new Node(root.val, de(root.left));
        }

        //生成多叉树传入节点的所有孩子
        public List<Node> de(TreeNode root) {
            List<Node> children = new ArrayList<>();
            //从根节点开始遍历
            while (root != null) {
                //1、二叉树的节点的左孩子是多叉树节点的第一个孩子
                Node cur = new Node(root.val, de(root.left));
                children.add(cur);
                //2、然后二叉树节点左孩子一直向右遍历，都是原节点的孩子们。
                root = root.right;
            }
            return children;
        }

    }
}

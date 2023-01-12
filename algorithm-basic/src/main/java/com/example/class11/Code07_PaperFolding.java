package com.example.class11;

/**
 * @Date: 2023/1/12 23:04
 * 题目：请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有3条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。请从上到下打印所有折痕的方向。
 * 例：N=1时，打印：down；N=2时，打印：down down up
 *
 * 思路：转换成中序遍历二叉树问题
 */
public class Code07_PaperFolding {
    public static void printAllFolds(int N) {
        //打印第一层第一个节点的状态，第一个节点为凹
        process(1, N, true);
        System.out.println();
    }

    // 当前你来了一个节点，脑海中想象的！
    // 这个节点在第i层，一共有N层，N固定不变的
    // 这个节点如果是凹的话，down = T
    // 这个节点如果是凸的话，down = F
    // 函数的功能：中序打印以你想象的节点为头的整棵树！
    public static void process(int i, int N, boolean down) {
        //检验该层是否超出N了
        if (i > N) {
            return;
        }
        //1、先打印该节点下一层的左孩子，左孩子为凹
        process(i + 1, N, true);
        //2、打印节点
        System.out.print(down ? "凹 " : "凸 ");
        //3、最后打印该节点下一层的右孩子，右孩子为凸
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 4;
        printAllFolds(N);
    }
}

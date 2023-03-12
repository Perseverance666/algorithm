package com.example.day02_链表;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2023/3/3 19:45
 * 剑指 Offer 06. 从尾到头打印链表
 */
public class Code01_ReversePrint {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        //将链表逆序放在list中
        process(head,list);
        int[] ans = new int[list.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = list.get(i);
        }
        return ans;

    }
    //将node以及往后的节点逆序添加到list中
    public static void process(ListNode node,List<Integer> list){
        if(node == null){
            return;
        }
        process(node.next,list);
        list.add(node.val);
    }
}

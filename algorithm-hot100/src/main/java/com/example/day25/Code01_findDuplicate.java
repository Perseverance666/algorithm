package com.example.day25;

/**
 * @Date: 2023/5/31 20:02
 * 287. 寻找重复数
 * <p>
 * https://leetcode.cn/problems/find-the-duplicate-number/
 * <p>
 * 方法与142. 环形链表 II类似，将数组看成链表，如果有重复数字就有环，用快慢指针
 * 例如nums = [3,1,3,4,2]，给出数组下标对应关系：
 * 0 -> 3
 * 1 -> 1
 * 2 -> 3
 * 3 -> 4
 * 4 -> 2
 * 这样将数组看成一个链表： 3 -> 4 -> 2 -> 3 -> 4....
 * 只要有重复数字就一定有环，返回链表开始入环的第一个元素就是重复的元素
 */
public class Code01_findDuplicate {
    public int findDuplicate(int[] nums) {
        //slow，fast开始都指向链表第一个元素
        int slow = nums[0];
        int fast = nums[0];
        while (true) {
            //只要有重复的数字，最终指针一定会相遇
            slow = nums[slow];          //每次移动一步
            fast = nums[nums[fast]];    //每次移动两步
            if(slow == fast) break;
        }
        //新建一个指针ptr指向第一个元素，ptr和slow每次都移动一步,
        //等到他两相遇，就找到入环的第一个元元素了，记住即可！！
        int ptr = nums[0];
        while (ptr != slow) {
            slow = nums[slow];
            ptr = nums[ptr];
        }
        return ptr;
    }
}

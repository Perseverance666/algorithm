package com.example.day13_双指针;

/**
 * @Date: 2023/3/14 19:31
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 */
public class Code01_Exchange {
    public int[] exchange(int[] nums) {
        int L = 0;
        int R = nums.length - 1;
        while (L < R) {
            while (L < R && (nums[L] & 1) == 1) {//L是奇数
                L++;
            }
            while (L < R && (nums[R] & 1) == 0) {//R是偶数
                R--;
            }
            //最终找到第一个不满足的L和R，交换
            swap(nums, L, R);
        }
        return nums;
    }

//    public int[] exchange(int[] nums) {
//        int L = 0;
//        int R = nums.length - 1;
//        while (L < R) {
//            if (nums[L] % 2 == 0 && nums[R] % 2 != 0) {
//                //L是偶，R是奇，两位置交换，比较下一组数
//                swap(nums, L, R);
//                L++;
//                R--;
//            } else if (nums[L] % 2 == 0 && nums[R] % 2 == 0) {
//                //L是偶，R是偶。R正确，R--
//                R--;
//            } else if (nums[L] % 2 != 0 && nums[R] % 2 != 0) {
//                //L是奇，R是奇，L正确，L++
//                L++;
//            } else {
//                //L是奇，R是偶，正确，比较下一组数
//                L++;
//                R--;
//            }
//        }
//        return nums;
//    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

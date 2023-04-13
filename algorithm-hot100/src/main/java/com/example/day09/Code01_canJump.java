package com.example.day09;

/**
 * @Date: 2023/4/12 15:25
 * 55. 跳跃游戏
 * <p>
 * https://leetcode.cn/problems/jump-game/?favorite=2cktkvj
 */
public class Code01_canJump {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        //dp[i]表示i位置是否能到尾部n-1位置
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        int canJumpTailIndex = n - 1;  //用来 从右往左 依次记录能到到尾部n-1的位置
        for(int i = n - 2; i >= 0; i--){
            if(i + nums[i] >= canJumpTailIndex){
                dp[i] = true;
                canJumpTailIndex = i;
            }
        }
        return dp[0];
    }
}

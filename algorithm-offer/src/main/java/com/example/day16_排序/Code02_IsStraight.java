package com.example.day16_排序;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Date: 2023/3/17 12:16
 * 面试题61. 扑克牌中的顺子
 */
public class Code02_IsStraight {
    //除去所有的0以外，最大值 - 最小值 < 5就能构成顺子
    //排序，O(logN)
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;  //用来统计0的数量
        for(int i = 0; i <= 3; i++){
            if(nums[i] == 0){
                joker++;
            }
            if(nums[i] != 0 && nums[i] == nums[i+1]){
                //nums[i]不是0，并且与nums[i+1]重复，提前宣布不是顺子
                return false;
            }
        }
        return nums[4] - nums[joker] < 5;
    }
    //不用排序，O(n)
    public boolean isStraight2(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int min = 14;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                if(map.containsKey(nums[i])){
                    return false;
                }else{
                    map.put(nums[i],1);
                    max = Math.max(max,nums[i]);
                    min = Math.min(min,nums[i]);
                }
            }
        }
        return max - min < 5;
    }
}

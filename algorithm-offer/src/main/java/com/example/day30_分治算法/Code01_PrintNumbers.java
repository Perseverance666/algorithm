package com.example.day30_分治算法;

/**
 * @Date: 2023/3/31 14:21
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 这道题要考虑 大数越界情况下的打印
 * https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 */
public class Code01_PrintNumbers {
    int[] res;
    int count = 0;

    public int[] printNumbers(int n) {
        res = new int[(int)Math.pow(10, n) - 1];
        for(int digit = 1; digit <= n; digit++){      //digit表示要生成的数字的位数
            for(char first = '1'; first <= '9'; first++){  //first表示第1位的数
                char[] num = new char[digit];
                num[0] = first;
                dfs(1, num, digit);
            }
        }
        return res;
    }
    //要生成的数有digit位，num存放要生成的数，对于num，0~index-1上的数已经讨论完毕，现在讨论index上的这一位数
    public void dfs(int index, char[] num, int digit){
        if(index == digit){
            //对于num，此时index已经越界。将填好的数num装入到res中
            res[count++] = Integer.parseInt(String.valueOf(num));
        }else{
            for(char i = '0'; i <= '9'; i++){
                //对于要生成的数num，index这一位填i
                num[index] = i;
                //准备讨论index+1位
                dfs(index + 1, num, digit);
            }
        }
    }

//    // 不考虑大数越界
//    public int[] printNumbers(int n) {
//        int end = (int) (Math.pow(10, n) - 1);
//        int[] res = new int[end];
//        for (int i = 0; i < end; i++) {
//            res[i] = i + 1;
//        }
//        return res;
//    }

}

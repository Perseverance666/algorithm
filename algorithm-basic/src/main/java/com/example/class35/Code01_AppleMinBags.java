package com.example.class35;

/**
 * @Date: 2023/2/16 13:52
 * 最少袋子装苹果
 *
 * 题目：小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量
 * 1）能装下6个苹果的袋子
 * 2）能装下8个苹果的袋子
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，
 * 且使用的每个袋子必须装满，给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 */
public class Code01_AppleMinBags {
    //1、暴力解
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        //先用最多的装8个苹果的袋子，然后袋子依次减少，装6个苹果的袋子依次增多，直到找到合适的最少袋子
        int bag8 = (apple >> 3);
        int rest = apple - (bag8 << 3);
        while(bag8 >= 0) {
            // rest 个
            if(rest % 6 ==0) {
                return bag8 + (rest / 6);
            } else {
                bag8--;
                rest += 8;
            }
        }
        return -1;
    }
    //2、根据对数器找规律
    public static int minBagAwesome(int apple) {
        //1、如果是奇数，返回-1
        if ((apple & 1) != 0) {
            return -1;
        }
        //2、apple<18，没看出啥规律，直接硬写
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        //3、apple>=18,8个数为一组，奇数为-1，偶数从3开始总过4个3，每一组过后偶数的值+1
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
        for(int apple = 1; apple < 200;apple++) {
            System.out.println(apple + " : "+ minBags(apple));
        }

    }
}

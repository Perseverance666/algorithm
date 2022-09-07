package com.example.day02;

/**
 * @Date: 2022/9/7 18:52
 *
 *
 * 已知f1()等概率返回[1,5]之间整数，求等概率返回[1,7]之间整数。已知f1(),求g()
 *
 * [1,5] -> [0,1] -> [0,6] -> [1,7]
 */
public class Primary05_Random1 {
    public static void main(String[] args) {
        int count =0;
        int testTimes = 10000000;

        System.out.println("= 等概率返回0,1 =");
        count = 0;
        for(int i = 0; i < testTimes; i++){
            if(f2() == 0){
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);

        System.out.println("= 等概率返回[1,7]之间整数 =");
        int[] counts = new int[8];
        for(int i = 0; i < testTimes; i++){
//            //等概率返回[0,7]之间整数
//            int ans = f3();
//            //等概率返回[0,6]之间整数
//            int ans = f4();
            //等概率返回[1,7]之间整数
            int ans = g();
            counts[ans]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }

    }

    //等概率返回[1,5]之间整数
    public static int f1(){
        return (int)(Math.random() * 5) + 1;
    }
    //随机机制，只能用f1，等概率返回0,1
    public static int f2(){
        int ans = 0;
        do{
            ans = f1();
        }while(ans == 3);
        //ans若是1,2 返回0；若是4,5 返回1；不让他返回3，即实现等概率返回0,1
        return ans < 3 ? 0 : 1;
    }

    // [1,7] -> [0,6]  因为2的3次方的8，故需要3位二进制位来表示[0,6],先表示[0,7]
    // 得到000~111 等概率返回[0,7]
    public static int f3(){
        return (f2() << 2) + (f2() << 1) + (f2() << 0);
    }

    // 等概率返回[0,6]
    public static int f4(){
        int ans = 0;
        do{
            ans = f3();
        }while(ans == 7);
        return ans;
    }

    //等概率返回[1,7]
    public static int g(){
        return f4() + 1;
    }
}

package com.example.day02;

/**
 * @Date: 2022/9/7 18:25
 *
 *
 * 求指定索引的前缀和
 */
public class Primary04_PreSum {
    public static class RangeSum1{
        private int[] arr;

        public RangeSum1(int[] array){
            arr = array;
        }

        public int rangeSum(int L,int R){
            int sum = 0;
            for(int i = L; i <= R;i ++){
                sum += arr[i];
            }
            return sum;
        }
    }

    public static class RangeSum2{
        private int[] preSum;

        public RangeSum2(int[] arr){
            preSum = new int[arr.length];
            preSum[0] = arr[0];
            for(int i = 1; i <arr.length; i++){
                preSum[i] = preSum[i - 1] + arr[i];
            }
        }

        public int rangeSum(int L,int R){
            return L == 0 ? preSum[R] : preSum[R] - preSum[L-1];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
//        RangeSum1 rs = new RangeSum1(arr);
        RangeSum2 rs = new RangeSum2(arr);
        int sum = rs.rangeSum(0, 2);
        System.out.println(sum);
    }

}

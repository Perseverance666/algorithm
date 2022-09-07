package com.example.day02;

/**
 * @Date: 2022/9/7 20:57
 *
 * 对数器：生成随机样本自己做比对的机器，调bug
 */
public class Primary07_Comp {
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //选择排序
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        //0~n-1 1~n-1  2~n-1 ... i~n-1
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
//                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
                minValueIndex = arr[j] >= arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr,i,minValueIndex);
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }

        //0~n-1  0~n-2 ...
        for(int end = arr.length - 1; end > 0; end--){
            //0 1    1 2    .... end-1 end
            for(int i = 1; i <= end; i++){
                if(arr[i] < arr[i-1]){
                    swap(arr,i,i-1);
                }
            }
        }
    }

    //插入排序
    public static void insertSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }

        //0~1 0~2 0~3 ... 0~n-1
        for(int end = 1; end < arr.length; end++){
            for(int curNumIndex = end; curNumIndex-1 >= 0  && arr[curNumIndex-1] > arr[curNumIndex]; curNumIndex--){
                swap(arr,curNumIndex-1,curNumIndex);
            }
        }
    }


    //返回一个数组arr,arr长度为[0,maxLen-1],arr中每个值[0,maxValue-1]
    public static int[] lenRandomValueRandom(int maxLen,int maxValue){
        int len = (int)(Math.random() * maxLen);
        int[] arr = new int[len];
        for(int i = 0 ; i < len; i ++){
            arr[i] = (int)((Math.random()) * maxValue);
        }
        return arr;
     }

     //拷贝函数
    public static int[] copyArray(int[] arr){
        int[] newArr = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    //arr1和arr2等长，验证该数组是否有序
    public static boolean isSorted(int[] arr){
        if(arr.length <= 1){
            return true;
        }
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(max > arr[i]){
                return false;
            }
            max = arr[i];
        }
        return true;
    }


    public static void main(String[] args) {
        int maxLen = 5;
        int maxValue = 1000;
        int testTime = 10000;
        for(int i = 0; i < testTime; i++){
            int[] arr1 = lenRandomValueRandom(maxLen,maxValue);
            int[] temp = copyArray(arr1);
            selectSort(arr1);
            if(!isSorted(arr1)){
                for(int j = 0; j < temp.length; j++){
                    System.out.print(temp[j] + " ");
                }
                System.out.println();
                System.out.println("选择排序有误！");
                break;
            }

         }
    }
}

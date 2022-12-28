# 算法笔记

## 一、基础+进阶

### 1、class01

#### 1）选择排序

1.先从a[0] - a[N-1] 个数中找到最小的数把它放在a[0]的位置

2.在从a[1] - a[N-1]个数中找到最小的数把它放在a[1]的位置上，以此类推

3.一次循环找到一个最小值，放在开头，然后每次循环找一个最小值往之前找到的最小值后面放，复杂度为O(N^2)

```java
    public static void SelectionSort(int[] arr) {
        //1、临界条件判断
        if (arr == null || arr.length <= 1) {
            return;
        }
        //2、进行从小到大选择排序
        for (int i = 0; i < arr.length; i++) {
            //先找一个当做最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);
        }
    }
```

#### 2）冒泡排序

1.先从a[0] - a[N-1]个数中，从头开始两两比较，将较大的数放到后面，这样最后一个数就是最大的

2.再从a[0] - a[N-2]个数中，像上面步骤一样两两比较，这样倒数第二个数就是第二大的，以此类推

3.一次循环两两比较，最终将找到一个最大值放在最后，然后每次循环再找剩下数中的最大值，复杂度为O(N^2)

```java
    public static void BubbleSort(int[] arr) {
        //1、临界条件判断
        if (arr == null || arr.length <= 1) {
            return;
        }
        //2、进行从小到大冒泡排序  0 ~ N-1    0 ~ N-2  ...
        for (int i = arr.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j + 1, j);
                }
            }
        }

    }
```

#### 3）插入排序

1.先确保0 - 1 位置上的数有序，若a[1] 小于a[0]，交换，反之不变

2.在确保0 - 2 位置上的数有序，若a[2] 小于a[1]，交换，直到比前面的数大，或者前面没数了，以此类推

3.这样确保第i个数前面的数都是有序的，然后将该数插入到合适位置，复杂度为O(N^2)

```java
    public static void insertionSort(int[] arr) {
        //1、临界条件判断
        if (arr == null || arr.length <= 1) {
            return;
        }
        //2、进行从小到大插入排序 0~1   0~2 ... 最后0~N-1 上都是有序的
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j - 1] > arr[j]){
                    swap(arr, j-1, j);
                }
            }
        }
    }
```

#### 4）二分法

1、在一个有序数组中，找某个数是否存在

1.先找中间的数，若当前数 = 指定数，找到了

2.若当前数 < 指定数，说明所求可能在右边，将L移到mid右边

2.若当前数 > 指定数，说明所求可能在左边，将R移到mid左边

3.当L> R时，就全找完了，没有找到，返回false

```java
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L <= R){
            mid = L + ((R - L) >> 1); //等同于 mid = (L + R) / 2;
            if(sortedArr[mid] == num){
                return true;
            } else if(sortedArr[mid] < num){
                L = mid + 1;
            } else {
                R = mid - 1 ;
            }
        }
        //全都比较完了，没有找到
        return false;
    }
```

2、在一个有序数组中，找到 >= 某个数最左侧的位置 (<= 最右侧位置 问题类似)

1.先找中间的数，若当前数 >= 指定数，说明左面有可能还有符合要求的数，记录当前位置，并将R移到mid左边

2.若当前数 < 指定数，说明左边肯定没有了，不记录当前位置，并将L移到mid右边，继续向右找

3.当L> R时，就找完了，index存的就是所求

```java
    public static int nearestIndex(int[] arr, int value) {
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;
        while(L <= R){
            mid = L + ((R - L) >> 1);
            if(arr[mid] >= value){
                //若当前数 >= 指定数，记录当前位置，并将R移到mid左边
                index = mid;
                R = mid - 1;
            }else{
                //若当前数 < 指定数，不记录当前位置，并将L移到mid右边
                L = mid + 1;

            }
        }
        return index;
    }
```

3.局部最小值问题，找一个局部最小即可

1.先判断第0个数，或者第N-1个数是否是局部最小

2.以上两个数不满足，开始二分，若mid大于左边的数，不管是否大于还是小于右边的数，mid左边一定存在局部最小

3.若mid大于右边的数，不管是否大于还是小于左边的数，mid右边一定存在局部最小

4.当L=R时，这个数一定是局部最小

```java
    public static int getLessIndex(int[] arr) {
        if(arr == null || arr.length <= 0){
            return -1; //不存在
        }
        if(arr.length == 1 || arr[0] < arr[1]){
            return 0; //0位置就是局部最小
        }
        if(arr[arr.length - 1] < arr[arr.length - 2]){
            return arr.length - 1;  //arr.length -1 位置就是局部最小
        }
        int L = 1;
        int R = arr.length - 2;
        int mid = 0;
        while(L < R){
            mid = L + ((R - L) >> 1);
            if(arr[mid] < arr[mid -1] && arr[mid] < arr[mid + 1]){
                return mid;
            }else if(arr[mid] > arr[mid -1]){
                //说明局部最小在左边
                R = mid - 1;
            }else{
                //说明局部最小在右边
                L = mid + 1;
            }
        }
        //此时L = R,这个数一定是局部最小
        return L;
    }
```


package com.example.class37;

/**
 * @Date: 2023/2/17 20:09
 * 题目：给定一个整数组成的无序数组arr，值可能正、可能负、可能0，
 * 给定一个整数值K，找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的，返回其长度
 */
public class Code03_LongestLessSumSubArrayLength {
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //minSums[i]：以arr[i]为开头的最小累加和
        int[] minSums = new int[arr.length];
        //minSumsEnd[i]：以arr[i]为开头的最小累加和的右边界
        int[] minSumEnds = new int[arr.length];
        //1、从右往左填这两个数组
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                //minSums[i]只依赖minSums[i+1]就可以
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }

        int end = 0;    // end是扩不进来那一块儿的开头位置
        int sum = 0;    // 窗口累加和
        int ans = 0;    //最终结果，最大长度
        for (int i = 0; i < arr.length; i++) {
            //!!! 2、求每一个位置最远能扩多久，这其中有可能某个位置扩的是错误的，它的真实答案是比这个长度小的，但是不影响结果，因为我们要求最长的
            // 这样就确保整个过程不回退，达到O(n)

            // while循环结束之后：
            // 1) 如果以i开头的情况下，累加和<=k的最长子数组是arr[i..end-1]，看看这个子数组长度能不能更新res；
            // 2) 如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响最终结果；
            while (end < arr.length && sum + minSums[end] <= k) {
                //以arr[i]为开头子数组还可以往右扩
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            //end-i可能不是以arr[i]为开头且累加和<=K的长度，因为end-i可能>K，但是无所谓，
            // 如果这样的话arr[i]为开头且累加和<=K的长度一定比这个短，这就把这种可能给舍弃了不去讨论了，因为放弃这个可能不会耽误最终答案
            ans = Math.max(ans, end - i);

            if (end > i) { // 还有窗口，准备讨论arr[i+1]，把累加和减去
                sum -= arr[i];
            } else { // i == end,  即将 i++, i > end, 此时窗口概念维持不住了，所以end跟着i一起走
                //end能等于i说明，arr[i]这个数始终没法进入窗口里，即arr[i]为开头的所有累加和都>K，
                // 准备讨论下一个位置，i和end一块跳到下一个位置，检验arr[i+1]
                end = i + 1;
            }
        }
        return ans;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}

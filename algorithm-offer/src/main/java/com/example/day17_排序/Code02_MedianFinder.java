package com.example.day17_排序;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Date: 2023/3/18 19:47
 * 剑指 Offer 41. 数据流中的中位数
 * <p>
 * 思想：小根堆保存中位数以上的数，大根堆保存中位数以下的数
 * 数据流为奇数时，小根堆堆顶元素为中位数。所以只要数据流为奇数，小根堆就比大根堆多一个元素
 * 数据流为奇数时，小根堆堆顶元素和大根堆堆顶元素分别是中位数的两个。所以只要数据流为偶数，小根堆和大根堆元素就一样多
 */
public class Code02_MedianFinder {
    public class MedianFinder {
        //数据流为奇数时，小根堆堆顶元素为中位数
        //数据流为奇数时，小根堆堆顶元素和大根堆堆顶元素分别是中位数的两个
        PriorityQueue<Integer> smallHeap;   //小根堆保存中位数以上的数
        PriorityQueue<Integer> bigHeap;     //大根堆保存中位数以下的数

        public MedianFinder() {
            smallHeap = new PriorityQueue<>(); //小根堆
            bigHeap = new PriorityQueue<>((x, y) -> (y - x)); //大根堆
        }

        public void addNum(int num) {
            if (smallHeap.size() == bigHeap.size()) {
                //此时数据流中有偶数个值
                bigHeap.add(num);
                smallHeap.add(bigHeap.poll());
                //确保偶数时，小跟堆和大根堆的堆顶元素就是中间的两个元素
            } else {
                //此时数据流中有奇数个值
                smallHeap.add(num);
                bigHeap.add(smallHeap.poll());
                //确保奇数时，小跟堆的堆顶元素就是中位数
            }
        }

        public double findMedian() {
            if (smallHeap.size() == bigHeap.size()) {
                //数据流中有偶数个值
                return (smallHeap.peek() + bigHeap.peek()) / 2.0;
            } else {
                //数据流中有奇数个值
                return smallHeap.peek();
            }
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */

}

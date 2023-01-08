package com.example.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2023/1/7 20:02
 * 加强堆
 *
 *  T一定要是非基础类型，有基础类型需求 包一层
 */
public class HeapGreater<T> {

	private ArrayList<T> heap;				//堆
	private HashMap<T, Integer> indexMap;	//反向索引表
	private int heapSize;					//数组中表示堆的有效长度
	private Comparator<? super T> comp;		//比较器

	public HeapGreater(Comparator<? super T> c) {
		heap = new ArrayList<>();
		indexMap = new HashMap<>();
		heapSize = 0;
		comp = c;
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public int size() {
		return heapSize;
	}

	public boolean contains(T obj) {
		return indexMap.containsKey(obj);
	}

	public T peek() {
		return heap.get(0);
	}

	public void push(T obj) {
		//1、堆和反向索引表都要添加该元素
		heap.add(obj);
		indexMap.put(obj, heapSize);
		//2、将该元素向上heapInsert，继续变成堆
		heapInsert(heapSize++);
	}

	public T pop() {
		//1、获取要弹出的元素
		T ans = heap.get(0);
		//2、将要弹出的元素和最后一个元素交换
		swap(0, heapSize - 1);
		//3、反向索引表和堆 都要删除 交换后的最后一个元素(就是要弹出的元素)
		indexMap.remove(ans);
		heap.remove(--heapSize);
		//4、新换上来的元素，向下进行heapify，继续变成堆
		heapify(0);
		return ans;
	}

	//删除元素：就是把要删除的元素和最后一个元素互换 然后删除
	//经过该方法删除完某个元素后，仍满足堆
	public void remove(T obj) {
		//1、获取最后一个元素
		T replace = heap.get(heapSize - 1);
		//2、获取要删除元素的索引
		int index = indexMap.get(obj);
		//3、反向索引表删除该元素 堆逻辑删除数组最后一个位置
		indexMap.remove(obj);
		heap.remove(--heapSize);
		//如果要删除的元素不是最后一个元素
		if (obj != replace) {
			//4、将删除的元素的位置替换成原来最后一个元素 并更新原来最后一个元素的反向索引表
			heap.set(index, replace);
			indexMap.put(replace, index);
			//5、对原来最后一个元素进行调整，使其仍满足堆
			resign(replace);
		}
	}

	//对元素obj进行调整，使其仍满足堆
	public void resign(T obj) {
		//两个只会执行一个
		heapInsert(indexMap.get(obj));
		heapify(indexMap.get(obj));
	}

	// 请返回堆上的所有元素
	public List<T> getAllElements() {
		List<T> ans = new ArrayList<>();
		for (T c : heap) {
			ans.add(c);
		}
		return ans;
	}

	private void heapInsert(int index) {
		while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
			swap(index, (index - 1) / 2);
			index = (index - 1) / 2;
		}
	}

	private void heapify(int index) {
		int left = index * 2 + 1;
		while (left < heapSize) {
			int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
			best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
			if (best == index) {
				break;
			}
			swap(best, index);
			index = best;
			left = index * 2 + 1;
		}
	}

	private void swap(int i, int j) {
		T o1 = heap.get(i);
		T o2 = heap.get(j);
		heap.set(i, o2);
		heap.set(j, o1);
		indexMap.put(o2, i);
		indexMap.put(o1, j);
	}

}

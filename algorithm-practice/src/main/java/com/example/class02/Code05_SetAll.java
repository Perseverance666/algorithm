package com.example.class02;

import java.util.HashMap;

/**
 * @Date: 2023/2/28 16:47
 * 题目：设计有setAll功能的哈希表，put、get、setAll方法，时间复杂度O(1)
 */
public class Code05_SetAll {
    public static class MyValue<V> {
        public V value;
        public long time; //该节点被操作的时间点

        public MyValue(V v, long t) {
            value = v;
            time = t;
        }
    }

    public static class MyHashMap<K, V> {
        private HashMap<K, MyValue<V>> map;
        private long time;          //此时的时间点
        private MyValue<V> setAll;  //setAll的信息和时间点

        public MyHashMap() {
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<V>(null, -1);
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<V>(value, time++));
        }

        public void setAll(V value) {
            setAll = new MyValue<V>(value, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                //在setAll之后，又进行了其他操作，返回map中的值
                return map.get(key).value;
            } else {
                //最后进行setAll的，返回setAll的值
                return setAll.value;
            }
        }
    }
}

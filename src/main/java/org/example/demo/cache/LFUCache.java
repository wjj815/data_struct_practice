package org.example.demo.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 最近最少使用
 */
public class LFUCache {

    // HashMap存储key到val的映射
    Map<Integer, Integer> keyToVal;
    //    HashMap存储key到freq的映射
    Map<Integer, Integer> keyToFreq;

    // 存储 freq 与 keys 的映射
    Map<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 存储最小频次
    int minFreq;
    // 构造容量为 capacity 缓存

    private int cap;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.minFreq = 0;
        this.cap = capacity;
    }

    // 在缓存中查询 key
    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加 key 的 freq
        increaseFreq(key);

        return keyToVal.get(key);
    }

    // 将 key 和 value 存入缓存
    public void put(int key, int value) {
        // 若存在 key 则修改值即可
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }

        // 如果容量满了，则删除最近最少使用的键
        if (cap == keyToVal.size()) {
            removeMinFreqKey();
        }

        // 存值
        keyToVal.put(key, value);
        // 存 key 对应的 freq
        keyToFreq.put(key, 1);
        // 存 freq 对应的 key
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 插入新值后 最小的freq 肯定是 1
        this.minFreq = 1;
    }

    private void removeMinFreqKey() {
        // freq最小的 key 列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        // 其中最先被插入的那个 key就是该淘汰的 key
        Integer deletedKey = keyList.iterator().next();

        // 更新 FK 表
        keyList.remove(deletedKey);

        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);
        }

        // 更新 KV 表
        keyToVal.remove(deletedKey);
        // 更新 KF表
        keyToFreq.remove(deletedKey);
    }

    public void increaseFreq(int key) {
        // 获取 freq
        Integer freq = keyToFreq.get(key);
        // 从原 freq 中移除当前 key
        freqToKeys.get(freq).remove(key);
        // 如果当前 freq 对应的 keys 为空则从 FK中删除
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果更新的刚好是 minFreq,则 minFreq++
            if (this.minFreq == freq) {
                this.minFreq++;
            }
        }
        // 更新 key 的 freq
        keyToFreq.put(key, freq + 1);
        // FK 中若无 freq+1 的键则新增
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        // 将当前键加入到 freq+1 的键值里
        freqToKeys.get(freq + 1).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.put(1, 2);
        cache.put(2, 2);
        cache.put(3, 2);

        cache.get(1);
        cache.get(2);
        cache.put(4, 2);
        cache.get(4);
        cache.get(1);
        cache.put(5,2);
        System.out.println(cache.freqToKeys);
    }
}

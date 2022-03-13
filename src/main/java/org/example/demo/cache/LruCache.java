package org.example.demo.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用算法
 * 实现方式： 哈希表+双向链表
 * reference : 剑指 Offer II 031. 最近最少使用缓存
 */
public class LruCache {

    // 默认容量
    private final static Integer DEFAULT_CAPACITY = 3;

    private final Map<Integer, Node> cache;

    private final DoubleList doubleList;


    private final int capacity;


    private Node deletedNode;

    public LruCache() {
        this(DEFAULT_CAPACITY);
    }

    public LruCache(int initCapacity) {
        if (initCapacity < DEFAULT_CAPACITY) {
            initCapacity = DEFAULT_CAPACITY;
        }
        cache = new HashMap<>(initCapacity);
        // 初始化双端节点
        doubleList = new DoubleList();
        capacity = initCapacity;
    }

    /**
     * 标记为最近使用的
     *
     * @param key
     */
    public void makeRecently(int key) {
        Node node = cache.get(key);
        // 从链表中删除
        doubleList.remove(node);
        // 然后添加到链表尾部
        doubleList.addLast(node);
    }

    public void addRecently(int key, int value) {
        Node x = new Node(key, value);
        // 链表中添加
        doubleList.addLast(x);
        // 在 map 中添加映射
        cache.put(key, x);
    }

    public void deleteKey(int key) {
        Node node = cache.get(key);
        // 从链表中删除
        doubleList.remove(node);
        // 从 cache中删除
        cache.remove(key);
    }

    public void removeLeastRecently() {
        // 从链表中删除第一个元素就是最近最久未使用
        Node first = doubleList.removeFirst();
        // 从 map 中删除
        int deletedKey = first.key;
        cache.remove(deletedKey);
    }

    /**
     * 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {

        if (cache.containsKey(key)) {
            // 从缓存中删除
            deleteKey(key);
            // 再重新添加到缓存中
            addRecently(key, value);
            return;
        }
        // 删除链表头最久没使用的节点
        if (cache.size() == capacity) {
            removeLeastRecently();
        }

        // 添加为最近使用的元素
        addRecently(key, value);

    }

    /**
     * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        makeRecently(key);

        return node.value;
    }

    @Override
    public String toString() {
        return cache + "\n" + doubleList;
    }

    public static void main(String[] args) {
        LruCache lruCache = new LruCache(3);
        lruCache.put(1, 2);
        lruCache.put(2, 3);
        lruCache.put(3, 4);
        lruCache.put(4, 2);
        lruCache.get(2);
        lruCache.get(3);
        lruCache.put(5, 3);
        lruCache.get(2);
        System.out.println(lruCache);
    }

}

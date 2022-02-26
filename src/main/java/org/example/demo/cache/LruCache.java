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
    private final static Integer DEFAULT_CAPACITY= 3;

    private final Map<Integer, Node> cache;

    private final Node head;

    private final Node tail;


    private final int maxSize;

    private static class Node {
        Integer key;
        Integer value;
        Node prev, next;

        public Node(Integer key, Integer value) {
            this.value = value;
            this.key = key;
        }
    }

    private Node deletedNode;

    public LruCache() {
        this(DEFAULT_CAPACITY);
    }

    public LruCache(int initCapacity) {
        if(initCapacity < DEFAULT_CAPACITY) {
            initCapacity = DEFAULT_CAPACITY;
        }
        cache = new HashMap<>(initCapacity);
        // 初始化头尾结点
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        maxSize = initCapacity;
    }

    /**
     *如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {

        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToTail(node);
        } else {
            // 删除链表头最久没使用的节点
            if(cache.size() == maxSize) {
                removeFromHead();
            }
            Node node;
            // 复用空间
            if(deletedNode != null) {
                node = deletedNode;
                node.key = key;
                node.value = value;
                deletedNode = null;
            } else {
                node = new Node(key, value);
            }

            cache.put(key, node);

            addToTail(node);
        }
    }

    /**
     * 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
     * @param key
     * @return
     */
    public int get(int key) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            // 更新顺序
            moveToTail(node);
            return node.value;
        }
        return -1;
    }

    /**
     * 添加节点到链表尾部
     * @param node
     */
    public void addToTail(Node node) {
        // 获取最后一个节点
        Node tailPrev = tail.prev;
        // 将新节点连接到最后一个节点的后面
        tailPrev.next = node;
        node.prev = tailPrev;

        // 将新节点标识为最后一个节点
        node.next = tail;
        tail.prev = node;
    }

    /**
     * 从链表中删除节点
     * @param node
     */
    public void deleteNode(Node node) {
        // 获取当前节点的前一个节点和后一个节点
        Node prev = node.prev;
        Node next = node.next;

        // 将当前节点的前一个节点与后一个节点连接
        prev.next = next;
        next.prev = prev;
        // 清除当前节点的前后指向
        node.prev = null;
        node.next = null;
    }

    /**
     * 将节点移动到链表尾部
     * @param node
     */
    public void moveToTail(Node node) {
        // 先将节点从链表中删除
        deleteNode(node);
        // 然后将节点放置到链表的最后
        addToTail(node);
    }


    public void removeFromHead() {
        Node node = head.next;
        deleteNode(node);
        // 复用空间
        deletedNode = node;
        cache.remove(node.key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node node = head.next;
        while(node != tail) {
            sb.append(String.format(" -> [ %s : %s ]", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
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

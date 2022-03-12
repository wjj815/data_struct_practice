package org.example.demo.leetcode.queue.monotonous;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调队列
 */
public class MonotonousQueue {

    // 双端链表 支持头部尾部增删元素
    Deque<Integer> q = new LinkedList<>();
    // 在队尾添加元素 n
    public void push(int n) {
        //// 将小于 n 的元素全部删除
        while(!q.isEmpty() && q.peekLast() < n) {
            q.pollLast();
        }
        // 然后将 n 加入尾部
        q.offerLast(n);
    }
    // 返回当前队列中的最大值
    public int max() {
        return q.isEmpty() ? -1 : q.peekFirst();
    }
    // 队头元素如果是 n，删除它
    public void pop(int n) {
        if(!q.isEmpty() && q.peekFirst() == n) {
            q.pollFirst();
        }
    }
}

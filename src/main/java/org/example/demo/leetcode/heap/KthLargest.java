package org.example.demo.leetcode.heap;

import java.util.PriorityQueue;

public class KthLargest {

    PriorityQueue<Integer> small = new PriorityQueue<>();
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * 向大小为 k 的队列中加数据
     * @param val
     * @return
     */
    public int add(int val) {
        small.offer(val);

        if (small.size() > k) {
            small.poll();
        }

        return small.peek();
    }
}

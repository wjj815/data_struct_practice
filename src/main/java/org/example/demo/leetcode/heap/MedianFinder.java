package org.example.demo.leetcode.heap;


import java.util.PriorityQueue;

/**
 *295. 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 思路：
 * 将数据流想象成三角形，求中位数就是求三角形中间的数，
 * 使用两个堆：第一个堆为大根堆，可以想象为三角形上半部分的倒三角
 * 第二个堆为小根堆，可以想象为三角形下半部分的梯形
 * 其中注意的点是
 * 1. 如何让两个堆的数量相差不大于1
 * 2. 第二个堆插入的数必须大于第一个堆的堆顶数
 *  若待插入第一个堆，则先插入到第二个堆，然后再从第二个堆中取出堆顶元素插入到第一个堆
 *  若待插入第二个堆则同上逻辑
 */
class MedianFinder {

    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;

    public MedianFinder() {
        // 小根堆
        large = new PriorityQueue<>();
        //  大根堆
        small = new PriorityQueue<>((a,b) -> b - a);
    }

    // 添加一个数字
    public void addNum(int num) {
        if(small.size() < large.size()) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
    }

    // 计算当前添加的所有数字的中位数
    public double findMedian() {

        if(small.size() > large.size()) {
            return (double) small.peek();
        } else if(small.size() < large.size()) {
            return (double) large.peek();
        } else {
            return (double) (small.peek() + large.peek()) / 2;
        }
    }
}
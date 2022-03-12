package org.example.demo.leetcode.stack;

import java.util.Stack;

public class MyQueue {

    // 添加数据的栈
    Stack<Integer> product = new Stack<>();
    // 消费数据的栈
    Stack<Integer> consumer = new Stack<>();

    public void offer(int x) {
        product.push(x);
    }

    public int poll() {
        transfer();

        return consumer.isEmpty() ? -1 :  consumer.pop();
    }

    /**
     * 将 product 数据转移到 consumer 里
     */
    public void transfer() {
        // 如果 consumer 里没有数据了
        if (consumer.isEmpty()) {
            while (!product.isEmpty()) {
                consumer.push(product.pop());
            }
        }
    }

    public int peek() {
        transfer();
        return consumer.isEmpty() ? -1 :  consumer.pop();
    }

    public boolean isEmpty() {
        return product.isEmpty() && consumer.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
    }

}

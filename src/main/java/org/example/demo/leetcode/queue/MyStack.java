package org.example.demo.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 */
public class MyStack {

    Queue<Integer> q = new LinkedList<>();
    int top_elem = 0;
    /** 添加元素到栈顶 */
    public void push(int x){
        // x 是队列的队尾， 栈的栈顶
        q.offer(x);
        top_elem = x;
    }

    /** 删除栈顶的元素并返回 */
    public int pop(){
        int size =q.size();
        while(size > 1) {
            q.offer(q.poll());
            size--;
        }
        // 之前的队尾已经到了队头
        return q.poll();
    }

    /** 返回栈顶元素 */
    public int top(){
        return top_elem;
    }

    /** 判断栈是否为空 */
    public boolean empty(){
        return q.isEmpty();
    }


    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.empty());
    }
}

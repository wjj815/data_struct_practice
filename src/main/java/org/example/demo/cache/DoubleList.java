package org.example.demo.cache;

/**
 * 双端链表
 */
public class DoubleList {


    private final Node head;

    private final Node tail;

    private int size;

    public DoubleList() {
        // 初始化头尾节点
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        // 连接头尾节点
        head.next = tail;
        tail.prev = head;

        size = 0;
    }


    /**
     * 在链表尾部添加节点
     * @param x
     */
    public void addLast(Node x) {
        // 更改 x 的指针
        x.prev = tail.prev;
        x.next = tail;

        // 更该 tail 的指针
        tail.prev.next = x;
        tail.prev = x;

        size++;
    }

    // 删除链表中的某个节点(节点一定存在) O(1)
    public void remove(Node x) {
        // 将 x 的 prev节点 与 x 的 next 节点连接
        x.prev.next = x.next;
        x.next.prev = x.prev;

        // 清空 x 的指针
        x.prev = null;
        x.next = null;
        size--;
    }

    // 删除第一个链表节点 O(1)
    public Node removeFirst() {

        if(head.next == tail) {
            return null;
        }

        Node first = head.next;
        remove(first);
        return first;
    }

    // 返回链表长度，时间 O（1）
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = head.next;
        while (node != tail) {
            sb.append(String.format(" -> [ %s : %s ]", node.key, node.value));
            node = node.next;
        }

        return sb.toString();
    }
}

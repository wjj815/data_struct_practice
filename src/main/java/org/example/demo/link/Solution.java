package org.example.demo.link;

public class Solution {


    /**
     * 翻转链表
     * @param head 头结点
     * @return
     */
    public ListNode reverseListRecur(ListNode head) {

        // 获取链表的最后一个非空节点
        if(head == null  || head.next == null) {
            return head;
        }
        // 将最后一个非空节点作为头结点
        ListNode newHead = reverseList(head.next);
        // 将当前链表节点的 next 节点的 next 指针指向当前节点实现翻转
        head.next.next = head;
        // 将当前链表节点的 next 指针置空
        head.next  = null;
        return newHead;
    }


    /**
     *  翻转链表
     * @param head 头结点
     * @return
     */
    public ListNode reverseList(ListNode head) {

        ListNode prev = null, cur = head, next;

        while(cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

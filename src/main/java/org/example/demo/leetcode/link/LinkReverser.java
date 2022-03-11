package org.example.demo.leetcode.link;

import org.example.demo.link.ListNode;

/**
 * 链表翻转相关
 */
public class LinkReverser {



    public ListNode reverse(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }

        // 传递直到最后一个链表节点
        ListNode newHead = reverse(head.next);
        // 将 head 的下一个节点的 next 指针指向当前的 head
        head.next.next = head;
        // 将当前的 head 指针置为空
        head.next = null;
        // 将新链表头（最后一个链表节点）返回
        return newHead;
    }

    public ListNode reverseUnRecur(ListNode head) {

        // 使用三个指针来翻转链表
        ListNode pre = null, cur = head, next = null;

        while(cur != null) {
            // next 记录 cur 的下一个节点
            next = cur.next;
            // 将 cur.next 指向前一个节点
            cur.next = pre;
            // 移动 pre 为 cur
            pre = cur;
            // 移动 cur 为 next
            cur = next;
        }

        return pre;
    }


    ListNode successor = null;
    /**
     * 翻转链表前 n 个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode reverseN(ListNode head, int n) {
        // 到第 n 个链表节点了记录后继
        if(n == 1) {
            successor = head.next;
            return head;
        }
        // 获得最后一个链表节点
        ListNode last = reverseN(head.next, n - 1);
        // 翻转
        head.next.next = head;
        // 将翻转后的链表节点的 next 指针指向后继者
        head.next = successor;
        return last;
    }


    /**
     * 反转链表中[left, right]区间的链表节点
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // base case
        if(left == 1) {
            return reverseN(head, right);
        }
        // // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, left - 1, right - 1);
        // 然后归到头节点
        return head;
    }
}

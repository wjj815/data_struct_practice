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


    /**
     * 反转区间[a,b)的元素， 注意这是左闭右开
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null, cur = a, next = null;
        // while 终止条件改一下就行了
        while(cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 返回反转后的头结点
        return pre;
    }

    /**
     *
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 进阶：
     *
     * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) return null;
        // 区间[a, b) 包含 k 个待反转的元素
        ListNode a, b;
        a = b = head;
        for(int i = 0; i < k; i++) {
            // 不足 k 个， 不需要反转， base case
            if(b == null) return head;
            b = b.next;
        }

        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }



}

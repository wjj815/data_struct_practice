package org.example.demo.link;


import org.junit.Test;

public class SolutionTest {

    @Test
    public void reverseList() {
        Solution solution = new Solution();
        ListNode node = new ListNode(-1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        node.next  = n1;
        n1.next = n2;
        n2.next = n3;
        Helper.printList(node);

        // 测试递归的
        System.out.println("递归版翻转链表：");
        ListNode listNode = solution.reverseListRecur(node);
        Helper.printList(listNode);
        // 测试非递归的
        System.out.println("非递归版翻转链表：");
        listNode = solution.reverseList(listNode);
        Helper.printList(listNode);
    }
}
package org.example.demo.leetcode.link;

import junit.framework.TestCase;
import org.example.demo.link.Helper;
import org.example.demo.link.ListNode;

public class LinkReverserTest extends TestCase {

    LinkReverser linkReverser;

    ListNode head;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        linkReverser = new LinkReverser();
        head = new ListNode(22);
        ListNode node = new ListNode(-1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        head.next = node;
        node.next  = n1;
        n1.next = n2;
        n2.next = n3;

    }

    public void testReverse() {
        ListNode reverse = linkReverser.reverse(head);
        Helper.printList(reverse);
    }

    public void testReverseUnRecur() {
        ListNode reverse = linkReverser.reverseUnRecur(head);
        Helper.printList(reverse);
    }

    public void testReverseN() {
        ListNode reverse = linkReverser.reverseN(head, 2);
        Helper.printList(reverse);
    }

    public void testReverseBetween() {
        ListNode reverse = linkReverser.reverseBetween(head,2, 5);
        Helper.printList(reverse);
    }
}
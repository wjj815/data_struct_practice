package org.example.demo.link;

public class Helper {


    public static void printList(ListNode head) {


        while(head != null) {
            System.out.print(head.val +" -> ");
            head = head.next;
        }
        System.out.println();
    }
}

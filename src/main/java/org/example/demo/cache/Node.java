package org.example.demo.cache;


public class Node {
    Integer key;
    Integer value;
    Node prev, next;

    public Node(Integer key, Integer value) {
        this.value = value;
        this.key = key;
    }
}


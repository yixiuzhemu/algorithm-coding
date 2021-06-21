package com.ly.algorithm;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * singly linked list
 * @author Ly
 * @create 2021/5/14 16:04
 * @desc
 **/
@Data
@NoArgsConstructor
public class Node {
    private int value;
    private Node next;


    public Node(int value) {
        this.value = value;
    }

    public Node addNode(Node node){
        this.next = node;
        return node;
    }

    public static Node reverseOrderNode(Node node){
        Node start = node;
        Node next = null;
        Node pre = null;
        while(start != null){
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        return pre;
    }
}

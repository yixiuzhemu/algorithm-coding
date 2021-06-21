package com.ly.algorithm.coding;

import com.ly.algorithm.Node;
import com.ly.algorithm.Stack;

/**
 * @author Ly
 * @create 2021/5/14 15:53
 * @desc
 **/
public class PalindRomeCoding {

    /**
     * 判断链表是否是回文，直接将整个链表放入栈中
     * @param head
     * @return
     */
    public static Boolean isPalindRome(Node head){
        Stack stack = new Stack(100);
        Node node = head;
        while(node != null){
            stack.push(node.getValue());
            node = node.getNext();
        }
        node = head;
        while(!stack.isEmpty()){
            if(node.getValue() != stack.pop()){
                return false;
            }
            node = node.getNext();
        }
        return true;
    }

    /**
     * 判断链表是否是回文，找出中点位置，将一半数据放入栈中
     * @param head
     * @return
     */
    public static Boolean isPalindRome2(Node head){
        Stack stack = new Stack(100);
        Node midNode = NodeCoding.midOrUpMidNode(head);
        Node node = midNode;
        while(node != null){
            stack.push(node.getValue());
            node = node.getNext();
        }
        node = head;
        while(!stack.isEmpty()){
            if(node.getValue() != stack.pop()){
                return false;
            }
            node = node.getNext();
        }
        return true;
    }

    /**
     * 判断链表是否是回文，找出中点位置，将一半链表逆序，判断完成之后，再将顺序变更回去
     * @param head
     * @return
     */
    public static Boolean isPalindrome3(Node head){
        Node midNode = NodeCoding.midOrUpMidNode(head);

        Node reverseOrderNode = Node.reverseOrderNode(midNode);
        Node r = reverseOrderNode;
        Node h = head;
        while(r != null && h != null){
            if(r.getValue() != h.getValue()){
                midNode = Node.reverseOrderNode(reverseOrderNode);
                return false;
            }
            r = r.getNext();
            h = h.getNext();
        }
        midNode = Node.reverseOrderNode(reverseOrderNode);
        return true;
    }

    public static void main(String[] args) {
        int[] arr=  new int[]{1,2,3,3,2,1};
        Node head = new Node(arr[0]);
        Node node = head;
        for (int i= 1;i<arr.length;i++) {
            node = node.addNode(new Node(arr[i]));
        }
        Boolean isBack = isPalindRome(head);
        if(isBack){
            System.out.println("是回文");
        }else{
            System.out.println("不是回文");
        }
        isBack = isPalindRome2(head);
        if(isBack){
            System.out.println("是回文");
        }else{
            System.out.println("不是回文");
        }
        isBack = PalindRomeCoding.isPalindrome3(head);
        if(isBack){
            System.out.println("是回文");
        }else{
            System.out.println("不是回文");
        }

    }

}

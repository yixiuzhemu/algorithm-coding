package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 * 一种消息接收并打印的结构设计
 *
 * ​	已知一个消息流会不断的吐出整数1~N,但不一定按照顺序吐出，如果上次打印的数为i，那么当i+1出现时，请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，请设计这种接收并打印的结构，初始时默认i=0
 * @author Ly
 * @create 2023/11/2 20:40
 * @desc
 **/
public class ReceiveAndPrintOrderLine {

    public static class Node{
        public String info;

        public Node next;

        public Node(String str){
            info =str;
        }
    }

    public static class MessageBox{
        private Map<Integer,Node> headMap;

        private Map<Integer,Node> tailMap;

        private int waitPoint;

        public MessageBox(){
            headMap = Maps.newHashMap();
            tailMap = Maps.newHashMap();
            waitPoint = 1;
        }

    }

    /**
     * 结构实现：准备一个头表和尾表，当i位置的消息进来时，将当前消息分别以i位置加入头表和尾表，
     *
     *  判断尾表是否存在i-1的结尾，如果存在，那么将尾表中的i-1节点移除，头表中的i节点移除，并将next指向i位置的头节点。
     *
     * 	判断头表是否存在i+1的开头，如果存在，那么将当前节点的next指向头表中的i+1的节点，并将头表的i+1节点移除，将尾表的i位置节点移除。
     *
     * 	当i位置等于当前等待的位置时，输出头表中i位置当前节点的整个单链表，输出后，将头节点从和尾节点分别从头表和尾表移除
     * @param i
     * @param message
     * @param messageBox
     */
    public static void printMessage(int i,String message,MessageBox messageBox){
        Node msg = new Node(message);
        if(messageBox.tailMap.containsKey(i-1)){
            //此时将尾表中的i-1节点移除，next指向当前节点
            Node tail = messageBox.tailMap.get(i - 1);
            messageBox.tailMap.remove(i-1);
            tail.next = msg;
        }else{
            messageBox.headMap.put(i,msg);
        }
        if(messageBox.headMap.containsKey(i+1)){
            //此时将头表的i+1节点移除，当前节点的next指向i+1的头节点
            Node head = messageBox.headMap.get(i+1);
            messageBox.headMap.remove(i+1);
            msg.next = head;
        }else{
            messageBox.tailMap.put(i,msg);
        }
        if(i == messageBox.waitPoint){
            Node node = messageBox.headMap.get(i);
            messageBox.headMap.remove(i);
            while(node != null){
                System.out.println(node.info);
                node = node.next;
                i++;
            }
            messageBox.tailMap.remove(i-1);
            messageBox.waitPoint = i;
        }
    }

    public static void main(String[] args) {
        MessageBox messageBox = new MessageBox();
        int[] arr = new int[]{15,7,5,8,9,10,2,3,1,4,6,13,12,11,14};
        for (int i : arr) {
            printMessage(i,i+"来了",messageBox);
        }
    }
}

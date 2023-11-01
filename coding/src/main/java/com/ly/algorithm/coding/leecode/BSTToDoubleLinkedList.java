package com.ly.algorithm.coding.leecode;

import lombok.Builder;
import lombok.Data;

/**
 *
 * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，next认为是right的化，
 *
 * 给定一个搜索二叉树的头节点head，请转换成一条有序的双向链表，并返回链表的头节点
 * @author Ly
 * @create 2023/10/9 21:11
 * @desc
 **/
public class BSTToDoubleLinkedList {

    @Data
    @Builder
    public static class Node{
        private Node left;

        private Node right;

        private int value;
    }

    @Data
    @Builder
    public static class Info{

        private Node start;

        private Node end;
    }

    /**
     *
     *
     * 二叉树递归套路：对于任意节点X，返回左子树的头节点和尾节点，节点X的left连接左子树的尾节点。返回右子树的头节点和尾节点，节点X的right连接右子树的头节点。
     * @param X
     * @return
     */
    public static Info process(Node X){
        if(X == null){
            return Info.builder().build();
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        if(leftInfo.end != null){
            leftInfo.end.right = X;
        }
        X.left = leftInfo.end;
        X.right = rightInfo.start;
        if(rightInfo.start != null){
            rightInfo.start.left = X;
        }
        return Info.builder().start(leftInfo.start != null ? leftInfo.start : X).end(rightInfo.end != null ? rightInfo.end : X).build();
    }

    public static void main(String[] args) {

    }
}

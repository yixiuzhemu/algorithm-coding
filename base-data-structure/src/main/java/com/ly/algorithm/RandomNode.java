package com.ly.algorithm;

import lombok.Data;

/**
 * 一种特殊的单链表节点
 * @author Ly
 * @create 2021/6/3 15:06
 * @desc
 **/
@Data
public class RandomNode {
    public int value;
    public RandomNode next;
    /**
     * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null
     */
    public RandomNode rand;
    public RandomNode(int value) {
        this.value = value;
    }

    public RandomNode addNode(RandomNode node){
        this.next = node;
        return node;
    }

    public RandomNode addRand(RandomNode node){
        this.rand = node;
        return node;
    }

    public RandomNode copyNode(){
        return new RandomNode(this.value);
    }
}

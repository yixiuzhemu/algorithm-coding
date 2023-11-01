package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * LRU内存替换算法实现
 *
 * 存在一个数据结构，包含K,V,TIME ，其中TIME表示当前K的操作时间，无论是get还是put。只要有操作就更新K，而当进行put时，如果发现空间已经满了，那么直接删除TIME最早的一条数据，即最早操作的那条数据。
 *
 * 	要求put、get的时间复杂度是O(1)
 *
 * 	结构：HashMap+双链表，其中hash表的key就是K，value是一个Node结构（K,V,pre，next）
 *
 * 	在每次进行put时，插入到hash表，并且同时插入到双链表的尾部，put的时间复杂度O(1)
 *
 * 	在每次进行get时，给定key，直接从hash表中进行返回，同时调整当前节点在双链表中的位置，将当前节点调整到双链表的末尾。
 *
 * 	所以对于双链表的含义为，越靠近头部，表示越早使用，当空间不足时，直接从头部开始删除。
 * @author Ly
 * @create 2023/10/14 10:08
 * @desc
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeastRecentlyUsedCache<K,V> {

    private Map<K,Node<K,V>> map;

    private int limit;

    private Node<K,V> head;

    private Node<K,V> tail;

    public LeastRecentlyUsedCache(int limit) {
        this.limit = limit;
        map = Maps.newHashMap();
    }

    public void put(K k, V v){
        if(head == null){
            Node node  = new Node<>(k,v);
            head = node;
            tail = node;
            map.put(k,node);
            return;
        }
        Node<K,V> vNode = map.get(k);
        if(vNode != null){
            vNode.setVal(v);
            changeToTail(vNode);
        }else{
            if(map.size() == limit){
               removeHead();
            }
            vNode = new Node<>(k,v);
            vNode.pre = tail;
            tail.next = vNode;
            tail = vNode;
            map.put(k,vNode);
        }
    }

    public V get(K k){
        Node<K, V> kvNode = map.get(k);
        if(kvNode == null){
            return null;
        }
        changeToTail(kvNode);
        return kvNode.val;
    }

    public void removeHead(){
        if(this.head == null){
            return;
        }
        Node<K,V> res = this.head;
        if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        }else{
            head = head.next;
            res.next = null;
            head.pre = null;
        }
        map.remove(res.getK());
    }

    public void changeToTail(Node<K, V> kvNode){
        if(kvNode == tail){
            return;
        }
        if(this.head == kvNode){
            this.head = kvNode.next;
            this.head.pre = null;
        }else{
            kvNode.pre.next = kvNode.next;
            kvNode.next.pre = kvNode.pre;
        }
        tail.next = kvNode;
        kvNode.pre = tail;
        kvNode.next = null;
        tail = kvNode;
    }

    public static class Node<K,V>{

        private Node<K,V> pre;

        private Node<K,V> next;

        private K k;

        private V val;

        public Node<K, V> getPre() {
            return pre;
        }

        public void setPre(Node<K, V> pre) {
            this.pre = pre;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }

        public Node(K k, V val) {
            this.k = k;
            this.val = val;
        }
    }

}

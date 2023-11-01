package com.ly.algorithm.coding.binarysearchtree;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author Ly
 * @create 2023/8/31 22:12
 * @desc
 **/
public class SkipList {

    public static class SkipListNode<K extends Comparable<K>,V>{
        public K key;

        public V val;

        public List<SkipListNode<K,V>> nextNodes;

        public SkipListNode(K k,V v){
            key = k;
            val = v;
            nextNodes = Lists.newArrayList();
        }

        /**
         * 遍历时，如果往右 遍历到null，
         * 头节点null认为时最小值
         * 比较key的大小
         * @param otherKey
         * @return
         */
        public boolean isKeyLess(K otherKey){
            //
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey){
            return (key == null && otherKey == null) || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    public static class SkipListMap<K extends Comparable<K>,V>{
        /**
         * 小于0.5 继续造层
         * 大于0.5 停止
         */
        private static final double PROBABILITY = 0.5F;

        private SkipListNode<K,V> head;

        private int size;

        private int maxLevel;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null,null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        /**
         * 找到第0层小于key 的最右节点
         * @param key
         * @return
         */
        private SkipListNode<K,V> mostRightLessNodeInTree(K key){
            if(key == null){
                return null;
            }
            int level = maxLevel;
            SkipListNode<K,V> cur = head;
            while(level >= 0){
                cur = mostRightLessNodeInLevel(key,cur,level--);
            }
            return cur;
        }

        public SkipListNode<K,V> mostRightLessNodeInLevel(K k,SkipListNode<K,V> cur,int level){
            //当前节点当前层的下一个节点
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            //如果下一个节点不为空，并且key小于当前key
            while(next != null && next.isKeyLess(k)){
                //移动到下一个节点
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }

        /**
         * 是否包含key，先找到小于当前key的最右节点，再获取第0层的下一个节点
         * @param key
         * @return
         */
        public boolean containsKey(K key){
            if(key == null){
                return false;
            }
            //找到小于当前key的最右位置
            SkipListNode<K,V> less = mostRightLessNodeInTree(key);
            //获取第-层的下一个节点
            SkipListNode<K,V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }

        public void put(K key,V value){
            if(key == null){
                return;
            }
            SkipListNode<K,V> less = mostRightLessNodeInTree(key);
            SkipListNode<K,V> find = less.nextNodes.get(0);
            if(find != null && find.isKeyEqual(key)){
                find.val = value;
                return;
            }
            size++;
            int newNodeLevel = 0;
            while(Math.random() < PROBABILITY){
                newNodeLevel++;
            }
            while(newNodeLevel > maxLevel){
                //头节点新增加一层
                head.nextNodes.add(null);
                maxLevel++;
            }
            SkipListNode<K,V> newNode = new SkipListNode<>(key,value);
            for(int i = 0;i < newNodeLevel;i++){
                newNode.nextNodes.add(null);
            }
            int level = maxLevel;
            SkipListNode<K,V> pre = head;
            while(level >= 0){
                //找到当前层最右小于key的节点
                pre = mostRightLessNodeInLevel(key,pre,level);
                //将当前newNode插入当前层
                if(level <= newNodeLevel){
                    newNode.nextNodes.set(level,pre.nextNodes.get(level));
                    pre.nextNodes.set(level,newNode);
                }
                level--;
            }
        }

        /**
         * 删除时，需要删除每一层的节点
         * @param key
         */
        public void remove(K key){
            if(containsKey(key)){
                size--;
                int level = maxLevel;
                SkipListNode<K,V> pre =head;
                while(level >= 0){
                    pre = mostRightLessNodeInLevel(key,pre,level);
                    SkipListNode<K,V> next = pre.nextNodes.get(level);
                    if(next != null && next.isKeyEqual(key)){
                        //删除每一层的当前节点
                        pre.nextNodes.set(level,next.nextNodes.get(level));
                    }
                    if(level != 0 && pre == head && pre.nextNodes.get(level) == null){
                        //如果当前只剩下头节点，那么删除当前层
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        public K firstKey(){
            return head.nextNodes.get(0) != null ? head.nextNodes.get(0).key : null;
        }

        public K lastKey(){
            int level = maxLevel;
            SkipListNode<K,V> cur = head;
            while(level >= 0){
                SkipListNode<K,V> next = cur.nextNodes.get(level);
                while(next != null){
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K ceillingKey(K key){
            if(key == null){
                return null;
            }
            SkipListNode<K,V> less = mostRightLessNodeInTree(key);
            SkipListNode<K,V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }

        public K floorKey(K key){
            if(key == null){
                return null;
            }
            SkipListNode<K,V> less = mostRightLessNodeInTree(key);
            SkipListNode<K,V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.key : less.key;
        }

        public V get(K key){
            if(key == null){
                return null;
            }
            SkipListNode<K,V> less = mostRightLessNodeInTree(key);
            SkipListNode<K,V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEqual(key) ? next.val : null;
        }
    }
}

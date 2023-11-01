package com.ly.algorithm.coding.binarysearchtree;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author Ly
 * @create 2023/9/5 21:54
 * @desc
 **/
public class SlidingWindowMedian {

    public static class SBTNode<K extends Comparable<K>>{
        public K key;


        public SBTNode<K> l;

        public SBTNode<K> r;

        public int size;

        public SBTNode(K key){
            this.key = key;

            size = 1;
        }
    }



    public static class SizeBalancedTreeMap<K extends Comparable<K>>{
        private SBTNode<K> root;

        /**
         * 当前节点进行右旋，返回新头部
         * @param cur
         * @return
         */
        private SBTNode<K> rightRotate(SBTNode<K> cur){
            SBTNode<K> leftNode = cur.l;
            //当前节点的左孩子的右孩子交给当前节点的左孩子
            cur.l = leftNode.r;
            //当前节点挂在当前节点的左孩子的右孩子下
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return leftNode;
        }

        /**
         * 左旋
         * @param cur
         * @return
         */
        public SBTNode leftRotate(SBTNode cur){
            SBTNode<K> rightNode = cur.r;
            //当前节点的右孩子的左孩子挂在当前节点的右孩子下
            cur.r = rightNode.l;
            //当前节点挂在当前节点的右孩子的左孩子下
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return rightNode;
        }

        private SBTNode matain(SBTNode cur){
            if(cur == null){
                return null;
            }
            //LL 型违规
            if(cur.l != null && cur.l.l != null && cur.r != null && cur.l.l.size > cur.r.size){
                //对当前节点进行一次右旋
                cur = rightRotate(cur);
                //旋转前的cur、cur.l 节点受影响
                //旋转后的cur、cur.r 节点受影响
                cur.r = matain(cur.r);
                cur = matain(cur);
            }else if(cur.l != null && cur.l.r != null && cur.r != null && cur.l.r.size > cur.r.size){
                //LR型违规
                //先对左孩子进行一次左旋
                cur.l = leftRotate(cur.l);
                //再对当前节点进行一次右旋
                cur = rightRotate(cur);
                //受影响的节点为，未旋转前的 cur、cur.l、cur.l.r
                //旋转后的： cur、cur.l、cur.r
                cur.l = matain(cur.l);
                cur.r = matain(cur.r);
                cur = matain(cur);
            }else if(cur.r != null && cur.r.r != null && cur.l != null && cur.r.r.size > cur.l.size){
                //RR型违规
                //对当前节点进行一次左旋
                cur = leftRotate(cur);
                //旋转前的cur、cur.r节点受影响
                //旋转后的cur、cur.l节点受影响
                cur.l = matain(cur.l);
                cur = matain(cur);
            }else if(cur.r != null && cur.r.l != null && cur.l != null && cur.r.l.size > cur.l.size){
                //RL型违规
                //先对cur.r进行一次右旋
                cur.r = rightRotate(cur.r);
                //再对当前节点进行一次左旋
                cur = leftRotate(cur);
                //受影响的节点为，旋转前的cur、cur.r 、 cur.r.l
                //旋转后：cur、cur.l、cur.r
                cur.l = matain(cur.l);
                cur.r = matain(cur.r);
                cur = matain(cur);
            }
            return cur;
        }

        private SBTNode<K> getIndex(SBTNode<K> cur,int kth){
            if(kth == (cur.l != null ? cur.l.size : 0) + 1){
                return cur;
            }else if(kth <= (cur.l != null ? cur.l.size : 0)){
                return getIndex(cur.l,kth);
            }else{
                return getIndex(cur.r,kth - (cur.l != null ? cur.l.size : 0) - 1);
            }
        }

        public int size(){
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key){
            if(key == null ){
                return false;
            }
            SBTNode<K> lastNode = findLastIndex(key);

            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }

        /**
         * 将节点添加到搜索二叉树中
         * @param cur
         * @param key
         * @return
         */
        private SBTNode add(SBTNode<K> cur, K key){
            if(cur == null){
                return new SBTNode<K>(key);
            }else{
                cur.size++;
                if(key.compareTo(cur.key) < 0){
                    cur.l = add(cur.l,key);
                }else{
                    cur.r = add(cur.r,key);
                }
                return matain(cur);
            }
        }


        public void add(K key){
            if(key == null){
                return;
            }
            SBTNode<K> lastNode = findLastIndex(key);
            if(lastNode == null || key.compareTo(lastNode.key) != 0){
                root = add(root,key);
            }
        }

        private SBTNode<K> findLastIndex(K key) {

            SBTNode<K> pre = root;
            SBTNode<K> cur = root;
            while(cur != null){
                pre = cur;
                if(key.compareTo(cur.key) == 0){
                    break;
                }else if(key.compareTo(cur.key) < 0){
                    cur =cur.l;
                }else{
                    cur = cur.r;
                }
            }
            return pre;
        }

        public void remove(K key){
            if(key == null){
                return;
            }
            if(containsKey(key)){
                root = delete(root,key);
            }
        }

        private SBTNode<K> delete(SBTNode<K> cur, K key) {
            cur.size--;
            if(key.compareTo(cur.key) > 0){
                cur.r = delete(cur.r,key);
            }else if(key.compareTo(cur.key) < 0){
                cur.l = delete(cur.l,key);
            }else{
                if(cur.l == null && cur.r == null){
                    cur = null;
                }else if(cur.l == null && cur.r != null){
                    cur = cur.r;
                }else if(cur.l != null && cur.r == null){
                    cur = cur.l;
                }else{
                    //有左有右，找当前节点的后继节点替代自己
                    SBTNode<K> pre = null;
                    SBTNode<K> des = cur.r;
                    des.size--;
                    while(des.l != null){
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if(pre != null){
                        //将后继节点的右孩子，交给上一个节点的左孩子
                        pre.l = des.r;
                        //后继节点接替当前节点的右孩子
                        des.r = cur.r;
                    }
                    //接替左孩子
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size)+1;
                    cur= des;
                }
            }
            return cur;
        }

        public K getIndexKey(int index){
            if(index < 0 || index >= this.size()){
                return null;
            }
            return getIndex(root,index+1).key;
        }


    }

    public static class Node implements Comparable<Node>{
        public int index;

        public int value;

        public Node(int i ,int v){
            index = i;
            value = v;
        }

        @Override
        public int compareTo(Node o) {
            return value != o.value ? Integer.valueOf(value).compareTo(o.value) : Integer.valueOf(index).compareTo(o.index);
        }
    }

    public static double[] medianSlidingWindow(int[] nums,int k){
        SizeBalancedTreeMap<Node> map = new SizeBalancedTreeMap<>();
        for(int i = 0;i<k-1;i++){
            map.add((new Node(i,nums[i])));
        }
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        for(int i = k - 1;i<nums.length;i++){
            map.add(new Node(i,nums[i]));
            if(map.size() % 2 == 0){
                Node upmid = map.getIndexKey(map.size()/2 - 1);
                Node downmid = map.getIndexKey(map.size() /2);
                ans[index++] = ((double) upmid.value + (double) downmid.value)/2;
            }else{
                Node mid = map.getIndexKey(map.size()/2);
                ans[index++] = (double) mid.value;
            }
            map.remove(new Node(i-k+1,nums[i-k+1]));
        }
        return ans;
    }
}

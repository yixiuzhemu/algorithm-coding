package com.ly.algorithm.coding.binarysearchtree;

/**
 * @author Ly
 * @create 2023/8/30 21:39
 * @desc
 **/
public class SizeBalancedTree {


    public static class SBTNode<K extends Comparable<K>, V>{
        public K key;

        public V value;

        public SBTNode<K,V> l;

        public SBTNode<K,V> r;

        public int size;

        public SBTNode(K key,V value){
            this.key = key;

            this.value = value;

            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>,V>{
        private SBTNode<K,V> root;

        /**
         * 当前节点进行右旋，返回新头部
         * @param cur
         * @return
         */
        private SBTNode<K,V> rightRotate(SBTNode<K,V> cur){
            SBTNode<K,V> leftNode = cur.l;
            //当前节点的左孩子的右孩子交给当前节点的左孩子
            cur.l = leftNode.r;
            //当前节点挂在当前节点的左孩子的右孩子下
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return leftNode;
        }

        /**
         * 当前节点进行左旋，返回新头部
         * @param cur
         * @return
         */
        private SBTNode<K,V> leftRotate(SBTNode<K,V> cur){
            SBTNode<K,V> rightNode = cur.r;
            //当前节点的右孩子的左孩子挂在当前节点的右孩子下
            cur.r = rightNode.l;
            //当前节点挂在当前节点的右孩子的左孩子下
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0);
            return rightNode;
        }

        /**
         * 以cur为头的树上，加入k，v记录
         * 加入完成后，会对cur进行检查，并进行调整
         * 返回调整完成后的新头部
         * @param cur
         * @param key
         * @param value
         * @return
         */
        private SBTNode<K,V> add(SBTNode<K,V> cur,K key,V value){
            if(cur == null){
                return new SBTNode<>(key,value);
            }
            cur.size++;
            if(key.compareTo(cur.key) < 0){
                cur.l = add(cur.l,key,value);
            }else{
                cur.r = add(cur.r,key,value);
            }
            return maintain(cur);
        }

        /**
         * put方法，有可能是新增，也有可能是改value
         * @param key
         * @param value
         */
        public void put(K key,V value){
            SBTNode<K, V> lastNode = findLastIndex(key);
            if(lastNode != null && key.compareTo(lastNode.key) == 0){
                lastNode.value = value;
            }else{
                root = add(root,key,value);
            }
        }

        private SBTNode<K,V> findLastIndex(K key){
            SBTNode<K,V> pre = root;
            SBTNode<K,V> cur = root;
            //从根节点开始，找到与key相同的节点
            while(cur != null){
                pre = cur;
                if(key.compareTo(cur.key) == 0){
                    break;
                }else if(key.compareTo(cur.key) < 0){
                    cur = cur.l;
                }else{
                    cur = cur.r;
                }
            }
            //如果没有找到，会返回离null节点最近的一个有效节点
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

        private SBTNode<K,V> delete(SBTNode<K,V> cur, K key) {
            cur.size--;
            if(key.compareTo(cur.key) > 0){
                cur.r = delete(cur.r,key);
            }else if(key.compareTo(cur.key) < 0){
                cur.l = delete(cur.l,key);
            }else{
                //判断类型，LL,LR,RL,RR
                if(cur.l == null && cur.r == null){
                    //删除叶子节点
                    cur = null;
                }else if(cur.l == null && cur.r != null){
                    //右孩子直接挂在cur节点上
                    cur = cur.r;
                }else if(cur.l != null && cur.r == null){
                    //左孩子直接挂在cur节点上
                    cur = cur.l;
                }else{
                   //有左有右，找当前节点的后继节点替代自己
                    SBTNode<K,V> pre = null;
                    SBTNode<K,V> des = cur.r;
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
            return maintain(cur);
        }

        private SBTNode<K,V> maintain(SBTNode<K,V> cur) {
            if(cur == null){
                return null;
            }
            //LL型，如果当前节点的左孩子的左孩子的节点数大于右孩子的节点数
            if(cur.l != null && cur.l.l != null && cur.l.l.size > cur.r.size){
                //LL型，直接对当前节点进行一次右旋
                cur = rightRotate(cur);
                //当前节点进行右旋时，当前节点和当前节点的右孩子发生变化
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(cur.l != null && cur.l.r != null &&  cur.l.r.size  > cur.r.size){
                //LR型，如果当前节点的左孩子的右孩子的节点数大于右孩子的节点数
                //先对当前节点的左孩子进行一次左旋，再对当前节点进行一次右旋
                leftRotate(cur.l);
                cur = rightRotate(cur);
                //当前节点进行左旋再进行右旋时，
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if(cur.r != null && cur.r.r != null && cur.r.r.size  > cur.l.size){
                //RR型，当前节点的右孩子的右孩子的节点数大于左孩子的节点数
                //RR型，直接对当前节点进行一次左旋
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }else if(cur.r != null && cur.r.l != null && cur.r.l.size  > cur.l.size){
                //RL型，当前节点的右孩子的左孩子的节点数大于左孩子的节点数
                //先对当前节点的右孩子进行一次右旋，再对当前节点进行一次左旋
                rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            return cur;
        }

        /**
         * 判断key是否在树中存在
         * @param key
         * @return
         */
        private boolean containsKey(K key) {
            if(root == null){
                return false;
            }
            SBTNode<K,V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }
    }



}

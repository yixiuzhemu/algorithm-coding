package com.ly.algorithm.coding.binarysearchtree;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 *
 * 给定一个数组arr，和两个整数a和b（a <= b)，求arr中有多少个子数组，累加和在[a,b]这个范围上，返回达标的子数组数量。
 *
 * @author Ly
 * @create 2023/9/4 15:06
 * @desc
 **/
public class CountRangeSum {

    public static class SBTNode{
        public long key;

        private SBTNode l;

        private SBTNode r;

        private long size;

        private long all;

        public SBTNode(long k){
            key = k;
            size = 1;
            all = 1;
        }
    }

    public static class SizeBalancedTreeSet{
        private SBTNode root;

        private Set<Long> set = Sets.newHashSet();

        /**
         * 右旋
         * @param cur
         * @return
         */
        private SBTNode rightRotate(SBTNode cur){
            //当前节点的节点数（相等的节点）
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0 ) + (cur.r != null ? cur.r.size : 0);
            leftNode.all = cur.all;
            cur.all = same + (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0);
            return leftNode;
        }

        /**
         * 左旋
         * @param cur
         * @return
         */
        public SBTNode leftRotate(SBTNode cur){
            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0 ) + (cur.r != null ? cur.r.size : 0);;
            rightNode.all = cur.all;
            cur.all = same + (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0);
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

        /**
         * 将节点添加到搜索二叉树中
         * @param cur
         * @param key
         * @param contains
         * @return
         */
        private SBTNode add(SBTNode cur,long key,boolean contains){
            if(cur == null){
                return new SBTNode(key);
            }
            //无论什么情况，all都需要增加
            cur.all++;

            if(key == cur.key){
                return cur;
            }
            //contains主要用于判断，size用不用增加
            if(!contains){
                cur.size++;
            }
            if(key < cur.key){
                cur.l =  add(cur.l,key,contains);
            }else{
                cur.r = add(cur.r,key,contains);
            }
            return matain(cur);
        }

        /**
         * 将累加和加入SB树
         * @param sum
         */
        public void add(long sum){
            boolean contains = set.contains(sum);
            root = add(root,sum,contains);
            set.add(sum);
        }


        /**
         * 小于目标值的累加和有多少
         * @param key
         * @return
         */
        public long lessKeySize(long key){
            SBTNode cur =root;
            long ans = 0;
            while(cur != null){
                //找到当前有多少是小于目标key的累加和
                if(key == cur.key){
                    return ans += (cur.l != null ? cur.l.all : 0);
                }else if(key < cur.key){
                    cur = cur.l;
                }else{
                    //往右滑，说明当前节点的左边的所有节点都小于当前key
                    cur = cur.r;
                    ans += (cur.all - (cur.r != null ? cur.r.all : 0));
                }
            }
            //如果到最后都没找到
            return ans;
        }

    }

    public static int countRangeSum(int[] nums,int lower,int upper){
        SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
        long sum = 0;
        int ans = 0;
        //一个数都没有的时候，此时的前缀和累加和为0
        treeSet.add(0);

        for(int i = 0;i<nums.length;i++){
            //计算累加和
            sum += nums[i];
            //sum 以i结尾的累加和要落在lower 到 upper
            //实际上也就是看在i之前有哪些位置的累加和是落在 sum-upper 到 sum-lower上的
            //判断当前落在sum-a 和 sum-b范围上的累加和有多少
            long lessI = treeSet.lessKeySize(sum - upper+1);
            long lessJ = treeSet.lessKeySize(sum - lower);
            //当前以i结尾的打表的子数组的数量
            ans += (lessJ - lessI);
            treeSet.add(sum);
        }

        return ans;
    }

}

package com.ly.algorithm.coding.leecode;

import lombok.Builder;
import lombok.Data;

/**
 * 求完全二叉树节点的个数，要求时间复杂度低于O（N）
 * @author Ly
 * @create 2023/10/13 22:13
 * @desc
 **/
public class CompleteTreeNodeNumber {

    @Data
    @Builder
    public static class Node{
        private Node left;

        private Node right;

        private int value;
    }


    /**
     * 	已知它是完全二叉树了，那么节点数一定是从左到右依次变满的
     *
     * 	先统计完全二叉树的左子树总深度（遍历最左子树，一直到为空为止）N
     *
     * 	再去找当前节点的右子树的的总深度M：
     *
     * 	如果M==N，那么左子树一定是满二叉树 2^(N) -1,然后再递归计算右树的节点数
     *
     * 	如果M==N-1，那么代表此时右树是满二叉树，节点数量为2^(M)-1,此时再递归计算左子树的节点数量
     * @param head
     * @return
     */
    public static int nodeNUm(Node head){
        if(head == null){
            return 0;
        }
        return bs(head,1,mostLeftLevel(head,1));
    }

    /**
     * node在level层，h是总深度，不变
     *
     * @param node
     * @param level
     * @param h
     * @return
     */
    public static int bs(Node node,int level,int h){
        //如果已经到了最深层，那么此时只有一个节点
        if(level == h){
            return 1;
        }
        //计算右孩子的深度，如果等于最大高度，那么说明左树是满二叉树
        if(mostLeftLevel(node.right,level+1) == h){
            //h-level 等于当前节点下有多高
            return (1 << (h-level)) + bs(node.right,level+1,h);
        }else{
            //如果不等，说明右树是满二叉树
            return (1 << (h-level - 1)) + bs(node.left,level+1,h);
        }
    }

    /**
     * 如果node在第level层，
     * 求此时以node为头的最大深度
     * @param node
     * @param level
     * @return
     */
    public static int mostLeftLevel(Node node,int level){
       while(node != null){
           level++;
           node = node.left;
       }
       //一直到空才停，所以包含了空
       return level-1;
    }
}

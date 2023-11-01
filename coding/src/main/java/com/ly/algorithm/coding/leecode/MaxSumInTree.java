package com.ly.algorithm.coding.leecode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定：
 *
 * 	路径必须是头节点出发，到叶节点为止，返回最大路径和
 *
 * 	路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 *
 * 	路径可以从任何节点出发，到任何节点，返回最大路径和
 * @author Ly
 * @create 2023/9/16 13:59
 * @desc
 **/
public class MaxSumInTree {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node{
        private Node left;

        private Node right;

        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    //路径必须是头节点出发，到叶节点为止，返回最大路径和

    private static int MAX_SUM = Integer.MIN_VALUE;

    /**
     * 方案一：定义一个全局的maxSum，从头节点开始来到任意节点时，将这个节点之前的路径和加上当前节点的值，再分别去左孩子和右孩子递归。只有当来到叶子节点时，才更新maxSum。
     * @param head
     * @return
     */
    public static int getMaxSumInTree(Node head){
        if(head == null){
            return 0;
        }
        sum(head,0);
        return MAX_SUM;
    }

    /**
     *
     * @param node
     * @param pre 当前节点之前路径的和
     * @return
     */
    public static void sum(Node node,int pre){
        pre += node.value;
        //来到叶子节点
        if(node.left == null && node.right == null){
            MAX_SUM = Math.max(pre,MAX_SUM);
            return;
        }
        //如果不是来到子节点，继续向后遍历
        if(node.left != null){
            sum(node.left,pre);
        }
        if(node.right != null){
            sum(node.right,pre);
        }

    }


    /**
     * 	方案二：	使用二叉树的递归套路，向你的左树收集答案，向你的右树收集答案，取最大值，再加上当前节点的值，就是以当前节点作为头节点的最大路径和
     * @param head
     * @return
     */
    public static int getMaxSumInTreeV2(Node head){
        if(head == null){
            return 0;
        }
        return  sumV2(head);
    }

    public static int sumV2(Node node){

        if(node.left == null && node.right == null){
            //如果左右孩子都为空，那么返回自身
            return node.value;
        }
        int sum = Integer.MIN_VALUE;
        //向左树要一个答案，
        if(node.left != null){
            sum = Math.max(sum,sumV2(node.left));
        }
        //向右树要一个答案
        if(node.right != null){
            sum = Math.max(sum,sumV2(node.right));
        }
        return sum+node.value;
    }




    //路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
    public static int getMaxSumInTree_2(Node head){
        if(head == null){
            return 0;
        }
        Info info = sum_2(head);
        int ans = Math.max(info.getAllTreeMaxSum(),info.getFromHeadSum());
        return  ans;
    }

    /**
     * 和X无关： 1、左树最大整树路径和 2、右树最大整树路径和
     * 和X有关  3、X节点值 4、X节点+左树头节点最大路径和 5、X节点+右树节点最大路径和
     * @param node
     * @return
     */
    public static Info sum_2(Node node){
        if(node == null){
            //如果左右孩子都为空，那么返回自身
            return null;
        }
        Info leftInfo = sum_2(node.left);
        Info rightInfo = sum_2(node.right);
        Integer fromHeadSum = node.value;
        Integer allTreeMaxSum = node.value;
        //向左树要一个答案，
        if(leftInfo != null){
            fromHeadSum = Math.max(fromHeadSum,leftInfo.getFromHeadSum()+node.value);
            allTreeMaxSum = Math.max(allTreeMaxSum, leftInfo.allTreeMaxSum);
        }
        //向右树要一个答案
        if(rightInfo != null){
            fromHeadSum = Math.max(fromHeadSum,rightInfo.getFromHeadSum()+node.value);
            allTreeMaxSum = Math.max(allTreeMaxSum, rightInfo.allTreeMaxSum);
        }
        //和X节点有关
        return  Info.builder().allTreeMaxSum(allTreeMaxSum).fromHeadSum(fromHeadSum).build();
    }


    public static int getMaxSumInTree_3(Node head){
        if(head == null){
            return 0;
        }
        Info info = sum_3(head);
        int ans = Math.max(info.getAllTreeMaxSum(),info.getFromHeadSum());
        return  ans;
    }

    /**
     * 和X无关： 1、左树最大整树路径和 2、右树最大整树路径和
     * 和X有关  3、X节点值 4、X节点+左树头节点最大路径和 5、X节点+右树节点最大路径和 6、X节点+左树头节点最大路径和+右树节点最大路径和
     * @param node
     * @return
     */

    private static int MAX = Integer.MIN_VALUE;
    public static Info sum_3(Node node){
        if(node == null){
            //如果左右孩子都为空，那么返回自身
            return null;
        }
        Info leftInfo = sum_3(node.left);
        Info rightInfo = sum_3(node.right);
        Integer fromHeadSum = node.value;
        Integer allTreeMaxSum = node.value;
        if(leftInfo != null && rightInfo != null){
            //node.value + 左树头节点 + 右树头节点 ,此时算作整树节点
            allTreeMaxSum = Math.max(fromHeadSum,node.value+ leftInfo.getFromHeadSum()+ rightInfo.getFromHeadSum()) ;
        }
        //向左树要一个答案，
        if(leftInfo != null){
            fromHeadSum = Math.max(fromHeadSum,node.value+leftInfo.getFromHeadSum());
            allTreeMaxSum = Math.max(allTreeMaxSum, leftInfo.allTreeMaxSum);
        }
        //向右树要一个答案
        if(rightInfo != null){
            fromHeadSum = Math.max(fromHeadSum,node.value+ rightInfo.getFromHeadSum());
            allTreeMaxSum = Math.max(allTreeMaxSum, rightInfo.getAllTreeMaxSum());
        }
        return  Info.builder().allTreeMaxSum(allTreeMaxSum).fromHeadSum(fromHeadSum).build();
    }

    @Builder
    @Data
    public static class Info{
        //整树最大和
        public int allTreeMaxSum;

        //从头最大和
        public int fromHeadSum;
    }

}

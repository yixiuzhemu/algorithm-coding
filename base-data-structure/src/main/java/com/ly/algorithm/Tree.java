package com.ly.algorithm;

import lombok.Data;

/**
 * 二叉树的递归套路
 * 1.假设以X节点为头，假设可以向X的左树和X的右树得到任何信息
 * 2。在上一步的假设下，讨论以X为头节点的树，得到答案的可能性(最重要)  常见的可能性 1.与X有关  2.与X无关
 * 3。列出所有可能性后，确定到底需求向左树和右树要什么样的信息
 * 4.把左树信息和右树信息求全集，就是任何一颗子树都需要返回的信息S
 * 5.递归函数返回S，每一颗子树都这么要求
 * 6.写代码，在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息
 * tree structure
 * @author Ly
 * @create 2021/6/4 10:50
 * @desc
 **/
@Data
public class Tree {


    protected Integer value;

    /**
     * left tree
     */
    protected Tree left;

    /**
     * right tree
     */
    protected Tree right;


}

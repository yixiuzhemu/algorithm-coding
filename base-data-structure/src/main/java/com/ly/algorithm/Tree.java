package com.ly.algorithm;

import lombok.Data;

/**
 * tree structure
 * @author Ly
 * @create 2021/6/4 10:50
 * @desc
 **/
@Data
public class Tree {


    private Integer value;

    /**
     * left tree
     */
    private Tree left;

    /**
     * right tree
     */
    private Tree right;

}

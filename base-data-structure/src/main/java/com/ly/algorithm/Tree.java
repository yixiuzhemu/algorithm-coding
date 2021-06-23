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

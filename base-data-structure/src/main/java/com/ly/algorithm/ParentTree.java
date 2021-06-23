package com.ly.algorithm;

import lombok.Data;

/**
 * the tree was have parent point
 * @author Ly
 * @create 2021/6/23 9:32
 * @desc
 **/
@Data
public class ParentTree extends Tree{

    /**
     * parent tree
     */
    private ParentTree parent;

    public ParentTree(Integer value) {
        this.value = value;
    }

    public ParentTree() {
    }

    public void setLeft(ParentTree left) {
        this.left = left;
        left.setParent(this);
    }

    public void setRight(ParentTree right) {
        this.right = right;
        right.setParent(this);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public ParentTree getLeft() {
        return (ParentTree)left;
    }

    @Override
    public ParentTree getRight() {
        return (ParentTree)right;
    }
}

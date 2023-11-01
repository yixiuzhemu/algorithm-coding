package com.ly.algorithm.coding.binarysearchtree;

/**
 * 自旋搜索二叉树
 * @author Ly
 * @create 2023/8/27 13:13
 * @desc
 **/
public abstract class AbstractSelfBalancingBinarySearchTree extends AbstractBinarySearchTree {

    /**
     * 左旋
     * @param node
     * @return
     */
    protected Node rotateLeft(Node node){
        //记录左旋节点的右孩子
        Node temp = node.right;
        //将右孩子连接到左旋节点的父节点上
        temp.parent = node.parent;
        //把右子树的左孩子放在左旋节点的右孩子
        node.right = temp.left;
        //如果左旋节点的右孩子存在左孩子，那么将左孩子的父节点更换为左旋节点（也就是把右子树的左孩子放在左旋节点的右孩子上）
        if(node.right != null){
            node.right.parent = node;
        }
        //左旋节点成为右孩子的左孩子，替换父子关系
        temp.left = node;
        return changeParent(node,temp);
    }

    /**
     * 右旋
     * @param node
     * @return
     */
    public Node rotateRight(Node node){
        //记录右旋节点的左孩子
        Node temp = node.left;
        //将左孩子连接到右旋节点的父节点上
        temp.parent = node.parent;
        //把右旋节点左孩子的右孩子放在右旋节点的左孩子上
        node.left = temp.right;
        //如果右旋节点的左孩子存在右孩子，那么将右孩子的父节点变更为右旋节点
        if(node.left != null){
            node.left.right = node;
        }
        //右旋节点成为左孩子的右孩子，替换父子关系
        temp.right = node;
        return changeParent(node,temp);
    }

    protected Node changeParent(Node node,Node temp){
        node.parent = temp;
        if(temp.parent != null){
            if(node == temp.parent.left){
                temp.parent.left = temp;
            }else{
                temp.parent.right = temp;
            }
        }else{
            root = temp;
        }
        return temp;
    }

}

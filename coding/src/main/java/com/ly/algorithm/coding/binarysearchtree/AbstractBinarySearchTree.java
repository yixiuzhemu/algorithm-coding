package com.ly.algorithm.coding.binarysearchtree;

/**
 *
 * 搜索二叉树操作
 * @author Ly
 * @create 2023/8/26 15:30
 * @desc
 **/
public class AbstractBinarySearchTree {

    /**
     * Root node where whiole tree starts
     */
    public Node root;

    /**
     * Tree Size
     */
    protected int size;

    protected Node createNode(int value,Node parent,Node left,Node right){
        return new Node(value,parent,left,right);
    }

    /**
     * Finds a node with concrete value，If it is not found then null is returned。
     * @param element
     * @return
     */
    public Node search(int element){
        Node node = root;

        while(node != null && node.value != null && node.value != element){
            if(element < node.value){
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return node;
    }

    /**
     * Insert new element to tree
     * @param element
     * @return
     */
    public Node insert(int element){
        if(root == null){
            root = createNode(element,null,null,null);
            size++;
            return root;
        }
        Node insertParentNode = null;
        Node searchTempNode = root;
        //找到当前节点挂在哪个父节点下
        while(searchTempNode != null && searchTempNode.value != null){
            insertParentNode = searchTempNode;
            if(element < searchTempNode.value){
                searchTempNode = searchTempNode.left;
            }else{
                searchTempNode = searchTempNode.right;
            }
        }
        Node newNode = createNode(element,insertParentNode,null,null);
        if(insertParentNode.value > newNode.value){
            insertParentNode.left = newNode;
        }else {
            insertParentNode.right = newNode;
        }
        size++;
        return newNode;
    }

    public Node delete(int element){
        Node deleteNode = search(element);
        if(deleteNode != null){
            return delete(deleteNode);
        }
        return null;
    }

    /**
     * Delete logic when node is already found
     * @param deleteNode
     * @return
     */
    protected Node delete(Node deleteNode){
        if(deleteNode == null){
            return null;
        }
        size--;
        if(deleteNode.left == null){
            //用b去替换a的环境
            return transplant(deleteNode,deleteNode.right);
        }else if (deleteNode.right == null){
            return transplant(deleteNode,deleteNode.left);
        }
        Node successOrNode = getMinimum(deleteNode.right);
        //如果当前后继节点不是删除节点的右孩子，那么才将后继节点的右孩子，交给后继节点的父节点接管。
        if(successOrNode.parent != deleteNode){
            //后继节点的右孩子接管后继节点的环境，也就是让后继节点的父节点接管后继节点的右孩子
            transplant(successOrNode,successOrNode.right);
            //后继节点接管删除节点的右孩子
            successOrNode.right = deleteNode.right;
            //更新右子树的parent节点
            successOrNode.right.parent = successOrNode;
        }
        //用后继节点接管删除节点,接管左子树
        transplant(deleteNode,successOrNode);
        successOrNode.left = deleteNode.left;
        successOrNode.left.parent = successOrNode;
        return successOrNode;
    }

    /**
     * 找到最左侧的节点
     * @param node
     * @return
     */
    protected Node getMinimum(Node node){
        Node left = node.left;
        while ((left.left != null)){
            left = left.left;
        }
        return left;
    }

    /**
     * put one node from tree（newNode）to the place of another(nodeToReplace)
     * @param nodeToReplace
     * @param newNode
     * @return
     */
    protected Node transplant(Node nodeToReplace,Node newNode){
        if(nodeToReplace.parent == null){
            this.root = newNode;
        }else if(nodeToReplace == nodeToReplace.parent.left){
            //如果当前节点在左子树上
            nodeToReplace.parent.left = newNode;
        }else{
            //如果当前节点在右子树上
            nodeToReplace.parent.right = newNode;
        }
        if(newNode != null){
            //变更父节点
            newNode.parent = nodeToReplace.parent;
        }
        return newNode;
    }

    class Node{
        Integer value;

        Node left;

        Node right;

        Node parent;

        public Node(Integer value, Node parent, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}

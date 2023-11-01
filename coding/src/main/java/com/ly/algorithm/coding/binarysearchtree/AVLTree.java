package com.ly.algorithm.coding.binarysearchtree;

/**
 * @author Ly
 * @create 2023/8/28 21:29
 * @desc
 **/
public class AVLTree extends AbstractSelfBalancingBinarySearchTree{

    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        rebalance((AVLNode) newNode);
        return newNode;
    }

    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if(deleteNode == null){
            return null;
        }
        //会返回接替当前位置的节点
        Node successorNode = super.delete(deleteNode);
        if(successorNode != null){
            //在删除时，如果存在左孩子、右孩子，那么肯定会使用后继节点去替换（会右后继节点的冗余，可以通过if else 优化）
            //如果只存在左孩子，那么左孩子会替当前节点，去拿当前左孩子的后继节点或者当前左孩子 都是可以遍历完所有父节点的（会右后继节点的冗余，可以通过if else 优化）
            //如果只存在右孩子，那么右孩子会替当前节点，去拿当前右孩子的后继节点或者当前右孩子 都是可以遍历完所有的父节点（会有后继节点的冗余，可以通过if else 优化）
            AVLNode minimum = successorNode.right != null ? (AVLNode) getMinimum(successorNode) : (AVLNode) successorNode;
            recomputeHeight(minimum);
            rebalance((AVLNode) minimum);
        }else{
            //代表没有节点替换当前位置，代表删除的是一个叶子节点，即没有左孩子替，也没有后继节点替。
            recomputeHeight((AVLNode)deleteNode.parent);
            rebalance((AVLNode) deleteNode.parent);
        }
        return successorNode;
    }

    private void recomputeHeight(AVLNode node){
        //从受影响的节点开始网上计算高度
        while ((node != null)) {
            //左子树和右子树最大高度+1
            node.height = maxHeight((AVLNode)node.left,(AVLNode)node.right)+1;
            node = (AVLNode) node.parent;
        }
    }

    private int maxHeight(AVLNode node1,AVLNode node2){
        if(node1 != null && node2 != null){
            return Math.max(node1.height,node2.height);
        }else if(node1 == null){
            return node2 != null ? node2.height : -1;
        }else if(node2 == null){
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value,parent,left,right);
    }

    public void rebalance(AVLNode node){
        while(node != null){
            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : ((AVLNode)node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AVLNode)node.right).height;
            int nodeBalance = rightHeight - leftHeight;
            if(nodeBalance == 2){
                //如果当前节点的右孩子的右孩子不等于空，并且右孩子的右孩子的高度，等于左孩子高度+1，那么认为是RR型
                if(node.right.right != null && ((AVLNode)node.right.right).height == leftHeight + 1){
                    node = (AVLNode) avlRotateLeft(node);
                    break;
                }else{
                    //RL类型
                    node = (AVLNode) doubleRotateRightLeft(node);
                    break;
                }
            }else if(nodeBalance == -2){
                //如果当前节点的左孩子的左孩子不等于空，并且左孩子的左孩子的高度，等于当前右孩子的高度+1，那么认为是LL型
                if(node.left.left != null && ((AVLNode)node.left.left).height == rightHeight + 1 ){
                    node = (AVLNode) avlRotateRight(node);
                    break;
                }else{
                    //LR类型
                    node = (AVLNode) doubleRotateLeftRight(node);
                    break;
                }
            }else{
                updateHeight(node);
            }
            node = (AVLNode) parent;
        }
    }

    private void updateHeight(AVLNode node){
        int leftHeight = (node.left == null)?-1 : ((AVLNode)node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode)node.right).height;
        node.height = 1+Math.max(leftHeight,rightHeight);
    }

    /**
     * 当前时LR型，那么先右旋、再左旋
     * @param node
     * @return
     */
    private Node doubleRotateRightLeft(Node node){
        //左孩子先进行一次右旋
        node.left = avlRotateRight(node.left);
        return avlRotateLeft(node);
    }

    /**
     * 当前RL型，先左旋再右旋
     * @param node
     * @return
     */
    private Node doubleRotateLeftRight(Node node){
        //右孩子先进行一次左旋
        node.right = avlRotateLeft(node.right);
        return avlRotateRight(node);
    }

    /**
     * 左旋之后，要更新受影响的左旋节点以及当前节点的高度
     * @param node
     * @return
     */
    private Node avlRotateLeft(Node node){
        Node temp = super.rotateLeft(node);
        updateHeight((AVLNode)temp.left);
        updateHeight((AVLNode)temp);
        return temp;
    }

    /**
     * 右旋之后，要更新受影响的右旋节点以及当前节点的高度
     * @param node
     * @return
     */
    private Node avlRotateRight(Node node){
        Node temp = super.rotateLeft(node);
        updateHeight((AVLNode)temp.right);
        updateHeight((AVLNode)temp);
        return temp;
    }

    class AVLNode extends Node{

        int height;
        public AVLNode(Integer value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }
    }
}

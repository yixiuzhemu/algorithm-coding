package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.ly.algorithm.ParentTree;
import com.ly.algorithm.Tree;

import java.util.Queue;

/**
 * @author Ly
 * @create 2021/6/4 10:51
 * @desc
 **/
public class TreeCoding {
    /**
     * 先序遍历
     * @param tree
     */
    public static void pre(Tree tree){
        if(tree == null){
            return;
        }
        System.out.println(tree.getValue());
        pre(tree.getLeft());
        pre(tree.getRight());
    }

    public static void pre1(Tree tree){
        if(tree == null){
            return;
        }
        java.util.Stack<Tree> treeStack = new java.util.Stack();
        treeStack.push(tree);

        while(!treeStack.isEmpty()){
            Tree tree1 = treeStack.pop();
            System.out.println(tree1.getValue());
            if(tree1.getRight() != null){
                treeStack .push(tree1.getRight());
            }
            if(tree1.getLeft() != null){
                treeStack .push(tree1.getLeft());
            }
        }

    }

    /**
     * 中序遍历
     * @param tree
     */
    public static void mid(Tree tree){
        if(tree == null){
            return;
        }
        mid(tree.getLeft());
        System.out.println(tree.getValue());
        mid(tree.getRight());
    }

    public static void mid1(Tree tree){
        if(tree == null){
            return;
        }
        java.util.Stack<Tree> treeStack = new java.util.Stack();
        while(!treeStack.isEmpty() || tree != null){
            if(tree != null){
                treeStack.push(tree);
                tree = tree.getLeft();
            }else{
                tree = treeStack.pop();
                System.out.println(tree.getValue());
                tree = tree.getRight();
            }
        }
    }




    /**
     * 后序遍历
     * @param tree
     */
    public static void suf(Tree tree){
        if(tree == null){
            return;
        }
        suf(tree.getLeft());
        suf(tree.getRight());
        System.out.println(tree.getValue());
    }

    public static void suf1(Tree tree){
        if(tree == null){
            return;
        }
        java.util.Stack<Tree> treeStack = new java.util.Stack();
        java.util.Stack<Integer> resultStack = new java.util.Stack();
        treeStack.push(tree);

        while(!treeStack.isEmpty()){
            Tree tree1 = treeStack.pop();
            resultStack.push(tree1.getValue());
            if(tree1.getLeft() != null){
                treeStack .push(tree1.getLeft());
            }
            if(tree1.getRight() != null){
                treeStack .push(tree1.getRight());
            }
        }
        while(!resultStack.isEmpty()){
            System.out.println(resultStack.pop());
        }

    }

    /**
     * 宽度遍历
     * @param tree
     */
    public static void suf2(Tree tree){
        if(tree == null){
            return;
        }
        java.util.Stack<Tree> treeStack = new java.util.Stack();
        treeStack.push(tree);
        Tree t = null;
        while(!treeStack.isEmpty()){
            t = treeStack.peek();
            if(t.getLeft() != null && t.getLeft() != tree && t.getRight() != tree){
                treeStack.push(t.getLeft());
            }else if(t.getRight() != null && t.getRight() != tree){
                treeStack.push(t.getRight());
            }else{
                System.out.println(treeStack.pop().getValue());
                tree = t;
            }
        }

    }

    /**
     * 层级遍历
     * @param tree
     */
    public static void width(Tree tree){
        if(tree == null){
            return;
        }
        Queue<Tree> queues = Lists.newLinkedList();
        queues.add(tree);
        while(!queues.isEmpty()){
            Tree poll = queues.poll();
            System.out.println(poll.getValue());
            if(poll.getLeft() != null){
                queues.add(poll.getLeft());
            }
            if(poll.getRight() != null){
                queues.add(poll.getRight());
            }
        }
    }

    /**
     * 获取二叉树的宽度
     * @param tree
     */
    public static void getWidth(Tree tree){
        if(tree == null){
            return;
        }
        Queue<Tree> queues = Lists.newLinkedList();
        queues.add(tree);
        //当前层的最后一个节点
        Tree curEnd = tree;
        //下一层的最后一个节点
        Tree nextEnd = null;
        //最大宽度
        int width = 0;
        //当前节点数
        int curNodeCount = 0;
        while(!queues.isEmpty()){
            Tree cur = queues.poll();
            if(cur.getLeft() != null){
                queues.add(cur.getLeft());
                nextEnd = cur.getLeft();
            }
            if(cur.getRight() != null){
                queues.add(cur.getRight());
                nextEnd = cur.getRight();
            }
            curNodeCount++;
            if(cur == curEnd){
                width = Math.max(width,curNodeCount);
                curNodeCount = 0;
                curEnd = nextEnd;
            }
        }
        System.out.println(width);
    }

    /**
     * 先序序列化
     * @param tree
     * @param results
     */
    public static void preSerial(Tree tree, Queue<String> results){
        if(tree == null){
            results.add(null);
            return;
        }
        results.add(String.valueOf(tree.getValue()));
        preSerial(tree.getLeft(),results);
        preSerial(tree.getRight(),results);
    }

    /**
     * 先序反序列化
     * @param source
     * @return
     */
    public static Tree buildTree(Queue<String> source){
        String value = source.poll();
        if(value == null){
            return null;
        }
        Tree tree = new Tree();
        tree.setValue(Integer.valueOf(value));
        tree.setLeft(buildTree(source));
        tree.setRight( buildTree(source));
        return tree;
    }

    /**
     * 层级序列化
     * @param tree
     * @param result
     */
    public static void levelSerial(Tree tree,Queue<String> result){
        if(tree == null){
            return;
        }
        Queue<Tree> queues = Lists.newLinkedList();
        queues.add(tree);
        result.add(String.valueOf(tree.getValue()));
        while(!queues.isEmpty()){
            Tree poll = queues.poll();
            if(poll.getLeft() != null){
                queues.add(poll.getLeft());
                result.add(String.valueOf(poll.getLeft().getValue()));
            }else{
                result.add(null);
            }
            if(poll.getRight() != null){
                queues.add(poll.getRight());
                result.add(String.valueOf(poll.getRight().getValue()));
            }else{
                result.add(null);
            }
        }
    }

    /**
     *
     * 层级反序列化
     * @param source
     * @return
     */
    public static Tree buildLevelTree(Queue<String> source){
        String poll = source.poll();
        Queue<Tree> queues = Lists.newLinkedList();
        Tree tree = new Tree();
        if(poll != null){
            tree.setValue(Integer.valueOf(poll));
            queues.add(tree);
        }
        while(!queues.isEmpty()){
            Tree poll1 = queues.poll();
            String left = source.poll();
            if(left == null){
                poll1.setLeft(null);;
            }else{
                poll1.setLeft(new Tree());
                poll1.getLeft().setValue(Integer.valueOf(left));
                queues.add(poll1.getLeft());
            }

            String right = source.poll();
            if(right == null){
                poll1.setRight(null);
            }else{
                poll1.setRight(new Tree());
                poll1.getRight().setValue(Integer.valueOf(right));
                queues.add(poll1.getRight());
            }
        }
        return tree;
    }

    /**
     * 从拥有父节点的树中找到某一个节点的后继节点
     * （后继节点：中序遍历中的下一个节点）
     * @param tree
     * @return
     */
    public static ParentTree getSuccessdingTree(ParentTree tree){
        if(tree == null){
            return null;
        }
        ParentTree right = tree.getRight();
        //当存在右子树时，右子树的最左节点就是当前节点的后继节点
        if(right != null){
            ParentTree left = right.getLeft();
            if(left == null){
                return right;
            }
            while(left.getLeft() != null){
                left = left.getLeft();
            }
            return left;
        }else{
            //当不存在右子树时，当前节点的某个父节点是其父节点的左子树时，这个父节点就为当前节点的后继节点
            ParentTree node = tree.getParent();
            if(node == null){
                return null;
            }
            ParentTree parent = node.getParent();
            while(parent != null && parent.getLeft() != node){
                node = parent;
                parent = node.getParent();
            }
            if(parent == null){
                return null;
            }
            return parent;
        }
    }

    /**
     * 从拥有父节点的树中找到某一个节点的前驱节点
     * （前驱节点：中序遍历中的上一个节点）
     * @param tree
     * @return
     */
    public static ParentTree getPrecursorNode(ParentTree tree){
        if(tree == null){
            return null;
        }
        ParentTree left = tree.getLeft();
        if(left != null){
            //如果存在左子树，那么左子树最右的节点就是当前节点的前驱节点
            ParentTree right = left.getRight();
            if(right == null){
                return left;
            }
            while(right.getRight() != null){
                right = right.getRight();
            }
            return right;
        }else{
            //当不存在左子树时，当前节点的父节点就是当前节点的前驱节点
            return tree.getParent();
        }
    }

    /**
     * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕
     * 后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。如果从
     * 纸条的下边向上方连续对折两次，压出折痕后展开，此时有三条折痕，从上到
     * 下依次是下折痕，下折痕和上折痕。
     * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打
     * 印所有折痕的方向
     * 例如 N = 1时，打印 down  N = 2时，打印Ldown down up
     */

    public static void printAllCrease(int N){
        printProcess(1,N,true);
    }

    /**
     * 纸张对折实际上 就是在当前这次折痕的上方全是凹折痕，下方全是凸折痕
     * 并且每次增加的折痕数是2的N次方，实际上可以组成一颗满二叉树
     * 而从上到下打印所有的折痕，（上，当前折痕，下）实际上是对二叉树进行中序遍历
     * @param i
     * @param N
     * @param down
     */
    private static void printProcess(int i,int N,Boolean down){
        if(i > N){
            return;
        }
        printProcess(i+1,N,true);
        System.out.print(" "+(down?"凹":"凸")+" ");
        printProcess(i+1,N,false);
    }


    /**
     * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
     * 1.最大距离和X无关： 最大距离在左子树  或 最大距离在右子树
     * 2.最大距离和X有关： 距离X最远的左子树上的节点 到 距离X最远的右子树上的点（左树的高度+1+右树的高度）
     *
     * 需要返回左子树的最大距离 以及右子树的最大距离
     *
     */

    static class Info{
        /**
         * 整棵树的最大距离
         */
        public int maxDistance;

        /**
         * 整棵树的最大高度
         */
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    private static Info getDistance(Tree tree){
        if(tree == null){
            return new Info(0,0);
        }
        Info leftInfo = getDistance(tree.getLeft());
        Info rightInfo = getDistance(tree.getRight());
        //最大高度，就是左子树高度和右子树高度其中的最大值
        int height = Math.max(leftInfo.height,rightInfo.height)+1;
        //最大距离，判断最大距离是否和X有关，如果左子树最大高度加上右子树最大高度加上1大于左右子树最大距离，那么最大距离和X有关
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance,rightInfo.maxDistance),leftInfo.height+1+rightInfo.height);
        return new Info(maxDistance,height);
    }

    public static Integer getMaxDistance(Tree tree){
        return getDistance(tree).maxDistance;
    }


    /**
     * 给定一棵二叉树的头节点head，返回这棵二叉树中最大的二叉搜索子树的头节点
     * 搜索二叉树：整个树上没有重复值，左树的值都比头节点小，右树的值都比头节点大
     * 1.与X无关：不以x为头节点，
     * 2.与X有关：左树整体为搜索二叉树，右树整体为搜索二叉树，且左树的搜索二叉树头节点要小于右树的搜索二叉树头节点
     *
     * 需要的信息
     * 左树
     * 1.最大左子树搜索二叉树头节点
     * 2.左子树整体是否是搜索二叉树
     * 3.左树上的最大值
     * 4.搜索二叉树的节点数
     *
     * 右树
     * 1.最大右子树搜索二叉树头节点
     * 2.右子树整体是否是搜索二叉树
     * 3.右子树的最大值，右子树的最小值
     * 4.搜索二叉树的节点数
     *
     * 取并集
     */

    static class SearchInfo{
        //整棵树是否是搜索二叉树
        public boolean isAllBST;
        //搜索二叉树的节点数
        public int maxSubBSTSize;
        //最小值
        public int min;
        //最大值
        public int max;

        //搜索二叉树的头节点
        public Tree maxSSearchHeadTree;

        public SearchInfo(boolean isAllBST, int maxSubBSTSize, int min, int max,Tree maxSSearchHeadTree) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
            this.maxSSearchHeadTree = maxSSearchHeadTree;
        }
    }

    private static SearchInfo getSearchTree(Tree tree){
        if(tree == null){
            return  null;
        }
        SearchInfo leftSearchTree = getSearchTree(tree.getLeft());
        SearchInfo rightSearchTree = getSearchTree(tree.getRight());
        boolean isAllBST = false;
        int maxSubBSTSize = 0;
        int min = tree.getValue();
        int max = tree.getValue();
        Tree maxSSearchHeadTree = null;
        if(leftSearchTree != null){
            min = Math.min(min,leftSearchTree.min);
            max = Math.max(max,leftSearchTree.max);
            if (maxSubBSTSize < leftSearchTree.maxSubBSTSize){
                maxSubBSTSize = leftSearchTree.maxSubBSTSize;
                if(leftSearchTree.isAllBST){
                    maxSSearchHeadTree = leftSearchTree.maxSSearchHeadTree;
                }

            }
        }
        if(rightSearchTree != null){
            min = Math.min(min,rightSearchTree.min);
            max = Math.max(max,rightSearchTree.max);
            if (maxSubBSTSize < rightSearchTree.maxSubBSTSize){
                maxSubBSTSize = rightSearchTree.maxSubBSTSize;
                if(rightSearchTree.isAllBST){
                    maxSSearchHeadTree = rightSearchTree.maxSSearchHeadTree;
                }
            }
        }
        if((leftSearchTree == null ? true : leftSearchTree.isAllBST)
                &&(rightSearchTree == null ? true:rightSearchTree.isAllBST)
        && (leftSearchTree == null ? true : leftSearchTree.max < tree.getValue())
        && (rightSearchTree == null ? true : rightSearchTree.min > tree.getValue())){
            isAllBST = true;
            maxSubBSTSize = (leftSearchTree == null ? 0 : leftSearchTree.maxSubBSTSize) + (rightSearchTree == null ? 0 :rightSearchTree.maxSubBSTSize)+1;
            maxSSearchHeadTree = tree;
        }
        return new SearchInfo(isAllBST,maxSubBSTSize,min,max,maxSSearchHeadTree);
    }

    public static Integer getMaxSearchTree(Tree tree){
        SearchInfo searchTree = getSearchTree(tree);
        if(searchTree == null){
            return null;
        }
        return searchTree.maxSubBSTSize;
    }
}

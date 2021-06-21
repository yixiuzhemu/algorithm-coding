package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.ly.algorithm.Tree;

import java.util.LinkedList;
import java.util.List;
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
}

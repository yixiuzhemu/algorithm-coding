package com.ly.algorithm.coding;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ly.algorithm.Tree;

import java.util.*;

/**
 * 二叉树的递归套路
 * 1.假设以X节点为头，假设可以向X的左树和X的右树得到任何信息
 * 2。在上一步的假设下，讨论以X为头节点的树，得到答案的可能性(最重要)  常见的可能性 1.与X有关  2.与X无关
 * 3。列出所有可能性后，确定到底需求向左树和右树要什么样的信息
 * 4.把左树信息和右树信息求全集，就是任何一颗子树都需要返回的信息S
 * 5.递归函数返回S，每一颗子树都这么要求
 * 6.写代码，在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息
 * @author Ly
 * @create 2021/6/25 16:38
 * @desc
 **/
public class RecursiveRoutineCoding {
    /**
     *
     * 二叉树最大距离
     *
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
     * 搜索二叉树的头节点
     *
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

    /**
     * 判断是否是满二叉树
     * 给定一棵二叉树的头节点head，判断一棵树是否是满二叉树
     * 满二叉树每一层的节点数都是满的 ，并且只有满二叉树满足2^L - 1 = N
     * 其中L代表二叉树的高度，N 代表二叉树的节点数
     *
     * 需要的信息：
     * 高度H
     * 节点数N
     */

    static class FullTreeInfo{
        public int h;

        public int n;

        public FullTreeInfo(int h, int n) {
            this.h = h;
            this.n = n;
        }
    }

    private static FullTreeInfo getFullTreeInfo(Tree tree){
        if(tree == null){
            return new FullTreeInfo(0,0);
        }
        FullTreeInfo fullLeftTreeInfo = getFullTreeInfo(tree.getLeft());
        FullTreeInfo fullRightTreeInfo = getFullTreeInfo(tree.getRight());
        return new FullTreeInfo(Math.max(fullLeftTreeInfo.h,fullRightTreeInfo.h)+1,fullLeftTreeInfo.n + fullRightTreeInfo.n + 1);
    }

    public static Boolean isFullTree(Tree tree){
        FullTreeInfo fullTreeInfo = getFullTreeInfo(tree);
        if(Math.pow(2,fullTreeInfo.h) - 1 == fullTreeInfo.n){
            return true;
        }
        return false;
    }


    /**
     *
     * 判断是否是完全二叉树
     *
     * 给定一棵二叉树的头节点head，判断二叉树是不是完全二叉树
     * 完全二叉树：如果不是满二叉树，那么一定是从左至右依次变满的二叉树
     * 常规解法： 进行宽度遍历 1.任何节点不能有右节点时无左节点   2.一旦遇到左右孩子不双全时，后续遇到的所有节点都只能时叶子节点
     */
    public static Boolean isCompletelyTree(Tree tree){
        if(tree == null){
            return true;
        }
        Queue<Tree> queues = Lists.newLinkedList();
        queues.add(tree);
        Boolean leaf = false;
        while(!queues.isEmpty()){
            Tree poll = queues.poll();
            Tree l = poll.getLeft();
            Tree r = poll.getRight();
            if(l == null && r != null){
                return false;
            }
            if(leaf && (l != null || r != null)){
                return false;
            }
            if(l != null){
                queues.add(l);
            }
            if(r != null){
                queues.add(r);
            }
            if((l != null && r == null) || (l == null && r == null)){
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 递归套路解法：
     * 1.整体时满二叉树
     * 2.左树是完全二叉树，右树是满二叉树，且左树的高度比右树高度高1
     * 3.左树是满二叉树，右树是满二叉树，且左树的高度比右树高度高1
     * 4.左树是满二叉树，右树是完全二叉树，且左树的高度和右树高度相同
     *
     * 需要知道的信息：
     * 1.是否是满二叉树
     * 2.子树的高度
     * 3.是否是完全二叉树
     */
    static class CompletelyInfo{
        public boolean isFull;

        public int h;

        public boolean isCompletely;

        public CompletelyInfo(boolean isFull, int h, boolean isCompletely) {
            this.isFull = isFull;
            this.h = h;
            this.isCompletely = isCompletely;
        }
    }

    private  static CompletelyInfo  getCompletelyInfo(Tree tree){
        if( tree == null){
            return new CompletelyInfo(true,0,true);
        }
        CompletelyInfo leftCompletelyInfo = getCompletelyInfo(tree.getLeft());
        CompletelyInfo rightCompletelyInfo = getCompletelyInfo(tree.getRight());
        int h = Math.max(leftCompletelyInfo.h,rightCompletelyInfo.h) + 1;
        if(leftCompletelyInfo.isFull && rightCompletelyInfo.isFull && leftCompletelyInfo.h == rightCompletelyInfo.h){
            return new CompletelyInfo(true,h,true);
        }
        if(leftCompletelyInfo.isCompletely && rightCompletelyInfo.isFull && leftCompletelyInfo.h - 1  == rightCompletelyInfo.h){
            return new CompletelyInfo(false,h,true);
        }

        if(leftCompletelyInfo.isFull && rightCompletelyInfo.isFull && leftCompletelyInfo.h - 1 == rightCompletelyInfo.h){
            return new CompletelyInfo(false,h,true);
        }

        if(leftCompletelyInfo.isFull && rightCompletelyInfo.isCompletely && leftCompletelyInfo.h  == rightCompletelyInfo.h){
            return new CompletelyInfo(false,h,true);
        }
        return new CompletelyInfo(false,h,false);
    }

    public static Boolean isCompletelyTree2(Tree tree){
        return getCompletelyInfo(tree).isCompletely;
    }

    /**
     *
     * 两个节点最低公共祖先
     *
     * 给定一棵二叉树的头节点head，和另外两个节点a和b，返回a和b的最低公共祖先
     * 与X节点有关：那么A或者B 其中一个节点一定在X的子树上
     *      1.A B 只有一个在X上
     *      2.A B 都在X上
     *        1.左右各一个
     *           1.A在左边 B在右边
     *           2.A在右边 B在左边
     *        2.都在左边
     *        3.都在右边
     *        4.A,B在X上
     * 与X节点无关：那么A或者B 肯定不在X节点或者X节点的子树上
     * 需要的信息
     * 子树上是否包含A
     * 子树上是否包含B
     * 是否已经同时包含，返回第一个节点
     */
    static class AncestorInfo{
        public boolean hasA;

        public boolean hasB;

        public Tree ancestor;

        public AncestorInfo(boolean hasA, boolean hasB, Tree ancestor) {
            this.hasA = hasA;
            this.hasB = hasB;
            this.ancestor = ancestor;
        }
    }

    private static AncestorInfo getAncestorInfo(Tree tree,Tree a,Tree b){
        if(tree == null){
            return new AncestorInfo(false,false,null);
        }
        AncestorInfo leftAncestorInfo = getAncestorInfo(tree.getLeft(), a, b);
        //当左树已经找到交汇点，直接返回
        if(leftAncestorInfo.ancestor != null){
            return leftAncestorInfo;
        }
        AncestorInfo rightAncestorInfo = getAncestorInfo(tree.getRight(), a, b);
        //当右树已经找到交汇点，直接返回
        if(rightAncestorInfo.ancestor != null){
            return rightAncestorInfo;
        }
        //A在左边，B在右边
        if((leftAncestorInfo.hasA && rightAncestorInfo.hasB)
                //B在左边 A在右边
                || (leftAncestorInfo.hasB && rightAncestorInfo.hasA)
                //A和B 都在左边
                || (leftAncestorInfo.hasA && leftAncestorInfo.hasB)
                //A和B 都在右边
                || (rightAncestorInfo.hasA && rightAncestorInfo.hasB)){
            //当在子树已经发现了A和B ，那么当前节点 就是交汇点
            return new AncestorInfo(true,true,tree);
        }
        boolean hasA = (leftAncestorInfo.hasA || rightAncestorInfo.hasA);
        boolean hasB = (leftAncestorInfo.hasB || rightAncestorInfo.hasB);
        Tree ancestor = null;
        if(tree.equals(a)){
            hasA = true;
        }
        if(tree.equals(b)){
            hasB = true;
        }
//        if(hasA && hasB){
//            ancestor = tree;
//        }
        return new AncestorInfo(hasA,hasB,ancestor);
    }

    private static Tree getAncestorInfo2(Tree tree,Tree a, Tree b){
        Map<Tree,Tree> maps = Maps.newHashMap();
        maps.put(tree,null);
        //当value相等时，map会认为 节点是同一个，此时这种方法会有问题，节点数会变少，并且获取到的祖先节点有问题
        fillParentMap(tree,maps);
        Tree parent = maps.get(a);
        Set<Tree> parentLink = Sets.newHashSet();
        while(parent != null){
            parentLink.add(parent);
            parent = maps.get(parent);
        }
        parent = maps.get(b);
        while(parent != null){
            if(parentLink.contains(parent)){
                return parent;
            }
            parent = maps.get(parent);
        }
        return null;
    }

    public static void fillParentMap(Tree tree,Map<Tree,Tree> parentMap){
        if(tree.getLeft() != null){
            parentMap.put(tree.getLeft(),tree);
            fillParentMap(tree.getLeft(),parentMap);
        }
        if(tree.getRight() != null){
            parentMap.put(tree.getRight(),tree);
            fillParentMap(tree.getRight(),parentMap);
        }
    }

    public static void getAncestor(Tree tree){
        Random random = new Random();
        Queue<Tree> queues = Lists.newLinkedList();
        List<Tree> results = Lists.newArrayList();
        queues.add(tree);
        while(!queues.isEmpty()){
            Tree poll = queues.poll();
            results.add(poll);
            Tree l = poll.getLeft();
            Tree r = poll.getRight();
            if(l != null){
                queues.add(l);
            }
            if(r != null){
                queues.add(r);
            }
        }

        int a = random.nextInt(results.size());
        int b =  random.nextInt(results.size());
        AncestorInfo ancestorInfo = getAncestorInfo(tree, results.get(a), results.get(b));
        Tree ancestorInfo2 = getAncestorInfo2(tree, results.get(a), results.get(b));
        if(ancestorInfo.ancestor != null && !ancestorInfo.ancestor.equals(ancestorInfo2)){
            System.out.println("树结构："+ JSON.toJSONString(tree));
            System.out.println("a："+ JSON.toJSONString(results.get(a)));
            System.out.println("b："+ JSON.toJSONString(results.get(b)));
            System.out.println("最低公共祖先1为："+ JSON.toJSONString(ancestorInfo.ancestor));
            System.out.println("最低公共祖先2为："+ JSON.toJSONString(ancestorInfo2));
        }
    }
}

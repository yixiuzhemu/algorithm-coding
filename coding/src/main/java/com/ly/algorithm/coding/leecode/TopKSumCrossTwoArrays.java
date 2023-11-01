package com.ly.algorithm.coding.leecode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定两个有序数组arr1和arr2，再给定一个正数K，求两个数累加和最大的前K个，两个数必须分别来自arr1和arr2，要求位置不能重复，例如i=3,j = 4已经算过累加和了，那么不能再一次使用。
 * @author Ly
 * @create 2023/10/23 20:02
 * @desc
 **/
public class TopKSumCrossTwoArrays {

    public static class Node{

        public int index1;

        public int index2;

        public int sum;

        public Node(int i1,int i2,int s){
            index1 = i1;
            index2 = i2;
            sum = s;
        }

    }

    public static class MaxHeapComp implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    /**
     * 准备一个大根堆，因为两个数组有序，arr1作为行，arr2作为列，形成一个二维dp数组，毫无疑问右下角的值最大，将当前位置的值加入到大根堆中。然后从大根堆中弹出堆顶。获取到弹出节点的位置，分别将它在二维表中的左侧和上侧的节点加入到大根堆。直到弹出K个数位置（再添加左侧和上侧节点时，可能会遇到重复加入，此时需要保证不能重复加入节点。），整体方法的时间复杂度为O(K)
     * @param arr1
     * @param arr2
     * @param K
     * @return
     */
    public static int[] topKSumCrossTowArrays(int[] arr1,int[] arr2,int K){
        K = Math.min(K,arr1.length * arr2.length);
        Node first = new Node(arr1.length-1,arr2.length-1,arr1[arr1.length-1]+arr2[arr2.length-1]);
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new MaxHeapComp());
        //保证节点不会被重复加入
        boolean[][] setMap = new boolean[arr1.length][arr2.length];
        queue.add(first);
        setMap[arr1.length-1][arr2.length-1] = true;
        int[] ans = new int[K];
        int index = 0;
        while(!queue.isEmpty() && index<K){
            Node cur = queue.poll();
            ans[index++] = cur.sum;
            if(cur.index1 > 0 && !setMap[cur.index1-1][cur.index2] ){
                queue.add(new Node(cur.index1-1,cur.index2,arr1[cur.index1-1]+arr2[cur.index2]));
                setMap[cur.index1-1][cur.index2] = true;
            }
            if(cur.index2>0 && !setMap[cur.index1][cur.index2-1] ){
                queue.add(new Node(cur.index1,cur.index2-1,arr1[cur.index1]+arr2[cur.index2-1]));
                setMap[cur.index1][cur.index2-1] = true;
            }
        }
        return ans;
    }
}

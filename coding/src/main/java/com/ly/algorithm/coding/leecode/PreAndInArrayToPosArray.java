package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Ly
 * @create 2023/10/6 12:01
 * @desc
 **/
public class PreAndInArrayToPosArray {

    /**
     * 先序遍历：中左右
     *
     * 	中序遍历：左中右
     *
     * 	后序遍历：左右中
     *
     * 	对于示例，通过中序遍历能够知道，中序遍历中的1，就是树的头节点，应该放在后序遍历的末尾位置
     *
     * 	而中序遍历的0~2 位置 对应先序遍历的 1~3 位置，去生成后序遍历的0~2位置
     *
     * 	中序遍历的4~6位置对应先序遍历的4~6位置去生成后序遍历3~5位置
     * @param pre
     * @param in
     * @return
     */
    public static int[] preInToPos1(int[] pre,int[] in){
        if(pre == null || in == null || pre.length != in.length){
            return null;
        }
        int N = pre.length;
        int[] pos = new int[N];
        process1(pre,0,N-1,in,0,N-1,pos,0,N-1);
        return pos;
    }

    public static void process1(
            int[] pre,int L1,int R1,
            int[] in,int L2,int R2,
            int[] pos,int L3,int R3
    ){
        if(L1 > R1){
            return;
        }
        //只有一个数了，那么直接用先序遍历的位置填到后序遍历中，不会再存在左右子树
        if(L1 == R1){
            pos[L3] = pre[L1];
            return;
        }
        //后序遍历的末尾位置（头节点） 等于先序遍历的开头位置
        pos[R3] = pre[L1];
        //去找中序遍历的中间位置
        int mid = L2;
//        if(L2 == 4 && R2 == 6){
//            return;
//        }
        for(;mid <= R2;mid++){
            if(in[mid] == pre[L1]){
                break;
            }
        }
        //左树的规模
        int leftSize = mid - L2;
        //先序遍历L1+1  ~ L1+leftSize 为左
        //中序遍历L2 ~ L2 + leftSize 为左
        //对应填L3 ~ L3 + leftSize-1 的部分
        process1(pre,L1+1,L1+leftSize,in,L2,mid-1,pos,L3,L3+leftSize-1);
        //先序遍历L1+leftSize + 1 ~ R1 为右
        //中序遍历mid+1 ~R2 为右
        //对应填L3+leftSize ~ R3 - 1 的部分
        process1(pre,L1+leftSize+1,R1,in,mid+1,R2,pos,L3+leftSize,R3-1);
    }


    public static int[] preInToPos2(int[] pre,int[] in){
        if(pre == null || in == null || pre.length != in.length){
            return null;
        }

        int N = pre.length;
        Map<Integer,Integer> inMap = Maps.newHashMap();
        for(int i = 0;i<N;i++){
            inMap.put(in[i],i);
        }
        int[] pos = new int[N];
        process2(pre,0,N-1,in,0,N-1,pos,0,N-1,inMap);
        return pos;
    }

    public static void process2(
            int[] pre,int L1,int R1,
            int[] in,int L2,int R2,
            int[] pos,int L3,int R3,
            Map<Integer,Integer> inMap
    ){
        if(L1 > R1){
            return;
        }
        //只有一个数了，那么直接用先序遍历的位置填到后序遍历中，不会再存在左右子树
        if(L1 == R1){
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];
        int mid = inMap.get(pre[L1]);
        int leftSize = mid - L2;
        //先序遍历L1+1  ~ L1+leftSize 为左
        //中序遍历L2 ~ L2 + leftSize 为左
        //对应填L3 ~ L3 + leftSize-1 的部分
        process2(pre,L1+1,L1+leftSize,in,L2,mid-1,pos,L3,L3+leftSize-1,inMap);
        //先序遍历L1+leftSize + 1 ~ R1 为右
        //中序遍历mid+1 ~R2 为右
        //对应填L3+leftSize ~ R3 - 1 的部分
        process2(pre,L1+leftSize+1,R1,in,mid+1,R2,pos,L3+leftSize,R3-1,inMap);
    }
}

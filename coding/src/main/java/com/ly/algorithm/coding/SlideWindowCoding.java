package com.ly.algorithm.coding;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * 滑动窗口
 * 1.滑动窗口是一种想象出来的数据结构
 * 2.滑动窗口有左边界L和右边界R
 * 3.在数组或者字符串或者一个序列上，记为S，窗口就是S[L..R]这一部分
 * 4.L往右滑意味着一个样本出了窗口，R往右滑意味着一个样本进了窗口
 * 5.L和R都只能往右滑
 *
 * 滑动窗口能做什么
 * 滑动窗口、首尾指针等技巧，说白了就是一种求解问题的流程设计
 *
 * 想用滑动窗口，要想办法把具体的问题转化为滑动窗口的处理流程
 * 想用滑动窗口的最值的更新接口，就看看处理流程下，是否需要最值这个信息
 * @author Ly
 * @create 2021/7/19 15:33
 * @desc
 **/
public class SlideWindowCoding {

    /**
     * 获取最大值
     * 假设一个固定大小为W的窗口，依次滑过arr，
     * 返回每一次滑出状况的最大值
     * 例如： arr=[4,3,5,4,3,3,6,7] W = 3
     * 返回：[5,5,5,4,6,7]
     */
    public static void slideMaxNum(int[] arr,int W){
        slide(arr,0,W);
    }

    public static void slide(int[] arr,int L,int R){
        if(R > arr.length){
            return;
        }
        int max = 0;
        for(int i = L;i < R;i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        System.out.println(max);
        slide(arr, ++L, ++R);
    }

    /**
     * 获取子数组
     * 给定一个整型数组arr，和一个整数num
     * 某个arr中的子数组sub，如果想达标，必须满足：
     * sub中最大值 - sub中最小值 <= num
     * 返回arr中达标子数组的数量
     */
    public static void getSubMember(int[] arr,int num){
        int i = subMember(arr, num);
        System.out.println("子数组的数量："+i);
    }

    /**
     * 暴力解法
     * @param arr
     * @param num
     * @return
     */
    public static int subMember(int[] arr,int num){
        int count = 0;
        for(int i = 0;i<arr.length;i++){
            for(int j = i;j<arr.length;j++){
                int max = arr[i];
                int min = arr[j];
                for(int m = i;m<=j;m++){
                    if(max < arr[m]){
                        max = arr[m];
                    }
                    if(min > arr[m]){
                        min = arr[m];
                    }
                }
                if(max - min <= num){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 滑动窗口实现
     * @param arr
     * @param num
     */
    public static void getSubMember2(int[] arr,int num){
        LinkedList<Integer> qmin = Lists.newLinkedList();
        LinkedList<Integer> qmax = Lists.newLinkedList();
        int result = 0;
        int L = 0;
        int R = 0;
        while(L < arr.length){
            while(R < arr.length){
                while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]){
                    qmin.pollLast();
                }
                qmin.addLast(R);
                while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]){
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if(arr[qmax.getFirst()] - arr[qmin.getFirst()] > num){
                    break;
                }
                R++;
            }
            //R 是最后一个达标位置的再下一个，第一个违规的位置
            result += R - L;
            //将L位置过期
            if(qmin.peekFirst() == L){
                qmin.pollFirst();
            }
            if(qmax.peekFirst() == L){
                qmax.pollFirst();
            }
            L++;
        }
        System.out.println("子数组的数量："+result);
    }

}

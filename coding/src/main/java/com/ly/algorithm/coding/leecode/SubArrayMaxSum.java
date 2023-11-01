package com.ly.algorithm.coding.leecode;

/**
 * 给定一个整型数组，返回子数组的最大累加和
 * @author L1y
 * @create 2023/10/8 21:22
 * @desc
 **/
public class SubArrayMaxSum {

    /**
     * 准备两个遍历cur、max。遍历数组，cur进行累加，比较cur和max谁更大，更新max。max更新结束后，如果cur<0 ，那么将cur恢复成0。
     *
     * 	原理：假设现在已经在数组中找到了累加和最大，并且长度也最大的子数组。范围为i~j
     *
     * 			那么对于任意i~j之间的位置K，都有i~k >=0 , 因为如果i~k <0 ,那么累加和最大的子数组必然是k+1~j ，而不是i~j
     *
     * 			对于任意m~i-1位置，都有m~i-1 < 0 ,因为如果m~i-1 >=0 ,那么累加和最大并且最长的子数组必然是m~j,而不是i~j
     *
     * 			对于任意j+1~n位置，都有j+1~n <0,因为如果j+1~n >=0 ,那么累加和最大并且最长子数组必然是i~n,而不是i~j
     *
     * 			所以从i一直累加到j，必然能更新max
     * @param arr
     * @return
     */
    public static int maxSum(int[] arr){
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<arr.length;i++){
            cur+=arr[i];
            max = Math.max(cur,max);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

}

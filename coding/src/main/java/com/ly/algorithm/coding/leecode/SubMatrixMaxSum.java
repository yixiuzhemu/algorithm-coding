package com.ly.algorithm.coding.leecode;

/**
 * 给定一个整型矩阵，返回子矩阵的最大累加和
 * @author Ly
 * @create 2023/10/8 22:10
 * @desc
 **/
public class SubMatrixMaxSum {

    /**
     * 分别计算出0~0、0~1、0~2.。。。0~n范围的最大累加和是多少
     *
     * 	再计算出1~1，1~2、1~3.。。。。1~n范围的最大累加和是多少
     *
     * 	最后再计算出第n~n范围的最大累加和是多少
     *
     * 	对于0~0来说，就是计算第0行的子数组最大累加和
     *
     * 	对于0~1来说，将0行和1行累加（压缩数组技巧），然后再计算累加后的数组的子数组最大累加和
     *
     * 	最后求出的最大值就是答案
     * @param arr
     * @return
     */
    public static int maxSum(int[][] arr){
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for(int i = 0;i<arr.length;i++){
            int[] temp = new int[arr[i].length];
            int count = 0;
            while(i+count < arr.length){
                cur = 0;
                for(int j = 0;j<arr[i].length;j++){
                    temp[j] += arr[i+count][j];
                    cur += temp[j];
                    max = Math.max(max,cur);
                    cur = cur < 0 ? 0 : cur;
                }
                count++;
            }
        }
        return max;
    }

}

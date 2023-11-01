package com.ly.algorithm.coding.leecode;

import java.util.Stack;

/**
 * 	一共有n袋零食，第i袋零食体积为v[i]，总体积不超过背包容量的情况下，一共有多少种零食放法（总体积为0也算一种放法）
 * @author Ly
 * @create 2023/9/25 20:46
 * @desc
 **/
public class SnacksWays {

    /**
     * 	从左到右的尝试方法，来到任意i位置都有两个选择，要或者不要，遍历整个数组，当来到数组边界，rest都大于等于0，那么说明此时肯定有一种方式。如果来到任意位置，rest小于0，那么当前方式异常。
     * @param arr
     * @param w
     * @return
     */
    public static int snacksWays_1(int[] arr,int w){
        return process(arr,0,w);
    }

    public static int process(int[] arr,int index,int rest){
        if(index >= arr.length || rest < 0){
            return -1;
        }
        if(index == arr.length - 1){
            return 1;
        }
        int next2 = process(arr,index+1,rest - arr[index]);
        int next1 = process(arr,index+1,rest);
        return next1 + Math.max(next2,0);
    }


    /**
     * 	定义一个二维dp数组，行为数组arr的下标，列为剩余容量rest
     *
     * 		初始化dp数组，当行=arr.length-1时，此时所有列的结果都为1
     *
     * 		对于任意i,j位置，依赖于它下一行不拿的值 + （下一行拿的值和0的最大值）
     *
     * 		表达式为： dp[i][j] = Math.max(0,j - arr[i] >= 0 ? dp[i+1] [j - arr[i]] : -1) + dp[i+1] [j];
     *
     * 		最后返回dp [ 0 ] [ w ]位置的值
     * @param arr
     * @param w
     * @return
     */
    public static int snacksWays_2(int[] arr,int w){
       int[][] dp = new int[arr.length+1][w+1];
       //初始化index 位置来到arr.length-1,此时返回一种拿去方法
       for(int i = 0;i<dp[0].length;i++){
           dp[arr.length-1][i] = 1;
       }
       //当前任意i,j位置的值，都依赖于下一行不拿的值+下一行拿的值和0的最大值
       for(int i = arr.length-2;i>=0;i--){
           for(int j = 0;j< dp[i].length;j++){
               dp[i][j] = Math.max(0,j - arr[i] >= 0 ? dp[i+1][j - arr[i]] : -1) + dp[i+1][j];
           }
       }
       return dp[0][w];
    }
}

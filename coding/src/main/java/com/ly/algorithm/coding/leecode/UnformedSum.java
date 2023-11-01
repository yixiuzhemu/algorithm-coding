package com.ly.algorithm.coding.leecode;

import java.util.Arrays;

/**
 *
 * 给定一个正整数数组arr，其中所有的值都为整数，以下是最小不可组成和的概念
 *
 * 	把arr每个子集内的所有元素加起来会出现很多值，其中最小的记为min，最大的记为max，在区间[min,max]上，如果有数不可以被arr某一个子集相加得到，那么其中最小的那个数是arr的最小不可组成和，在区间[min,max]上，如果所有的数都可以被arr的某一个子集相加得到，那么max+1是arr的最小不可组成和，请写函数返回正整数数组arr的最小不可组成和。
 *
 * 	例如：arr=[3,2,5]，子集{2}相加产生2为min，子集{3,2,5}相加产生10为max，在区间[2,10]上，4,6,和9不能被任何子集相加得到，其中4是arr的最小不可组成和，arr=[1,2,4]，子集{1}相加产生1为min，子集{1,2,4}相加产生7为max，在区间[1,7]上，任何书都可以被子集相加得到，所以8是arr的最小不可相加组成和。
 *
 * @author Ly
 * @create 2023/10/26 22:20
 * @desc
 **/
public class UnformedSum {

    /**
     * 使用背包问题相同的解法，计算出数组的最小累加和Min和最大累加和Max，准备一张boolean类型的二维表booleanDp，行为数组长度，列为0~max，忽略所有0~min的列。
     *
     * 	隐藏条件：数组是正整数数组，所以子数组最小累加和是数组中的最小值，最大累加和就是整个数组的累加和
     *
     * 	初始化：第一行的数据可以直接直接填出
     *
     * 	对于任意i,j位置
     *
     * 	如果当前arr[i] ==j ，那么当前列为true
     *
     * 	如果上一行当前列已经为true了，那么当前行当前列为true booleanDp[i] [j] = booleanDp[i-1] [j]
     *
     * 	如果arr[i] 加上之前的累加和可以得到当前的累加和j，booleanDp[i-1] [j-arr[i]] = true.那么当前i,j位置也为true，即booleanDp[i] [j] = booleanDp[i-1] [j-arr[i]]
     *
     * 	最后判断N-1行是否全为true，如果存在false，那么返回为false的最小值
     * @param arr
     * @return
     */
    public static int unformedSum(int[] arr){
        int min = arr[0];
        int max = 0;
        for (int i : arr) {
            if(min > i){
                min = i;
            }
            max += i;
        }
        boolean[][] booleanDp = new boolean[arr.length][max+1];
        booleanDp[0][arr[0]] = true;
        for(int i = 1; i < arr.length;i++){
            for(int j = min;j<=max;j++){
                booleanDp[i][j] = arr[i] == j || booleanDp[i-1][j] || (j-arr[i] > 0 && booleanDp[i-1][j-arr[i]]);
            }
        }
        for(int i = min;i<=max;i++){
            if(!booleanDp[arr.length-1][i]){
                return i;
            }
        }
        return max+1;
    }


    /**
     * 进阶：如果已知正整数arr中肯定有1这个数，是否能更快的得到最小不可相加组成和
     * 如果数组中必然存在1，那么就意味 着min=1，max为数组累加和
     *
     * 	先将数组进行排序，准备一个range，range代表的是，可以通过数组计算出1~range中的所有数
     *
     * 	初始化，由于知道必然存在一个1，所以range初始值为1
     *
     * 	对于1~max中任意位置i，如果i位置的值为a
     *
     * 	如果a<=range+1，那么更新range，range=range+a (因为1~range所有数都能计算出来，所以遇上a时，必然能够得到range+a中的任何一个值，例如range=99，a=100，那么可以通过range中的数以及100计算出1~199中的任意一个值)
     *
     * 	如果a>range+1，那么最小不可累加和等于range+1(因为a大于range，那么必然无法加工出range+1,数组后面的值只会大于a，所以最小不可累加和就是等于range+1,例如a=50,range=30，此时无法加工出31，所以最后的结果为31)
     * @param arr
     * @return
     */
    public static int unformedSum_Plus(int[] arr){
        Arrays.sort(arr);
        int range = 1;
        int sum = 1;
        for(int i = 1;i<arr.length;i++){
            if(arr[i] <= range+1){
                range += arr[i];
            }else{
                return range+1;
            }
            sum += arr[i];
        }
        return sum+1;
    }

}

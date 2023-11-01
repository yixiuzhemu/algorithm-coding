package com.ly.algorithm.coding.leecode;

import java.util.Arrays;

/**
 * 给定一个有序的正数数组arr和一个正数aim，如果可以自由选择arr中的数字，想累加得到1~aim范围上所有的数，返回arr最少还缺几个数。
 *
 * 	例如：arr={1,2,3,7},range=15，想累加得到1~15范围上所有的数，arr还缺14这个数，所以返回arr={1，5，7}，range=15
 *
 * 	想累加得到1~15范围上所有的数，arr还缺2，4，所以返回2
 * @author Ly
 * @create 2023/10/27 20:52
 * @desc
 **/
public class MinPatches {

    /**
     * 如果想得到1~aim所有的结果，那么需要最大限度的使用每一个位置的数
     *
     * 	将数组进行排序，如果想使用0位置的数，假设0位置上的数为a=5
     *
     * 	如果要使用5，那么使用5之前，必须能计算出1-4上的所有数
     *
     * 	定义一个遍历range，初始值为0
     *
     * 	1~range没有包含1~4范围，此时range= range+（range+1）=1
     *
     * 	1~range没有包含1~4范围，此时range=range+（range+1) = 3
     *
     * 	1~range没有包含1~4范围，此时range=range+（range+1）=7
     *
     * 	1~range包含了1~4范围，此时range=range+a = 13
     *
     * 	.。。。。一直到遍历完整个数组
     *
     * 	即：
     *
     * 	如果对于任意i位置的数a，要想最大限度的使用它，之前必须能计算出1~a-1范围的所有数
     *
     * 	如果1~range 无法覆盖1~a-1的范围，那么range=range+range+1
     *
     * 	如果1~range覆盖了1~a-1的范围，那么range=range+a
     *
     * 	如果遍历到中途发现大于等于aim了，那么直接返回过程中增加的数
     *
     * 	如果遍历到最后发现也没达到aim，那么继续使用range=range+range+1的方式进行扩展
     * @param arr
     * @param aim
     * @return
     */
    public static int minPatches(int[] arr,int aim){
        Arrays.sort(arr);
        int range = 0;
        int ans = 0;
        for(int i = 0; i < arr.length;i++){
            while(range < arr[i]-1){
                range += (range+1);
                ans++;
                if(range >= aim){
                    return ans;
                }
            }
            range += arr[i];
            if(range >= aim){
                return ans;
            }
        }
        while(range < aim){
            range += (range+1);
            ans++;
        }
        return ans;
    }

}

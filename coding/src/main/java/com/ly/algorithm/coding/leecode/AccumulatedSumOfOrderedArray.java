package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 给定一个有序数组arr，给定一个正数aim
 *
 * 	返回累加和为aim的，所有不同二元组
 *
 * 	返回累加和为aim的，所有不同三元组
 * @author Ly
 * @create 2023/9/22 22:19
 * @desc
 **/
public class AccumulatedSumOfOrderedArray {

    public static List<String> binaryAccumulatedSumOfOrderedArray(int[] arr,int aim){
        return binaryAccumulatedSumOfOrderedArray(arr,aim,0,arr.length-1);
    }

    /**
     * 准备双指针L,R
     *
     * 		当L+R > aim 时，R--
     *
     * 		当L+R < aim 时，L++
     *
     * 		当L+R ==aim时，L++或R-- 或L++,R--，如果执行L++，如果判断arr[L-1] != arr[L] 此时收集答案
     * @param arr
     * @param aim
     * @param L
     * @param R
     * @return
     */
    public static List<String>  binaryAccumulatedSumOfOrderedArray(int[] arr,int aim,int L,int R){
        List<String> ans = Lists.newArrayList();
        while(L < R){
            if(arr[L] + arr[R] > aim){
                R--;
                continue;
            }
            if(arr[L] + arr[R] < aim){
                L++;
                continue;
            }
            if(arr[L] + arr[R] == aim){
                if(L-1 < 0 || arr[L-1] != arr[L]){
                    //记录此时的答案
                    ans.add(arr[L]+","+arr[R]);
                }
                L++;
            }
        }
        return ans;
    }

    /**
     * 遍历数组，来到任意i位置，求i位置之后的数组范围求二元组哪些位置的值相加等于aim-arr[i]
     *
     * 		当计算完i位置后，来到i+1位置，如果i+1位置的值等于i位置的值，那么直接跳过。（之前位置已经求完当前值的组合了）
     * @param arr
     * @param aim
     * @return
     */
    public static List<String>  ternaryAccumulatedSumOfOrderedArray(int[] arr,int aim){
        List<String> ans = Lists.newArrayList();
        for(int i = 0;i<arr.length-2;i++){
            if(i > 0 && arr[i] == arr[i-1]){
                continue;
            }
            List<String> doubleList = binaryAccumulatedSumOfOrderedArray(arr, aim - arr[i],i + 1, arr.length - 1 );
            if(CollectionUtils.isEmpty(doubleList)){
                continue;
            }
            for (String s : doubleList) {
                ans.add(arr[i]+","+s);
            }
        }
        return ans;
    }

}

package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 给定一个整型数组，长度为N，在这个数组上可以将其划分成很多块，并且同一个数不能被划分到两块里面，请返回异或和为0的最多划分块。
 * 例如：[3,2,1,,0,4,3,2,1,0,0,3,1,2,0,2,1,3]
 *
 * ​	异或和为0最多的划分块的分法为：[3,2,1] [0] [4] [3,2,1] [0] [0] [3,1,2] [0] [2,1,3]
 * @author Ly
 * @create 2023/10/21 9:39
 * @desc
 **/
public class MostEor {

    /**
     * 首先计算出任意i位置的异或和为sum
     *
     * ​	假设0~i 范围内最大划分块已经确定了，那么存在以下几种情况
     *
     * ​	i位置不包含在异或和为0的块中，那么dp[i] = dp[i-1]
     *
     * ​	i位置包含在异或和为0的块中，假设起始位置为j，那么j~i 的异或和为0，那么必然j位置是相对于i位置最早出现能使块异或和为0的位置，j~i中间任意位置到i的异或和都不为0.又因为整体的异或和为sum，所以0~j-1的异或和必然也是sum，所以此时dp[i] = dp[j-1] + 1
     * @param arr
     * @return
     */
    public static int mostEor(int[] arr){
        int[] dp = new int[arr.length];
        int eorSum = 0;
        //记录i位置之前出现过的异或和最晚出现的位置
        Map<Integer,Integer> map = Maps.newHashMap();
        map.put(0,-1);
        for(int i = 0;i<arr.length;i++){
            eorSum ^= arr[i];
            //如果上一次出现过当前的异或和，那么代表出现的位置j~i位置的异或和为0
            int j = i - 1;
            if(map.containsKey(eorSum)){
                Integer pre = map.get(eorSum);
                dp[i] = pre == -1 ? 1 : dp[pre]+1;
            }
            if(i > 0){
                dp[i] = Math.max(dp[i],dp[i-1]);
            }
            map.put(eorSum,i);
        }
        return dp[dp.length-1];
    }
}

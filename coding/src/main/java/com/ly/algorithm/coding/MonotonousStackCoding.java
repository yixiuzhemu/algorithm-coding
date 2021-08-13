package com.ly.algorithm.coding;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 * 一种特别设计的栈结构，为了解决如下的问题：
 * 给定一个可能含有重复值的数组arr，i位置的树一定存在如下两个信息
 * 1.arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪儿
 * 2.arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪儿
 * 如果想得到arr钟所有位置的两个信息，怎么能让得到信息的过程尽量快
 * 想用单调栈，要想办法把具体的问题转化为单调栈塑哦解决的原问题
 * @author Ly
 * @create 2021/8/12 17:07
 * @desc
 **/
public class MonotonousStackCoding {

    /**
     * 单调栈-获取左边以及右边比当前值小的位置
     * @param arr
     * @return
     */
    public  static int[][] getNearLess(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stacks = new Stack<>();
        for(int i = 0;i<arr.length;i++){

            while(!stacks.isEmpty() &&  arr[stacks.peek().get(0)] > arr[i]){
                final List<Integer> pop = stacks.pop();
                Integer leftIndex  = stacks.isEmpty() ? -1 : stacks.peek().get(stacks.peek().size() - 1) ;
                for (Integer index : pop) {
                    res[index][0] = leftIndex;
                    res[index][1] = i;
                }
            }
            if(!stacks.isEmpty() && arr[stacks.peek().get(0)] == arr[i]){
                stacks.peek().add(i);
            }
            if(stacks.isEmpty() || (!stacks.isEmpty() && arr[stacks.peek().get(0)] < arr[i])){
                List<Integer> indexs = Lists.newArrayList();
                indexs.add(i);
                stacks.push(indexs);
            }
        }
        while(!stacks.isEmpty()){
            List<Integer> pop = stacks.pop();
            Integer leftIndex = stacks.isEmpty()? -1 : stacks.peek().get(stacks.peek().size() - 1);
            for (Integer index : pop) {
                res[index][0] = leftIndex;
                res[index][1] = -1;
            }
        }
        return res;
    }

    /**
     * 单调栈-获取最大和
     * 给定一个只包含正整数的数组arr，arr钟任何一个子数组sub，一定都可以算出(sub累加和) * （sub中的最小值）是什么
     * 那么所有子数组钟，这个值最大是多少
     * 如果把每一个位置当作当成最小值，以后的每一个位置的值，都不允许比当前值小，
     * 分别向两边扩展，得到一组数，计算该数的和
     * 再找出最大值，那么这个值肯定是最大值
     */

    public static int getMaxSum(int[] arr){
        int[][] nearLess = getNearLess(arr);
        int max = 0;
        for(int i = 0;i<nearLess.length;i++){
            int minIndex = nearLess[i][0]  != -1 && nearLess[i][0] < i ? nearLess[i][0] + 1 : i;
            int maxIndex = nearLess[i][1] != -1 && nearLess[i][1] > i ? nearLess[i][1] - 1 : i;
            int sum = 0;
            int min = arr[minIndex];
            for(int j = minIndex;j<=maxIndex ;j++){
                sum += arr[j];
                if(min > arr[j]){
                    min = arr[j];
                }
            }
            int count = sum * min;
            if(max < count){
                max = count;
            }
        }
        return max;
    }

}

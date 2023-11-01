package com.ly.algorithm.coding.leecode;

/**
 * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为做部分，剩下的作为右部分。但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，左部分最大值减去右部分最大值的绝对值
 * @author Ly
 * @create 2023/9/19 21:44
 * @desc
 **/
public class SplitArray {

    /**
     * 	对于任意一个数组，因为要求分割时，左右两边都必须有值，所以总的分割方式为N-1
     *
     * 	但是实际上只需要找到整个数组最大值max
     *
     * 	如果max在左部分，那么左部分的最大值一定是max，如果要让最后的结果尽可能大，那么就需要让右部分的值尽可能小，而最小的情况只可能在N-1位置（不可能会存在比N-1还小的右部分）
     *
     * 	如果max在右部分，那么右部分的最大值一定是max，如果要让最后的结果尽可能大，那么就需要让做部分的值尽可能小，而最小的情况只可能在0位置（不可能会存在比0位置还小的左部分）
     *
     * 	所以实际上的答案就等于 max-Math.max(0,N-1);
     * @param arr
     * @return
     */
    public static int splitArray(int[] arr){

        int max = 0;
        for(int i = 0;i<arr.length-1;i++){
            max = Math.max(max,arr[i]);
        }
        return Math.max(max,Math.max(arr[0],arr[arr.length-1]));
    }

}

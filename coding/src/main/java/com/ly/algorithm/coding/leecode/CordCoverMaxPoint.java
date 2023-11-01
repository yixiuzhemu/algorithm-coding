package com.ly.algorithm.coding.leecode;

/**
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置，给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点（绳子的边缘碰到X轴上的点，也算盖住）
 * @author Ly
 * @create 2023/9/9 9:35
 * @desc
 **/
public class CordCoverMaxPoint {

    /**
     * 暴力解
     * @param arr
     * @param K
     * @return
     */
    public static int maxPoint(int[] arr,int K){
        int res = 1;
        for(int i = 0;i<arr.length;i++){
            int nearest = nearestIndex(arr,i,arr[i]+K);
            res = Math.max(res,nearest - i + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr,int L,int value){
        //从L位置往后找<=value的值
        int R = arr.length - 1;
        int index = L;
        while(L <= R){
            int mid = L + ((R-L)>>1);
            if(arr[mid] > value){
                R = mid-1;
            }else{
                index = mid;
                L = mid + 1;
            }
        }
        return index;

    }


    public static int maxPoint_V2(int[] arr,int K){
        int L = 0;
        int R = 0;
        int ans = 1;
        int N = arr.length;
        while(L < N){
            while(R < N && arr[R] - arr[L] <= K){
                R++;
            }
            ans = Math.max(ans,R - (L++));
        }
        return ans;
    }
}

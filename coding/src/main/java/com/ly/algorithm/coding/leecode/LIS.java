package com.ly.algorithm.coding.leecode;

/**
 * 最长递增子序列问题的O(N*logN)的解法
 * @author Ly
 * @create 2023/10/7 18:41
 * @desc
 **/
public class LIS {

    /**
     * O(N^2)解法：对于任意i位置求出以它作为结尾时，最长递增子序列是多长
     *
     * 	例如：3，1，4，2，3
     *
     * 	当i=0时，此时只有它自己，所以长度为1
     *
     * 	当i=1时，去左边找比它小的数有哪些，没有，所以长度也为1
     *
     * 	当i=2时，去左边找比他小的数有哪些，有3和1，取其中值最大的再加1，所以长度为2
     *
     * 	当i=3时，去左边找比他小的数有哪些，只有1，1所在位置的长度再加1，所以长度为2
     *
     * 	当i=4时，去左边找比他小的数有哪些，有1和2，取其中的最大值2，再加1，所以长度为3
     * @param arr
     * @return
     */
    public static int getLis(int[] arr){
        int[] dp = new int[arr.length];
        int ans = 0;
        for(int i = 0;i<arr.length;i++){
            int max = 0;
            for(int j = i-1;j>=0;j--){
                if(arr[i] > arr[j]){
                    max = Math.max(max,dp[j]);
                }
            }
            dp[i] = max+1;
            ans = Math.max(dp[i],ans);
        }
        return ans;
    }

    /**
     * O(N*logN)解法：
     *
     * 		定义数组dp，记录对于任意i位置求出以它作为结尾时，最长递增子序列是多长
     *
     * 		定义数组ends：记录在所有长度为i+1的递增子序列中，最小结尾是多少
     *
     * 		定义right：表示ends数组的有效区
     *
     * 		例如：3，1，4，2，3
     *
     * 		当i=0时，dp[0]=1, ends[0]=3,right=0
     *
     * 		当i=1时，找到ends数组中大于当前值的最左侧的位置，此时将他的值替换,再看当前位置左侧有多少个数，dp[1]=1,ends[0]=1
     *
     * 		当i=2时，找到ends数组中大于当前值的最左侧的位置，没有，那么将ends的有效区扩充，right=1,ends[1]=4,dp[2]=2
     *
     * 		当i=3时，找到ends数组中大于当前值最左侧的位置，此时将他的值替换，再看当前位置左侧有多少个数，dp[3]=2,ends[1]=2,right=1
     *
     * 		当i=4时，找到ends数组中大于当前值最左侧的位置，没有，那么将ends的有效区扩充，right=2，ends[2]=3,dp[4]=3
     *
     * 		所以对于ends数组，在调整的过程中，始终保证单调递增，所以对于当前值最左侧的位置可以直接通过二分的方法进行查找，所以总的时间复杂度为O(N*logN)
     * @param arr
     * @return
     */
    public static int getLis_V2(int[] arr){
        int[] ends = new int[arr.length];
        int[] dp = new int[arr.length];
        int l = 0;
        int r = 0;
        int m = 0;
        int right = 0;
        dp[0] = 1;
        ends[0] = arr[0];
        int ans = 0;
        for(int i = 1;i<arr.length;i++){
            l = 0;
            r = right;
            //二分查找最左侧大于当前值的位置
            while(l <= r){
                m = (l+r)/2;
                if(arr[i] > ends[m]){
                    l = m+1;
                }else{
                    r = m-1;
                }
            }
            //如果一直到right之外都没有找到，那么此时需要扩充有效区，此时l会大于right
            right = Math.max(right,l);
            ends[l] = arr[i];
            dp[i] = l+1;
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

}

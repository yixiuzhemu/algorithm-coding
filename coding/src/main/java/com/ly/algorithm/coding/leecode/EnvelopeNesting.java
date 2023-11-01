package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 每个信封都有长和宽两个维度的数据，A信封如果想套在B信封里面，A信封必须在长和宽上都小于B信封才行。如果给你一批信封，返回最大的嵌套层数。
 * @author Ly
 * @create 2023/10/7 20:55
 * @desc
 **/
public class EnvelopeNesting {

    /**
     * 将信封按照一维数据（长）从小到大排序，如果一维数据相同，那么二维数据（宽）按照从大到小排序。
     *
     * 	排序完成后，取出所有二维数据，最后求出最长子序列就是最大的嵌套层数
     *
     * 	例如：[3,2] [2,4] [3,5] [1,2] [2,3]
     *
     * 	排序后：[1,2] [2,4] [2,3] [3,5] [3,2]
     *
     * 	最后提取出的数组为[2,4,3,5,2]
     *
     * 	因为对于排序后的结果，一定能知道的是，对于提取出的数组，
     * 	如果对于任意i位置，i位置的值大于i-1位置的值，因为一维数据从小到大排列，二维数据从大到小排列，
     * 	所以出现i位置的值大于i-1位置的值，那么必然i位置的一维数据是大于i-1位置的一维数据的值的。
     * 	所以i位置一定能装下i-1位置的信封。所以最大递增子序列就是最大嵌套层数。
     * @param arr
     * @return
     */
    public static int maxNesting(int[][] arr){
        List<Envelope> envelopes = Lists.newArrayList();
        for (int[] ints : arr) {
            envelopes.add(Envelope.builder().l(ints[0]).w(ints[1]).build());
        }
        Collections.sort(envelopes);
        //提取出所有的宽
        int[] newArr = new int[envelopes.size()];
        for (int i = 0;i<envelopes.size();i++) {
            newArr[i] = envelopes.get(i).w;
        }
        //使用最大递增子序列O(N*logN)的解法
        int right = 0;
        int[] dp = new int[newArr.length];
        int[] ends = new int[newArr.length];
        int l = 0;
        int m = 0;
        int r = 0;
        dp[0] = 1;
        int ans = 0;
        ends[0] = newArr[0];
        for(int i = 0;i<newArr.length;i++){
            l = 0;
            r = right;
            while(l <= r){
                m = (l+r)/2;
                if(newArr[i] > ends[m]){
                    l = m+1;
                }else{
                    r = m-1;
                }
            }
            right = Math.max(right,l);
            ends[l] = newArr[i];
            dp[i] = l+1;
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    @Data
    @Builder
    public static class Envelope implements Comparable<Envelope> {
        private int w;

        private int l;

        @Override
        public int compareTo(Envelope o) {
            if(this.l == o.l){
                return o.w - this.w;
            }
            return this.l - o.l;
        }
    }
}

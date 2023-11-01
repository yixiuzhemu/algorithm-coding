package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 给定两个数组arrx和arry，长度都为N，代表二维平面上有N个点，第i个点的x坐标和y坐标分别为arrx[i] 和arr[i]，返回求一条直线最多能穿过多少个点。
 * @author Ly
 * @create 2023/10/31 20:55
 * @desc
 **/
public class MaxPointsOneLine {

    /**
     * 由两个数组，组成一个点 的集合（x,y）数组，求出直线分别必须经过每个点的答案
     *
     * 	对于任意点a，假设存在另一点b，有以下4种情况
     *
     * 	a、b两点重合
     *
     * 	a、b两点共y
     *
     * 	a、b两点共x
     *
     * 	a、b两点共斜率 ：斜率可以使用map存储，对于斜率的计算(ay-by)/(ax-bx) ，由于可能会存在精度问题，所以可以计算最大公约数，化简后转换为字符串(ay-by)_(ax-bx) 作为key。
     *
     * 	所以最后的结果等于后面三种情况的最大值加上情况一的结果
     *
     * 	而对于点a，如果已经计算过点a的结果，那么后续的点都不需要再与a点进行计算
     * @param arrx
     * @param arry
     * @return
     */
    public static int maxPoints(int[] arrx,int[] arry){
        int N = arrx.length;
        int ans = 0;
        Map<String,Integer> sameKMap = Maps.newHashMap();
        for(int i = 0;i < N;i++){
            int same = 0;
            int sameX = 0;
            int sameY = 0;
            int maxSameK = 0;
            for(int j = i+1;j<N;j++){
                if(arrx[i] == arrx[j] && arry[i] == arry[j]){
                    same++;
                }else if(arrx[i] == arrx[j]){
                    sameX++;
                }else if(arry[i] == arry[j]){
                    sameY++;
                }else{
                    //存在斜率
                    int m = arry[i] - arry[j];
                    int n = arrx[i] - arrx[j];
                    //计算mn的最大公约数
                    int gcd = gcd(m,n);
                    String key = (m/gcd) +"_" + (n/gcd);
                    Integer sameK = sameKMap.get(key);
                    sameK = sameK == null ? 1 : sameK+1;
                    maxSameK = Math.max(maxSameK,sameK);
                }
            }
            ans = Math.max(ans,Math.max(Math.max(sameX,sameY),maxSameK)+same);
        }
        return ans;
    }

    public static int gcd(int m,int n){
        return n == 0? m : gcd(n,m%n);
    }
}

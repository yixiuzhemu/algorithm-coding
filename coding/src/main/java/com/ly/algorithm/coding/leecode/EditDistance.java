package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @author Ly
 * @create 2023/10/10 19:59
 * @desc
 **/
public class EditDistance {

    private static int rc;

    private static int ic;

    private static int dc;

    public static int editDistance(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return process(chars1, chars2, chars1.length-1,chars2.length-1);
    }

    public static int editDistance_V2(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        return process(chars1, chars2);
    }

    public static int process(char[] chars1,char[] chars2,int i ,int j){
        //如果其中一个数组遍历完了，那么返回另一个数组剩下的，需要进行删除或者新增
        if(i == 0){
            return j*ic;
        }
        if(j == 0){
            return i*dc;
        }
        if(chars1[i] == chars2[j]){
            return process(chars1,chars2,i-1,j-1);
        }
        int ans = process(chars1,chars2,i-1,j-1)+rc;
        //删除当前元素
        ans = Math.min(process(chars1,chars2,i-1,j)+dc,ans);
        //新增当前元素
        ans = Math.min(process(chars1,chars2,i,j-1)+ic,ans);
        return ans;
    }

    public static int process(char[] chars1,char[] chars2){
        int[][] dp = new int[chars1.length+1][chars2.length+1];
        dp[0][0] = 0;
        for(int i = 1;i<=chars1.length;i++){
            dp[i][0] = dp[i-1][0]+dc;
        }
        for(int j = 1;j<=chars2.length;j++){
            dp[0][j] = dp[0][j-1]+ic;
        }
        for(int i = 1;i <= chars1.length;i++){
            for(int j = 1;j<=chars2.length;j++){
                //分情况讨论
                //1、如果当前位置相等，那么不用进行操作
                if(chars1[i-1] == chars2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //2、如果当前位置不相等，直接进行替换操作
                    dp[i][j] = dp[i-1][j-1]+rc;
                    //3、进行删除操作，那么就是保证0~i-1 位置与0~j位置能对齐
                    dp[i][j] = Math.min(dp[i-1][j]+dc,dp[i][j]);
                    //4、0~i 与 0~j-1 对齐，对i位置进行插入操作
                    dp[i][j] = Math.min(dp[i][j-1]+ic,dp[i][j]);
                }
            }
        }
        return dp[chars1.length][chars2.length];
    }


    public static void main(String[] args) {
        String str1 = "abcaefaefx";
        String str2 = "adc3araewasf";
        rc = 1;
        ic = 1;
        dc = 1;
        System.out.println(editDistance(str1,str2));
        System.out.println(editDistance_V2(str1,str2));
    }

}

package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

/**
 * 给定两个字符串s1和s2，问s2最少删除多少个字符可以成为s1的子串
 *
 * 	比如s1=”abcde“ ，s2=”axbc“ ，返回1，s2删除'x'就是s1的子串
 * @author Ly
 * @create 2023/10/11 20:45
 * @desc
 **/
public class DeleteMinCost {

    /**
     * 生成s2的所有子序列，找到其中是s1子串（KMP算法）的子序列，总的时间复杂度为O(N*2^M)
     * @param s1
     * @param s2
     * @return
     */
    public static int minCost_v1(String s1,String s2){
        List<String> s2Subs = Lists.newArrayList();
        process(s2.toCharArray(),0,"",s2Subs);
        s2Subs.sort(Comparator.comparingInt(a->-a.length()));
        for (String s2Sub : s2Subs) {
            //indexOf底层和KMP算法的代价几乎一样，也可以用KMP算法代替
            if(s1.indexOf(s2Sub) != -1){
                return s2.length() - s2Sub.length();
            }
        }
        return s2.length();
    }

    /**
     * 获取str字符串的子序列
     * @param str2
     * @param index
     * @param path
     * @param list
     */
    public static void process(char[] str2, int index, String path, List<String> list){
        if(index == str2.length){
            list.add(path);
            return;
        }
        //对于每一个位置，都可以要或者不要
        process(str2,index+1,path+str2[index],list);
        process(str2,index+1,path,list);
    }

    /**
     *生成s1所有的子串（N^2），计算每一个子串和s2字符串的编辑距离（只有插入行为）（每次生成K*M大小的二维数组 ），K的平均长度可以认为是N/2，所以总的时间复杂度为O(N^3 * M)
     * @param s1
     * @param s2
     * @return
     */
    public static int minCost_v2(String s1,String s2){
       int ans = Integer.MAX_VALUE;
       char[] str2 = s2.toCharArray();
       for(int start = 0;start < s1.length();start++){
           for(int end = start + 1;end <= s1.length();end++){
               //子串要插入多少个字符才能变成str2，等效于str2要删掉多少个字符才能变成str1的子串
               ans = Math.min(ans,editDistance(s1.substring(start,end).toCharArray(),str2));
           }
       }
        //以每一个位置作为开头
        return ans;
    }

    public static int editDistance(char[] chars1, char[] chars2){
        //str1只有插入代价
        int[][] dp = new int[chars1.length][chars2.length];
        if(chars1[0] != chars2[0]){
            dp[0][0] = Integer.MAX_VALUE;
        }
        //与原本的编辑距离算法不同的是，这里不需要计算rc和dc，所以不需要预留0位置，直接从字符位置开始判断
        for(int i = 1;i<chars1.length;i++){
            dp[i][0] = Integer.MAX_VALUE;
        }
        //第0行，可能会出现从第一个字符开始一直添加元素，可以最后等于chars2，如果其中某个位置无法通过插入获得，那么后续都无法再通过插入获得
        for(int i = 1;i<chars2.length;i++){
            //如果中途出现了0位置的字符，那么整体是可以通过插入变成str2的
            if(chars1[0] == chars2[i] || dp[0][i-1] != Integer.MAX_VALUE){
                dp[0][i] = i;
            }else{
                dp[0][i] = Integer.MAX_VALUE;
            }
        }
        for(int i = 1;i<chars1.length;i++){
            for(int j = 1;j<chars2.length;j++){
                dp[i][j] = Integer.MAX_VALUE;
               //i位置和j位置要相等，只有2中方案
                //1、0~ i 对应0~j-1 ，再插入一个j位置的值
                //2、0~i 对应 0~j
                //对应插入操作
                if(dp[i][j-1] != Integer.MAX_VALUE){
                    dp[i][j] = dp[i][j-1]+1;
                }
                if(chars1[i] == chars2[j] && dp[i-1][j-1] != Integer.MAX_VALUE){
                    dp[i][j] = Math.min(dp[i][j],dp[i-1][j-1]);
                }
            }
        }
        return dp[chars1.length-1][chars2.length-1];
    }

    /**
     * 对于方法2来说，遍历子串的过程，实际上对于生成的dp数组，对于同一个开头，每次编辑就是在上一次的基础上再加一行，所以dp可以进行复用。例如：a -> abcde 时，当子串变为ab -> abcde，此时只需要将二维dp数组再增加一行即可。通过这种优化方式，子串生成时间复杂度为O(N^2) ，而对于editDistance方法， 由于不需要每次再去完整遍历dp数组，所以可以认为时间复杂度为O(M)(chars2的长度，每次只需要遍历一行。)，所以总的时间复杂度为O(N^2 * M)
     * @param str1
     * @param str2
     * @return
     */
    public static int minCost_v3(String str1,String str2){
        char[] chars2 = str2.toCharArray();
        char[] chars1 = str1.toCharArray();
        int ans = Integer.MAX_VALUE;
        for(int i = 0;i<str1.length();i++){
            int[] dp = new int[chars2.length];
            dp[0] = chars1[i] == chars2[0] ? 0 : Integer.MAX_VALUE;
            for(int k = 1; k < dp.length;k++){
                dp[k] = chars1[i] == chars2[k] || dp[k-1]  != Integer.MAX_VALUE ? k : Integer.MAX_VALUE;
            }
            for(int j=i+2;j<=str1.length();j++){
                dp = editDistance(str1.substring(i,j).toCharArray(),chars2,dp);
            }
            ans = Math.min(ans,dp[dp.length-1]);
        }
        return ans;
    }


    public static int[] editDistance(char[] chars1,char[] chars2,int[] lastDp){
        int[] dp = new int[chars2.length];
        if(chars1[chars1.length-1] != chars2[0]){
            dp[0] = Integer.MAX_VALUE;
        }
        for(int i = 1; i < chars2.length;i++){
            dp[i] = Integer.MAX_VALUE;
            if(dp[i-1] != Integer.MAX_VALUE){
                dp[i] = dp[i-1]+1;
            }
            if(chars1[chars1.length-1] == chars2[i] && lastDp[i] != Integer.MAX_VALUE){
                dp[i] = Math.min(lastDp[i-1],dp[i]);
            }
        }
        return dp;
    }
}

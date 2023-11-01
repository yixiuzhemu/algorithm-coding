package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 *
 * 在一个字符串中找到没有重复字符串子串中最长的长度
 *
 * 	例如：
 *
 * 	abcabcbb没有重复字符的最长字串是abc，长度为3
 *
 * 	bbbbb，答案是b，长度为1
 *
 * 	pwwkew，答案是wke、kew，长度是3
 *
 * 	要求：答案必须是子串，”pwke"是一个子序列不是一个子串
 * @author Ly
 * @create 2023/10/27 21:11
 * @desc
 **/
public class MaxNoRepeatSubStr {

    /**
     * 暴力解：遍历数组，记录沿途出现过的字符的位置，一旦遇到字符重复，取出之前当前字符的位置，记录当前长度，再从之前出现该字符的位置index+1位置继续往后找。
     * @param str
     * @return
     */
    public static int maxNoRepeatSubStr(String str){
        char[] chars = str.toCharArray();
        Map<Character,Integer> map = Maps.newHashMap();
        int ans = 0;
        for(int i = 0;i<chars.length;i++){
            Integer before = map.get(chars[i]);
            if(before == null){
                map.put(chars[i],i);
            }else{
                ans = Math.max(map.size(),ans);
                i = before;
                map.clear();
            }
        }
        return ans;
    }

    /**
     * 动态规划：构建一张一维dp表，任意i位置的含义是，必须以i位置字符结尾的最长不重复前缀子串是多长。
     *
     * 	需要知道的信息：
     *
     * 	i位置字符上次出现的位置j
     *
     * 	以及i-1位置的答案，如果i-1位置的答案为m
     *
     * 	如果m>j，那么说明i位置的字符到j位置之前就会因为i-1位置重复导致无法推到j位置
     *
     * 	如果m<j，那么说明i位置的字符到j位置是会遇到与自身重复的字符
     *
     * 	想知道上一次i位置的字符出现的位置，此时需要准备一张map表，记录字符出现的位置信息
     *
     * 	所以dp[i] = Math.min(dp[i-1],map.get(arr[i]))
     * @param str
     * @return
     */
    public static int maxNoRepeatSubStr_V2(String str){
        char[] chars = str.toCharArray();
        Map<Character,Integer> map = Maps.newHashMap();
        int[] dp = new int[chars.length];
        dp[0] = 0;
        map.put(chars[0],0);
        int ans = 0;
        for(int i = 1; i < dp.length;i++){
            Integer index = map.get(chars[i]);
            if(index == null){
                dp[i] = dp[i-1];
            }else{
                dp[i] = Math.max(index+1,dp[i-1]);
            }
            ans = Math.max(i-dp[i]+1,ans);
            map.put(chars[i],i);
        }
        return ans;
    }

}

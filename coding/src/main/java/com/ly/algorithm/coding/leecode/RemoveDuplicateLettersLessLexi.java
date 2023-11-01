package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Ly
 * @create 2023/10/30 20:46
 * @desc
 **/
public class RemoveDuplicateLettersLessLexi {

    public static String remove(String str){
        Map<Character,Integer> countMap = Maps.newHashMap();
        char[] chars = str.toCharArray();
        for(int i = 0;i <chars.length;i++){
            Integer count = countMap.get(chars[i]);
            if(count == null){
                countMap.put(chars[i],1);
            }else{
                countMap.put(chars[i],count+1);
            }
        }
        return  remove(chars,countMap.keySet().size(),0, new StringBuilder());
    }

    public static String remove(char[] chars,int limit, int L, StringBuilder builder){
        if(limit == builder.length()){
            return builder.toString();
        }
        Map<Character,Integer> countMap = Maps.newHashMap();
        for(int i = L;i <chars.length;i++){
            //已经挑选过的字符不再统计
            if(builder.indexOf(String.valueOf(chars[i])) > -1){
                continue;
            }
            Integer count = countMap.get(chars[i]);
            if(count == null){
                countMap.put(chars[i],1);
            }else{
                countMap.put(chars[i],count+1);
            }
        }
        Character minChar = null;
        for(int i = L;i < chars.length;i++){
            if(builder.indexOf(String.valueOf(chars[i])) > -1){
                continue;
            }
            Integer count = countMap.get(chars[i]);
            if(minChar == null || minChar > chars[i]){
                minChar = chars[i];
            }
            if(count > 1){
                countMap.put(chars[i],count-1);
                continue;
            }
            //如果某个词频等于0，那么在当前位置往前找字典序最小的字符
            for(int j = L;j <= i ;j++){
                if(builder.indexOf(String.valueOf(chars[j])) > -1){
                    continue;
                }
                if(minChar == chars[j]){
                    builder.append(chars[j]);
                    return remove(chars,limit,j+1,builder);
                }
            }
        }
        return null;
    }

}

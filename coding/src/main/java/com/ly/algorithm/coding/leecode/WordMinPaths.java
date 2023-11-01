package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

/**
 *
 * 给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to，list中没有重复字符串，所有字符串都是小写
 *
 * 	规定：start每次只能改变一个字符，最终的目标时彻底变成to，但是每次变成的新字符串必须在list中存在，请返回所有最短的变换路径
 *
 * 	例如：start="abc"  end="cab" list = {"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
 *
 * 	转换路径的方法有很多种，但所有最短的转换路径如下
 *
 * 	abc->abb->aab->cab
 *
 * 	abc->abb->cbb->cab
 *
 * 	abc->cbc->cac->cab
 *
 * 	abc->cbc->cbb->cab
 * @author Ly
 * @create 2023/10/15 13:18
 * @desc
 **/
public class WordMinPaths {

    public static List<List<String>> minPaths(List<String> paths,String start,String end){
        //计算paths路径中，所有距离相互为1的字符
        List<List<String>> res = Lists.newArrayList();
        LinkedList<String> pathList = Lists.newLinkedList();
        Map<String, List<String>> nextsMap = getNexts(paths, start.length());
        Map<String, Integer> distanceMap = getDistance(start, nextsMap);
        getShortestPaths(start,end,distanceMap,nextsMap,pathList,res);
        return res;
    }

    /**
     * 因为每次只能变动一个字符，所以先找出所有字符替换距离为1的其他字符
     * 这种类型的问题使用什么方法，取决于N^2 * K 和 N * K ^ 2 谁更小
     * @param paths
     * @param K
     * @return
     */
    public static Map<String,List<String>> getNexts(List<String> paths,int K){
        int N = paths.size();
        if(N * N * K > N * K * K){
            return getNexts_V2(paths);
        }
        return getNexts_V1(paths);
    }


    /**
     * 因为每次只能变动一个字符，所以先找出所有字符替换距离为1的其他字符
     *
     * 	如果是通常写法，那么需要两层循环（N^2），再加上字符长度的遍历（K），所以总的时间复杂度为o(N^2 * K)
     * @param paths
     * @return
     */
    public static Map<String,List<String>> getNexts_V1(List<String> paths){
        Map<String,List<String>> res = Maps.newHashMap();
        for (int i = 0; i < paths.size();i++) {
            String cur = paths.get(i);
            List<String> nexts = Lists.newArrayList();
            res.put(cur,nexts);
            for(int j = i+1;j < paths.size();j++){
                String next = paths.get(j);
                int count = 0;
                for(int k = 0; k < cur.length() ; k++){
                    if(cur.charAt(k) != next.charAt(k)){
                        count++;
                    }
                }
                if(count == 1){
                    nexts.add(next);
                }
            }
        }
        return res;
    }

    /**
     * 	对于当前问题，因为字符都是小写，所以可以存在一个优化，对于任意字符，从第一个位置开始一直到最后一个字符，每次变动一个位置的字符，从a->z ，判断此时集合中是否存在，如果存在，那么这个字符和当前字符就是替换距离为1的字符。
     *
     * 	例如：abc
     *
     * 	从1位置开始：bbc、cbc、dbc、。。。。。、zbc
     *
     * 	从2位置开始：aac、acc、adc、。。。。。、azc
     *
     * 	从3位置开始：aba、abb、abd、。。。。、abz
     *
     * 	对于该优化，每个字符都需要从a-z的枚举，所以总的时间复杂度为25*K，对于hashSet来说，get方法的时间复杂度为O(1)，但是在该问题中，字符的长度是不可忽略的，所以get方法的时间复杂度为O(K)，所以总的时间复杂度为O(N * K^2) .
     * @param paths
     * @return
     */
    public static Map<String,List<String>> getNexts_V2(List<String> paths){
        Map<String,List<String>> res = Maps.newHashMap();
        Set<String> containInfos = Sets.newHashSet(paths);
        for (String path : paths) {
            res.put(path,getNext(path, containInfos));
        }
        return res;
    }

    private static List<String> getNext(String path,Set<String> containInfos){
        List<String> res = Lists.newArrayList();
        char[] chs = path.toCharArray();
        for(char i = 'a';i<='z';i++){
            for(int j = 0;j<path.length();j++){
                if(i == chs[j]){
                    continue;
                }
                char temp = chs[j];
                chs[j] = i;
                if(containInfos.contains(String.valueOf(chs))){
                    res.add(String.valueOf(chs));
                }
                chs[j] = temp;
            }
        }
        return res;
    }

    /**
     * 当得到这样一个图之后，再使用宽度优先遍历，计算出从start字符开始到其他字符的所有距离
     * @param start
     * @param nexts
     * @return
     */
    private static Map<String,Integer> getDistance(String start,Map<String,List<String>> nexts){
        Map<String,Integer> distances = Maps.newHashMap();
        distances.put(start,0);
        Queue<String> queue = Lists.newLinkedList();
        queue.add(start);
        //保证不会再回到已经计算过的字符
        Set<String> set = Sets.newHashSet();
        set.add(start);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if(!set.contains(next)){
                    distances.put(next,distances.get(cur) + 1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    /**
     * 收集所有最短距离
     * @param cur 现在来到的位置
     * @param to 目的地
     * @param distanceMap 距离map
     * @param nextsMap 邻居表
     * @param pathList 路径集合
     * @param res
     * @return
     */
    private static void getShortestPaths(String cur,String to,Map<String, Integer> distanceMap,Map<String,List<String>> nextsMap,LinkedList<String> pathList,List<List<String>> res){
        //获取最小距离
        pathList.add(cur);
        if(to.equals(cur)){
            res.add(new LinkedList<>(pathList));
        }else{
            for (String next : nextsMap.get(cur)) {
                //只有当邻居与当前字符串的距离严格相差1，才走下去，（因为有可能会回到start的位置）
                if(distanceMap.get(next) == distanceMap.get(cur) + 1){
                    getShortestPaths(next,to,distanceMap,nextsMap,pathList,res);
                }
            }
        }
        //弹出尾元素，遍历其他路径
        pathList.pollLast();
    }
}

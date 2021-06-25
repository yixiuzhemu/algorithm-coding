package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * 贪心算法
 * 1.最自然智慧的算法
 * 2.用一种局部最功利的标准，总是做出在当前看来是最好的选择
 * 3.难点在于证明局部最功利的标准可以得到全局最优解
 * 4.对于贪心算法的学习主要以增加阅历和经验为主
 *
 * @author Ly
 * @create 2021/6/25 16:39
 * @desc
 **/
public class GreedyAlgorithmCoding {

    /**
     * 给定一个由字符串组成的数组strs
     * 必须把所有字符串拼接起来
     * 返回所有可能的拼接结果钟，字典序最小的结果
     * 1.将strs中的所有字符串进行排序，再从小到大拼接起来，此时结果不是最小，反例 {ba,b} 这种情况拼接结果 bba，而最小的情况 bab
     * 2.将strs中的字符串两两结合，如果 a*b <= b * a  那么a放在前面，否则 b放在前面
     *
     * 证明第二种方法 具有排序传递性
     * 如果 通过  a*b <= b*a  以及  b*c <= c*b 可以推导出 a*c <= c*a  那么就可以肯定  a,b,c  的顺序一定是最小的字符串
     * 假设 abc 为 26进制的一个正数，那么 a*b (a和b拼接) = a * 26^(b长度)+b
     * 再设 方法m ，传入值可以自动获得结果  即m(b) = 26^(b长度）
     *那么等式变成 1.a*m(b)+b <= b*m(a)+a  2.b*m(c)+c <= c*m(b)+b
     *将等式1左右两边同时减 b再乘以c  3.a*m(b)*c <= b*m(a)*c + a*c - b*c
     * 将等式2左右两边同时减 b再乘以a  4.b*m(c)*a + c*a - b*a <= c*m(b)*a
     * 由结果3，4 可得 b*m(c)*a + c*a - b*a <=  b*m(a)*c + a*c -b * c
     * 再将等式两边同时除以b
     * m(c)*a - a <= m(a)*c - c
     * a*m(c) + c <= c * m(a) + a
     * ac <= ca 证明成功
     *
     */

    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static String lowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (String str : strs) {
            res+=str;
        }
        return res;
    }

    public static String lowestString2(String[] strs){
       List<String> all =  Lists.newArrayList();
        Set<Integer> objects = Sets.newHashSet();
        process(strs, objects,"",all);
        String lowest = all.get(0);
        for (String s : all) {
            if(s.compareTo(lowest) < 0){
                lowest = s;
            }
        }
        return lowest;
    }

    /**
     * @param strs 放着所有的字符串
     * @param use 登记已经使用过的字符串的下标，不再使用
     * @param path 使用过的字符串拼接出的结果
     * @param all 用all保存所有结果
     */
    private static void process(String[] strs,
                               Set<Integer> use, String path, List<String> all){
        if(use.size() == strs.length){
            all.add(path);
        }else{
            for(int i = 0;i<strs.length;i++){
                if(!use.contains(i)){
                    use.add(i);
                    process(strs, use, path+strs[i], all);
                    //每次递归结束后，当前下标的字符串 应该可以重新被其它拼接情况所使用
                    //例如 a 在拼接结束后，下一个应该拼接b ，那么a应该可以被b开头的path所拼接，所以需要把a移除
                    use.remove(i);
                }
            }
        }
    }
}

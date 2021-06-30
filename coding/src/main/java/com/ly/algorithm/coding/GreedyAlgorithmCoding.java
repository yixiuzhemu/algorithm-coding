package com.ly.algorithm.coding;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 贪心算法
 * 1.最自然智慧的算法
 * 2.用一种局部最功利的标准，总是做出在当前看来是最好的选择
 * 3.难点在于证明局部最功利的标准可以得到全局最优解
 * 4.对于贪心算法的学习主要以增加阅历和经验为主
 *
 * 贪心算法的求解的标准流程
 * 1.分析业务
 * 2.根据业务逻辑找到不同的贪心策略
 * 3.对于能举出反例的策略直接跳过，不能举出反例的策略要证明有效性，这往往是特别困难的，要求数学能力很高且不具有统一的技巧性
 *
 *
 * 贪心算法的解题套路
 * 1.实现一个不依靠贪心策略的解法X，可以用最暴力的尝试
 * 2.脑补出贪心策略A、贪心策略B、贪心策略C。。。
 * 3.用解法X和对数器，用实验的方式得知哪个贪心策略最正确
 * 4.不要去纠结贪心策略的证明
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


    /**
     * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
     * 给你每一个项目的开始时间和结束时间，
     * 你来安排宣讲得到日程，要求会议室进行的宣讲的场次最多
     * 返回最多的宣讲场次
     * 贪心策略：按照结束时间早进行安排（谁的结束时间早先安排谁）
     */

    public static class Program {
        /**
         * 会议开始时间
         */
        public int start;

        /**
         * 会议结束时间
         */
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Program{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static class MyProgramComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * 获取会议
     * @return
     */
    public static List<Program> getRandomPrograms(){
        List<Program> programs  = Lists.newArrayList();
        Random random = new Random();
        for(int i = 0;i<15;i++){
            int start = random.nextInt(17)+6;
            int m = 0;
            while(( m = random.nextInt(24-start)) == 0){}
            int end = start + m;
            programs.add(new Program(start,end));
        }
        return programs;
    }

    /**
     * 还剩什么会议都放在program中
     * done之前已经安排了多少会议
     * timeLine 目前来到的时间点是什么
     * 目前来到timeLine的时间点，已经安排了done的会议，剩下的可安排会议
     *
     * 每次循环找到 以当前会议为开始的所有可能的组合
     */
    public static int process(Program[] programs,int done,int timeLine){
        if(programs.length == 0){
            return done;
        }
        int max = done;
        //每次都从全部会议中获取
        for(int i = 0;i<programs.length;i++){
            if(programs[i].start >= timeLine){
                //将当前会议从数组中移除
                //每次都复制一个新数组，不用恢复现场
                Program[] next = copyButExcept(programs,i);
                max = Math.max(max,process(next,done+1,programs[i].end));
            }
        }
        return max;
    }

    /**
     * 贪心策略，按照结束时间晚进行排序
     * 然后遍历得到最多的安排
     * @param programs
     * @return
     */
    public static int process2(Program[] programs){
        Arrays.sort(programs,new MyProgramComparator());
        int timeLine = 0;
        int result = 0;
        for(int i = 0;i<programs.length;i++){
            if(timeLine <= programs[i].start){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }


    public static Program[] copyButExcept(Program[] programs,int i ){
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for(int k = 0;k<programs.length;k++){
            if( k != i){
                ans[index++] = programs[k];
            }
        }
        return  ans;
    }

    /**
     * 给定一个字符串str，只由‘X’和‘。’两种字符构成
     * ‘X’表示墙，不能放灯，也不需要点亮
     * ‘。’表示居民点，可以放灯，需要点亮
     * 如果灯放在i位置，可以让i-1，i和i+1三个位置点亮
     * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
     */

}

package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 暴力递归
 *暴力递归就是尝试
 * 1.把问题转化为规模缩小了的同类问题的子问题
 * 2.有明确的不需要进行递归的条件（base case）
 * 3.有当得到了子问题的结果之后的决策过程
 * 4.不记录每一个子问题的解
 *
 * @author Ly
 * @create 2021/7/7 15:44
 * @desc
 **/
public class ViolentRecursionCoding {

    /**
     * 暴力递归尝试
     * 1.打印N层汉诺塔从最左边移动到最右边的全部过程
     *
     * 4.
     * 5.打印一个字符串的全部排列 ，要求不出现重复的排列
     */
    public static void hanoi1(int n ){
        leftToRight(n);
    }

    /**
     * 把1-N 层圆盘 从最左移到最右
     * 那么就需要先将 1- N-1 层圆盘 从左边移到中间
     * 然后将N-1层圆盘从中间移到右边
     * @param n
     */
    public static void leftToRight(int n ){
        if(n == 1){
            System.out.println("1  left to right");
            return;
        }
        leftToMid(n-1);
        System.out.println(n+"   left to right");
        midToRight(n-1);
    }

    /**
     * 把1-N层圆盘从最左移到中间
     * 那么就需要先把1-N-1的圆盘移到右边
     * 然后再将N-1层圆盘从右边移到中间
     * @param n
     */
    public static void leftToMid(int n ){
        if(n == 1){
            System.out.println("1  left to mid");
            return;
        }
        leftToRight(n-1);
        System.out.println(n+"  left to mid");
        rightToMid(n-1);
    }

    /**
     * 把1-N层圆盘从最右边移到中间
     * 那么就需要把1-N-1层圆盘从右边移动到左边
     * 然后再将N-1层圆盘从左边移到中间
     * @param n
     */
    public static void rightToMid(int n){
        if(n == 1){
            System.out.println("1  right to mid");
            return;
        }
        rightToLeft(n);
        System.out.println(n+"  right to mid");
        leftToMid(n);
    }

    /**
     * 把1-N层圆盘从最右边移到左边
     * 那么就需要把1-N-1层圆盘从右边移动到中间
     * 然后再将N-1层圆盘从中间移到左边
     * @param n
     */
    public static void rightToLeft(int n){
        if(n == 1){
            System.out.println("1  right to left");
            return;
        }
        rightToMid(n-1);
        System.out.println(n+"  right to left");
        midToRight(n-1);
    }

    /**
     * 把1-N层圆盘从中间移到右边
     * 那么就需要把1-N-1层圆盘从中间移动到左边
     * 然后再将N-1层圆盘从左边移到右边
     * @param n
     */
    public static void midToRight(int n){
        if(n == 1){
            System.out.println("1  mid to right");
            return;
        }
        midToLeft(n-1);
        System.out.println(n+"  mid to right");
        leftToRight(n-1);
    }

    /**
     * 把1-N层圆盘从中间移到左边
     * 那么就需要把1-N-1层圆盘从中间移动到右边
     * 然后再将N-1层圆盘从右边移到左边
     * @param n
     */
    public static void midToLeft(int n){
        if(n == 1){
            System.out.println("1  mid to left");
            return;
        }
        midToRight(n-1);
        System.out.println(n+"  mid to right");
        rightToLeft(n-1);
    }

    public static void hanoi2(int n){
        if(n > 0){
            func(n,"左","右","中");
        }
    }
    /**
     * 目标从from -> to，other时另外一个
     * 要从from到to ，那么就需要把n-1 从from 到other
     * 再将n-1 从other 到to
     * @param n
     * @param from
     * @param to
     */
    public static void func(int n,String from,String to,String other){
        if(n == 1){
            System.out.println("1 from "+from+" to "+to);
        }else{
            func(n-1,from,other,to);
            System.out.println(n+" from "+from+" to "+to);
            func(n-1,other,to,from);
        }
    }

    public static void hanoi3(int n){
        if(n <  0){
            return;
        }
        Record record = new Record(false, "左", "右", "中", n);
        Stack<Record> stacks = new Stack<>();
        stacks.push(record);
        while(!stacks.isEmpty()){
            Record pop = stacks.pop();
            if(pop.n == 1){
                System.out.println(pop.n+" from "+pop.from+" to "+pop.to);
                if(!stacks.isEmpty()){
                    stacks.peek().finish = true;
                }
            }else{
                if(pop.finish){
                    System.out.println(pop.n+" from "+pop.from+" to "+pop.to);
                }else{
                    //从from ->to 后，需要将圆盘从other到to
                    stacks.push(new Record(false,pop.other,pop.to,pop.from,pop.n-1));
                    stacks.push(pop);
                    //需要从form->to，需要将圆盘从from到other
                    stacks.push(new Record(false,pop.from,pop.other,pop.to,pop.n-1));

                }
            }
        }
    }
    public static class Record{

        public boolean finish;

        public String from;

        public String to;

        public String other;

        public int n;

        public Record(boolean finish, String from, String to, String other, int n) {
            this.finish = finish;
            this.from = from;
            this.to = to;
            this.other = other;
            this.n = n;
        }
    }

    /**
     * 给你一个栈，。请逆序这个栈
     * 不能申请额外的数据结构
     * 只能使用递归函数，如何实现
     */

    public static void reverse(Stack stack){
        if(stack.isEmpty()){
            return;
        }
        Object i = popLast(stack);
        reverse(stack);
        stack.push(i);
    }

    public static Object popLast(Stack stack){
       Object result = stack.pop();
       if(stack.isEmpty()){
           return result;
       }else{
           Object last = popLast(stack);
           stack.push(result);
           return last;
       }
    }
    //////////////////////从左往右的尝试的模型
    /**
     *打印一个字符串的全部子序列
     */

    public static void printSubsequence(String value){
        if(value == null){
            return;
        }
        char[] chars = value.toCharArray();
        List<String> results = Lists.newArrayList();
        print(0,chars,"",results);
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void print(int i,char[] chars,String value,List<String> results){
        if(i >= chars.length){
            results.add(value);
            return;
        }else{
            print(i+1,chars,value+chars[i],results);
            print(i+1,chars,value,results);
            return;
        }
    }

    /**
     * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
     */
    public static void printSubsequence2(String value){
        if(value == null){
            return;
        }
        char[] chars = value.toCharArray();
        Set<String> results = Sets.newHashSet();
        print2(0,chars,"",results);
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void print2(int i,char[] chars,String value,Set<String> results){
        if(i >= chars.length){
            results.add(value);
            return;
        }else{
            print2(i+1,chars,value+chars[i],results);
            print2(i+1,chars,value,results);
            return;
        }
    }

    /**
     * 打印一个字符串的全部排列
     * @param value
     */
    public static void printSubsequence3(String value){
        char[] chars = value.toCharArray();
        List<String> results = Lists.newArrayList();
        print3(chars,0,results);
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void print3(char[] chars,int i,List<String> results){
        if(i >= chars.length){
            results.add(new String(chars));
            return;
        }else{
            boolean[] characters = new boolean[26];
            for (int j = i;j<chars.length;j++) {
                //分支限界
                int index = chars[j] - 'a';
                if(characters[index]){
                    continue;
                }
                characters[index] = true;
                swap(chars,i,j);
                print3(chars, i+1, results);
                swap(chars,i,j);
            }
            return;
        }
    }

    public static void swap(char[] chars,int i ,int j){
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    /**
     * 从左往右的尝试模型1
     * 规定1和A对应、2和B对应、3和C对应。。。。
     * 那么一个数字字符串比如“111”就可以转化为“AAA”、“KA"和”AK“
     * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
     */
    public static void convertString(String numberStr){
        char[] chars = numberStr.toCharArray();
        List<String> results = Lists.newArrayList();
        convert(chars,0,"",results);
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void convert(char[] chars,int i,String value ,List<String> results){
        if(i >= chars.length){
            results.add(value);
            return;
        }
        if(chars[i] == '0'){
            return;
        }
        char ic = (char)(('A' + chars[i] - '0') - 1);
        convert(chars,i+1,value+ic,results);
        if(i+1 < chars.length){
            int m = (chars[i] - '0') * 10;
            int n =  chars[i+1]-'0';
            if((m  +  n <= 26)){
                convert(chars,i+2,value+(char)('A'+(m+n) - 1),results);
            }
        }
    }

    /**
     * 背包问题
     * 给定两个长度都为N的数组 weights和values
     * weights[i]和values[i]分别代表i号物品的重量和价值
     * 给定一个正数bag，表示一个载重bag的袋子
     * 你装的物品不能超过这个重量
     * 返回你能装下最多的价值是多少
     */
    public static int getMaxPrice(int[] weights,int[] values,int bag){
        return maxPrice(weights,values,0,bag,0);
    }

    public static int maxPrice(int[] weights,int[] values,int i,int bag,int weight){
        if(i >= weights.length && weight <= bag){
            return 0;
        }else if(weight > bag){
            return -1;
        }
        //不要当前货物
        int p1 = maxPrice(weights, values, i+1, bag, weight);
        //要当前货物
        int p2 = maxPrice(weights, values, i+1, bag, weight+weights[i]);
        int pp2 = -1;
        if(p2 != -1){
            pp2 = values[i]+p2;
        }
        return Math.max(p1,pp2);
    }

    public static int getMaxPrice2(int[] weights,int[] values,int bag){
        return maxPrice2(weights,values,0,bag);
    }

    /**
     *
     * @param w
     * @param v
     * @param index
     * @param reset 剩余空间
     * @return
     */
    public static int maxPrice2(int[] w,int[] v,int index,int reset){
        if(reset<=0 || index >= w.length){
            return 0;
        }
        //不要当前货物
        int p1 = maxPrice2(w, v, index+1, reset);
        int p2 = 0;
        //要当前货物
        if(reset >= w[index]){
            p2 = v[index] + maxPrice2(w, v, index+1, reset-w[index]);
        }
        return Math.max(p1,p2);
    }
    /////////////////////范围上尝试的模型
    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
     * 玩家A和玩家B依次拿走每张纸牌
     * 规定玩家A先拿，玩家B后拿，
     * 但是每个玩家每次只能拿走最左或最右的纸牌，
     * 玩家A和玩家B 都决定聪明，请返回最后获胜者的分数
     *
     */

    public static int getMaxScore(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int score1 = rMaxScore(arr,0,arr.length - 1);
        int score2 = sMaxScore(arr,0,arr.length-1);
        String str = score1 > score2?"先手":"后手";
        System.out.println(str+"拿赢");
        return Math.max(rMaxScore(arr,0,arr.length - 1),sMaxScore(arr,0,arr.length-1));
    }

    /**
     * 先手拿
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int rMaxScore(int[] arr,int L,int R){
        //只剩一张牌。先手直接拿走
        if(L == R){
            return arr[L];
        }
        int score1 = arr[L] + rMaxScore(arr,L + 1,R);
        int score2 = arr[R] + rMaxScore(arr,L,R - 1);
        return Math.max(score1,score2);
    }

    /**
     * 后手拿，后手怎么拿，却决于先手怎么拿
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int sMaxScore(int[] arr,int L,int R){
        //只剩一张牌。后手肯定拿不到
        if(L == R){
            return 0;
        }
        //需要由对手确定 先手结果，先手结果一定是最差的情况
        int score1 = rMaxScore(arr,L+1,R);
        int score2 = rMaxScore(arr,L,R-1);
        //先手一定会让 后手拿到分数最低的情况
        return Math.min(score1,score2);
    }

    /**
     * N 皇后
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，。
     * 要求任何两个皇后不同行、不同列，也不在同一条斜线上
     * 给定一个整数n,返回n皇后的摆法有多少种
     * n=1，返回1
     * n=2或3 2皇后和3皇后问题怎么摆都不行，返回0
     * n=8，返回92
     */

}

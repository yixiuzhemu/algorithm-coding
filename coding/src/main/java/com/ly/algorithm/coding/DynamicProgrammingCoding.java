package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 动态规划
 * 暴力递归和动态规划的关系
 * 1.某一个暴力递归，有解的重复调用，就可以把这个暴力递归优化成动态规划
 * 2.任何动态规划问题，都一定对应着某一个有解的重复调用的暴力递归
 * 3.但不是所有的暴力递归，都一定对应着动态规划
 *
 * 面试题和动态规划的关系
 * 1.解决一个问题，可能有很多尝试方法
 * 2.可能在很多尝试方法中，又有若干个尝试方法有动态规划的方式
 * 3.一个问题可能有若干种动态规划的解法
 *
 * 如何找到某个问题的动态规划方式
 * 1.设计暴力递归：重要原则+4中常见尝试模型
 * 2.分析有没有重复解：套路解决
 * 3.用记忆化搜索——>用严格表结构实现动态规划，套路解决
 * 4.看是否能继续优化，套路解决
 *
 * 暴力递归到动态规划的套路
 * 1.你已经有了一个不违反原则的暴力递归，而且的确存在解的重复调用
 * 2.找到哪些参数的变化会影响返回值，对每一个参数列出变化范围
 * 3.参数间的所有的组合数量，意味着表大小
 * 4.记忆化搜索的方法就是傻缓存，非常容易得到
 * 5.规定好严格表的大小，分析位置的依赖顺序，然后从基础填写到最终解
 * 6.对于有枚举行为的决策过程，进一步优化
 *
 * 动态规划的进一步优化
 * 1.空间压缩
 * 2.状态化简
 * 3.四边形不等式
 * 4.其它优化技巧
 * @author Ly
 * @create 2021/7/12 15:53
 * @desc
 **/
public class DynamicProgrammingCoding {

    /**
     *     /**
     *      * 机器人移动
     *      * 假设有排成一行的N个位置，记为1~N，N一定大于或等于2
     *      * 开始时机器人在其中的M位置上（M一定是1~N种的一个）
     *      * 如果机器人来到1位置，那么下一步只能往右来到2位置
     *      * 如果机器人来到N位置，那么下一步只能往左来到N-1位置
     *      * 如果机器人来到中间位置，那么下一步可以往左走或者往右走
     *      * 规定机器人必须走K步，最终能来到P位置的方法有多少种
     *      * 给定4个参数N、M、K、P,返回方法数
     * @param N
     * @param M
     * @param K
     * @param P
     * @return
     */
    public static int move3(int N,int M,int K,int P){
        int[][] dp = new int[N+1][K+1];
        return m2(N,M,K,P,dp);
    }
    /**
     * 该问题的结果与M,K有关，N,P郑国过程都不会进行改变，所以这是一个2维数组的问题
     * 并且根据条件，可以提前确定，dp[0][j]上 不会有值 （条件是1-N）
     * dp[i][0] 上只有P位置上值为1
     * 并且由递归函数可以推导出
     * 第一行，任何一个点都等于 左下角的值  move(N,M+1,K-1,P);
     * 最后一行，任何一个点的值都等于左上角的值 move(N,M-1,K-1,P);
     * 其它行，任何一个点都等于左上角的值加上左下角的值 move(N,M+1,K-1,P) + move(N,M-1,K-1,P)
     * 最后再返回M,K位置的值
     * @param N
     * @param M
     * @param K
     * @param P
     * @param dp
     * @return
     */
    public static int m2(int N,int M,int K,int P,int[][] dp){
        for(int i = 0;i<N+1;i++){
            if(i == P){
                dp[i][0] = 1;
            }else{
                dp[i][0] = 0;
            }
        }

        for(int j = 1;j<K+1;j++){
            for(int i = 1;i<N+1;i++){
                //第一行，任何一个点都等于 左下角的值
                if(i == 1){
                    dp[i][j] = dp[i+1][j-1];
                }
                //最后一行，任何一个点的值都等于左上角的值
                else if(i == N){
                    dp[i][j] = dp[i-1][j-1];
                }
                //其它行，任何一个点都等于左上角的值加上左下角的值
                else{
                    dp[i][j] = dp[i-1][j-1] + dp[i+1][j-1];
                }
            }
        }
        return dp[M][K];
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
        return maxPrice(weights,values,weights.length,bag);
    }

    /**
     * 由暴力递归进行动态规划的改造
     * 问题由index，weight两个参数决定，所以这是一个二维数组的问题
     * index为行，weight为列，整个二维数组是 以 索引位置以及重量组成的二维数组
     * 由条件 index >= w.length return 0 可知，dp[index][] = 0
     * 由递归方法maxPrice(weights, values, i+1, bag, weight)  可以推出，当不拿当前货物时，当前行金额等于下一行的金额
     * 由递归方法 maxPrice(weights, values, i+1, bag, weight+weights[i]) 可以推出，当拿当前货物时，当前金额等于下一行的金额加上当前位置的金额
     * 最后取两者之间的最大值，并返回dp[0][weight]位置的值
     *
     * @param w
     * @param v
     * @param index
     * @param weight
     * @return
     */
    public static int maxPrice(int[] w,int[] v,int index,int weight){
        int[][] dp = new int[index + 1][weight+1];
        //从下往上开始填充二维数组，且从倒数第二行开始填充
        for(int i = index - 1;i>=0;i--){
            for(int j = 0;j<weight+1;j++){
                dp[i][j] = dp[i+1][j];
                if(j >= w[i]){
                    dp[i][j] = Math.max(dp[i+1][j],v[i]+dp[i+1][j - w[i]]);
                }
            }
        }
        return dp[0][weight];
    }

    /**
     *打印一个字符串的全部子序列
     * 创建一个字符串的包装类，里面包含一个字符数组
     * 分析可知，该问题共存在两个数据维度，一个是索引位置，一个是字符串长度
     * 以索引位置作为行，字符串长度作为列
     * 当字符串长度为0时，结果全为null
     * 当字符串长度为1时，结果为当权索引位置的值
     * 当字符串长度大于1时，结果为当前位置的前一列之前所有行的 所有字符串结果加上当前字符
     * 最后打印整个二维数组不为空的结果
     */
    public static void printSubsequence(String value){
        if(value == null){
            return;
        }
        char[] chars = value.toCharArray();
        StringPacking[][] dp = new StringPacking[chars.length][chars.length+1];
        for(int j = 1;j<chars.length+1;j++){
            for(int i = 0;i<chars.length;i++){
                if(j == 1){
                    dp[i][j] = new StringPacking();
                    dp[i][j].allResult.add(chars[i]+"");
                }else if(i >= j - 1){
                    dp[i][j] = new StringPacking();
                    for(int k = i;k>0;k--){
                        if(dp[i-k][j-1] != null){
                            dp[i][j].allResult.addAll(dp[i-k][j-1].addA(chars[i]+""));
                        }
                    }
                }
            }
        }
        for(int i = 0;i<chars.length;i++){
            for (int j = 0;j<chars.length+1;j++) {
                if(dp[i][j] != null){
                    for (String s : dp[i][j].allResult) {
                        System.out.println(s);
                    }

                }
            }
        }
    }

    static class StringPacking{
        public List<String> allResult = Lists.newArrayList();
        public List<String> addA(String s){
            List<String> results = Lists.newArrayList();
            for (String s1 : allResult) {
                results.add(s1+s);
            }
            return results;
        }
    }

    /**
     * 数字字符串转换为字符字符串
     *  通过分析，这是一个与长度相关的一维问题，所以以数组长度创建一个数组
     *  从最后一位开始尝试，通过递归可以知道当前单字符可以作为一种可能，
     *  当前字符加上下一个字符如果不超过26也可以作为一种可能
     */

    public static int  convertString(String numberStr){
        char[] chars = numberStr.toCharArray();
        int N = chars.length;
        int[] dp = new int[N+1];
        dp[N] = 1;
        for(int i = N-1;i>=0;i--){
            if(chars[i] == '0'){
                return 0;
            }
            dp[i] = dp[i+1];
            if(i+1 < chars.length){
                int m = (chars[i] - '0') * 10;
                int n =  chars[i+1]-'0';
                if((m  +  n <= 26)){
                   dp[i] += dp[i+2];
                }
            }
        }
       return dp[0];
    }

    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
     * 玩家A和玩家B依次拿走每张纸牌
     * 规定玩家A先拿，玩家B后拿，
     * 但是每个玩家每次只能拿走最左或最右的纸牌，
     * 玩家A和玩家B 都决定聪明，请返回最后获胜者的分数
     *
     * 由分析递归可知，将先手拿转换成在L-R上的一个二维数组，将后手拿转换成在L-R上的一个二维数组
     * 当 L == R 时，此时f二维数组的值就为数组对应位置的值，s二维数组的值为0
     * 分析递归函数可知，f函数的返回值依赖于s函数，所以 f数组的值 等于 s数组的处理结果值
     * s函数的返回值依赖于f函数，所以s数组的值等于f数组的处理结果值
     */

    public static int getMaxScore(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for(int i = 0;i<arr.length;i++){
            f[i][i] = arr[i];
        }
        for(int i = 1;i<arr.length;i++){
            int L = 0;
            int R = i;
            while(L < arr.length && R < arr.length){
                f[L][R] =  Math.max(arr[L]+s[L+1][R],arr[R]+s[L][R-1]);
                s[L][R] =  Math.max(arr[R]+f[L][R-1],arr[L]+f[L+1][R]);
                L++;
                R++;
            }
        }
        int score1 = f[0][arr.length-1];
        int score2 = s[0][arr.length-1];
        String str = score1 > score2?"先手":"后手";
        System.out.println(str+"拿赢");
        return Math.max(score1,score2);
    }

    /**
     * 记忆化搜索-凑金额
     * 给定一个数组，数组中代表金额，每一个金额都有无数张，且数组中的金额不重复
     * 现在给定一个金额，请问数组中有多少种方法可以刚好等于该金额
     * [70,30,100,50]  1000
     */
    public static int getPriceCount(int[] prices,int price){
        int[][] dp = new int[prices.length+1][price+1];
        for(int i = 0;i<prices.length+1;i++){
            for(int j = 0;j<price+1;j++){
                dp[i][j] = -1;
            }
        }
        return price(prices,0,price,dp);
    }

    //如果算过，dp[i][j] 大于-1
    //如果没算过，dp[i][j] 等于-1
    public static int price(int[] prices,int index,int price,int[][] dp){
        if(dp[index][price] > -1){
            return dp[index][price];
        }
        if(index >= prices.length){
            int m = price == 0 ? 1 : 0;
            dp[index][price] = m;
            return m;
        }
        int res = 0;
        for(int i = 0;prices[index] * i <= price;i++ ){
            res+= price(prices,index+1,price - (prices[index] * i),dp);
        }
        dp[index][price] = res;
        return res;
    }

    /**
     * 动态规划-凑金额
     * 给定一个数组，数组中代表金额，每一个金额都有无数张，且数组中的金额不重复
     * 现在给定一个金额，请问数组中有多少种方法可以刚好等于该金额
     * [70,30,100,50]  1000
     */
    public static int getPriceCount2(int[] prices,int price){
        int[][] dp = new int[prices.length+1][price+1];
        dp[prices.length][0] = 1;
        for(int index = prices.length-1;index>=0;index--){
            for(int rest = 0;rest<price+1;rest++){
                int res = 0;
//                for(int k = 0;prices[index] * k <= rest;k++){
//                    res+= dp[index+1][rest - (prices[index] * k)];
//                }
                //优化枚举，通过分析可知道，当前位置的值，等于当前位置下一行的值加上 当前位置-prices[i]列的值
                //不需要再一次计算之前的值
                //例如 需要计算 3，100的值，实际上已经计算好了 3，97的值 和 4，100的值，而3，97的值加上4，100的值就是3，100的值
                //所以可以将循环直接优化成当前语句
                if(rest >= prices[index]){
                    res = dp[index+1][rest] + dp[index][rest-prices[index]];
                }else{
                    res = dp[index+1][rest];
                }
                dp[index][rest] = res;
            }
        }
        return dp[0][price];
    }

    /**
     * 记忆化搜索 - 贴纸
     * 给定一个字符串str，给定一个字符串类型的数组arr
     * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
     *
     * 返回至少需要多少张贴纸可以完成任务
     * 例子： str="babac", arr=["ba","c","abcd"]
     * 至少需要这两张贴纸ba和abcd，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、两个b、1个c
     * 是可以拼出str的，所以返回2
     */
    public static int getStickerStr1(String[] strs,String sticker){
        Map<String,Integer> dp = Maps.newHashMap();
        int[][] map = new int[strs.length][26];
        for (int i = 0;i<strs.length;i++) {
            for (char c : strs[i].toCharArray()) {
                map[i][c-'a']++;
            }
        }
        dp.put("",0);
        return sticker(sticker,dp,map);
    }

    public static int sticker(String sticker, Map<String,Integer> dp,int[][] map){
        if(dp.containsKey(sticker)){
            return dp.get(sticker);
        }
        char[] chars = sticker.toCharArray();

        int[] tmap = new int[26];
        int res = Integer.MAX_VALUE;
        for (char aChar : chars) {
            tmap[aChar - 'a'] ++;
        }
        for(int i = 0;i<map.length;i++){
            //至少要包含一个字符 判断切割出的字符第一个是否存在于初始化的结果中
            if(map[i][chars[0]-'a'] <= 0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j<26;j++){
                if(tmap[j] > 0){
                    //判断使用了当前贴纸后还有多少个字符
                    for(int k = 0;k < Math.max(0,tmap[j]  - map[i][j]);k++){
                        sb.append((char)('a'+j));
                    }
                }
            }
            int tmp = sticker(sb.toString(),dp,map);
            if(tmp != -1){
                res = Math.min(res,tmp + 1);
            }
        }
        dp.put(sticker,res == Integer.MAX_VALUE ? -1 : res);
        return dp.get(sticker);
    }
}

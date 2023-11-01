package com.ly.algorithm.coding.leecode;

/**
 * 给定一个字符串，至少切多少刀，可以让所有块都变成回文串
 * @author Ly
 * @create 2023/10/22 19:35
 * @desc
 **/
public class PMinParts {

    /**
     * 暴力解：对于任意i位置，都去尝试从i位置出发到N-1位置一共能切出至少多少个回文串
     *
     * 			可能性1：i位置字符单独作为回文串，f(str,i+1) + 1
     *
     * 			可能性2： 从i位置开始找到能组成回文串的部分，从该位置开始尝试，假设j位置可以与i位置形成回文串。那么f(str,j+1)+1,再取可能性1和可能性2之中的最小值
     * @param str
     * @return
     */
    public static int pMinParts_v1(String str){
        return process(str.toCharArray(),0);
    }

    public static int process(char[] chars,int i){
        if(i == chars.length){
            return 0;
        }
        int ans = process(chars,i+1)+1;
        for(int j = i+1;j < chars.length;j++){
            if(isCircleStr(chars,i,j)){
                ans = Math.min(process(chars,j+1)+(j == chars.length-1 ? 0 : 1),ans);
            }
        }
        return ans;
    }

    /**
     * 动态规划：存在一个变量i，准备一个一维数组dp,长度为N+1，初始化dp[N] 位置的值为0，从后往前填值，对于任意位置，当前位置的值依赖于i位置的值以及后续能形成回文的j位置中的最小值。最后返回dp[0]位置的值
     * @param str
     * @return
     */
    public static int pMinParts_v2(String str){
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length+1];
        for(int i = chars.length-1;i>=0;i--){
            dp[i] = dp[i+1]+1;
            for(int j = i+1; j < chars.length;j++){
                if(isCircleStr(chars,i,j)){
                    dp[i] = Math.min(dp[j+1]+(j == chars.length-1 ? 0 : 1),dp[i]);
                }
            }
        }
        return dp[0];
    }

    /**
     * 优化解：让暴力解和动态规划解变成三阶问题的原因在于每次遍历需要检查字符串是否是回文，如果存在一张回文表，可以直接检查i,j位置是否是回文串，那么就可以将问题变成两阶的问题。
     *
     * ​		回文表的生成：准备一个二维dp数组，行为L，列为R，表示L~R，所以二维表的左下角不需要填，初始化对角线，对角线代表L==R，那么此时必为回文。所以对角线上的值都为true，对于任意L,R位置，如果当前字符串需要是回文，那么必须要满足arr[L] == arr[R] 并且L+1,R-1 范围需要时回文。所以依次填充对角线，最后得到booleanDp
     * @param str
     * @return
     */
    public static int pMinParts_v3(String str){
        char[] chars = str.toCharArray();
        int N  = chars.length;
        boolean[][] booleanDp = new boolean[N][N];
        for(int i = 0; i < N;i++){
            booleanDp[i][i] = true;
        }
        for(int j = 1; j < N;j++){
            int L = 0;
            int R = j;
            for(;R < N && L < N ;L++,R++){
                if(chars[L] != chars[R]){
                    continue;
                }
                if(L+1 > R-1){
                    booleanDp[L][R] = true;
                }else{
                    booleanDp[L][R] = booleanDp[L+1][R -1];
                }
            }
        }
        int[] dp = new int[chars.length+1];
        for(int i = chars.length-1;i>=0;i--){
            dp[i] = dp[i+1]+1;
            for(int j = i+1; j < chars.length;j++){
                if(booleanDp[i][j]){
                    dp[i] = Math.min(dp[j+1]+(j == chars.length-1 ? 0 : 1),dp[i]);
                }
            }
        }
        return dp[0];
    }


    public static boolean isCircleStr(char[] chars,int start,int end){
        if(start == end){
            return true;
        }
        while(start < end){
            if(chars[start++] != chars[end--]){
                return false;
            }
        }
        return true;
    }
}

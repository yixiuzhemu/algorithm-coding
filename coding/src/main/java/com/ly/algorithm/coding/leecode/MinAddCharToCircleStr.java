package com.ly.algorithm.coding.leecode;

/**
 *给定一个字符串，如果可以在任意位置添加字符，最少添加几个字符能让字符串整体都是回文串。
 *
 *
 *
 * @author Ly
 * @create 2023/11/1 19:58
 * @desc
 **/
public class MinAddCharToCircleStr {

    /**
     * 暴力解：L,R范围上，最少添加几个字符可以让字符变成回文串
     * 暴力解的可能性：
     *
     * 			假设L~R-1可以添加最少字符形成回文串，再单独配对R位置字符
     *
     * 			假设L+1~R可以添加最少的字符形成回文串，再单独配对L位置的字符
     *
     * 			假设L位置与R位置字符相等，那么只需让L+1 ~ R-1添加字符形成回文串即可
     *
     * 			这三种情况的最小值，就是最后的结果
     * @param str
     * @return
     */
    public static int minAdd_v1(String str){
        return process(str,0,str.length()-1);
    }

    public static int process(String str,int L,int R){
        if(L == R){
            return 0;
        }
        //此时两个字符
        if(L == R - 1){
            if (str.charAt(L) == str.charAt(R)){
                return 0;
            }
            return 1;
        }
        //对于任意位置，如果L~R-1 形成回文串，R位置单独配对
        int res = process(str,L,R-1)+1;
        //L+1~R ,形成回文串，L位置单独配置
        res = Math.min(res,process(str,L+1,R)+1);
        //L位置和R位置相等，此时让L+1,R-1形成回文串
        if(str.charAt(L) == str.charAt(R)){
            res = Math.min(res,process(str,L+1,R-1));
        }
        return res;
    }

    /**
     * 动态规划：存在两个遍历,L和R，所以是一个二维问题，由于L<=R,所以左下角不用填值。
     *
     * 		初始化值：当只有一个字符时，即dp对角线，此时不用添加字符，当有两个字符时（第二条对角线），判断字符是否相等，如果不等，那么需要添加一个字符。
     *
     * 		对于任意i,j位置，与暴力递归可能性一样，所以
     *
     * 		dp[i] [j] = dp[i] [j-1]+1
     *
     * 		dp[i] [j] = dp[i+1] [j]+1
     *
     * 		chars[i] == chars[j]  dp[i] [j] = dp[i+1] [j-1];
     *
     * 		所以任意一个普遍位置，依赖于左侧的值，下方的值以及左下角的值，所以整体从下到上，从左到右依次填出数组，最后返回0，N-1位置的值。
     * @param str
     * @return
     */
    public static int minAdd_V2(String str){
        int N = str.length();
        int[][] dp = new int[N][N];
        char[] chars = str.toCharArray();
        //L<=R,初始化第二条对角线
        for(int i = 0;i<N-1;i++){
           dp[i][i+1] = chars[i] == chars[i+1] ? 0 : 1;
        }
        //对于任意普遍位置,依赖左边的位置、依赖下方的位置，依赖左下角的位置
        //所以从下往上、从左往右进行填充
        for(int k = N - 3;k>=0;k--){
            int j = N - 1;
            int i = k;
            for(;i>=0;j--,i--){
                dp[i][j] = Math.min(dp[i][j-1]+1,dp[i+1][j]+1);
                if(chars[i] == chars[j]){
                    dp[i][j] = Math.min(dp[i][j],dp[i+1][j-1]);
                }
            }
        }
        return dp[0][N-1];
    }
}

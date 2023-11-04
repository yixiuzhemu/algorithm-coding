package com.ly.algorithm.coding.leecode;

/**
 *
 * 现有n1+n2种面值的硬币，其中n1种为普通币，可以取任意数（n1数组种无重复值），后n2种为纪念币，每种最多只能取一枚（n2可以有重复值），每种硬币有一个面值，问能有多少种方法拼出m的面值
 * @author Ly
 * @create 2023/11/2 21:27
 * @desc
 **/
public class MoneyWays {

    /**
     * 遍历货币，假设每种货币都可以取n张，直到取出的结果大于m时停止，尝试所有可能。
     * @param arr
     * @param m
     * @return
     */
    public static int moneyWay(int[] arr,int m){
        return process(arr,0,m);
    }

    public static int process(int[] arr,int index,int curM){
        if(curM == 0){
            return 1;
        }
        if(index ==  arr.length || curM < 0){
            return 0;
        }
        //假设当前位置选择n张
        int res = 0;
        int n = 0;
        while(curM - arr[index] * n>=0){
            res += process(arr,index+1,curM-arr[index] * n);
            n++;
        }
        return res;
    }

    /**
     * 存在两个变量i以及面值m，
     *
     * 	以数组下标作为行，钱数m作为0，对于0元列，方法数为1（一张钱都不拿），对于第一行，找到能整除0位置货币的列，j%arr[i]的位置方法数=1 。对于任意i,j位置的含义为，使用任意张0~i的货币，能否组合出j位置的钱数。
     *
     * 	假设当前i位置的货币取0张，那么只需要i-1能正确组合成j即可，即dp[i] [j] = dp[i-1] [j]
     *
     * 	假设当前i位置的货币取1张，那么只需要j-arr[ii >= 0 dp[i] [j] = dp[i-1] [j-1]
     *
     * 	..一直到取i位置的货币，让j-arr[i] * n <0 ,此时停，将上面尝试的结果全部进行累加。
     *
     * 	所以对于重复货币的动态规划，依赖于上一行的值，所以从上往下，从左往右填，最后返回N-1，m位置的结果即可。
     * @param arr
     * @param m
     * @return
     */
    public static int[] moneyWay_v2(int[] arr,int m){
        int[][] dp = new int[arr.length][m+1];
        for(int i = 0;i<arr.length;i++){
            dp[i][0] = 1;
        }
        for(int i = 1;i<=m;i++){
            if(i % arr[0] == 0){
                dp[0][i] = 1;
            }
        }
        for(int i = 1;i<arr.length;i++){
            for(int j = 1;j<=m;j++){
                //i位置的货币选择n张
//                int n = 0;
//                while(j - arr[i] * n >= 0){
//                    dp[i][j] += dp[i-1][j-arr[i]*n];
//                    n++;
//                }
                //优化
                //对于任意i,j位置 实际上在dp[i] [j-arr[i]] 位置已经做过相同的计算了，所以内部的递归可以优化成dp[i] [j] = dp[i-1] [j] + dp[i] [j - arr[j]]
                dp[i][j] = dp[i-1][j] + (j - arr[i] >= 0 ? dp[i][j-arr[i]] : 0);
            }
        }
        return dp[arr.length-1];
    }

    /**
     * 	对于n2数组，就是背包问题
     * 遍历货币，每种货币都只能取1张，所以可以选择取或者不取，尝试所有可能
     * @param arr
     * @param m
     * @return
     */
    public static int moneyWay_v3(int[] arr,int m){
        return process_v3(arr,0,m);
    }

    public static int process_v3(int[] arr,int index,int curM){
        if(curM == 0){
            return 1;
        }
        if(index == arr.length || curM < 0){
            return 0;
        }
        return process_v3(arr,index+1,curM)+process_v3(arr,index+1,curM-arr[index]);
    }

    /**
     * 存在两个变量i以及面值m，以数组下标作为行，钱数m作为0，对于0元列，方法数为1（一张钱都不拿），对于第一行，找到等于0位置货币的列，方法数=1 。对于任意i,j位置的含义为，使用0~i的货币，能否组合出j位置的钱数。
     * @param arr
     * @param m
     * @return
     */
    public static int[] moneyWay_v4(int[] arr,int m){
        int[][] dp = new int[arr.length][m+1];
        for(int i = 0;i<arr.length;i++){
            dp[i][0] = 1;
        }
        dp[0][arr[0]] = 1;
        for(int i = 1;i<arr.length;i++){
            for(int j = 1;j<=m;j++){
                //i位置的货币选择1张或者选择0张
                dp[i][j] = dp[i-1][j] + (j-arr[i] >= 0 ? dp[i-1][j-arr[i]] : 0);
            }
        }
        return dp[arr.length-1];
    }

    /**
     * 对于n1+n2数组
     *
     * 	对于n1数组0~m 已经求出所有的方法数了，n2数组也求出所有的方法数了
     *
     * 	假设让n1完成0元，n2完成m元，此时的方法数=dp1[n1.length-1] [0] * dp2[n2.length-1] [m]
     *
     * 	假设让n1完成1元，n2完成m-1元，此时的方法数=dp1[n1.length-1] [1] * dp2[n2.length-1] [m-1]
     *
     * 	.。。。
     *
     * 	将上面的结果累加起来，就是最后的结果数
     * @param n1
     * @param n2
     * @param m
     * @return
     */
    public static int moneyWay_v5(int[] n1,int[] n2,int m){
        int[] dp1 = moneyWay_v2(n1,m);
        int[] dp2 = moneyWay_v4(n2,m);
        int res = 0;
        for(int i = 0;i<=m;i++){
            res += (dp1[i] * dp2[m-i]);
        }
        return res;
    }

}

package com.ly.algorithm.coding;

/**
 * 动态规划
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

}

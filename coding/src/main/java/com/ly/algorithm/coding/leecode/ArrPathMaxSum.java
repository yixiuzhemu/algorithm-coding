package com.ly.algorithm.coding.leecode;

/**
 *
 * 给定一个二维数组matrix，其中每个数都是正数，要求从左上到右下，每一步只能向右或者向下，沿途经过的数字累加起来，返回最小路径和
 * @author Ly
 * @create 2023/9/26 20:52
 * @desc
 **/
public class ArrPathMaxSum {

    /**
     * 对于任意i，j位置，存在两个选择，向下或者向右
     * @param matrix
     * @return
     */
    public static int arrPathMaxSum(int[][] matrix){
        return process(matrix,0,0);
    }

    /**
     * 对于任意i,j位置，它依赖于上方的值以及左方的值
     * @param matrix
     * @return
     */
    public static int arrPathMaxSum_2(int[][] matrix){
        return process(matrix);
    }

    /**
     * 对于任意i,j位置，它依赖于上方的值以及左方的值，准备一个一维数组（假设行比列多），从0,0位置开始遍历，在第0行时，所有位置只依赖于左侧的值，可以直接更新出
     *
     * 来到第1行时，dp数组当前位置的值就代表上方的值，dp数组上一个位置的值就代表左侧的值，两者求最大值。
     * @param matrix
     * @return
     */
    public static int arrPathMaxSum_3(int[][] matrix){
        return process_2(matrix);
    }


    public static int process(int[][] matrix,int row,int cell){
        if(row >= matrix.length || cell >= matrix[0].length){
            return 0;
        }
        //任意位置都可以向下或者向右走
        int bottom = process(matrix,row+1,cell);
        int right = process(matrix,row,cell+1);
        //求最大值
        return Math.max(bottom,right) + matrix[row][cell];
    }

    public static int process(int[][] matrix){
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        for(int i = 0;i<dp.length;i++){
            for(int j = 0;j<dp[i].length;j++){
                //任意i,j位置的值，依赖于它左边或者上方的最大值
                dp[i][j] = matrix[i][j] + (j > 0 ? Math.max((i > 0 ? dp[i-1][j] : 0),dp[i][j-1]) : (i > 0 ? dp[i-1][j] : 0));
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int process_2(int[][] matrix){
        //这里挑选大的一边作为滚动边
        //更新第一行的值
        int M = matrix[0].length;
        int N = matrix.length;
        int[] dp = new int[M > N ? N : M];
        if(M <= N){
            for(int i = 0;i<N;i++) {
                for (int j = 0; j < M; j++) {
                    dp[j] = matrix[i][j]+ (j > 0 ? Math.max(( i > 0 ? dp[j] : 0),dp[j-1]) : ( i > 0 ? dp[j] : 0));
                }
            }
        }else{
            for(int j = 0;j<M;j++){
                for(int i = 0;i<N;i++){
                    dp[i] = matrix[i][j]+(i > 0 ? Math.max((j > 0 ? dp[i] : 0),dp[i-1]) : (j > 0 ? dp[i] : 0));
                }
            }
        }
        return dp[dp.length-1];
    }
}

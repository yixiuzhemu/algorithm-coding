package com.ly.algorithm.coding;

/**
 * @author Ly
 * @create 2023/8/20 7:07
 * @desc
 **/
public class ParallelCoding {

    /**
     * 一个只有0和1两种数字的二位矩阵中，上下左右能连成一片的1，算一个岛，返回矩阵中，一共有几个岛。
     * 如果系统是单CPU
     * @param m
     * @return
     */
    public static int countIsland(int[][] m){
        if(m == null || m[0] == null){
            return 0;
        }
        int N = m.length;
        int M = m[0].length;
        int res = 0;
        for(int i = 0;i<N;i++){
            for(int j = 0;j<M;j++){
                if(m[i][j] == 1){
                    //代表当前遇到了一个岛的开始
                    res++;
                    infect(m,i,j,N,M);
                }
            }
        }
        return res;
    }

    /**
     * 感染函数，当遇到一个位置为1后，将它上下左右的位置都改成其他值，防止被再次遍历到
     * @param m
     * @param i
     * @param j
     * @param N
     * @param M
     */
    private static void infect(int[][] m, int i, int j, int N, int M) {
        if(i >= N || j >= M || i < 0 || j < 0 || m[i][j] == 0){
            return;
        }
        m[i][j] = 0;
        infect(m,i-1,j,N,M);
        infect(m,i+1,j,N,M);
        infect(m,i,j-1,N,M);
        infect(m,i,j+1,N,M);
    }
}

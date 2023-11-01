package com.ly.algorithm.coding.leecode;

/**
 *
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 *
 * 例如：
 *
 * 01111
 *
 * 01001
 *
 * 01001
 *
 * 01111
 *
 * 01011
 * @author Ly
 * @create 2023/9/13 21:39
 * @desc
 **/
public class MaxOneBorderMap {

    public static int getMaxSize(int[][] matrix){
        int[][] r = new int[matrix.length][matrix[0].length];
        int[][] d = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length;i++){
            r[i][matrix[0].length - 1] = matrix[i][matrix[0].length-1];
            for(int j =  matrix[0].length - 2; j >= 0;j--){
                if(matrix[i][j] == 0){
                    r[i][j] = 0;
                }else{
                    r[i][j] = r[i][j+1] + 1;
                }
            }
        }

        for(int j = 0; j < matrix[0].length;j++){
            r[matrix.length-1][j] = matrix[matrix.length - 1][j];
            for(int i = matrix.length - 2;i>=0;i--){
                if(matrix[i][j] == 0){
                    d[i][j] = 0;
                }else{
                    d[i][j] = d[i+1][j]+1;
                }
            }
        }
        int ans = 0;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                //枚举点，再枚举边
                if(matrix[i][j] != 1){
                    continue;
                }
                //枚举边长
                //直接获取右边或者下方的最大长度
                int rMax = r[i][j];
                int dMax = d[i][j];
                //最大边长
                int max = Math.min(rMax,dMax);
                //枚举边长
                for(int k = 1;k<max;k++){
                    //校验
                    if(d[i][j+k] >= k+1 && r[i+k][j] >= k+1){
                        //合规
                        ans = Math.max(ans,k+1);
                    }
                }
            }
        }
        return ans;
    }

}

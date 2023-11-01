package com.ly.algorithm.coding.leecode;

/**
 *
 * 给定一个正整数M，请构造出一个长度为M的数组arr，要求对任意的i,j,k三个位置，如果i<j<k，都有arr[i]+arr[k] != 2*arr[j],返回构造出的arr
 * @author Ly
 * @create 2023/9/14 21:14
 * @desc
 **/
public class MakeNoCode {


    /**
     * 假设现在存在一个种子数组[a,b,c] ,它满足a < b < c ，并且 a + c != 2b
     *
     * 	那么将种子数组分别加工成奇数和偶数，得到一个新的数组
     *
     * 奇数：[2a-1,2b-1,2c-1] ，它必然也满足2a-1 < 2b-1 < 2c-1 并且2a - 1 + 2b - 1 != 2 * (2b-1)
     *
     * 偶数：[2a,2b,2c] ，必然也是满足的
     *
     * 此时将数组构造成[2a-1,2b-1,2c-1,2a,2b,2c] ，对于左侧的奇数部分，是满足的，对于右侧的偶数部分也是一定满足的。而中间区域，由于左侧是奇数，右侧是偶数，无论怎么圈定，都会包含至少一个奇数，而一个奇数+偶数 永远不可能等于某一个正整数*2得到。
     *
     * 所以对于种子数组，取决于M的长度
     *
     * 第一个种子数组长度等于: M/2
     *
     * 第二个种子数组长度等于：M/4
     *
     * 。。。。
     *
     * 直到种子数组长度为1时，种子数组只有1一个值。
     *
     * 此时再反推回去得到最后的种子数组
     * @param M
     * @return
     */
    public static int[] MakeNo(int M){
        if(M == 1){
            return new int[]{1};
        }
        //种子数组
        int[] process = MakeNo((M+1) / 2);
        //由种子数组生成当前数组
        int[] arr = new int[M];
        //左侧区域生成奇数
        for(int i = 0;i<process.length;i++){
            arr[i] = 2 * process[i] - 1;
        }
        for(int i = process.length;i < M;i++){
            arr[i] = 2 * process[i-process.length];
        }
        return arr;
    }

}

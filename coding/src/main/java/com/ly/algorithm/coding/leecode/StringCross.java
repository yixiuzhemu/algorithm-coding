package com.ly.algorithm.coding.leecode;

/**
 *
 * 给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持原来在str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim是否是str1和str2交错组成
 *
 * 例如：str1=”AB" ，str2 = “12” .那么“AB12”、“A1B2”、"A12B"、“1A2B”和“1AB2”等都是str1和str2的交错组成
 * @author Ly
 * @create 2023/10/25 19:48
 * @desc
 **/
public class StringCross {


    /**
     * 如果str1的长度是N，str2的长度是M，那么str3的长度必须是M+N
     *
     * 	准备一张二维表booleanDp，行为0~N，列为0~M，对于任意i,j位置的含义为，str1拿出0~i-1长度的字符串，str2拿出0~j-1长度的字符串，判断两个字符串是否能交错组成str3 i+j长度的字符串，如果能，那么i,j位置的为true，否则为false，最后返回右下角的值。
     *
     * 	例如：
     *
     * 	str1 = “aabcb"
     *
     * 	str2 = "aacbb"
     *
     * 	str3 = "aaabaccbbb"
     *
     * 	当i=0,j=0时，必然能组成str3 0长度的字符串，所以0,0位置为true
     *
     * 	当i=0,j<3时，所有位置都为true
     *
     * 	当j=0,i<3时，所有位置都为true
     *
     * 	这样就填好了第一行和第一列的值
     *
     * 	如果i,j是一个任意位置，对应的字符串分别时str1[0...i-1] str2[0...j-1] str[0...i+j-1]
     *
     * 	分析可能性
     *
     * 	对于str3字符串，
     *
     * 	如果str3[i+j-1]位置的值来自str1[i-1]，此时str1剩余长度为i-1,str2剩余长度为j，所以此时dp[i] [j] = dp[i-1] [j] ,
     *
     * 	如果str3[i+j-1]位置的值来自str2[j-1]，此时str1剩余长度为i，str2剩余长度为j-1，那么此时dp[i] [j-1]
     *
     * 	以上两个条件任意一个成立，当前位置的结果都为true
     *
     * 	最后再返回dp[N] [M]位置的值
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    public static boolean stringCross(String str1,String str2,String str3){
        int N = str1.length();
        int M = str2.length();
        int K = str3.length();
        if(N+M != K){
            return false;
        }
        //准备一个boolean dp ，i,j位置的含义为 0~i-1长度 已经0~j-1位置是否能组成0~i+j-1长度的str3字前缀字符串
        boolean[][] booleanDp = new boolean[N+1][M+1];
        booleanDp[0][0] = true;
        //初始化第一行、第一列
        for(int i = 1; i <= N;i++){
            //如果当前i列能够在str3找到对应的长度
            if(str2.charAt(i-1) != str3.charAt(i-1)){
                break;
            }
            booleanDp[0][i] = true;
        }
        for(int i = 1 ; i <= M ;i++){
            //如果当前i列能够在str3找到对应的长度
            if(str1.charAt(i-1) != str3.charAt(i-1)){
                break;
            }
            booleanDp[i][0] = true;
        }
        for(int i = 1;i<=N;i++){
            for(int j = 1;j <= M;j++){
                int index = i + j - 1;
                char charAt = str3.charAt(index);
                //如果当前charAt来自str1
                if(str1.charAt(i-1) == charAt && str2.charAt(j-1) == charAt){
                    booleanDp[i][j] = booleanDp[i-1][j] ||  booleanDp[i][j-1];
                }else if(str2.charAt(j-1) == charAt){
                    booleanDp[i][j] = booleanDp[i][j-1];
                }else if(str1.charAt(i-1) == charAt){
                    booleanDp[i][j] = booleanDp[i-1][j];
                }
            }
        }
        return booleanDp[N][M];
    }

}

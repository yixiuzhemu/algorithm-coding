package com.ly.algorithm.coding.leecode;

/**
 * 请注意区分子串和子序列的不同，给定两个字符串str1和str2，求两个字符的最长公共子序列
 * @author Ly
 * @create 2023/9/26 21:31
 * @desc
 **/
public class PublicIncreasingSubsequence {

    /**
     *遍历str1，从0位置开始尝试，去str2上找相等的位置，一旦找到这个位置，从这个位置开始往后尝试。遍历结束后，获取到i=0时的结果，此时再到下一个位置进行尝试。
     * @param str1
     * @param str2
     * @return
     */
    public static int maxPublicIncreasingSubsequence(String str1,String str2){
        return process(str1.toCharArray(),str2.toCharArray(),str1.length()-1,str2.length()-1);
    }

    public static int maxPublicIncreasingSubsequence_2(String str1,String str2){
        return process(str1.toCharArray(),str2.toCharArray());
    }


    public static int maxPublicIncreasingSubsequence_3(String str1,String str2){
        return process_2(str1.toCharArray(),str2.toCharArray());
    }


    /**
     * @param chars1
     * @param chars2
     * @param i
     * @param j
     * @return
     */
    public static int process(char[] chars1,char[] chars2,int i,int j){
        //如果都只剩一个字符，判断当前字符是否相等
       if(i == 0 && j == 0){
           return chars1[i] == chars2[j] ? 1 : 0;
       }
       //如果i只剩一个字符，此时去j位置上找与i位置字符相等的字符
       if(i == 0){
           return ((chars1[i] == chars2[j]) || process(chars1,chars2,i,j-1) == 1) ? 1 : 0;
       }
       //如果j只剩一个字符，此时去i位置上找与j位置字符相等的字符
       if(j == 0){
           return ((chars1[i] == chars2[j])|| process(chars1,chars2,i-1,j)==1 )?1:0;
       }
       //以j位置字符结尾
       int ans = process(chars1,chars2,i-1,j);
       //以i位置字符结尾
       ans = Math.max(ans,process(chars1,chars2,i,j-1));
       //同时以i位置结尾，也以j位置结尾，前提是当前位置的字符要相等
       if(chars1[i] == chars2[j]){
           ans = Math.max(ans,process(chars1,chars2,i-1,j-1))+1;
       }
       return ans;
    }

    /**
     * 定义二维dp数组，行为：str1.length + 1, 列为：str2.length + 1 (防止越界)
     *
     * 		对于最后一行、最后一列可以直接初始化（为了方便coding，当然也可以初始化第0行，第0列的值）
     *
     * 		根据上述的情况，可以知道，对于任意i,j位置，它的值依赖于，i+1,j位置的值 i，j+1位置的值中的最大值，这两个条件是互斥的。
     *
     * 		而当i,j位置字符不等时，此时无论取i+1,j 位置的值还是取i,j+1位置的值，实际上都是一样的，它都等于i+1,j+1位置的值。
     *
     * 		而当i,j位置相等时，此时就需要取i+1,j+1位置的值+1 和 i+1,j位置的值 i，j+1位置的值中的最大值。
     * @param chars1
     * @param chars2
     * @return
     */
    public static int process(char[] chars1,char[] chars2){
        int[][] dp = new int[chars1.length+1][chars2.length+1];
        //对于第一行、第一列的值可以直接填出来
        for(int i = chars1.length-1;i >= 0;i--){
            for(int j = chars2.length-1;j>=0;j--){
                //上方的值
                //上方的值或者左方的值
                dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
                //当既以i结尾，又以j结尾时
                //字符相等的情况是和i+1,j+1的情况互斥的，他们不可能同时满足
                //i+1满足的前提是，不以i结尾，以j结尾
                //j+1满足的前提是，不以j结尾，以i结尾
                if(chars1[i] == chars2[j]){
                    dp[i][j] = Math.max(dp[i][j],dp[i+1][j+1]+1);
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 定义一维数组，从下往上滚动
     * @param chars1
     * @param chars2
     * @return
     */
    public static int process_2(char[] chars1,char[] chars2){
        int[] dp = new int[chars2.length+1];
        //记录对角线的值
        int temp = 0;
        // 先更新出最后一行的值
        for(int i = chars1.length-1;i>=0;i--){
            for(int j = chars2.length-1;j>=0;j--){
                //dp数组当前列位置的的值，就等于下一行的值，当前列+1位置的值，等于右侧的值
                //temp代表的是对角线位置的值
                //也就是在处理j+1位置时，暂存了j+1位置之前的值，也就是j位置对角线的值
                int max = Math.max(dp[j],dp[j+1]);
                //是否更新
                if(chars1[i] == chars2[j]){
                    max = Math.max(temp+1,max);
                }
                temp = dp[j];
                dp[j] = max;
            }
            temp = 0;
        }
        return dp[0];
    }



}

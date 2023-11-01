package com.ly.algorithm.coding.leecode;

/**
 *
 * 给定两个字符串str1和str2，求两个字符串的最长公共子串
 * @author Ly
 * @create 2023/9/30 10:50
 * @desc
 **/
public class PublicIncreasingStr {

    /**
     * 尝试每一个位置能匹配出的最大子串
     * @param str1
     * @param str2
     * @return
     */
    public static int maxPublicIncreasingStr(String str1,String str2){
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int ans = 0;
        for(int m = 0;m<chars1.length;m++){
            for(int n = 0;n < chars2.length;n++){
                if(chars1[m] == chars2[n]){
                    ans = Math.max(process(chars1,chars2,m,n),ans);
                }
            }
        }
        return ans;
    }

    public static int process(char[] chars1,char[] chars2,int i,int j){
        if(i >= chars1.length || j >= chars2.length || chars1[i] != chars2[j]){
            return 0;
        }
        return process(chars1,chars2,i+1,j+1)+1;
    }

    /**
     * 准备一个二维dp数组，行为：str1.length + 1, 列为：str2.length + 1 (防止越界)
     *
     * ​		对于任意i,J位置，必须同时以str1的i位置作为结尾，以str2的j位置作为结尾的最大公共子串长度是多少
     *
     * ​		可以直接初始化str1-1行和str2-1列，如果任意str1[i] != str2[j] 那么dp[i] [j] = 0。如果str1[i] == str2[j] , 那么dp[i] [j] = dp[i+1] [j+1] + 1 ,并且最后的结果ans = Math.max(ans,dp[i] [j])
     * @param str1
     * @param str2
     * @return
     */
    public static int maxPublicIncreasingStr_2(String str1,String str2){
        return process(str1.toCharArray(),str2.toCharArray());
    }

    public static int process(char[] chars1,char[] chars2){
        int[][] dp = new int[chars1.length+1][chars2.length+1];
        int ans = 0;
        for(int i = chars1.length-1;i>=0;i--){
            for(int j = chars2.length-1;j>=0;j--){
                if(chars1[i] == chars2[j]){
                    dp[i][j] = dp[i+1][j+1]+1;
                    ans = Math.max(ans,dp[i][j]);
                    continue;
                }
                dp[i][j] = 0;
            }
        }
        return ans;
    }

    /**
     * 构建虚拟的二维dp数组，准备两个遍历row和cell，初始位置从row=str1.length-1  cell = 0 位置（左下角）开始，
     * 沿着对角线进行遍历，到达当前行的末尾位置后（row=str1.length-1 cell=str2.length-1），开始往上移动，同样对每个位置进行对角线遍历，直到row=0，结束遍历。
     * 并且最后的结果ans=全局求max
     * @param str1
     * @param str2
     * @return
     */
    public static int maxPublicIncreasingStr_3(String str1,String str2){
        return process2(str1.toCharArray(),str2.toCharArray());
    }

    public static int process2(char[] chars1,char[] chars2){
        int row = chars1.length - 1;
        int cell = 0;
        int ans = 0;
        while(row >=0){
            int i = row;
            int j = cell;
            int count = 0;
            while(i >= 0 && j >= 0){
                if(chars1[i] == chars2[j]){
                    count++;
                    ans = Math.max(ans,count);
                }else {
                    count = 0;
                }
                i--;
                j--;
            }
            if(cell == chars1.length - 1){
                row--;
            }else{
                cell++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str1 = "abc123def45aefaefasedfsewr543efsdr34";
        String str2 = "kf123zys4t5aefaef34regws34r5regts4123resad325";
        System.out.println(maxPublicIncreasingStr(str1,str2));
        System.out.println(maxPublicIncreasingStr_2(str1,str2));
        System.out.println(maxPublicIncreasingStr_3(str1,str2));
    }

}

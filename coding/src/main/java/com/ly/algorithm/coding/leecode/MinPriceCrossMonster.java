package com.ly.algorithm.coding.leecode;

/**
 * int[] d,d[i] : i号怪兽的能力
 *
 * 	int[] p, p[i] : i号怪兽要求的钱
 *
 * 	开始时你的能力是0，你的目标从0号怪兽开始，通过所有怪兽，如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加在你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上，返回通过所有的怪兽，需要花的最小钱数
 * @author Ly
 * @create 2023/10/31 21:10
 * @desc
 **/
public class MinPriceCrossMonster {

    public static int minPriceCrossMonster(int[] d,int[] p){
        return cross(d,p,0,0);
    }

    /**
     * 对于任意i位置的怪兽，如果当前能力小于怪兽，只能给钱，如果当前能力大于等于怪兽，可以通过，也可以给钱。最后选取最小值。
     * @param d
     * @param p
     * @param index
     * @param level
     * @return
     */
    public static int cross(int[] d,int[] p,int index,int level){
        if(index == d.length){
            return 0;
        }
        int ans = 0;
        //能力小于怪兽，只能给钱
        if(level < d[index]){
            ans = cross(d,p,index+1,level+d[index])+p[index];
        }else{
            //选择给钱或者直接通过
            ans = cross(d,p,index+1,level);
            ans = Math.min(ans,cross(d,p,index+1,level+d[index])+p[index]);
        }
        return ans;
    }


    /**
     * 存在两个变量index以及level，所以是一个二维度的问题，index做行，level做列，其中level就是数组中的最大值，初始化最大列的值，可以直接通过，对于任意i,j位置，如果当前能力小于怪兽能力，那么只能选择给钱，即依赖于dp[i+1] [j+d[i]]位置的值再加上p[i]，如果当前能力大于等于怪兽能力，那么可以直接通过或者选择给钱。dp[i] [j] = Math.min(dp[i+1] [j] , dp[i+1] [j+d[i]]+p[i]),最后返回0,0位置的值。
     * @param d
     * @param p
     * @return
     */
    public static int minPriceCrossMonster_V2(int[] d,int[] p){
        int N = d.length;
        int maxLevel = 0;
        for(int i = 0;i<N ;i++){
            maxLevel += d[i];
        }
        int[][] dp = new int[N+1][maxLevel+1];
        for(int i = N-1;i>=0;i--){
            for(int j = maxLevel;j>=0;j--){
                if(j < d[i]){
                    dp[i][j] = dp[i+1][Math.min(j+d[i],maxLevel)]+p[i];
                }else{
                    dp[i][j] =  Math.min(dp[i+1][j],dp[i+1][Math.min(j+d[i],maxLevel)]+p[i]);
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 怪兽位置做行，钱数做列，对于任意i,j位置的含义是，如果要完全消耗j的钱数，能达到的最大能力是多少。列的最大值，如果无法完全消耗j，那么当前位置的能力为-1，而j的最大值为怪兽中所有金额的累加和（如果怪兽的金额过大，这种尝试不好）.值初始化，对于0列，所有位置都是-1。
     *
     * 对于0行，只有p[0]位置能获得最大能力，其余位置都是-1.对于任意i,j位置，
     *
     * 如果i位置之前已经超过了i位置怪兽的能力，那么此时可以选择不贿赂，那么dp[i] [j] = dp[i-1] [j]
     *
     * 也可以选择贿赂，那么dp[i-1] [j-p[i-1]] 必须大于-1，所以 dp[i] [j] = dp[i-1] [j-p[i-1]]  + d[i]
     *
     * 如果i位置之前没有超过i位置怪兽的能力，那么此时必须选择贿赂那么dp[i-1] [j-p[i-1]] 必须大于-1，所以 dp[i] [j] = dp[i-1] [j-p[i-1]]  + d[i]
     *
     * 最后返回最后一个行第一个不为-1的列
     * @param d
     * @param p
     * @return
     */
    public static int minPriceCrossMonster_V3(int[] d,int[] p){
        int N = d.length;
        int maxPrice = 0;
        for(int i = 0;i<N ;i++){
            maxPrice += p[i];
        }
        int[][] dp = new int[N][maxPrice+1];
        for(int i = 0;i<N;i++){
            dp[i][0] = -1;
            if(i == p[0]){
                dp[0][i] = d[i];
            }
        }
        for(int i = 1;i< N ;i++){
            for(int j = 1;j<=maxPrice;j++){
                int price = Math.max(0,j-p[i]);
                if(dp[i-1][j] < d[i]){
                    //此时必须贿赂
                    dp[i][j] = dp[i-1][price] == -1 ? -1 : dp[i-1][price]+d[i];
                }else{
                    dp[i][j] = dp[i-1][price] > -1 ? Math.max(dp[i-1][j],dp[i-1][price]+d[i]) : dp[i-1][j];
                }
                if(i == N-1 && dp[i][j] != -1){
                    return j;
                }
            }
        }
        return -1;
    }
}

package com.ly.algorithm.coding.leecode;

/**
 * 给出一组正整数arr，你从第0哥数向最后一个数，每个数的值表示你从这个位置可以向右跳跃的最大长度，计算如何以最少的跳跃次数跳到最后一个数
 * @author Ly
 * @create 2023/10/22 9:25
 * @desc
 **/
public class JumpGame {

    /**
     * 遍历数组，看每一个位置的值能到达的位置，分别尝试每个可能性
     * @param arr
     * @return
     */
    public static int jumpGame(int[] arr){
        return p(arr,0);
    }

    public static int p(int[] arr,int index){
        if(index >= arr.length){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= arr[index];i++){
            if(i < arr.length){
                min = Math.min(p(arr,index+i) + 1,min);
            }
        }
        return min;
    }

    /**
     * 存在一个变量index，所以准备一个一维数组dp，从后往前填，对于任意一个i位置，依赖于i~ i + arr[i] 范围中的最小值。最后返回dp[0]
     * @param arr
     * @return
     */
    public static int jumpGame_v2(int[] arr){
        int[] dp = new int[arr.length];
        for(int i = arr.length-2;i >= 0;i--){
            int min = Integer.MAX_VALUE;
            for(int j = 1;j <= arr[i];j++){
                if(i + j >= arr.length){
                    break;
                }
                min = Math.min(dp[i+j]+1,min);
            }
            dp[i] = min;
        }
        return dp[0];
    }

    /**
     * 准备三个变量
     *
     * 	step：当前的总步数
     *
     * 	curR：当前步数下的右边界
     *
     * 	next：下一步能达到的最右位置
     *
     * 	例如:3,1,4,2,1,1,5,2,1,1
     *
     * 	初始值，step = 0, curR = 0,next=-1 ,代表的含义，0步能达到的右边界是0，此时下一步能达到的位置3
     *
     * 	来到1位置，此时右边界是0，需要增加一步，此时的右边界就是next的值，step = 1, cur=3，next=2
     *
     * 	来到2位置，此时右边界是3，不需要增加部署，下一步能来到的位置是2+4 大于next，更新next，step=1,cur=3,next=6
     *
     * 	......
     * @param arr
     * @return
     */
    public static int jumpGame_v3(int[] arr){
        int step = 0;
        int curR = 0;
        int next = -1;
        for(int i = 0;i<arr.length;i++){
            if(curR < i){
                step++;
                curR = next;
            }
            next = Math.max(next,i+arr[i]);
        }
        return step;
    }
}

package com.ly.algorithm.coding.leecode;

/**
 *
 * 有N个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打包机上，放到每个机器上的这些物品数量有多有少，由于物品数量不相同，需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、每次只能搬一个物品进行移动，为了省力，只在相邻的机器上移动。请计算在搬动量最小轮数的前提下，使每个机器上的物品数量相等。如果不能使每个机器上的物品相同，返回-1.
 *
 * 	例如：[1,0,5]表示有3个机器，每个机器上分别有，1，0，5个物品，经过这些轮后：
 * 	第一轮：1 0 <-5 => 1 1 4
 *
 * 第二轮：1 < -1 < -4 => 2 1 3
 *
 * 	第三轮：2 1 <-3 => 2 2 2
 *
 * 	移动了3轮，每个机器上的物品相等，所以返回3
 *
 * 	例如[2,2,3]表示有3个机器，每个机器上分别由2、2、3个物品，这些物品不管怎么移动，都不能使三个机器上的物品数量相等，返回-1
 * @author Ly
 * @create 2023/9/19 21:08
 * @desc
 **/
public class PackingMachine {

    /**
     * 如果所有机器上的物品数量相加M%N 不等于0，那么此时永远不可能让每个机器上的货物都相等，直接返回-1
     *
     * 	假设此时总共有m台机器，来到任意i位置，左边有a台机器，右边有b台机器
     *
     * 	已知：
     *
     * 		i位置的左侧，一共有的物品数总数an，最后应该保留的数量为a * (m/n) , 那么左侧多出来的数量为 am = an - a * (m/n) (可以是负数)
     *
     * 		i位置的物品数量，in
     *
     * 		i位置的右侧，一共有的物品数总数bn = n - an -in , 最后应该保留的数量为 b*(m/n),那么右侧多出来的数量为bm = bn - b * (m/n) (可以是负数)。
     *
     * 		am > 0 && bm > 0 ,此时都需要将物品扔向i，总的轮数为max（am,bm)
     *
     * 		am > 0 && bm < 0 ，此时左侧的物品需要扔向i以及右侧，总的轮数为max(abs(am),abs(bm))
     *
     * 		am < 0 && bm > 0 , 此时右侧的物品需要扔向i以及左侧， 总的轮数为max（abs(am),abs(bm))
     *
     * 		am < 0 && bm < 0, 此时需要将物品从i位置向两侧扔，总的轮数为abs(am) + abs(bm)
     *
     * 		将每一个i位置的答案都求出来，再取最大值，就是总的需要的轮数
     * @param machines
     * @return
     */
    public static int packingMachine(int[] machines){
        int n = 0;
        for(int i = 0;i<machines.length;i++){
            //总的货物
            n += machines[i];
        }
        if(n % machines.length != 0){
            return -1;
        }
        //最终每一个机器上应该保留的货物
        int offset = n / machines.length;
        //i位置之前的物品数量
        int sum = 0;
        int ans = 0;
        for(int i = 0;i < machines.length;i++){
            //i位置左侧的物品总数量
            //i位置左侧应该有的物品数量
            int am = sum - i * offset;
            //i位置右侧的物品总数量
            //i位置右侧应该右的物品数量
            int bm = n - sum - machines[i] - (machines.length - 1 - i) * offset;
            if(am < 0 && bm < 0){
                //从i位置往两侧分拣
                ans = Math.max(ans,Math.max(ans,Math.abs(am) + Math.abs(bm)));
            } else{
                //求绝对值的最大值
                ans = Math.max(ans,Math.max(Math.abs(am),Math.abs(bm)));
            }
            sum += machines[i];
        }
        return ans;
    }

}

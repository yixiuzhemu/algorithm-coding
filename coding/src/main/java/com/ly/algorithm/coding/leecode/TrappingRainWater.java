package com.ly.algorithm.coding.leecode;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水
 *
 * 比如，arr={3，1，2，5，2，4}，根据值画出的直方图就是容器相撞，该容器可以装下5格水
 *
 * 再比如 arr={4，5，1，3，2，该容器可以装下2格水
 *
 * 对于任意i位置，如果知道左边的max值为M，右边的max值为N
 *
 * 如果arr[i] < M && arr[i] <N ,那么i位置的蓄水量为Math.min(M,N)-arr[i]
 *
 * 如果arr[i] < M && arr[i] > N 那么i位置的蓄水量为0
 *
 * 如果arr[i] > M && arr[i] < N 那么i位置的蓄水量为0
 *
 * 如果arr[i] > M && arr[i] > N 那么i位置的蓄水量
 *
 * 所以对于i位置来说蓄水量=Math.max(Math.min(M,N)-arr[i], 0)
 *
 * 求出所有i位置的蓄水量，那么总的蓄水量就能计算出
 *
 *
 *
 * @author Ly
 * @create 2023/9/20 21:22
 * @desc
 **/
public class TrappingRainWater {

    /**
     * 暴力嵌套循环，时间复杂度O（N^2）
     * @param arr
     * @return
     */
    public static int trappingRainWater_1(int[] arr){
        if(arr == null || arr.length < 3){
            return -1;
        }
        int ans = 0;
        for(int i = 1;i<arr.length-1;i++){
            int leftMax = 0;
            for (int m = i - 1;m>=0;m--) {
                leftMax = Math.max(leftMax,arr[m]);
            }
            int rightMax = 0;
            for(int m = i + 1;m <= arr.length - 1;m++){
                rightMax = Math.max(rightMax,arr[m]);
            }
            ans+= Math.max(Math.min(leftMax,rightMax) - arr[i] , 0);
        }
        return ans;
    }

    /**
     * 准备一个前缀预处理数组，一个后缀预处理数组，分别记录每个位置的值，时间复杂度O(N),额外空间复杂度O（N）
     * @param arr
     * @return
     */
    public static int trappingRainWater_2(int[] arr){
        if(arr == null || arr.length < 3){
            return -1;
        }
        int ans = 0;
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        int max = 0;
        for(int i = 0;i<arr.length;i++){
            max = Math.max(max,arr[i]);
            leftMax[i] = max;
        }
        max = 0;
        for(int i = arr.length-1;i>=0;i--){
            max = Math.max(max,arr[i]);
            rightMax[i] = max;
        }
        for(int i = 1 ;i <arr.length-1;i++){
            ans += Math.max(Math.min(leftMax[i],rightMax[i]) - arr[i] , 0);
        }
        return ans;
    }

    /**
     * 准备两个指针L,R，准备两个变量leftMax，rightMax，初始L=1,R=arr.length-2. leftMax=arr[0] rightMax = arr[arr.length - 1]
     *
     * 对于数组的两个边界来说，是肯定无法存储水的，所以L,R指针直接从1和arr.length-2位置开始
     *
     * 判断leftMax和rightMax，
     *
     * 	如果leftMax < rightMax，那么意味着在左侧区域，不会再有比leftMax还大的边界。此时可以直接更新L位置的水量 = Math.max[leftMax - arr[L],0] , 同时判断leftMax是否需要更新 Math.max(leftMax,arr[L++])
     *
     * 	如果leftMax > rightMax, 那么意味着在右侧区域，不会再有比rightMax还大的边界，此时可以直接更新R位置的水量=Math.max(rightMax-arr[L],0)，同时判断rightMax是否需要更新 Math.max(rightMax,arr[R--])
     * @param arr
     * @return
     */
    public static int trappingRainWater_3(int[] arr){
        if(arr == null || arr.length < 3){
            return -1;
        }
        int ans = 0;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int L = 1;
        int R = arr.length - 2;
        while( L <= R){
            if(leftMax < rightMax){
                //更新左边L
                ans += Math.max(leftMax - arr[L] , 0);
                leftMax = Math.max(leftMax,arr[L++]);
            }else if(leftMax > rightMax){
                //更新右边R
                ans += Math.max(rightMax - arr[R],0);
                rightMax = Math.max(rightMax,arr[R--]);
            }else {
                //两边同时更新
                ans += Math.max(leftMax - arr[L] , 0);
                leftMax = Math.max(leftMax,arr[L++]);
                ans += Math.max(rightMax - arr[R],0);
                rightMax = Math.max(rightMax,arr[R--]);
            }
        }
        return ans;
    }

    /**
     * 如果给你一个二维数组，每一个值表示一块地形的高度，求整块地形能装下多少水
     *
     * 前提：任意一个位置[i,j] 只算上下左右四个方向，不考虑斜角方向
     *
     * 	这个问题的本质实际上是找边缘最薄弱的位置
     *
     * 	例如：
     *
     * 	33333133333
     *
     * 	32211111333
     *
     * 	33333333333
     *
     * 	其中第一行的1 就是整个二维数组中的最薄弱的位置
     *
     * 	所以准备一个小根堆
     *
     * 	小根堆中每个节点以[num,row,cell]节点进行组织，按照num进行排序
     *
     * 	准备一个boolean数组，记录i,j位置是否加入过小根堆，已经加入过的小根堆不再加入小根堆
     *
     * 	准备一个max，这个max记录当前弹出小根堆中所有位置的最大值
     *
     * 	流程：
     *
     * 		先将所有边缘加入到小根堆中，此时弹出堆顶val
     *
     * 		判断val跟max的大小，如果大于max，那么更新此时的max
     *
     * 		并且判断此时i,j位置上、下、左、右四个方位是否加入过小根堆，如果存在并且没有加入过，那么将他们都加入小根堆，并且计算每一个位置跟max的差值，差值就是当前位置的存水量。（因为边缘都加入了小根堆，所以能够知道在二维数组的边缘中，不会有再比当前堆顶更小的瓶颈的，所以每个位置的水一定至少都能涨到瓶颈位置）
     * @param arr
     * @return
     */
    public static int trappingRainWater(int[][] arr){
        int max = Integer.MIN_VALUE;
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        boolean[][] isEnter = new boolean[arr.length][arr[0].length];
        //将边缘放入到小根堆中
        for(int i = 0; i < arr.length;i++){
            nodes.add(Node.builder().num(arr[i][0]).row(i).cell(0).build());
            nodes.add(Node.builder().num(arr[i][arr[0].length-1]).row(i).cell(arr[0].length-1).build());
            isEnter[i][0] = true;
            isEnter[i][arr[0].length-1] = true;
        }

        for(int j = 1;j<arr[0].length-1;j++){
            nodes.add(Node.builder().num(arr[0][j]).row(0).cell(j).build());
            nodes.add(Node.builder().num(arr[arr.length-1][j]).row(arr.length-1).cell(j).build());
            isEnter[0][j] = true;
            isEnter[arr.length-1][j] = true;
        }
        //从小根堆中弹出堆顶
        int ans = 0;
        while(!nodes.isEmpty()){
            Node poll = nodes.poll();
            //是否更新此时的瓶颈
            max = Math.max(poll.num,max);
            int row = poll.row;
            int cell = poll.cell;
            //上、下、左、右判断是否加入过小根堆，并且计算水量
            //上
            if(row - 1 >= 0 && !isEnter[row-1][cell]){
                //计算水量
                ans += Math.max(max - arr[row-1][cell],0);
                //加入小根堆
                nodes.add(Node.builder().num(arr[row-1][cell]).row(row-1).cell(cell).build());
                isEnter[row-1][cell] = true;
            }
            //下
            if(row + 1 < arr.length && !isEnter[row+1][cell]){
                //计算水量
                ans += Math.max(max - arr[row+1][cell],0);
                //加入小根堆
                nodes.add(Node.builder().num(arr[row+1][cell]).row(row+1).cell(cell).build());
                isEnter[row+1][cell] = true;
            }
            //左
            if(cell - 1 >= 0 && !isEnter[row][cell-1]){
                //计算水量
                ans += Math.max(max - arr[row][cell - 1],0);
                //加入小根堆
                nodes.add(Node.builder().num(arr[row][cell - 1]).row(row).cell(cell - 1).build());
                isEnter[row][cell - 1] = true;
            }
            //右
            if(cell + 1 < arr[0].length && !isEnter[row][cell+1]){
                //计算水量
                ans += Math.max(max - arr[row][cell + 1],0);
                //加入小根堆
                nodes.add(Node.builder().num(arr[row][cell + 1]).row(row).cell(cell + 1).build());
                isEnter[row][cell + 1] = true;
            }
        }
        return ans;
    }

    @Data
    @Builder
    public static class Node implements Comparable<Node> {
        private int num;

        private int row;

        private int cell;

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }


}

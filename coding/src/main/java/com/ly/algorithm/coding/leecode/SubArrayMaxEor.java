package com.ly.algorithm.coding.leecode;

import lombok.Builder;
import lombok.Data;

/**
 * 一个数组的异或和是指数组中所有的数异或在一起的结果
 *
 * 给定一个数组arr，求最大子数组异或和
 * @author Ly
 * @create 2023/10/17 20:48
 * @desc
 **/
public class SubArrayMaxEor {

    /**
     * 找出所有的子数组，再求每个子数组的异或和，时间复杂度O(N^3)
     * @param arr
     * @return
     */
    public static  int getSubArrayMaxEor(int[] arr){
        int ans = 0;
        for(int i = 0;i < arr.length;i++){
            for(int j = i; j < arr.length;j++){
                int eor = arr[i];
                for(int k = i+1;k<=j;k++){
                    eor ^= arr[k];
                }
                ans = Math.max(ans,eor);
            }
        }
        return ans;
    }

    /**
     * 准备一个异或和数组EOR，记录以i位置作为结尾的异或和
     *
     * 	再遍历数组，对于任意i位置，计算0~j中每一个位置作为开头，以i位置作为结尾，计算出0~i范围中所有子数组的异或和, 对
     * 于任意j位置开头，已知j位置结尾的异或和为EOR[j] , i位置结尾的异或和为EOR[I]，那么J~i 位置的异或值为EOR[I] ^ EOR[J]，时间复杂度O(N^2)
     * @param arr
     * @return
     */
    public static int getSubArrayMaxEor_V2(int[] arr){
        int[] eors = new int[arr.length];
        eors[0] = arr[0];
        for(int i = 1; i < arr.length;i++){
            eors[i] = eors[i-1] ^ arr[i];
        }
        int ans = eors[0];
        for(int i = 0; i < arr.length;i++){
            ans = Math.max(eors[i],ans);
            for(int j = 0; j < i;j++){
                ans = Math.max(ans,eors[i] ^ eors[j]);
            }
        }
        return ans;
    }

    /**
     * 前缀树优化
     *
     * 	例如：arr={11,1,15,10,13,4},现在要求以4作为结尾情况下的子数组最大异或和
     *
     * 	0 = 0000
     *
     * 	eor[0] = arr[0..0] 的异或和 = 11 = 1011
     *
     * 	eor[1] = arr[0..1]的异或和=11 ^ 1 = 1010
     *
     * 	eor[2] = arr[0..2]的异或和=11 ^ 1 ^ 15 = 0101
     *
     * 	eor[3] = arr[0..3]的异或和=11 ^ 1 ^ 15 ^ 10= 1111
     *
     * 	eor[4] = arr[0..4]的异或和=11 ^ 1 ^ 15 ^ 10 ^ 13= 0010
     *
     * 	eor[5] = arr[0..5]的异或和=11 ^ 1 ^ 15 ^ 10 ^ 13 ^ 4= 0110
     *
     * 	遍历数组，将所有值的二进制组织成前缀树
     *
     * 	遍历数组，将i位置之前的数都加入前缀树，组织前缀树，计算任意i位置的最大异或值时，去前缀树上找异或上当前位置上的值最大的一条路径
     *
     * 即如果当前位是0，那么就去找1，如果当前位是1，那么就去找0
     *
     * 	对于任意int类型的数，符号位，尽量遇到与自身符号相同，其他位，无论整体是正还是负，从高位到低位都是优先变成1，（对于负数，需要进行补码，所以全是1时，值等-1）
     *
     * 	关键点：
     *
     * 	构建前缀树时，从高位依次获得每一位的状态，int类型总共32位，从第32位开始，往右移动31位，再与上1，那么就获得了最左侧的状态，即( arr[i] >> 31) & 1
     *
     * 	从前缀树种找到异或上arr[i]的值能得到最大值，此时需要知道arr[i]的每一位的状态（和构建前缀树的逻辑一样），当获取到当前位时，如果当前位为0，那么去找1，当前位为1，那么去找0，即优先让当前位的异或值为1。最后再将当前为还原，或到结果中，即 res | (path ^ best) << move ，其中path代表第move位的原始的状态，best代表异或上当前位的哪个值（0还是1），最后左移move位。
     * @param arr
     * @return
     */
    public static int getSubArrayMaxEor_V3(int[] arr){
        NumTrie numTrie = new NumTrie();
        //一个数也没有的时候，异或和为0
        numTrie.add(0);
        int eor = 0;
        int ans = 0;
        for(int i = 0;i<arr.length;i++){
            eor ^= arr[i];
            //返回以i结尾的最大异或和
            ans = Math.max(ans,numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return ans;
    }

    public static class Node{
        /**
         * 路径
         */
        private Node[] nexts = new Node[2];
    }

    public static class NumTrie{

        public Node head = new Node();

        public void add(int newNum){
            Node cur = head;
            for(int move = 31; move >= 0;move--){
                //从高位到低位，取出每一位的状态,取出最左边的1
                //取最右边的1，取反 加一 再与上自身
                int path = ((newNum >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        /**
         * 如果传入sum，判断前缀树中谁和sum异或 值最大,把结果返回
         * @param sum
         * @return
         */
        public int maxXor(int sum){
            Node cur = head;
            int res = 0;
            for(int move = 31; move >= 0 ; move--){
                int path = ((sum >> move)) & 1;
                //判断当前位的路径
                //代表符号位，那么此时就是等于path
                //其他位，则相反
                int best = move == 31 ? path : (path ^ 1);
                //如果当前best路径不存在，那么只能取之前的路径
                best = cur.nexts[best] == null ? (best ^ 1) : best;
                //对当前位进行计算,结果或上当前位的值
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }

}

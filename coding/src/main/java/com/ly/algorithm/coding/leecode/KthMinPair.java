package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Ly
 * @create 2023/9/23 11:13
 * @desc
 **/
public class KthMinPair {

    @Data
    @Builder
    public static class Node{
        private int key;

        private int val;

        @Override
        public String toString() {
            return "("  + key +
                    "," + val +
                    ')';
        }
    }

    /**
     * 暴力生成数值对，再从小到大进行排序，时间复杂度O(N^2)
     * @param arr
     * @param K
     * @return
     */
    public static String kthMinPair_V1(int[] arr,int K){
        if(K > arr.length * arr.length){
            return null;
        }
        List<Node> nodeList = Lists.newArrayList();
        for (int k : arr) {
            for (int v : arr) {
                nodeList.add(Node.builder().key(k).val(v).build());
            }
        }
        Collections.sort(nodeList, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.key > o2.key){
                    return 1;
                }else if(o1.key < o2.key){
                    return -1;
                }else{
                    if(o1.val > o2.val){
                        return 1;
                    }else if(o1.val < o2.val){
                        return -1;
                    }
                }
                return 0;
            }
        });
        return nodeList.get(K-1).toString();
    }

    /**
     * 假设现在数组是有序的，那么求第K小数值对，对于数值对的第一位，可以直接进行定位
     *
     * 		例如数组长度为N，那么每一个位置能形成的数值对的长度都是N，所以数值对的第一位所在的位置=K-1/N
     *
     * 		例如：数组长度为10，每一个位置能形成长度为10的数值对，如果K=11. 最后K所在的位置一定是在第二个数（下标从0开始计算）形成的数值对中
     *
     * 		数值对所在位置为K-1/N = f ，那么就可以确认在数组arr中，有a个数小于f，有b个数等于f
     *
     * 		对于小于f的数，可以直接排除掉，也就是可以直接过滤掉 a * N 个数
     *
     * 		那么接下来只需要在b个f中找到第  S = K - a * N 小的数即可，所以第二个数= S - 1 / b
     *
     * 		例如：数组长度为10，要找第56的数，1122233345，此时f = 3，所以可以将11222 形成的数值对都排除掉 56 - 50 = 6，然后再在数组上找6 - 1 /3 = 2 (因为已经知道f有3个，那么对于数组上每一个位置，都可以形成长度为3的第二个数，所以就是除以3) ,第二个数p = 2，所以最后形成的数值对为（3,2）
     *
     * 		所以大的流程变成：
     *
     * 			排序（O(N * logN)） 快排
     *
     * 			找到数值对第一个数 k-1/N O(1)
     *
     * 			找到<f 数的个数a，等于f数的个数 b O(N)
     *
     * 			最后再找到第二个数 (K - a * N) - 1/ b，O(1)
     *
     * 		但是实际上排序的目的，就是为了在一个无序数组中找到第K小的数，所以可以使用bfprt算法（O(N)） ,但是bfprt算法的流程较为复杂， 所以直接使用改进后的快排即可。
     * @param arr
     * @param K
     * @return
     */
    public static String kthMinPair_V2(int[] arr,int K){
        //使用改进后的快排找到第K小的数
        int N = arr.length;
        if(K > N * N){
            return null;
        }
        //找到数值对的第一位数
        int f = quickSort(arr,0,arr.length-1,(K - 1)/N);
        //遍历数组，找到小于f的个数，等于f的个数
        int a = 0;
        int b = 0;
        for (int i : arr) {
            if(i < f){
                a++;
            }else if(i == f){
                b++;
            }
        }
        //直接排除掉小于f的a组数后，需要定位到第S小的数
        int S = K - a * N;
        //数值对的第二位数，就是在b个f中找到对应的位置 ,在数组中，对于b个f，每个位置都能形成b长度的规模，所以第S小的数= S-1/b
        int p = quickSort(arr,0,arr.length-1, (S-1)/b);
        return "("+f+","+p+")";
    }

    /**
     * 快排改进写法，时间复杂度为O(N)
     * @param arr
     * @param L
     * @param R
     * @param K
     * @return
     */
    public  static int quickSort(int[] arr,int L ,int R,int K){
        if(L >= R){
            return arr[L];
        }
        int M = arr[L + (int)(Math.random() * (R-L))];
        int less = L-1;
        int more = R+1;
        int index = L;
        while(index < more){
            if(arr[index] > M){
                swap(arr,index,--more);
                continue;
            }else if(arr[index] < M){
                swap(arr,index,++less);
            }
            index++;
        }
        if(K <= less){
            return quickSort(arr,L,less,K);
        }
        if(K >= more){
            return quickSort(arr,more,R,K);
        }
        return M;
    }

    public static void swap(int[] arr,int i,int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}

package com.ly.algorithm.coding;

/**
 * 获取第K小的数据
 * @author Ly
 * @create 2021/9/13 14:42
 * @desc
 **/
public class MinKthCoding {

    public static int getMinKth(int[] arr,int L,int R,int index){
        if(L == R){
            return arr[L];
        }
        int pivot = arr[L + (int)(Math.random() * (R-L+1))];
        int[] range = partition(arr,L,R,pivot);
        if(index >= range[0] && index <= range[1]){
            return arr[index];
        }else if(index < range[0]){
            return getMinKth(arr,L,range[0]-1,index);
        }else{
            return getMinKth(arr,range[1]+1,R,index);
        }
    }

    public static int[] partition(int[] arr,int L,int R ,int pivot){
        int less = L - 1;
        int more = R + 1;
        int index = L;
        while(index < more){
            if(arr[index] > pivot){
                SortCoding.swap(arr,index,--more);
            }else if(arr[index] < pivot){
                SortCoding.swap(arr,index++,++less);
            }else{
                index++;
            }
        }
        return new int[]{less,more};
    }

    /**
     * bfprt算法
     * 1.将数组每5个数分成一组，一共N/5 组，在每一组中保证有序（每组排序，事件复杂度0(1)），整体时间复杂度 O(N)
     * 2.遍历每个分组，取出每一组的中位数，构造出一个新数组，大小为N/5,时间复杂度为T(N/5)
     * 3.此时再从新数组中取出中位数作为进行partition的值，（新数组的中位数，就是新数组中第K大的数） 时间复杂度为T(7/10 N)
     *     3.1 使用bfprt计算新数组的中位数，那么需要分别知道小于区域最多会有多少个数，大于区域会有多少个数
     *     3.2 求小于区域最多会有多少个数，可以转换成求大于区域最少有多少个数，大于区域中比当前中位数都要大，而大于区域中的每一个数在为5的分组中，都有2个数比自己要大，所以在整个数组中 共有 (3/10)N个数至少比中位数大，
     *          那么小于区域最多有（7/10）N个数
     *     3.3 同理可知大于区域最多也有(7/10)N个数，所以此时该操作的时间复杂度为T(7/10)N
     * 4.再使用该值 对原数组进行partition操作。
     * @param arr
     * @param L
     * @param R
     * @param index
     * @return
     */
    public static int bfprt(int[] arr,int L,int R,int index){
       if(L == R){
           return arr[L];
       }
       int pivot = getMedian(arr,L,R);
       int[] range = partition(arr,L,R,pivot);
       if(index >= range[0] && index <= range[1]){
           return arr[index];
       }else if(index < range[0]){
           return bfprt(arr,L,range[0] -1 ,index);
       }else{
           return bfprt(arr,range[1]+1,R,index);
       }
    }

    public static int getMedian(int[] arr,int L,int R){
        int size = R - L + 1;
        int offset = size % 5 > 0? 1 : 0;
        int[] medianArr = new int[(arr.length/5) + offset];
        for(int i = 0;i<medianArr.length;i++){
             int left = L + 5 * i;
             int right = left + 4;
             medianArr[i] = getMedianOfMedian(arr,left,Math.min(R,right));
        }
        return bfprt(medianArr,0,medianArr.length - 1,medianArr.length/2);
    }

    public static int getMedianOfMedian(int[] arr,int L,int R){
        for(int i = L;i < R;i++){
            for(int j = L + 1;j < R ;j++){
                if(arr[i] > arr[j]){
                    SortCoding.swap(arr,i,j);
                }
            }
        }
        return arr[(R-L)/2];
    }



}

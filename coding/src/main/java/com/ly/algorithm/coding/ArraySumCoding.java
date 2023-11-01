package com.ly.algorithm.coding;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Ly
 * @create 2023/8/11 11:00
 * @desc
 **/
public class ArraySumCoding {


    /**
     * 从每一个位置开始往后尝试，如果小于N，继续往后累加，如果等于N，返回长度，如果大于N，返回0
     * @param arr
     * @param N
     * @return
     */
    public static int maxLength(int[] arr,int N){
        //从每一个位置出发，向后进行累加，当累加和等于N时，记录长度
        int length = 0;
        for(int i = 0;i<arr.length;i++){
            if(arr[i] == N){
                length = Math.max(1,length);
                continue;
            }else if(arr[i] > N){
                continue;
            }
            int sum = arr[i];
            int len = 1;
            for(int j = i+1;j<arr.length;j++){
                sum += arr[j];
                if(sum == N){
                    length = Math.max(length,len+1);
                }else if(sum < N){
                    len++;
                }else {
                    break;
                }
            }
        }
        return length;
    }

    /**
     * 存在单调性的问题，一定存在优雅的解法，
     *
     * 准备头尾两个指针分别往中间移动，或者准备一个窗口，从左往右滑动。
     *
     * 例如现在要找到数组[4,3,2,6,3,2,1,1,3,2,6]累加和为6的最大子数组长度
     *
     * 准备一个窗口，最开始窗口的长度为0，窗口中的数累加和此时也为0
     *
     * 窗口移动规律
     *
     * 1、当窗口累加和小于sum时，窗口右边界向右移动
     *
     * 2、当窗口累加和大于sum时，窗口左边界向右移动
     *
     * 3、当窗口累加和等于sum时，记录此时窗口的长度
     * @param arr
     * @param N
     * @return
     */
    public static int maxLengthV2(int[] arr,int N){
        int l = 0;
        int r = 0;
        int windowSum = arr[0];
        int length = 0;
        while (r < arr.length ){
            if(windowSum < N){
                r++;
                if(r == arr.length){
                    break;
                }
                windowSum += arr[r];
            }else if(windowSum > N){
                windowSum -=  arr[l++];
            }else{
                r++;
                length = Math.max(length,r-l+1);
                //移动左边可以少一个边界判断
                windowSum -= arr[l++];
            }
        }
        return length;
    }


    /**
     * 在一个有正数、负数、0的数组中，再给定一个值N，求数组的子数组（连续）累加和等于N的最大长度
     *
     * 由于扩大范围和缩小范围不再具有单调性，所以不能用上面的方法进行处理
     *
     * 需要了解一个原理
     *
     * 假设现在知道0~I位置上的累加和是1000，0~j位置累加和是800，那么j位置一定是i位置累加和为200出现最早的位置。
     *
     * 所以可以准备一个map，map中存放累加和，以及出现这个累加和最早的位置（如果出现相同的累加和，不更新）
     *
     * 例如数组[5,6,4,-3,0,0]，求累加和为10
     *
     * 从0开始遍历，当i=0时，此时累加和为5，5-10 = -5 ，找之前位置是否存在累加和为-5，不存在，那么当前位置一定不存在以它结尾的子数组的累加和能等于10，将5放入map中 key=5 value=0
     *
     * 当i=1时，此时累加和为11，11-10 = 1，找之前位置是否存在累加和为1，不存在，那么当前位置一定不存在以它结尾的子数组的累加和能等于10，将5放入map中 key=11 value=1
     *
     * 当i=2时，此时累加和=15，15-10=5，找之前的位置是否存在累加和为5，存在，位置为0，那么1~2位置就是以当前数位结尾，能得到累加和的最大子数组长度。
     *
     * 。。。。
     *
     * 对于累加和map，一开始需要将累加和位0，位置-1放入到map中，否则后续位置，如果出现刚好累加和等于0，此时是找不到位置的，此时就会出现距离计算错误。
     *
     * 例如数组[5,1,-1,-5,10]，当i来到3位置时，此时累加和为0，如果map中没有记录，那么会key=0，value=3放入到map中，此时到10累加和刚好等于10，此时算出的长度为1.
     * @param arr
     * @param N
     * @return
     */
    public static int maxLengthV3(int[] arr,int N){
        Map<Integer,Integer> sumMap = Maps.newHashMap();
        sumMap.put(0,-1);
        int length = 0;
        int sum = 0;
        for(int i = 0;i<arr.length;i++){
            sum += arr[i];
            int lastSum = sum - N;
            Integer index = sumMap.get(lastSum);
            if(index != null){
                length = Math.max(length,i - index);
            }else{
                sumMap.put(sum,i);
            }
        }
        return length;
    }

    /**
     * 在一个有正数、负数、0的数组中,求含有1和含有2数量相同的子数组最大长度
     *
     * 将数组中，不为1的数都变成0，将1保持为1，将2变成-1，然后再遍历数组，计算累加和为0的子数组最大长度
     * @param arr
     * @return
     */
    public static int maxSameLength(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        for(int i = 0;i<arr.length;i++){
            if(arr[i] == 1){
                continue;
            }
            if(arr[i] == 2){
                arr[i] = -1;
                continue;
            }
            arr[i] = 0;
        }
        return maxLengthV3(arr,0);
    }

    /**
     *
     * 、在一个有正数、负数、0的数组中,给定一个数K，在子数组累加和中只要都小于等于K，那么子数组达标，求最大的子数组长度
     *
     * 定义概念：以i位置开头的子数组，能让累加和最小的范围最大是多少
     *
     * 准备两个数组minSum和minEnd，从右往左遍历，当得到累加和最小时得到最大范围后，将范围的结尾位置放在minEnd中。
     *
     * 例如数组：3,7,4,-6,6,3,-2,0,7,-3,2
     *
     * 当i=10时，此时以10位置为开头，最小累加和就是2，此时结尾位置也是10，minSum：[2] ，minEnd:[10]
     *
     * 当i=9时，此时以9位置为开头，最小累加和为-3+10位置的最小累加和，结果大于-3，所以结尾位置也是9，minSum[-3,2], minEnd:[9,10]
     *
     * 当i=8时，此时以8位置开头，最小累加和为7+9位置的最小累加和，结果小于7，所以结尾位置就是9位置的结尾位置，minSum[4,-3,2] ,minEnd[9,9,10]
     *
     * ..... 遍历结束后，得到最终的minSun数组和minEnd数组
     *
     * minSum: [3, 5, -2, -6, 6, 1, -2, 0, 4, -3, 2]
     *
     * minEnd: [0, 3, 3, 3, 4, 7, 7, 7, 9, 9, 10]
     *
     * 现在要计算累加和小于K的
     *
     * 从0位置开始遍历，每一个位置的累加和我们是知道的，那么在遍历时，只需要累加和往右扩即可，因为minSum中一定记录的是最小能扩到的累加和
     * @param arr
     * @param K
     * @return
     */
    public static int maxLengthLessThanK(int[] arr,int K){
        int[] minSum = new int[arr.length];
        int[] minEnd = new int[arr.length];
        minSum[arr.length-1] = arr[arr.length-1];
        minEnd[arr.length-1] = arr.length-1;
        for(int i = arr.length-2;i>=0;i--){
           if(arr[i] + minSum[i+1] > arr[i]){
               minSum[i] = arr[i];
               minEnd[i] = i;
           }else{
               minSum[i] = arr[i]+minSum[i+1];
               minEnd[i] = minEnd[i+1];
           }
        }
        int length = 0;
        for(int i = 0;i<minSum.length;i++){
            int start = i;
            int end = minEnd[i];
            int sum = minSum[i];
            if(sum > K){
                continue;
            }
            for(int j = end+1;j<minSum.length;j++){
                sum += minSum[j];
                if(sum >  K){
                    break;
                }else{
                    end = minEnd[j];
                    j = end;
                }
            }
            length = Math.max(length,end-start+1);
        }
        return length;
    }


    /**
     * 暴力解是精确的计算了每个位置的累加和，当我们计算出i位置一直扩到J位置，就无法再扩展下一个累加和了，因为再扩展就超过K了，所以以i位置开头的最大长度就是j-i。
     *
     * 而通过i位置的累加和减去arr[i]，就能知道i+1到j位置的累加和。
     *
     * 如果发现这个累加和还是无法右扩，那么当前位置就不需要再去求它了，因为即使在小于j的位置存在一个位置m，能计算出j+1到m的累加和是小于K的，但是这个结果的长度一定是小于j-i的，所以不需要计算这个结果。
     *
     * 直到某个位置开始可以开始往右扩，如果一直扩到j位置，都没有这样的值，那么直接到j+1位置开始进行判断。
     * @param arr
     * @param K
     * @return
     */
    public static int maxLengthLessThanKV2(int[] arr,int K){
        int[] minSum = new int[arr.length];
        int[] minEnd = new int[arr.length];
        minSum[arr.length-1] = arr[arr.length-1];
        minEnd[arr.length-1] = arr.length-1;
        for(int i = arr.length-2;i>=0;i--){
            if(arr[i] + minSum[i+1] > arr[i]){
                minSum[i] = arr[i];
                minEnd[i] = i;
            }else{
                minSum[i] = arr[i]+minSum[i+1];
                minEnd[i] = minEnd[i+1];
            }
        }
        //end代表无法再下扩的位置
        int end = 0;
        //i一直扩到end的累加和
        int sum = 0;
        int length = 0;
        for(int i = 0;i < arr.length;i++){
            while(end < arr.length && sum + minSum[end] <= K){
                sum += minSum[end];
                end = minEnd[end]+1;
            }
            //当无法再右扩时，得到它的长度
            length = Math.max(length,end-i);
            if(end > i){
                //中间还有其他位置；
                //将最左边的数移除
                sum -= arr[i];
            }else{
                //窗口已经没有数了，此时移动到下一个位置计算
                end = i+1;
            }
        }
        return length;
    }

}

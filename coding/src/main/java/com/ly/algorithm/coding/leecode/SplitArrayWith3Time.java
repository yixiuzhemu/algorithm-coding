package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 给定一个正数数组arr，返回该数组能不能分成4个部分，并且每个部分的累加和相等，切分位置的数不要。
 *
 * 例如：arr=[3,2,4,1,4,9,5,10,1,2,2] 返回true
 *
 * 三个切割点下表为2，5，7，切出的四个子数组为[3,2] [1,4] [5] [1,2,2] ,累加和都是5
 * @author Ly
 * @create 2023/10/24 20:23
 * @desc
 **/
public class SplitArrayWith3Time {

    /**
     * 隐藏条件，由于都是正数数组，所以每个位置的累加和一定不等。准备一个Map，记录所有累加和的位置。
     *
     * 	如果要将数组切成四部分，那么必然需要切三刀
     *
     * 	对于第一刀：位置必须>=1 , <= N-6
     *
     * 	对于第二刀：位置必须>=3，<= N-4
     *
     * 	对于第三刀：位置必须>=5, <=N-2
     *
     * 	遍历数组计算出所有位置的前缀和
     *
     * 	对于第一刀，如果切在i位置，假设i位置之前的累加和为X，i位置的值为a，那么此时如果要切出累加和相等的部分，那么必然在后续需要存在累加和为2X+a,如果不存在，那么代表当前位置不能进行切分。
     *
     * 	对于第二刀，通过第一刀的位置，找到第二刀的位置j，假设此时j位置的值为b，那么此时需要知道是否有位置的值等于3x+a+b，如果存在，那么此时的位置就是第三刀的位置
     *
     * 	对于第三刀，如果切完三刀后，末尾的部分累加和也为X，那么就查询此时是否4x+a+b+c的累加和的位置是否等于N-1，如果等于，那么这种切分方案可行，如果不等于，那么方案不可行。
     * @param arr
     * @return
     */
    public static boolean canSplit(int[] arr){
        if(arr.length < 7){
            return false;
        }
        Map<Integer,Integer> sumMap = Maps.newHashMap();
        int sum = 0;
        for (int i = 0;i < arr.length;i++) {
            sum+=arr[i];
            sumMap.put(sum,i);
        }
        sum = arr[0];
        //对于第一刀从i=1位置开始尝试，一直到N-6位置
        for(int i = 1; i <= arr.length-6;i++){
            Integer firstVal = arr[i];
            Integer curSum = sum;
            sum += firstVal;
            //当前位置之前的累加和为sum,即X
            //如果第一刀的位置成立，那么第二刀的位置的累加和等于2X + a
            Integer scend = sumMap.get(2 * curSum + firstVal);
            if(scend == null || scend > arr.length-4){
                //代表当前不存在
                continue;
            }
            //如果scend存在，那么第二刀的位置等于scend+1
            scend = scend+1;
            Integer scendVal = arr[scend];
            //判断第三刀的位置3X + a + b
            Integer third = sumMap.get(3 * curSum + firstVal + scendVal);
            if(third == null || third > arr.length - 2){
                continue;
            }
            //验证三刀之后的累加和,4X+a+b+c
            third++;
            Integer thirdVal = arr[third];
            Integer last = sumMap.get(4*curSum + firstVal+scendVal+thirdVal);
            if(last != null && last == arr.length-1){
                return true;
            }
        }
        return false;
    }

}

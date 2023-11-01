package com.ly.algorithm.coding.leecode;

/**
 * 给定一个无序数组arr，如果只能在一个子数组上排序，返回如果让arr整体有序，需要排序的最短子数组长度
 *
 * 	例如：1，2，5，3，2，4，6，7
 *
 * 	此时只需要将5，3，2，4进行排序即可
 * @author Ly
 * @create 2023/10/25 20:40
 * @desc
 **/
public class MinLengthForSort {

   /**
    * 先进行一次从左到右的遍历，准备一个变量maxLeft，记录遍历过程中的最大值，一旦遇到某个位置的值小于或等于maxLeft时，不更新maxLeft，直到某个位置i再次大于maxLeft，此时记录最右侧位置i。
    *
    * 	再从右往左遍历一次，准备一个遍历minRight，记录遍历过程中的最小值，一旦遇到某个位置的值大于或等于minRight时，不更新minRight，制动某个位置j再次小于minRight，记录此时的最左侧位置j
    *
    * 	那么j~i范围就是需要排序的范围
    *
    * 	对于maxLeft，如果需要排序，那么从左到右maxLeft是依次增大的，当遇到maxLeft不需要更新时，那么证明此时出现了数变小的情况，而记录最右侧的不更新maxLeft的位置，就意味着这个位置之后会重新开始更新maxLeft。
    *
    * 	对于minRight同理
    * @param arr
    * @return
    */
   public static int getMinLength(int[] arr){
      int maxLeft = 0;
      int i = 0;
      int minRight = Integer.MAX_VALUE;
      int j = Integer.MAX_VALUE;
      for(int m = 0; m < arr.length;m++){
         if(arr[m] < maxLeft){
            i = m;
         }else{
            maxLeft = Math.max(maxLeft,arr[m]);
         }
      }
      if(maxLeft == -1){
         return 0;
      }
      for(int m = arr.length-1;m>=0;m--){
         if(minRight < arr[m]){
            j = m;
         }else{
            minRight = Math.min(minRight,arr[m]);
         }
      }
      return i - j+1;
   }

   public static void main(String[] args) {

   }

}

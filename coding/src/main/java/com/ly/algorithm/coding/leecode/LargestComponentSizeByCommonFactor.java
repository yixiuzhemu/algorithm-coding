package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Stack;

/**
 * @author Ly
 * @create 2023/10/28 8:55
 * @desc
 **/
public class LargestComponentSizeByCommonFactor {

    /**
     * 遍历数组，从每个位置往后去尝试，判断是否具有公共因子，如果具有公共因子，那么将他们所在的域进行合并，时间复杂度O(N^2)
     * @param arr
     * @return
     */
    public static int largestComponentSize(int[] arr){
        UnionSet unionSet = new UnionSet(arr.length);
        for(int i = 0;i<arr.length;i++){
            for(int j = i+1;j<arr.length;j++){
                if(gcd(arr[i],arr[j]) != 1){
                    //最大公约数不等于1，那么将他们进行合并
                    unionSet.union(i,j);
                }
            }
        }
        return unionSet.maxSize();
    }

    /**
     * 准备一个map，遍历数组，对于当前数v从1~v开始尝试，并且将过程中出现的质数因子加入到map中，如果后续出现了相同的质数因子，那么将他们进行合并，时间复杂度O(N * V) , 如果v小于N，那么可以使用这个方式
     * @param arr
     * @return
     */
    public static int largestComponentSize_v2(int[] arr){
        UnionSet unionSet = new UnionSet(arr.length);
        Map<Integer,Integer> indexMap = Maps.newHashMap();
        for(int i = 0;i<arr.length;i++){
            for(int j = 2; j<=arr[i];j++){
                if(arr[i]%j == 0 && practice(j)){
                    Integer before = indexMap.get(j);
                    if(before == null){
                        indexMap.put(j,i);
                    }else {
                        unionSet.union(before,i);
                    }
                }
            }
        }
        return unionSet.maxSize();
    }

    /**
     * 对于优化1中，遍历数组，对于当前数V，实际上可以不用从1~v开始进行尝试，而是从1~根号v进行尝试，每次得到两个因子，只保留大于1的因子，这样到根号v * 根号v，就能试出所有的因子组合，再将他们放入map中，如果后续出现了相同的质数因子，那么将他们进行合并，时间复杂度O（N*根号v）
     * @param arr
     * @return
     */
    public static int largestComponentSize_v3(int[] arr){
        UnionSet unionSet = new UnionSet(arr.length);
        Map<Integer,Integer> indexMap = Maps.newHashMap();
        for(int i = 0;i<arr.length;i++){
            int num = (int)Math.sqrt(arr[i]);
            for(int j = 1; j<=num;j++){
                if(arr[i]%j != 0){
                   continue;
                }
                if(j != 1){
                    Integer before = indexMap.get(j);
                    if(before == null){
                        indexMap.put(j,i);
                    }else {
                        unionSet.union(before,i);
                    }
                }
                int other = arr[i]/j;
                if(other != 1){
                    Integer before = indexMap.get(other);
                    if(before == null){
                        indexMap.put(other,i);
                    }else {
                        unionSet.union(before,i);
                    }
                }
            }
        }
        return unionSet.maxSize();
    }

    public static boolean practice(int num){
        //一个数总能写成a*b，质数是因子只包含1和它本身
        for(int i = 2;i<=num/2;i++){
            if(num %i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 求两个数的最大公约数（辗转相除法）
      * @param m
     * @param n
     * @return
     */
   public static int gcd(int m,int n){
        return n == 0 ? m : gcd(n,m%n);
    }

    public static class UnionSet{
       public Map<Integer,Integer> fatherMap;

       public Map<Integer,Integer> sizeMap;

       public UnionSet(int size){
           fatherMap = Maps.newHashMap();
           sizeMap = Maps.newHashMap();
           for(int i = 0; i < size;i++){
               fatherMap.put(i,i);
               sizeMap.put(i,1);
           }
       }

       public int findFather(int cur){
           if (fatherMap.get(cur).equals(cur)) {
                return cur;
           }
           Stack<Integer> path = new Stack<>();
           while(cur != fatherMap.get(cur)){
               path.push(cur);
               cur = fatherMap.get(cur);
           }
           //将并查集扁平化
           while(!path.isEmpty()){
               fatherMap.put(path.pop(),cur);
           }
           return cur;
       }

       public boolean isSameSet(int cur,int other){
           return findFather(cur) == findFather(other);
       }

       public void union(int cur,int other){
           int curFather = findFather(cur);
           int otherFather = findFather(other);
           if(curFather != otherFather){
               int curSize = sizeMap.get(curFather);
               int otherSize = sizeMap.get(otherFather);
               if(curSize >= otherSize){
                   fatherMap.put(otherFather,curFather);
                   sizeMap.put(curFather,curSize+otherSize);
                   sizeMap.remove(otherFather);
               }else{
                   fatherMap.put(curFather,otherFather);
                   sizeMap.put(otherFather,curSize+otherSize);
                   sizeMap.remove(curFather);
               }
           }
       }

       public int maxSize(){
           return sizeMap.size();
       }
    }
}

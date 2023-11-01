package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 *
 * 		TopRecord{
 *
 * 			public TopRecord(int K) : 构造时事先指定好K的大小，构造后就固定不变了
 *
 * 			public void add(String str): 向该结构中加入一个字符串，可以重复加入
 *
 * 			public List<String> top : 返回之前加入的所有字符串中，词频最大的K个
 *
 * 		}
 *
 * 	要求：add方法，复杂度O(logK);
 *
 * 				top方法，复杂度O(K);
 *
 * @author Ly
 * @create 2023/10/2 15:56
 * @desc
 **/
public class TopRecord {

    public static class Record{

        private Node[] arr;

        private Map<String,Node> countMap;

        private int heapSize;

        private Map<String,Integer> indexMap;

        public Record(int K){
            arr = new Node[K];
            heapSize = 0;
            indexMap = Maps.newHashMap();
            countMap = Maps.newHashMap();
        }


        public void push(String val){
            if(!countMap.containsKey(val)){
                countMap.put(val,Node.builder().values(val).count(0).build());
            }
            countMap.get(val).count++;

            if(!indexMap.containsKey(val)){
                //判断此时堆满没有
                if(heapSize >= arr.length){
                    //判断堆顶是否可以被替换,如果不能被替换，加入失败
                    if(arr[0].compareTo(countMap.get(val)) <= 0 ){
                        return;
                    }
                    pop();
                }
                arr[heapSize] = countMap.get(val);
                indexMap.put(arr[heapSize].values,heapSize);
                heapInsert(arr,heapSize++);
            }else{
                heapify(arr,indexMap.get(val),heapSize);
            }
        }

        public void heapInsert(Node[] arr,int index){
            while(arr[index].compareTo(arr[(index+1)/2]) > 0){
                swap(arr,index,(index-1)/2);
                index = (index - 1)/2;
            }
        }

        public void swap(Node[] arr,int i,int j){
            Node temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            indexMap.put(arr[i].values,i);
            indexMap.put(arr[j].values,j);
        }

        public Node pop(){
            Node top = arr[0];
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
            indexMap.remove(top.values);
            return top;
        }

        public void heapify(Node[] arr,int index,int heapSize){
            int left = (2 * index) + 1;
            while(left < heapSize){
                int less = left + 1 < heapSize && arr[left+1].compareTo(arr[left]) > 0 ? left+1 : left;
                less = arr[index].compareTo(arr[less]) < 0 ? less : index;
                if(less == index){
                    break;
                }
                swap(arr,less,index);
                index = less;
                left = (2 * index) + 1;
            }
        }

    }

    @Data
    @Builder
    public static class Node implements Comparable<Node>{

        private String values;

        private Integer count;

        @Override
        public int compareTo(Node o) {
            return o.count - this.count;
        }
    }

    public static String[] generateRandomArray(int len,int max){
        String[] res = new String[len];
        for(int i = 0;i != len ;i++){
            res[i] = String.valueOf((int)(Math.random()*(max+1)));
        }
        return res;
    }

    public static void printTopKAndRank_1(String[] arr2,int K){
        Record record = new Record(K);
        for (String s : arr2) {
            record.push(s);
        }
        while (K-- >= 1){
            System.out.println(record.pop());
        }

    }

}

package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.util.*;

/**
 * 给定一个由字符串组成的数组String[] strs,给定一个正数K，返回词频最大的前K个字符
 * @author Ly
 * @create 2023/10/2 9:44
 * @desc
 **/
public class TopKTimes {


    /**
     * 建立词频map，倒转map，按照词频进行组织，再按照词频排序后，取出第K个（时间复杂度O(N * logN) ）
     * @param arr
     * @param topK
     * @return
     */
    public static List<String> printTopKAndRank_1(String[] arr,int topK){
        Map<String,Integer> map = getMap(arr);
        Map<Integer, List<String>> countMap = getCountMap(map);
        List<Integer> counts = Lists.newArrayList(countMap.keySet());
        Collections.sort(counts, Comparator.comparingInt(a->-a));
        return countMap.get(counts.get(topK-1));
    }

    /**
     * 建立词频map，建立大根堆，大小为N，将所有记录放入大根堆，然后依次弹出前K个字符(时间复杂度O(N*logN))
     * @param arr
     * @param topK
     * @return
     */
    public static List<String> printTopKAndRank_2(String[] arr,int topK){
        Map<String,Integer> map = getMap(arr);
        Map<Integer, List<String>> countMap = getCountMap(map);
        PriorityQueue<Node> nodes = new PriorityQueue<>(arr.length);
        Iterator<Map.Entry<Integer, List<String>>> iterator = countMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, List<String>> next = iterator.next();
            nodes.add(Node.builder().values(next.getValue()).count(next.getKey()).build());
        }
        while(topK-- > 1){
            nodes.poll();
        }
        return nodes.poll().values;
    }

    /**
     * 建立词频map，倒转map，按照词频进行组织(如果结果唯一，那么就不需要按照词频组织)，
     * 建立小根堆，大小为K，将所有记录放入小根堆，如果放入时，小根堆没满，那么直接放入。
     * 如果小根堆满了，判断当前堆顶能否被替换。遍历结束后，输出堆顶元素。（时间复杂度 O(N + K * logK) ）
     * @param arr
     * @param topK
     * @return
     */
    public static List<String> printTopKAndRank_3(String[] arr,int topK){
        Map<String,Integer> map = getMap(arr);
        Map<Integer, List<String>> countMap = getCountMap(map);
        PriorityQueue<Node_2> nodes = new PriorityQueue<>(topK);
        Iterator<Map.Entry<Integer, List<String>>> iterator = countMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, List<String>> next = iterator.next();
            if(nodes.size() >= topK){
                if(nodes.peek().count > next.getKey()){
                    continue;
                }
                nodes.poll();
            }
            nodes.add(Node_2.builder().values(next.getValue()).count(next.getKey()).build());
        }
        return nodes.poll().values;
    }

    /**
     * bfprt解：遍历字符串，建立对象数组，对象包含字符串以及词频。使用bfprt算法找到第K大的词频，然后再遍历数组，找出所有大于词频K的所有字符串（时间复杂度O（N））
     * @param arr
     * @param topK
     * @return
     */
    public static List<String> printTopKAndRank_4(String[] arr,int topK){
        Map<String,Integer> map = getMap(arr);
        Map<Integer, List<String>> countMap = getCountMap(map);
        Iterator<Map.Entry<Integer, List<String>>> iterator = countMap.entrySet().iterator();
        List<Node_3> nodes = Lists.newArrayList();
        while(iterator.hasNext()){
            Map.Entry<Integer, List<String>> next = iterator.next();
            nodes.add(Node_3.builder().values(next.getValue()).count(next.getKey()).build());
        }
        //使用bfprt算法找到第topK的元素
        Node_3 bfprt = bfprt(nodes, topK-1);
        return bfprt.values;
    }
    private static Node_3 bfprt(List<Node_3> nodes,int topK){
        return partition(nodes,0,nodes.size()-1,topK);
    }

    private static Node_3 partition(List<Node_3> nodes,int L,int R,int topK){
        if(L >= R){
            return nodes.get(L);
        }
        int less = L - 1;
        int more = R + 1;
        int index = L;
        Node_3 M = getMedianAndMedian( nodes,L, R);
        while(index < more){
            Node_3 node = nodes.get(index);
            if(node.compareTo(M) > 0){
                swap(nodes,index,--more);
                continue;
            }else if(node.compareTo(M) < 0){
                swap(nodes,index,++less);
            }
            index++;
        }
        if(topK < less){
            return partition(nodes,L,less-1,topK);
        }
        if(topK > more){
            return partition(nodes,more+1,R,topK);
        }
        return nodes.get(topK);
    }

    private static void swap(List<Node_3> nodes,int i ,int j){
        Node_3 temp = nodes.get(i);
        nodes.set(i,nodes.get(j));
        nodes.set(j,temp);
    }

    private static Node_3 getMedianAndMedian(List<Node_3> nodes, int l, int r) {
        int size = r- l  + 1;
        int offset = size % 5 == 0 ? size/5 : (size/5) + 1;
        List<Node_3> mArr = Lists.newArrayList();
        for(int i = 0 ; i < offset;i++){
            int first = l + i * 5;
            mArr.add(getMedian(nodes,first,Math.min(first+4,r)));
        }
        return bfprt(mArr,(offset/2));
    }

    private static Node_3 getMedian(List<Node_3> nodes,int first,int end){
        //排序
        sort(nodes,first,end);
        return nodes.get((end + first)/2);
    }

    private static void sort(List<Node_3> nodes,int L,int R){
        for(int i = L;i<=R;i++){
            for(int j = i + 1;j<=R;j++){
                if(nodes.get(i).compareTo(nodes.get(j)) < 0){
                    swap(nodes,i,j);
                }
            }
        }
    }

    private static Map<String,Integer> getMap(String[] arr){
        Map<String,Integer> map = Maps.newHashMap();
        for (String s : arr) {
            if(!map.containsKey(s)){
                map.put(s,0);
            }
            map.put(s,map.get(s)+1);
        }
        return map;
    }

    private static  Map<Integer, List<String>> getCountMap(Map<String,Integer> map){
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        Map<Integer, List<String>> countMap = Maps.newHashMap();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            Integer count = next.getValue();
            if(!countMap.containsKey(count)){
                countMap.put(count, Lists.newArrayList());
            }
            countMap.get(count).add(next.getKey());
        }
        return countMap;
    }

    @Data
    @Builder
    public static class Node implements Comparable<Node>{

        private List<String> values;

        private Integer count;

        @Override
        public int compareTo(Node o) {
            return o.count - this.count;
        }
    }

    @Data
    @Builder
    public static class Node_2 implements Comparable<Node_2>{

        private List<String> values;

        private Integer count;

        @Override
        public int compareTo(Node_2 o) {
            return this.count - o.count;
        }
    }

    @Data
    @Builder
    public static class Node_3 implements Comparable<Node_3>{

        private List<String> values;

        private Integer count;

        @Override
        public int compareTo(Node_3 o) {
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
}

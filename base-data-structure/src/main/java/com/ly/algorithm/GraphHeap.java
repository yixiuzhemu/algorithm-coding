package com.ly.algorithm;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Ly
 * @create 2021/7/7 14:43
 * @desc
 **/
public class GraphHeap {

    private GraphNode[] nodes;

    private Map<GraphNode,Integer> distanceMap;

    private Map<GraphNode,Integer> indexMap;

    private int size;

    public GraphHeap(int size) {
        this.nodes = new GraphNode[size];
        distanceMap = Maps.newHashMap();
        indexMap = Maps.newHashMap();
        this.size = 0;
    }

    public Boolean isEmpty(){
        return size == 0;
    }

    private void swap(int index1,int index2){
        Integer index = indexMap.get(nodes[index1]);
        indexMap.put(nodes[index1],indexMap.get(nodes[index2]));
        indexMap.put(nodes[index2], index);
        GraphNode tmp = nodes[index1];
        nodes[index1] = nodes[index2];
        nodes[index2] = tmp;
    }

    /**
     * 新增节点与父节点进行比较，如果距离小于父节点的距离，则交换父子节点
     * @param index
     */
    private void heapInsert(int index){
        int parent = (index - 1) >> 1;
        while(parent >= 0 && distanceMap.get(nodes[index]) < distanceMap.get(nodes[parent])){
            swap(index,parent);
            index = parent;
            parent = (index - 1) >> 1;
        }
    }

    /**
     * 从指定位置 与子节点进行比较，将index的值放到该放的位置
     * @param index
     */
    private void heapify(int index){
        int left = (index << 1) + 1;
        while(left + 1< size){
            int largest = distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1]) ? left : left+1;
            largest = distanceMap.get(nodes[largest]) < distanceMap.get(nodes[index])?largest : index;
            if(largest == index){
                break;
            }
            swap(index,largest);
            index = largest;
            left = (index << 1) + 1;
        }
    }


    /***
     * 新增 更新或者忽略节点的距离
     * @param node
     * @param distance
     */
    public void addOrUpdateOrIgnore(GraphNode node,int distance){
        if(!indexMap.containsKey(node)){
            nodes[size] = node;
            distanceMap.put(node,distance);
            indexMap.put(node,size);
            heapInsert(size++);
        }else if(indexMap.containsKey(node) && distanceMap.get(node) > distance){
            distanceMap.put(node,distance);
            heapify(indexMap.get(node));
        }
    }

    /**
     * 弹出时，将头节点弹出，并将最后一个节点放到头节点再进行heapify操作。
     * @return
     */
    public NodeRecord pop(){
        if(isEmpty()){
            return  null;
        }
        GraphNode node = nodes[0];
        swap(0,--size);
        indexMap.remove(node);
        heapify(0);
        return new NodeRecord(node,distanceMap.get(node));
    }

    public static class NodeRecord{
        public GraphNode node;

        public int distance;

        public NodeRecord(GraphNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}

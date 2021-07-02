package com.ly.algorithm;

import java.util.List;

/**
 * 图上的点
 * @author Ly
 * @create 2021/7/2 11:07
 * @desc
 **/
public class GraphNode<V> {

    /**
     * 点上的值
     */
    public V value;

    /**
     * 入度
     */
    public int in;

    /**
     * 出度
     */
    public int out;

    /**
     * 直接指向的节点
     */
    public List<GraphNode> nexts;

    /**
     * 出度的边
     */
    public List<GraphEdge> edges;
}

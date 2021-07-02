package com.ly.algorithm;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 *
 * 图
 * 1。由点的集合和边的集合构成
 * 2.虽然存在有向图和无向图的概念，但实际上都可以用有向图来表达
 * 3.边上可能带有权值
 *
 * 图结构的表达
 * 1.邻接表法
 * 2.邻接矩阵法
 * 3.其它还有很多方式
 * 但是都可以将这些结构，转成我们熟悉的结构 ，再进行处理
 *
 *
 * 图的算法都不算难，但是coding的代价比较高
 * 1.先用自己最熟练的方式，实现图结构的表达
 * 2.在自己熟悉的结构上，实现所有常用的图算法
 * 3.把面试题提供的图结构转换成自己熟悉的图结构，再调用模板改写即可
 * @author Ly
 * @create 2021/7/2 11:08
 * @desc
 **/
public class Graph<V>{

    /**
     * 图中所有的点
     */
    public Map<V,GraphNode> nodes;

    /**
     * 图中的所有的边
     */
    public Set<GraphEdge> edges;

    public Graph() {
        nodes = Maps.newHashMap();
        edges = Sets.newHashSet();
    }
}

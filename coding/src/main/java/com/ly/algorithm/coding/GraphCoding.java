package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ly.algorithm.Graph;
import com.ly.algorithm.GraphEdge;
import com.ly.algorithm.GraphNode;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 *
 * 1。由点的集合和边的集合构成
 * 2.虽然存在有向图和无向图的概念，但实际上都可以用有向图来表达
 * 3.边上可能带有权值
 *
 * @author Ly
 * @create 2021/7/2 10:29
 * @desc
 **/
public class GraphCoding {

    /**
     * 图的宽度优先遍历
     *
     * 1.利用队列实现
     * 2.从源节点开始依次按照宽度进队列，然后弹出
     * 3.每弹出一个点，把该节点所有没有进过队列的邻接节点加入队列
     * 4.知道队列变空
     */
    public static void width(GraphNode<String> node){
        if(node == null){
            return;
        }
        Queue<GraphNode<String>> queue = new LinkedList<>();
        Set<GraphNode<String>> nexts = Sets.newHashSet();
        queue.add(node);
        nexts.add(node);
        while(!queue.isEmpty()){
            GraphNode<String> cur = queue.poll();
            System.out.println(cur.value);
            for (GraphNode<String> next : cur.nexts) {
                if(!nexts.contains(next)){
                    nexts.add(next);
                    queue.add(next);
                }
            }
        }
    }

    /**
     * 图的深度优先遍历
     * 1.利用栈实现
     * 2.从源节点开始把节点按照深度放入栈，然后弹出
     * 3.每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
     * 4.直到栈变空
     */

    public static void height(GraphNode node){
        if(node == null){
            return;
        }
        Stack<GraphNode> stacks = new Stack<>();
        Set<GraphNode> nexts = Sets.newHashSet();
        stacks.push(node);
        nexts.add(node);
        System.out.println(node.value);
        while(!stacks.isEmpty()){
            GraphNode<String> cur = stacks.pop();
            for (GraphNode next : cur.nexts) {
                if(!nexts.contains(next)){
                    nexts.add(next);
                    stacks.push(cur);
                    stacks.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    /**
     * 图的拓扑排序算法
     * 1.在图中找到所有入度为0 的点输出
     * 2，把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
     * 3.图的所有的点都被删除后，依次输出的顺序就是拓扑排序
     *
     * 要求：有向图切其中没有环
     * 应用：事件安排、编译顺序
     */
    public static List<GraphNode> sort(Graph graph){
        Map<Integer, GraphNode> nodes = graph.nodes;
        Iterator<Map.Entry<Integer, GraphNode>> iterator = nodes.entrySet().iterator();
        //有向无环图 一定存在入度为0的点
        Queue<GraphNode> zeroInQueue = Lists.newLinkedList();
        while(iterator.hasNext()){
            GraphNode value = iterator.next().getValue();
            if(value.in == 0){
                zeroInQueue.add(value);
            }
        }
        List<GraphNode> result = Lists.newArrayList();
        while(!zeroInQueue.isEmpty()){
            GraphNode cur = zeroInQueue.poll();
            result.add(cur);
            List<GraphNode> nexts = cur.nexts;
            for (GraphNode next : nexts) {
                next.in = next.in - 1;
                if(next.in <= 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

    /**
     * 最小生成树算法之Kruskal
     * 1.总是从权值最小的边开始考虑，依次考察权值依次变大的边
     * 2.当前的边要么进入最小生成树的集合，要么丢弃
     * 3.如果当前的边进入最小生成树的集合中不会形成环，就要当前边
     * 4.如果当前的边进入最小生成树的集合中会形成环，就不要当前边
     * 5.考察完所有边之后，最小生成树的集合也得到了
     */
    public static List<GraphEdge> kruskal(Graph graph){
        Set<GraphEdge> edges = graph.edges;
        List<GraphEdge> graphEdges = Lists.newArrayList(edges);
        Collections.sort(graphEdges,new EdgeComparator());
        List<GraphNode> values = Lists.newArrayList(graph.nodes.values());
        SearchUnionCoding<GraphNode> graphNodeSearchUnionCoding = new SearchUnionCoding<>(values);
        List<GraphEdge> results = Lists.newArrayList();
        for (GraphEdge graphEdge : graphEdges) {
            if(!graphNodeSearchUnionCoding.isSampleSet(graphEdge.from,graphEdge.to)){
                graphNodeSearchUnionCoding.union(graphEdge.from,graphEdge.to);
                results.add(graphEdge);
            }
        }
        return results;
    }

    static class EdgeComparator implements Comparator<GraphEdge>{
        @Override
        public int compare(GraphEdge o1, GraphEdge o2) {
            return o1.weight - o2.weight;
        }
    }

    /**
     * 最小生成树算法只psim
     * 1.任意从图中选择一个点出发
     * 2.解锁当前点的所有出度边
     * 3.从所有边中找到权重最小的一条边
     * 4.找到权重最小的那条边对应的点，并解锁这个点
     * 5.直到所有点都被解锁，再移除不需要的边
     * @param graph
     * @return
     */
    public static List<GraphEdge> psim(Graph graph){
        List<GraphNode> values = Lists.newArrayList( graph.nodes.values());
        GraphNode firstNode = values.get(0);
        Stack<GraphNode> stacks = new Stack<>();
        stacks.push(firstNode);
        List<GraphNode> nodes = Lists.newArrayList();
        nodes.add(firstNode);
        List<GraphEdge> results = Lists.newArrayList();
        while(!stacks.isEmpty()){
            GraphNode pop = stacks.pop();
            List<GraphEdge> edges = pop.edges;
            if(CollectionUtils.isEmpty(edges)){
                break;
            }
            Collections.sort(edges,new EdgeComparator());
            for (GraphEdge edge : edges) {
                if(!nodes.contains(edge.to)){
                    nodes.add(edge.to);
                    stacks.push(edge.to);
                    results.add(edge);
                    break;
                }
            }
        }
        return results;
    }

    /**
     * 使用小根堆实现
     * @param graph
     * @return
     */
    public static List<GraphEdge> psim2(Graph graph){
        PriorityQueue<GraphEdge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        Set<GraphNode> nodeSets = Sets.newHashSet();
        Set<GraphEdge> results = Sets.newHashSet();
        GraphNode node = (GraphNode)Lists.newArrayList(graph.nodes.values()).get(0);
        if(!nodeSets.contains(node)){
            nodeSets.add(node);
            for (GraphEdge edge : (List<GraphEdge>)node.edges) {
                priorityQueue.add(edge);
            }
            while(!priorityQueue.isEmpty()){
                GraphEdge edge = priorityQueue.poll();
                GraphNode toNode = edge.to;
                if(!nodeSets.contains(toNode)){
                    nodeSets.add(toNode);
                    results.add(edge);
                    for (GraphEdge nextEdge : (List<GraphEdge>)node.edges) {
                        priorityQueue.add(nextEdge);
                    }
                }
            }
        }
        return Lists.newArrayList(results);
    }
}

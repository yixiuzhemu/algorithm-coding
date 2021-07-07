import com.google.common.collect.Lists;
import com.ly.algorithm.Graph;
import com.ly.algorithm.GraphEdge;
import com.ly.algorithm.GraphNode;
import com.ly.algorithm.coding.GraphCoding;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2021/7/2 17:37
 * @desc
 **/
public class GraphCodingTest {

    @Test
    public void testKruskal(){
        Graph graph = TestData.getGraph();
        List<GraphEdge> kruskal = GraphCoding.kruskal(graph);
        for (GraphEdge  graphEdge : kruskal) {
            System.out.println(" "+graphEdge.weight+",");
        }
    }


    @Test
    public void testPsim(){
        Graph graph = TestData.getGraph();
        List<GraphEdge> psim = GraphCoding.psim(graph);
        for (GraphEdge  graphEdge : psim) {
            System.out.println(" "+graphEdge.weight+",");
        }
        List<GraphEdge> psim2 = GraphCoding.psim2(graph);
        for (GraphEdge  graphEdge : psim2) {
            System.out.println(" "+graphEdge.weight+",");
        }
    }

    @Test
    public void testDijkstra(){
        Graph graph = TestData.getGraph();
        GraphCoding.dijkstra((GraphNode) Lists.newArrayList(graph.nodes.values()).get(0));
        GraphCoding.dijkstra2((GraphNode) Lists.newArrayList(graph.nodes.values()).get(0),5);
    }
}

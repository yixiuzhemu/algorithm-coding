import com.ly.algorithm.Graph;
import com.ly.algorithm.GraphEdge;
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
    }
}

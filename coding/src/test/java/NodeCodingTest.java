import com.ly.algorithm.Node;
import com.ly.algorithm.coding.NodeCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/6/21 11:25
 * @desc
 **/
public class NodeCodingTest {

    @Test
    public void haveLoopTest(){
        Node head = new Node(9);
        Node node = head;
        Node node1 = new Node(4);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        node =  node.addNode(new Node(1));
        node =  node.addNode(new Node(2));
        node =  node.addNode(node3);
        node =  node.addNode(node1);
        node =  node.addNode(node4);
        node =  node.addNode(new Node(7));
        node =  node.addNode(new Node(8));
        node.setNext(node1);

        Node head2 = new Node(10);
        Node node2 = head2;
        node2 =  node2.addNode(new Node(11));
        node2 =  node2.addNode(new Node(12));
        node2 =  node2.addNode(new Node(13));
        node2 =  node2.addNode(new Node(14));
        node2.setNext(node4);
        NodeCoding.haveLoop(head,head2);
    }

}

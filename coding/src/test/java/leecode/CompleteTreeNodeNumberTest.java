package leecode;

import com.ly.algorithm.coding.leecode.CompleteTreeNodeNumber;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/13 22:48
 * @desc
 **/
public class CompleteTreeNodeNumberTest {

    @Test
    public void nodeNum(){
        CompleteTreeNodeNumber.Node head = CompleteTreeNodeNumber.Node.builder()
                .left(CompleteTreeNodeNumber.Node.builder()
                        .left(CompleteTreeNodeNumber.Node.builder()
                                .left(CompleteTreeNodeNumber.Node.builder().value(5).build())
                                .right(CompleteTreeNodeNumber.Node.builder().value(6).build()).value(3).build())
                        .right(CompleteTreeNodeNumber.Node.builder()
                                .left(CompleteTreeNodeNumber.Node.builder().value(7).build())
                                .right(CompleteTreeNodeNumber.Node.builder().value(8).build()).value(4).build())
                        .value(2).build())
                .right(CompleteTreeNodeNumber.Node.builder()
                        .left(CompleteTreeNodeNumber.Node.builder()
                                .left(CompleteTreeNodeNumber.Node.builder().value(12).build())
                                .right(CompleteTreeNodeNumber.Node.builder().value(13).build()).value(10).build())
                        .right(CompleteTreeNodeNumber.Node.builder()
                                .left(CompleteTreeNodeNumber.Node.builder().value(14).build())
                                .right(CompleteTreeNodeNumber.Node.builder().value(15).build())
                                .value(11).build())
                        .value(9).build())
                .value(1).build();
        System.out.println(CompleteTreeNodeNumber.nodeNUm(head));
    }

}

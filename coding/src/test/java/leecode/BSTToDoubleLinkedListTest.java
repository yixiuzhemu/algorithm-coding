package leecode;

import com.ly.algorithm.coding.leecode.BSTToDoubleLinkedList;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/9 21:25
 * @desc
 **/
public class BSTToDoubleLinkedListTest {

    @Test
    public void BSTToDoubleLinked(){
        BSTToDoubleLinkedList.Node head = BSTToDoubleLinkedList.Node.builder()
                .left(BSTToDoubleLinkedList.Node.builder()
                        .left(BSTToDoubleLinkedList.Node.builder().value(2).build()).right(BSTToDoubleLinkedList.Node.builder().value(3).build()).value(4).build())
                .right(BSTToDoubleLinkedList.Node.builder().left(BSTToDoubleLinkedList.Node.builder().value(7).build()).right(BSTToDoubleLinkedList.Node.builder().value(8).build()).value(6).build())
                .value(5).build();
        BSTToDoubleLinkedList.Info process = BSTToDoubleLinkedList.process(head);
        BSTToDoubleLinkedList.Node start = process.getStart();
        while(start != null){
            System.out.println(start.getValue());
            start = start.getRight();
        }
    }

}

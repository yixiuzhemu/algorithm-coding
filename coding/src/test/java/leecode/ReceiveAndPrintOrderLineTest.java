package leecode;

import com.ly.algorithm.coding.leecode.ReceiveAndPrintOrderLine;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/11/2 21:04
 * @desc
 **/
public class ReceiveAndPrintOrderLineTest {

    @Test
    public void receiveAndPrintOrderLine(){
        ReceiveAndPrintOrderLine.MessageBox messageBox = new ReceiveAndPrintOrderLine.MessageBox();
        int[] arr = new int[]{15,7,5,8,9,10,2,3,1,4,6,13,12,11,14};
        for (int i : arr) {
            ReceiveAndPrintOrderLine.printMessage(i,i+"来了",messageBox);
        }
    }

}

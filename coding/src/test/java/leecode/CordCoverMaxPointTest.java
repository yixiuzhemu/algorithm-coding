package leecode;

import com.ly.algorithm.coding.leecode.CordCoverMaxPoint;
import com.ly.algorithm.coding.leecode.NeedParentheses;
import org.junit.Test;
import org.omg.IOP.CodecOperations;

/**
 * @author Ly
 * @create 2023/9/9 10:02
 * @desc
 **/
public class CordCoverMaxPointTest {

    @Test
    public void testMaxPoint(){
        int[] arr = new int[]{1,3,4,6,9,12,13,15};
        int K = 5;
        System.out.println(CordCoverMaxPoint.maxPoint(arr,K));
        System.out.println(CordCoverMaxPoint.maxPoint_V2(arr,K));
    }

}

package leecode;

import com.ly.algorithm.coding.leecode.UnformedSum;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/26 22:52
 * @desc
 **/
public class UnformedSumTest {

    @Test
    public void unformedSum(){
        int[] arr = new int[]{1,3,2,5,8,4,7,9,};
        System.out.println(UnformedSum.unformedSum(arr));
        System.out.println(UnformedSum.unformedSum_Plus(arr));
    }

}

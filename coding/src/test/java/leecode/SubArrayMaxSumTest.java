package leecode;

import com.ly.algorithm.coding.leecode.SubArrayMaxSum;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/8 21:26
 * @desc
 **/
public class SubArrayMaxSumTest {

    @Test
    public void maxSum(){
        int[] arr = new int[]{3,-5,3,-1,2,3,-7,-1};
        System.out.println(SubArrayMaxSum.maxSum(arr));
    }

}

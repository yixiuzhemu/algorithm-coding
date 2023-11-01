package leecode;

import com.ly.algorithm.coding.leecode.SubMatrixMaxSum;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/8 22:10
 * @desc
 **/
public class SubMatrixMaxSumTest {

    @Test
    public void maxSum(){
        int[][] arr = new int[][]{{-3,5,3,1,-4},{6,-2,-1,0,7},{-9,8,3,2,-100}};
        System.out.println(SubMatrixMaxSum.maxSum(arr));
    }

}

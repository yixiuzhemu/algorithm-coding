package leecode;

import com.ly.algorithm.coding.leecode.ArrPathMaxSum;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/26 21:19
 * @desc
 **/
public class ArrPathMaxSumTest {
    @Test
    public void arrPathMaxSum(){
        int[][] matrix = new int[][]{{1,2,3,4,5,6},{9,8,7,6,5,4},{11,13,1,6,17,2},{2,3,4,5,6,7}};
        System.out.println(ArrPathMaxSum.arrPathMaxSum(matrix));
        System.out.println(ArrPathMaxSum.arrPathMaxSum_2(matrix));
        System.out.println(ArrPathMaxSum.arrPathMaxSum_3(matrix));
    }

}

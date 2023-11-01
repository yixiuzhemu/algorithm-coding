package leecode;

import com.ly.algorithm.coding.leecode.EnvelopeNesting;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/7 21:05
 * @desc
 **/
public class EnvelopeNestingTest {


    @Test
    public void maxNesting(){
        int[][] arr = new int[][]{{3,2},{2,4},{3,5},{1,2},{2,3}};
        System.out.println(EnvelopeNesting.maxNesting(arr));
    }

}

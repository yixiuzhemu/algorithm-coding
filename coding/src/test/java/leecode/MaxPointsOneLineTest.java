package leecode;

import com.ly.algorithm.coding.leecode.MaxPointsOneLine;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/31 21:04
 * @desc
 **/
public class MaxPointsOneLineTest {

    @Test
    public void maxPoints(){
        int[] arrx = new int[]{1,2,3,4,5,6,7,8,9};
        int[] arry = new int[]{3,2,5,4,2,6,8,2,10};
        System.out.println(MaxPointsOneLine.maxPoints(arrx,arry));
    }

}

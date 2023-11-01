package leecode;

import com.ly.algorithm.coding.leecode.SnacksWays;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/25 20:51
 * @desc
 **/
public class SnacksWaysTest {
    @Test
    public void snacksWays(){
        int[] arr = new int[]{2,5,3,6,7,9,3,3,1,2,3,7,6,6,6,4,3,5};
        int w = 30;
        System.out.println(SnacksWays.snacksWays_1(arr,w));
        System.out.println(SnacksWays.snacksWays_2(arr,w));
    }

}

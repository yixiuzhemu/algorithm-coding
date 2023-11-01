package leecode;

import com.ly.algorithm.coding.leecode.SplitArrayWith3Time;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/24 20:38
 * @desc
 **/
public class SplitArrayWith3TimeTest {

    @Test
    public void canSplit(){
        int[] arr = new int[]{3,2,4,1,4,9,5,10,1,2,2};
        System.out.println(SplitArrayWith3Time.canSplit(arr));
    }

}

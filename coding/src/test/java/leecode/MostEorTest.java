package leecode;

import com.ly.algorithm.coding.leecode.MostEor;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/21 10:19
 * @desc
 **/
public class MostEorTest {
    @Test
    public void mostEor(){
        int[] arr = {3, 2, 1, 0, 4, 3, 2, 1, 0, 0, 3, 1, 2, 0, 2, 1, 3};
        System.out.println(MostEor.mostEor(arr));
    }

}

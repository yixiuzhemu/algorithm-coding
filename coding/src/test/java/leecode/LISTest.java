package leecode;

import com.ly.algorithm.coding.leecode.LIS;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/7 20:02
 * @desc
 **/
public class LISTest {

    @Test
    public void getLIS(){
        int[] arr=  new int[]{3,1,4,2,3};
        System.out.println(LIS.getLis(arr));
        System.out.println(LIS.getLis_V2(arr));
    }

}

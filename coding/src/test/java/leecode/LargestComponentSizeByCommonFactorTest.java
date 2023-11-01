package leecode;

import com.ly.algorithm.coding.leecode.LargestComponentSizeByCommonFactor;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/28 9:36
 * @desc
 **/
public class LargestComponentSizeByCommonFactorTest {

    @Test
    public void largestComponentSize(){
        int[] arr = new int[]{10,5,7,4,14};
        System.out.println(LargestComponentSizeByCommonFactor.largestComponentSize(arr));
        System.out.println(LargestComponentSizeByCommonFactor.largestComponentSize_v2(arr));
        System.out.println(LargestComponentSizeByCommonFactor.largestComponentSize_v3(arr));
    }

}

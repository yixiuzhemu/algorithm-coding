package leecode;

import com.ly.algorithm.coding.leecode.SubArrayMaxEor;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/18 23:39
 * @desc
 **/
public class SubArrayMaxEorTest {

    @Test
    public void subArrayMaxEor(){
        int[] arr = new int[]{11,3,5,6,2,1,77,32,15,32,63};
        System.out.println(SubArrayMaxEor.getSubArrayMaxEor(arr));
        System.out.println(SubArrayMaxEor.getSubArrayMaxEor_V2(arr));
        System.out.println(SubArrayMaxEor.getSubArrayMaxEor_V3(arr));
    }

}

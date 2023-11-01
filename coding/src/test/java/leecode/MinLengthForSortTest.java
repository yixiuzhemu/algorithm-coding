package leecode;

import com.ly.algorithm.coding.leecode.MinLengthForSort;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/25 20:47
 * @desc
 **/
public class MinLengthForSortTest {

    @Test
    public void getMinLength(){
        int[] arr = new int[]{1,2,5,3,2,4,6,7};
        System.out.println(MinLengthForSort.getMinLength(arr));
    }

}

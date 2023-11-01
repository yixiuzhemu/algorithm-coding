package leecode;

import com.ly.algorithm.coding.leecode.AccumulatedSumOfOrderedArray;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2023/9/22 22:47
 * @desc
 **/
public class AccumulatedSumOfOrderedArrayTest {

    @Test
    public void AccumulatedSumOfOrderedArray() {
        int[] arr = new int[]{-15,-5,1,1,2,2,2,3,3,3,3,5,6,6,6,7,7,8,8,10,15,30};
        List<String> process = AccumulatedSumOfOrderedArray.binaryAccumulatedSumOfOrderedArray(arr,9);
        for (String s : process) {
            System.out.println(s);
        }
        arr = new int[]{1,1,1,2,4,4,4,6,6,7,7,8,9};
        process = AccumulatedSumOfOrderedArray.ternaryAccumulatedSumOfOrderedArray(arr,14);
        for (String s : process) {
            System.out.println(s);
        }
    }

}

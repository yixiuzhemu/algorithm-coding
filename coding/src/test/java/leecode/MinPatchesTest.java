package leecode;

import com.ly.algorithm.coding.leecode.MinPatches;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/27 21:00
 * @desc
 **/
public class MinPatchesTest {

    @Test
    public void minPatches(){
        int[] arr = new int[]{1,5,7};
        System.out.println(MinPatches.minPatches(arr,15));
    }

}

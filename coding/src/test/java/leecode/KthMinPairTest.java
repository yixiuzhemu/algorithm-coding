package leecode;

import com.ly.algorithm.coding.leecode.KthMinPair;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/23 12:19
 * @desc
 **/
public class KthMinPairTest {

    @Test
    public void kthMinPair(){
        int[] arr = {1,1,2,2,2,3,3,3,4,5};
        System.out.println(KthMinPair.kthMinPair_V1(arr,56));
        System.out.println(KthMinPair.kthMinPair_V2(arr,56));
    }

}

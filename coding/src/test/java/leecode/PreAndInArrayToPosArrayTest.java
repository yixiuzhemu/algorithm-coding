package leecode;

import com.ly.algorithm.coding.leecode.PreAndInArrayToPosArray;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/6 12:16
 * @desc
 **/
public class PreAndInArrayToPosArrayTest {

    @Test
    public void preAndInArrayToPosArray(){
        int[] pre = {1,2,4,5,3,6,7};
        int[] in = {4,2,5,1,6,3,7};
        int[] ints = PreAndInArrayToPosArray.preInToPos1(pre, in);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        System.out.println("--------------------------");
        int[] ints2 = PreAndInArrayToPosArray.preInToPos2(pre, in);
        for (int anInt : ints2) {
            System.out.println(anInt);
        }
    }
}

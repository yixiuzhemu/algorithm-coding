package leecode;

import com.ly.algorithm.coding.leecode.TopKSumCrossTwoArrays;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/23 20:25
 * @desc
 **/
public class TopKSumCrossTwoArraysTest {

    @Test
    public void TopKSumCrossTwoArrays(){
        int[] arr1 = new int[]{1,2,5,7,9,24,65,73,92};
        int[] arr2 = new int[]{1,2,3,5,6,7,11,14,18,21,31};
        int[] ints = TopKSumCrossTwoArrays.topKSumCrossTowArrays(arr1, arr2, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}

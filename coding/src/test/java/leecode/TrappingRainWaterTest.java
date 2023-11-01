package leecode;

import com.ly.algorithm.coding.leecode.TrappingRainWater;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/20 21:37
 * @desc
 **/
public class TrappingRainWaterTest {


    @Test
    public void trappingRainWater(){
        int[] arr = new int[]{3,1,2,5,2,4};
        System.out.println(TrappingRainWater.trappingRainWater_1(arr));
        System.out.println(TrappingRainWater.trappingRainWater_2(arr));
        System.out.println(TrappingRainWater.trappingRainWater_3(arr));
    }

    @Test
    public void trappingRainWater2(){
        int[][] arr = new int[][]{{3,3,3,3,3,2,3,3,3,3},{3,2,2,1,1,1,1,1,3,3},{3,3,3,3,3,3,3,3,3,3}};
        System.out.println(TrappingRainWater.trappingRainWater(arr));
    }
}

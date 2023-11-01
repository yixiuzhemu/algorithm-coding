package leecode;

import com.ly.algorithm.coding.leecode.MaxOneBorderMap;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/13 22:07
 * @desc
 **/
public class MaxOneBorderMapTest {

    @Test
    public void getMaxSize(){
        int[][] matrix = new int[][]{{0,1,1,1,1},{0,1,0,0,1},{0,1,0,0,1},{0,1,1,1,1},{0,1,0,1,1}};
        System.out.println(MaxOneBorderMap.getMaxSize(matrix));
    }
}

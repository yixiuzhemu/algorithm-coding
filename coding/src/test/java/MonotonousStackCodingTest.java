import com.ly.algorithm.coding.MonotonousStackCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/8/13 17:05
 * @desc
 **/
public class MonotonousStackCodingTest {

    @Test
    public void testGetNearLess(){
        int[] arr = new int[]{3,5,2,3,6,8,3,2,4,5};
        int[][] nearLess = MonotonousStackCoding.getNearLess(arr);
        for(int i = 0;i<nearLess.length;i++){
            System.out.println(arr[i]+"左边最小的数为："+nearLess[i][0]+" 右边最小的数为："+nearLess[i][1]);
        }
    }

    @Test
    public void testGetMaxSum(){
        int[] arr = new int[]{3,5,2,3,6,8,3,2,4,5};
        int maxSum = MonotonousStackCoding.getMaxSum(arr);
        System.out.println("最大值为："+maxSum);
    }

}

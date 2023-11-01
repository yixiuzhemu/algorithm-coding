import com.ly.algorithm.coding.ArraySumCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/8/11 11:05
 * @desc
 **/
public class ArraySumCodingTest {

    @Test
    public void getMaxLength(){
        int[] arr = new int[]{3,2,1,1,1,6,1,1,1,1,1,1};
        int N = 6;
        System.out.println(ArraySumCoding.maxLength(arr,N));
        System.out.println(ArraySumCoding.maxLengthV2(arr,N));
    }


    @Test
    public void getMaxLengthV3(){
        int[] arr = new int[]{5,6,4,-3,0,3};
        int N = 10;
        System.out.println(ArraySumCoding.maxLengthV3(arr,N));
    }

    @Test
    public void maxSameLength(){
        int[] arr = new int[]{1,5,6,4,-3,0,3,2};
        System.out.println(ArraySumCoding.maxSameLength(arr));
    }


    @Test
    public void maxLengthLessThanK(){
        int[] arr = new int[]{3,7,4,-6,6,3,-2,0,7,-3,2};
        System.out.println(ArraySumCoding.maxLengthLessThanK(arr,2));
        System.out.println(ArraySumCoding.maxLengthLessThanKV2(arr,2));
    }
}

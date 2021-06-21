import com.ly.algorithm.coding.SortCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/6/21 15:03
 * @desc
 **/
public class SortTest {

    @Test
    public void sortTest(){
        int[] arr = new int[]{100,101,5,1,3,6,8,9,5,7,2,8,5,10};
        SortCoding.radixSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

import com.ly.algorithm.coding.MonotonousStackCoding;
import com.ly.algorithm.coding.SlideWindowCoding;
import com.ly.algorithm.coding.SortCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/7/20 9:17
 * @desc
 **/
public class SlideWindowCodingTest {

    @Test
    public void testSlideMaxNum(){
        int[] arr = new int[]{6,3,5,4,3,3,6,7};
        SlideWindowCoding.slideMaxNum(arr,3);
        System.out.println("--------------------------------------------");
         MonotonousStackCoding.slideMaxNum2(arr,3);
    }

    @Test
    public void testSubMember(){
        int[] arr = new int[]{6,3,5,4,3,3,6,7,7,3,4,1};
        SortCoding.quickSort(arr);
        SlideWindowCoding.getSubMember(arr,2);
        System.out.println("--------------------------------------------");
        SlideWindowCoding.getSubMember2(arr,2);
    }


}

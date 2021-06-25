import com.ly.algorithm.coding.GreedyAlgorithmCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/6/25 17:52
 * @desc
 **/
public class GreedyAlgorithmcodingTest {

    @Test
    public void testLowestStr(){
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        for(int i = 0;i<testTimes;i++){
            String[] arr1 = TestData.generateRandomStringArray(arrLen,strLen);
            String[] arr2 = TestData.copyStringArray(arr1);
            if(!GreedyAlgorithmCoding.lowestString(arr1).equals(GreedyAlgorithmCoding.lowestString2(arr2))){
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}

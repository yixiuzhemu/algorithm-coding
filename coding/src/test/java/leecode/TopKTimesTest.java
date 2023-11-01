package leecode;

import com.ly.algorithm.coding.leecode.TopKTimes;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2023/10/2 10:25
 * @desc
 **/
public class TopKTimesTest {


    @Test
    public void printTopKAndRank() {
        String[] arr2 = TopKTimes.generateRandomArray(50, 10);
        List<String> list_1 = TopKTimes.printTopKAndRank_1(arr2, 2);
        List<String> list_2 = TopKTimes.printTopKAndRank_2(arr2, 2);
        List<String> list_3 = TopKTimes.printTopKAndRank_3(arr2, 2);
        List<String> list_4 = TopKTimes.printTopKAndRank_4(arr2, 2);
        for (String s : list_1) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------");
        for (String s : list_2) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------");
        for (String s : list_3) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------");
        for (String s : list_4) {
            System.out.println(s);

        }
    }
}

package leecode;

import com.ly.algorithm.coding.leecode.TopKTimes;
import com.ly.algorithm.coding.leecode.TopRecord;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/3 10:49
 * @desc
 **/
public class TopRecordTest {

    @Test
    public  void topRecord() {
        String[] arr2 = TopRecord.generateRandomArray(50,10);
        TopRecord.printTopKAndRank_1(arr2, 3);

    }
}

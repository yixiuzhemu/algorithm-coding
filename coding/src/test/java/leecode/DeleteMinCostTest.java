package leecode;

import com.ly.algorithm.coding.leecode.DeleteMinCost;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/13 22:05
 * @desc
 **/
public class DeleteMinCostTest {

    @Test
    public void deleteMinCost(){
        String str1 = "abcde";
        String str2 = "axbcde";
        System.out.println(DeleteMinCost.minCost_v1(str1,str2));
        System.out.println(DeleteMinCost.minCost_v2(str1,str2));
        System.out.println(DeleteMinCost.minCost_v3(str1,str2));
    }

}

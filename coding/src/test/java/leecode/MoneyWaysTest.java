package leecode;

import com.ly.algorithm.coding.leecode.MoneyWays;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/11/3 21:58
 * @desc
 **/
public class MoneyWaysTest {

    @Test
    public void moneyWay(){
        int[] arr = new int[]{3,2,5};
        int[] arr2 = new int[]{1,1,3,5,5,6};
        int m =10;
        System.out.println(MoneyWays.moneyWay(arr,m));
        System.out.println(MoneyWays.moneyWay_v2(arr,m)[m]);
        System.out.println(MoneyWays.moneyWay_v3(arr2,m));
        System.out.println(MoneyWays.moneyWay_v4(arr2,m)[m]);
        System.out.println(MoneyWays.moneyWay_v5(arr,arr2,m));
    }

}

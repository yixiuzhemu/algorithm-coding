package leecode;

import com.ly.algorithm.coding.leecode.MinPriceCrossMonster;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/31 21:35
 * @desc
 **/
public class MinPriceCrossMonsterTest {
    @Test
    public void minPriceCrossMonster(){
        int[] d = new int[]{0,1,2,3,4,5,6,7,8};
        int[] p = new int[]{0,1,2,3,4,5,6,7,8};
        System.out.println(MinPriceCrossMonster.minPriceCrossMonster(d,p));
        System.out.println(MinPriceCrossMonster.minPriceCrossMonster_V2(d,p));
        System.out.println(MinPriceCrossMonster.minPriceCrossMonster_V3(d,p));
    }

}

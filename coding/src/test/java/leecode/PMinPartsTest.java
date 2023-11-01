package leecode;

import com.ly.algorithm.coding.leecode.PMinParts;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/22 21:10
 * @desc
 **/
public class PMinPartsTest {

    @Test
    public void pMinParts(){
//        String s = "1213121abakfkfaaf";
        String s = "aba12321412321TabaKFK";
        System.out.println(PMinParts.pMinParts_v1(s));
        System.out.println(PMinParts.pMinParts_v2(s));
        System.out.println(PMinParts.pMinParts_v3(s));
    }

}

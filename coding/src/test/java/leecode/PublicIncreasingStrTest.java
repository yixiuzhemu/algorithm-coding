package leecode;

import com.ly.algorithm.coding.leecode.PublicIncreasingStr;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/1 8:18
 * @desc
 **/
public class PublicIncreasingStrTest {

    @Test
    public  void maxPublicIncreasingStr() {
        String str1 = "abc123def45aefaefasedfsewr543efsdr34";
        String str2 = "kf123zys4t5aefaef34regws34r5regts4123resad325";
        System.out.println(PublicIncreasingStr.maxPublicIncreasingStr(str1,str2));
        System.out.println(PublicIncreasingStr.maxPublicIncreasingStr_2(str1,str2));
        System.out.println(PublicIncreasingStr.maxPublicIncreasingStr_3(str1,str2));
    }

}

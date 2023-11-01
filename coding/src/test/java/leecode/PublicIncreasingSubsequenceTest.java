package leecode;

import com.ly.algorithm.coding.leecode.PublicIncreasingSubsequence;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/27 23:17
 * @desc
 **/
public class PublicIncreasingSubsequenceTest {

    @Test
    public  void maxPublicIncreasingSubsequence() {
        String str1 = "abc123def45aefaefasedfsewr543efsdr34";
        String str2 = "kf123zys4t5awefraw34regws34r5regts4123resad325";
        System.out.println(PublicIncreasingSubsequence.maxPublicIncreasingSubsequence(str1,str2));
        System.out.println(PublicIncreasingSubsequence.maxPublicIncreasingSubsequence_2(str1,str2));
        System.out.println(PublicIncreasingSubsequence.maxPublicIncreasingSubsequence_3(str1,str2));
    }

}

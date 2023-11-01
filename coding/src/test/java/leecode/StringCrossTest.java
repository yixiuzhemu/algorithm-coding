package leecode;

import com.ly.algorithm.coding.leecode.StringCross;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/25 20:22
 * @desc
 **/
public class StringCrossTest {

    @Test
    public void stringCross(){
        String str1 = "aabcb";
        String str2 = "aacbb";
        String str3 = "aaabaccbbb";
        System.out.println(StringCross.stringCross(str1,str2,str3));
    }

}

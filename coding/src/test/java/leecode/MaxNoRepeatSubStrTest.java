package leecode;

import com.ly.algorithm.coding.leecode.MaxNoRepeatSubStr;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/27 21:46
 * @desc
 **/
public class MaxNoRepeatSubStrTest {

    @Test
    public void maxNoRepeatSubStr(){
        String str = "pwwkewdssssewssf";
        System.out.println(MaxNoRepeatSubStr.maxNoRepeatSubStr(str));
        System.out.println(MaxNoRepeatSubStr.maxNoRepeatSubStr_V2(str));
    }

}

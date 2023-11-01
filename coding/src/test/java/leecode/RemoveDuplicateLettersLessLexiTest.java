package leecode;

import com.ly.algorithm.coding.leecode.RemoveDuplicateLettersLessLexi;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/30 21:21
 * @desc
 **/
public class RemoveDuplicateLettersLessLexiTest {

    @Test
    public void remove(){
        String str = "daccbdbaccdbba";
        System.out.println(RemoveDuplicateLettersLessLexi.remove(str));
    }

}

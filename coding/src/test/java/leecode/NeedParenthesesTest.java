package leecode;

import com.ly.algorithm.coding.leecode.NeedParentheses;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/10 9:12
 * @desc
 **/
public class NeedParenthesesTest {


    @Test
    public void needParentheses(){
        String str = "()))((()";
        if(!NeedParentheses.valid(str)){
            System.out.println(NeedParentheses.needParentheses(str));
        }
    }


    @Test
    public  void maxLength() {
        String str = "))(())()))(())()()(())";

        System.out.println(NeedParentheses.maxLength(str));
    }

}

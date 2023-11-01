package leecode;

import com.ly.algorithm.coding.leecode.MakeNoCode;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/14 21:33
 * @desc
 **/
public class MakeNoCodeTest {

    @Test
    public void makeNo(){
        int[] process = MakeNoCode.MakeNo(21);
        for (int i : process) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

}

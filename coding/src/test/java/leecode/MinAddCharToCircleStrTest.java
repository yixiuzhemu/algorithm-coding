package leecode;

import com.ly.algorithm.coding.leecode.MinAddCharToCircleStr;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/11/1 20:59
 * @desc
 **/
public class MinAddCharToCircleStrTest {

    @Test
    public void minAdd(){
        String s = "abccabcdge";
        System.out.println(MinAddCharToCircleStr.minAdd_v1(s));
        System.out.println(MinAddCharToCircleStr.minAdd_V2(s));
    }

}

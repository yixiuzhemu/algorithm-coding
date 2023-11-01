package leecode;

import com.ly.algorithm.coding.leecode.ExpressionNumber;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/21 14:15
 * @desc
 **/
public class ExpressionNumberTest {

    @Test
    public void expressionNumber(){
        String express = "1^0|0|0|1";
        boolean desired = false;
        System.out.println(ExpressionNumber.expressionNum_v1(express,desired));
        System.out.println(ExpressionNumber.expressionNum_V2(express,desired));
    }

}

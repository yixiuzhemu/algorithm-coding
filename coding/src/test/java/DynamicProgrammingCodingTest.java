import com.ly.algorithm.coding.DynamicProgrammingCoding;
import com.ly.algorithm.coding.ViolentRecursionCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/7/12 15:54
 * @desc
 **/
public class DynamicProgrammingCodingTest {

    @Test
    public void testMove(){
        int move3 = DynamicProgrammingCoding.move3(7, 3, 3, 2);
        System.out.println("机器人一共有"+move3+"种移动方法");
    }

}

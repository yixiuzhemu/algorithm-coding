import com.ly.algorithm.coding.FibonacciProblemCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/8/16 16:22
 * @desc
 **/
public class FibonacciProblemCodingTest {

    @Test
    public void testFibonacci(){
        System.out.println("15的斐波那契数列值为："+FibonacciProblemCoding.recent(15));
        System.out.println("15的斐波那契数列值为："+FibonacciProblemCoding.linear(15));
        System.out.println("15的斐波那契数列值为："+FibonacciProblemCoding.recent(15));
    }

    @Test
    public void testCow(){
        System.out.println("15年后奶牛一共有："+FibonacciProblemCoding.cowRecent(15));
    }
}

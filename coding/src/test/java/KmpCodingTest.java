import com.ly.algorithm.coding.KmpCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/8/19 15:34
 * @desc
 **/
public class KmpCodingTest {

    @Test
    public void testSearch(){
        String s = "aabaabaccscaczasscabbc";
        String m = "acz";
        int search = KmpCoding.search(s, m);
        System.out.println(search);
    }

}

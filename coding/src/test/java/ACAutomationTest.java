import com.ly.algorithm.coding.ACCoding;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2023/9/8 22:10
 * @desc
 **/
public class ACAutomationTest {

    @Test
    public void test(){
        ACCoding.ACAutomation ac = new ACCoding.ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("abcdheks");
        ac.build();
        List<String> contains = ac.containsWord("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for(String word : contains){
            System.out.println(word);
        }
    }

}

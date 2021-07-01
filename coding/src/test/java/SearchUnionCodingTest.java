import com.google.common.collect.Lists;
import com.ly.algorithm.coding.SearchUnionCoding;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2021/7/1 17:18
 * @desc
 **/
public class SearchUnionCodingTest {

    @Test
    public void testIndependentAccount(){
        List<SearchUnionCoding.Account> accounts = Lists.newArrayList();
        accounts.add(new SearchUnionCoding.Account("1","bilibili1","gitHub1"));
        accounts.add(new SearchUnionCoding.Account("2","bilibili2","gitHub2"));
        accounts.add(new SearchUnionCoding.Account("1","bilibili5","gitHub5"));
        accounts.add(new SearchUnionCoding.Account("3","bilibili3","gitHub3"));
        accounts.add(new SearchUnionCoding.Account("4","bilibili8","gitHub1"));
        accounts.add(new SearchUnionCoding.Account("2","bilibili2","gitHub12"));
        accounts.add(new SearchUnionCoding.Account("3","bilibili3","gitHub6"));
        accounts.add(new SearchUnionCoding.Account("1","bilibili7","gitHub7"));
        System.out.println("独立用户一共有："+SearchUnionCoding.getIndependentUsers(accounts)+"个");


    }

}

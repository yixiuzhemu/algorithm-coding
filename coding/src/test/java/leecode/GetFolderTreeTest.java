package leecode;

import com.google.common.collect.Lists;
import com.ly.algorithm.coding.leecode.GetFolderTree;
import org.junit.Test;

import java.util.List;

/**
 * @author Ly
 * @create 2023/10/6 11:48
 * @desc
 **/
public class GetFolderTreeTest {

    @Test
    public void getFolderTree(){
        String test1 = "a\\b\\c";
        String test2 = "a\\b\\s";
        String test3 = "a\\d\\e";
        String test4 = "e\\f\\sty";
        List<String> arr = Lists.newArrayList();
        arr.add(test1);
        arr.add(test2);
        arr.add(test3);
        arr.add(test4);
        GetFolderTree.print(arr);
    }

}

package leecode;

import com.google.common.collect.Lists;
import com.ly.algorithm.coding.leecode.WordMinPaths;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ly
 * @create 2023/10/15 15:32
 * @desc
 **/
public class WordMinPathsTest {

    @Test
    public void minPaths(){
        ArrayList<String> paths = Lists.newArrayList("cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb");
        String start = "abc";
        String end = "cab";
        paths.add(start);
        paths.add(end);
        List<List<String>> res = WordMinPaths.minPaths(paths, start, end);
        for (List<String> re : res) {
            for (int i = 0;i<re.size();i++) {
                System.out.print(re.get(i));
                if(i < re.size() - 1){
                    System.out.print("->");
                }
            }
            System.out.println();
        }
    }

}

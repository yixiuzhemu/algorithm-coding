import com.ly.algorithm.Tree;
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

    @Test
    public void testRotateString(){
        String str1 = "123456";
        String str2 = "345612";
        System.out.println("是否互为旋转字符串："+KmpCoding.rotateStr(str1,str2));
    }

    @Test
    public void testEqualTree(){
        Tree N1 = TestData.getTree();
        Tree N2 = new Tree();
        N2.setValue(7);
        Tree tree6 = new Tree();
        tree6.setValue(6);
        Tree tree7 = new Tree();
        tree7.setValue(8);
        N2.setLeft(tree6);
        N2.setRight(tree7);
        System.out.println("是否属于相等结构树："+KmpCoding.treeEquals(N1,N2));

    }

}

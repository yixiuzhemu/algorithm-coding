import com.google.common.collect.Lists;
import com.ly.algorithm.Tree;
import com.ly.algorithm.coding.TreeCoding;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

/**
 * @author Ly
 * @create 2021/6/21 14:12
 * @desc
 **/
public class TreeCodingTest {

    private Tree tree;

    @Before
    public void buildTree(){
        Tree tree1 = new Tree();
        tree1.setValue(1);
        Tree tree2 = new Tree();
        tree2.setValue(2);
        Tree tree3 = new Tree();
        tree1.setLeft(tree2);
        tree1.setRight(tree3);
        tree3.setValue(3);
        Tree tree4 = new Tree();
        tree4.setValue(4);
        Tree tree5 = new Tree();
        tree5.setValue(5);
        tree2.setLeft(tree4);
        tree2.setRight(tree5);
        Tree tree6 = new Tree();
        tree6.setValue(6);
        Tree tree7 = new Tree();
        tree7.setValue(7);
        tree3.setLeft(tree6);
        tree3.setRight(tree7);
        tree = tree1;
    }
    
    @Test
    public  void showTreeTest() {
        TreeCoding.pre(tree);
        System.out.println("-------------------");
        TreeCoding.pre1(tree);
        System.out.println("-------------------");
        TreeCoding.mid(tree);
        System.out.println("-------------------");
        TreeCoding.mid1(tree);
        System.out.println("-------------------");
        TreeCoding.suf(tree);
        System.out.println("-------------------");
        TreeCoding.suf1(tree);
        System.out.println("-------------------");
        TreeCoding.suf2(tree);
        System.out.println("-------------------");
        TreeCoding.width(tree);
        System.out.println("-------------------");
        TreeCoding.getWidth(tree);

    }

    public void serialAndBuildTreeTest(){
        Queue<String> result = Lists.newLinkedList();
        TreeCoding.preSerial(tree,result);
        Tree newTree =TreeCoding.buildTree(result);
        TreeCoding.pre(newTree);
        result = Lists.newLinkedList();
        TreeCoding.levelSerial(tree,result);
        Tree tree = TreeCoding.buildLevelTree(result);
        TreeCoding.width(tree);
    }
}

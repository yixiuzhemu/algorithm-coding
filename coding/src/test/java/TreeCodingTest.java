import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ly.algorithm.ParentTree;
import com.ly.algorithm.Tree;
import com.ly.algorithm.coding.RecursiveRoutineCoding;
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


    @Test
    public void testParentNode(){
        ParentTree[] trees = TestData.getParentTree();
        System.out.println("中序遍历结果：");
        TreeCoding.mid(trees[0]);
        ParentTree successdingTree = TreeCoding.getSuccessdingTree(trees[1]);
        ParentTree precursorNode = TreeCoding.getPrecursorNode(trees[1]);
        System.out.println("--------------------------------------------------------");
        System.out.println("当前节点为："+trees[1].getValue());
        System.out.println("当前节点的后继节点为："+(successdingTree == null ? null:successdingTree.getValue()));
        System.out.println("当前节点的前驱节点为："+(precursorNode == null ? null:precursorNode.getValue()));
    }

    @Test
    public void testPrint(){
        TreeCoding.printAllCrease(6);
    }

    @Test
    public void testMaxDistance(){
        Tree tree = TestData.getTree();
        Integer distance = RecursiveRoutineCoding.getMaxDistance(tree);
        System.out.println("最大距离为："+distance);

    }

    @Test
    public void testMaxSearchTree(){
        Tree tree = TestData.getTree();
        Integer maxSearchTree = RecursiveRoutineCoding.getMaxSearchTree(tree);
        System.out.println("最大搜索子树节点数为："+maxSearchTree);

    }

    @Test
    public void testFullTree(){
        Tree tree = new Tree(13);
        tree.setLeft(new Tree(76));
        Tree tree1 = new Tree(87);
        tree1.setLeft(new Tree(17));
        tree.setRight(tree1);
        System.out.println(JSON.toJSONString(tree));
        System.out.println("是否是满二叉树："+ RecursiveRoutineCoding.isFullTree(tree));
    }

    @Test
    public void testCompletelyTree(){
        for(int i = 0 ;i<100000;i++){
            Tree tree = TestData.getRandomTree();
            if(RecursiveRoutineCoding.isCompletelyTree2(tree) != RecursiveRoutineCoding.isCompletelyTree(tree)){
                System.out.println("1："+ RecursiveRoutineCoding.isCompletelyTree2(tree) +" 2:"+RecursiveRoutineCoding.isCompletelyTree(tree));
                System.out.println("校验失败："+ JSON.toJSONString(tree));
                RecursiveRoutineCoding.isCompletelyTree(tree);
                TreeCoding.width(tree);
                TreeCoding.pre(tree);
                TreeCoding.mid(tree);
            }

        }
        System.out.println("校验通过！");
    }


    @Test
    public void testAncestorTree(){
            Tree tree =  TestData.getRandomTree();
            RecursiveRoutineCoding.getAncestor(tree);

    }
}

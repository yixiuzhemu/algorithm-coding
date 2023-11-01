package leecode;

import com.ly.algorithm.coding.leecode.MaxSumInTree;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/16 14:04
 * @desc
 **/
public class MaxSumInTreeTest {

    @Test
    public void maxSumInTree(){
        MaxSumInTree.Node head = new MaxSumInTree.Node(3);
        MaxSumInTree.Node left = new MaxSumInTree.Node(-4);
        MaxSumInTree.Node right = new MaxSumInTree.Node(-5);
        MaxSumInTree.Node leftLeft = new MaxSumInTree.Node(9);
        MaxSumInTree.Node leftRight = new MaxSumInTree.Node(4);
        MaxSumInTree.Node rightLeft = new MaxSumInTree.Node(-10);
        MaxSumInTree.Node rightRight = new MaxSumInTree.Node(7);
        head.setLeft(left);
        head.setRight(right);
        left.setLeft(leftLeft);
        left.setRight(leftRight);
        right.setLeft(rightLeft);
        right.setRight(rightRight);
        System.out.println(MaxSumInTree.getMaxSumInTree(head));
        System.out.println(MaxSumInTree.getMaxSumInTreeV2(head));
        System.out.println(MaxSumInTree.getMaxSumInTree_2(head));
        System.out.println(MaxSumInTree.getMaxSumInTree_3(head));
    }

}

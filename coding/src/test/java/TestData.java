import com.google.common.collect.Lists;
import com.ly.algorithm.Node;
import com.ly.algorithm.ParentTree;
import com.ly.algorithm.RandomNode;
import com.ly.algorithm.Tree;

import java.util.List;
import java.util.Random;

/**
 * @author Ly
 * @create 2021/6/22 15:50
 * @desc
 **/
public class TestData {

    public static int[] getArr(){
        int[] arr = new int[]{100,101,5,1,3,6,8,9,5,7,2,8,5,10};
        return arr;
    }

    public static Node getNode(){
        Node head = new Node(9);
        Node node = head;
        Node node1 = new Node(4);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        node =  node.addNode(new Node(1));
        node =  node.addNode(new Node(2));
        node =  node.addNode(node3);
        node =  node.addNode(node1);
        node =  node.addNode(node4);
        node =  node.addNode(new Node(7));
        node =  node.addNode(new Node(8));
        return head;
    }

    public static ParentTree[] getParentTree(){
        List<ParentTree> treeLists = Lists.newArrayList();
        ParentTree tree = new ParentTree(1);
        treeLists.add(tree);
        ParentTree tree2 = new ParentTree(2);
        treeLists.add(tree2);
        ParentTree tree3 = new ParentTree(3);
        treeLists.add(tree3);
        ParentTree tree4 = new ParentTree(4);
        treeLists.add(tree4);
        ParentTree tree5 = new ParentTree(5);
        treeLists.add(tree5);
        ParentTree tree6 = new ParentTree(6);
        treeLists.add(tree6);
        ParentTree tree7 = new ParentTree(7);
        treeLists.add(tree7);
        ParentTree tree8 = new ParentTree(8);
        treeLists.add(tree8);
        ParentTree tree9 = new ParentTree(9);
        treeLists.add(tree9);
        ParentTree tree10 = new ParentTree(10);
        treeLists.add(tree10);
        tree.setLeft(tree2);
        tree.setRight(tree3);
        tree2.setLeft(tree4);
        tree2.setRight(tree5);
        tree3.setLeft(tree6);
        tree3.setRight(tree7);
        tree4.setRight(tree8);
        tree6.setLeft(tree9);
        tree7.setRight(tree10);
        Random random = new Random();
        return new ParentTree[]{tree, treeLists.get(random.nextInt(treeLists.size()))};
    }

    public static Node getLoopNode(){
        Node head = new Node(9);
        Node node = head;
        Node node1 = new Node(4);
        Node node3 = new Node(3);
        Node node4 = new Node(6);
        node =  node.addNode(new Node(1));
        node =  node.addNode(new Node(2));
        node =  node.addNode(node3);
        node =  node.addNode(node1);
        node =  node.addNode(node4);
        node =  node.addNode(new Node(7));
        node =  node.addNode(new Node(8));
        node.setNext(node1);
        return head;
    }

    public static RandomNode getRandomNode(){
        RandomNode head = new RandomNode(1);
        RandomNode node2 = new RandomNode(2);
        RandomNode node =  head.addNode(node2);
        RandomNode node3 = new RandomNode(3);
        head.setRand(node3);
        node = node.addNode(node3);
        node = node.addNode(new RandomNode(4));
        RandomNode node5 = new RandomNode(5);
        node2.setRand(node5);
        node = node.addNode(node5);
        return head;
    }


    public static Tree getTree(){
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
        return tree1;
    }

    public static void swap(int[] arr,int i,int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}

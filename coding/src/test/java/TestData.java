import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ly.algorithm.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

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
        tree3.setValue(7);
        Tree tree4 = new Tree();
        tree4.setValue(8);
        Tree tree5 = new Tree();
        tree5.setValue(5);
        tree2.setLeft(tree4);
        tree2.setRight(tree5);
        Tree tree6 = new Tree();
        tree6.setValue(6);
        Tree tree7 = new Tree();
        tree7.setValue(8);
        tree3.setLeft(tree6);
        tree3.setRight(tree7);
        return tree1;
    }

    public static Tree getRandomTree(){
        int maxLevel = 2;
        int maxValue = 100;
        return  generate(0,maxLevel,maxValue);
    }

    private static Tree generate(int level,int maxLevel,int maxValue){
        if(level > maxLevel || Math.random() < 0.5){
            return null;
        }
        Tree head = new Tree((int) (Math.random() * maxValue));
        head.setLeft(generate(level+1,maxLevel,maxValue));
        head.setRight(generate(level+1,maxLevel,maxValue));
        return head;
    }


    public static void swap(int[] arr,int i,int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static EmployeeInfo getEmployeeInfo(){
        Random random = new Random();
        Integer baseHappy = 100;
        EmployeeInfo boss = new EmployeeInfo(10);
        List<EmployeeInfo> e1 = Lists.newArrayList();
        boss.subordinates = e1;
        List<EmployeeInfo> e2 = Lists.newArrayList();
        List<EmployeeInfo> e3 = Lists.newArrayList();
        List<EmployeeInfo> e4 = Lists.newArrayList();
        List<EmployeeInfo> e5 = Lists.newArrayList();
        List<EmployeeInfo> e6 = Lists.newArrayList();
        List<EmployeeInfo> e7 = Lists.newArrayList();
        EmployeeInfo employeeInfo1 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        EmployeeInfo employeeInfo2 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        EmployeeInfo employeeInfo3 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e1.add(employeeInfo1);
        e1.add(employeeInfo2);
        e1.add(employeeInfo3);
        employeeInfo1.subordinates = e2;
        employeeInfo2.subordinates = e3;
        employeeInfo3.subordinates = e4;
        EmployeeInfo employeeInfo4 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        EmployeeInfo employeeInfo5 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e2.add(employeeInfo4);
        e2.add(employeeInfo5);
        EmployeeInfo employeeInfo6 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e3.add(employeeInfo6);
        EmployeeInfo employeeInfo7 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        EmployeeInfo employeeInfo8 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        EmployeeInfo employeeInfo9 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e4.add(employeeInfo7);
        e4.add(employeeInfo8);
        e4.add(employeeInfo9);
        employeeInfo7.subordinates = e5;
        employeeInfo8.subordinates = e6;
        employeeInfo9.subordinates = e7;
        EmployeeInfo employeeInfo10 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e5.add(employeeInfo10);
        EmployeeInfo employeeInfo11 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        EmployeeInfo employeeInfo12 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e6.add(employeeInfo11);
        e6.add(employeeInfo12);
        EmployeeInfo employeeInfo13 = new EmployeeInfo(random.nextInt(baseHappy) + 1);
        e7.add(employeeInfo13);
        return boss;
    }

    /**
     * 生成一组随机字符串
     * @param arrLen
     * @param strLen
     * @return
     */
    public static String[] generateRandomStringArray(int arrLen,int strLen){
        String[] ans = new String[(int)(Math.random() * arrLen) + 1];
        for(int i =0;i<ans.length;i++){
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    /**
     * 复制一组随机字符串
     * @param arr
     * @return
     */
    public static String[] copyStringArray(String[] arr){
        String[] ans = new String[arr.length];
        for(int i = 0;i<ans.length;i++){
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    /**
     * 生成一个随机字符串
     * @param strLen
     * @return
     */
    private static String generateRandomString(int strLen){
        char[] ans = new char[(int)(Math.random() * strLen) + 1];
        for(int i = 0;i<ans.length;i++){
            int value = (int)(Math.random() * 5);
            ans[i] = (char)(97+value);
        }
        return String.valueOf(ans);
    }
}

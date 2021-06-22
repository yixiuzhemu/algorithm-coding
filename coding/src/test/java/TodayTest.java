import com.alibaba.fastjson.JSON;
import com.ly.algorithm.Node;
import com.ly.algorithm.RandomNode;
import com.ly.algorithm.Tree;
import jdk.nashorn.internal.ir.LoopNode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/6/22 14:29
 * @desc
 **/
public class TodayTest {

    private int[] arr = null;

    private Tree tree = null;

    private Node loopNode = null;

    private Node head = null;

    private int partition = 5;

    private RandomNode randomNode = null;

    @Before
    public void init(){
        arr = TestData.getArr();
        tree = TestData.getTree();
        head = TestData.getNode();
        loopNode = TestData.getLoopNode();
        randomNode = TestData.getRandomNode();
    }

    @Test
    public void selectSort(){
        for (int i=0;i<arr.length;i++) {
            int min = arr[i];
            int count = i;
            for(int j = i+1;j<arr.length;j++){
                if(min > arr[j]){
                    min = arr[j];
                    count = j;
                }
            }
            TestData.swap(arr,count,i);
        }

        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void bubblingSort(){
        for(int i = 0;i<arr.length;i++){
            for(int j = 0;j<arr.length-1;j++){
                if(arr[j] > arr[j+1]){
                    TestData.swap(arr,j,j+1);
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void insertSort(){
        for(int i = 0;i< arr.length;i++){
            for(int j = i+1;j<arr.length;j++){
                if(arr[j] < arr[i]){
                    TestData.swap(arr,i,j);
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void radixSort(){
        int radix = 10;
        int[] help = new int[arr.length];
        int bit = 1;
        int max = 0;
        for (int i : arr) {
            if(i > max){
                max = i;
            }
        }
        while((max/=radix)>0){
            bit++;
        }
        for(int i = 0;i<bit;i++){
            int divideRadix =(int) Math.pow(radix,i);
            int[] count = new int[radix];
            int[] countB = new int[radix];
            for(int j = 0;j<arr.length;j++){
                count[(arr[j]/divideRadix)%radix]++;
            }
            int sum = 0;
            for(int j = 0;j<count.length;j++){
                sum+=count[j];
                countB[j] = sum;
            }
            for(int j = arr.length-1;j>=0;j--){
                help[--countB[(arr[j]/divideRadix)%radix]] = arr[j];
            }
            for(int j = 0;j<arr.length;j++){
                arr[j] = help[j];
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void dutchFlag(){
        Node lessH = null;
        Node lessE = null;
        Node equalH = null;
        Node equalE = null;
        Node moreH = null;
        Node moreE = null;
        Node cur = head;
        while(cur != null){
            Node next = cur.getNext();
            int value = cur.getValue();
            if(value<partition){
                if(lessH == null){
                    lessH = cur;
                    lessE = cur;
                }else{
                    lessE.setNext(cur);
                    lessE = cur;
                }
            }else if(value == partition){
                if(equalH == null){
                    equalH = cur;
                    equalE = cur;
                }else{
                    equalE.setNext(cur);
                    equalE = cur;
                }
            }else{
                if(moreH == null){
                    moreH = cur;
                    moreE = cur;
                }else{
                    moreE.setNext(cur);
                    moreE = cur;
                }
            }
            cur = next;
        }
        Node newHead = null;
        if(lessH != null){
            newHead = lessH;
        }
        if(equalH != null){
            if(newHead != null){
                lessE.setNext(equalH);
            }else{
                newHead = equalH;
            }
        }
        if(moreH != null){
            if(newHead != null){
                if(equalH != null){
                    equalE.setNext(moreH);
                }else{
                    lessE.setNext(moreH);
                }
            }else{
                newHead = moreH;
            }
        }
        System.out.println(JSON.toJSONString(newHead));
    }

    @Test
    public void copyRandomNode(){
        RandomNode cur = randomNode;
        while(cur != null){
            RandomNode next = cur.getNext();
            RandomNode copyNode = cur.copyNode();
            cur.setNext(copyNode);
            copyNode.setNext(next);
            cur = next;
        }
        cur = randomNode;
        while(cur != null){
            RandomNode copyNode = cur.getNext();
            RandomNode next = copyNode.getNext();
            copyNode.setRand(cur.getRand() == null ? null : cur.getRand().getNext());
            cur = next;
        }
        cur = randomNode;
        RandomNode copyHead = cur.getNext();
        while(cur != null){
            RandomNode copyNode = cur.getNext();
            RandomNode next = copyNode.getNext();
            cur.next = next;
            copyNode.setNext(next == null ? null:next.getNext());
            cur = next;
        }
        System.out.println(JSON.toJSONString(copyHead));
    }
}

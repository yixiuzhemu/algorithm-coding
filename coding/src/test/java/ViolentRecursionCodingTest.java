import com.alibaba.fastjson.JSON;
import com.ly.algorithm.coding.ViolentRecursionCoding;
import org.junit.Test;

import java.util.Stack;

/**
 * @author Ly
 * @create 2021/7/7 16:19
 * @desc
 **/
public class ViolentRecursionCodingTest {

    @Test
    public void testHanoi(){
        ViolentRecursionCoding.hanoi1(3);
        System.out.println("--------------------------------------------");
        ViolentRecursionCoding.hanoi2(4);
        System.out.println("--------------------------------------------");
        ViolentRecursionCoding.hanoi3(4);
    }

    @Test
    public void reverseStack(){
        Stack stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        ViolentRecursionCoding.reverse(stack);
        System.out.println("reverse:"+ JSON.toJSONString(stack));
    }

    @Test
    public void testSubsequence(){
        ViolentRecursionCoding.printSubsequence("aaa");
        System.out.println("--------------------------------------------");
        ViolentRecursionCoding.printSubsequence2("aaa");
        System.out.println("--------------------------------------------");
        ViolentRecursionCoding.printSubsequence3("abcd");
    }

    @Test
    public void testConvertString(){
        ViolentRecursionCoding.convertString("11126");
    }

    @Test
    public void testMaxPrice(){
        int[] weights = new int[]{10,5,20,200,30,100,150,26,46,12,57};
        int[] values = new int[]{5,3,10,140,15,50,150,13,23,6,28};
        int maxPrice = ViolentRecursionCoding.getMaxPrice(weights, values, 200);
        int maxPrice2 = ViolentRecursionCoding.getMaxPrice2(weights, values, 200);
        System.out.println("能够装的货物的最大价值为："+maxPrice);
        System.out.println("能够装的货物的最大价值为："+maxPrice2);
    }


    @Test
    public void testMaxScore(){
        int[] arr = new int[]{10,5,20,200,30,100,150,26,46,12,57};
        int win = ViolentRecursionCoding.getMaxScore(arr);
        System.out.println("赢家能获得的最大分数为："+win);
    }

    @Test
    public void testQueen(){
        ViolentRecursionCoding.nQueen(15);
        ViolentRecursionCoding.nQueen2(15);
    }

    @Test
    public void testMove(){
        int move = ViolentRecursionCoding.move(7, 3, 3, 2);
        int move2 = ViolentRecursionCoding.move2(7, 3, 3, 2);
        System.out.println("机器人一共有"+move+"种移动方法");
        System.out.println("机器人一共有"+move2+"种移动方法");
    }

    @Test
    public void testPriceCount(){
        int[] ints = new int[]{100,50};
        int price =200;
        int count2 = ViolentRecursionCoding.getPriceCount2(ints,price);
        System.out.println("一共有"+count2+"种方法");
    }

    @Test
    public void washCoffeeCup(){
        int[] arr = new int[]{5,2,6,19,31,28,5,6,8,10};
        int a = 3;
        int b = 20;
        int i = ViolentRecursionCoding.washCoffeeCup(arr, a, b);
        System.out.println("洗干净所有咖啡杯需要："+i);
    }
}

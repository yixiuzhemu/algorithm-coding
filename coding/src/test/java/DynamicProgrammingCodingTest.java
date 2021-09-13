import com.ly.algorithm.coding.DynamicProgrammingCoding;
import com.ly.algorithm.coding.ViolentRecursionCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2021/7/12 15:54
 * @desc
 **/
public class DynamicProgrammingCodingTest {

    @Test
    public void testMove(){
        int move3 = DynamicProgrammingCoding.move3(7, 3, 3, 2);
        System.out.println("机器人一共有"+move3+"种移动方法");
    }

    @Test
    public void testMaxPrice(){
        int[] weights = new int[]{10,5,20,200,30,100,150,26,46,12,57};
        int[] values = new int[]{5,3,10,140,15,50,150,13,23,6,28};
        int maxPrice = DynamicProgrammingCoding.getMaxPrice(weights, values, 200);
        System.out.println("能够装的货物的最大价值为："+maxPrice);
    }

    @Test
    public void testSubsequence(){
        ViolentRecursionCoding.printSubsequence("abcde");
        System.out.println("--------------------------------------------");
        DynamicProgrammingCoding.printSubsequence("abcde");
        System.out.println("--------------------------------------------");
    }

    @Test
    public void testConvertString(){
        ViolentRecursionCoding.convertString("11126");
        System.out.println("--------------------------------------------");
        int i = DynamicProgrammingCoding.convertString("11126");
        System.out.println("共有"+i+"种转换结果");
    }

    @Test
    public void testMaxScore(){
        int[] arr = new int[]{10,5,20,200,30,100,150,26,46,12,57};
        int win = ViolentRecursionCoding.getMaxScore(arr);
        System.out.println("赢家能获得的最大分数为："+win);
        int win2 = DynamicProgrammingCoding.getMaxScore(arr);
        System.out.println("赢家能获得的最大分数为："+win2);
    }

    @Test
    public void testPriceCount(){
        int[] ints = new int[]{5,10,50,100};
        int price =1000;
        int count1 = DynamicProgrammingCoding.getPriceCount(ints,price);
        System.out.println("一共有"+count1+"种方法");
        int count2 = ViolentRecursionCoding.getPriceCount2(ints,price);
        System.out.println("一共有"+count2+"种方法");
        int count3 = DynamicProgrammingCoding.getPriceCount2(ints,price);
        System.out.println("一共有"+count3+"种方法");
    }

    @Test
    public void testSticker(){
        String[] strs = new String[]{"ba","c","abcd"};
        String str = "babac";
        int stickerStr1 = DynamicProgrammingCoding.getStickerStr1(strs, str);
        System.out.println("至少需要"+stickerStr1+"张贴纸");
    }

    @Test
    public void testLongestPublicSubsequence(){
        String str1 = "a1b2c3s4d4e5";
        String str2 = "1f2f22g3kh4i5mj";
        int length = DynamicProgrammingCoding.longestPublicSubsequence(str1,str2);
        System.out.println("最长公共子序列长度为："+length);
    }

    @Test
    public void washCoffeeCup(){
        int[] arr = new int[]{5,2,6,19,31,28,5,6,8,10};
        int a = 3;
        int b = 20;
        int i2 = DynamicProgrammingCoding.washCoffeeCup(arr, a, b);
        System.out.println("洗干净所有咖啡杯需要："+i2);
    }

    @Test
    public void horseJump(){
        int i = ViolentRecursionCoding.horseJump(8, 6,10);
        System.out.println("马跳到目标位置，一共有："+i+"种方案");
        int j = DynamicProgrammingCoding.horseJump(8, 6,10);
        System.out.println("马跳到目标位置，一共有："+j+"种方案");
    }
}

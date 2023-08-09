import com.ly.algorithm.coding.MakeTableCoding;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/8/5 12:35
 * @desc
 **/
public class MakeTableTest {

    @Test
    public void minBag(){
        for(int i = 1;i<1000;i++){
            int m = MakeTableCoding.minBagAwsome2(i);
            System.out.println(i+"个苹果至少需要"+m+"个袋子");
        }

        for(int i = 1;i<1000;i++){
            int m = MakeTableCoding.minBagAwsome(i);
            System.out.println(i+"个苹果至少需要"+m+"个袋子");
        }
    }

    @Test
    public void fastEat(){
//        for(int i = 1;i<1000;i++){
//            boolean m = MakeTable.fastEat(i,true);
//            System.out.println("i:"+i+(m?" 牛赢！":" 羊赢！"));
//        }
        for(int i = 1;i<1000;i++){
            boolean m = MakeTableCoding.fastEat(i);
            System.out.println();
        }
    }

    @Test
    public void isContinuationSum(){
        for(int i = 1;i<1000;i++){
            if(MakeTableCoding.isContinuationSum(i) != MakeTableCoding.isContinuationSum2(i)){
                System.out.println(i);
            }
        }
        System.out.println("true");
    }

}

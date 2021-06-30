import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.ly.algorithm.coding.GreedyAlgorithmCoding;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ly
 * @create 2021/6/25 17:52
 * @desc
 **/
public class GreedyAlgorithmcodingTest {

    @Test
    public void testLowestStr(){
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 100000;
        for(int i = 0;i<testTimes;i++){
            String[] arr1 = TestData.generateRandomStringArray(arrLen,strLen);
            String[] arr2 = TestData.copyStringArray(arr1);
            if(!GreedyAlgorithmCoding.lowestString(arr1).equals(GreedyAlgorithmCoding.lowestString2(arr2))){
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    @Test
    public void testMeeting(){
        for(int k = 0;k<100000;k++){
            List<GreedyAlgorithmCoding.Program> randomPrograms = GreedyAlgorithmCoding.getRandomPrograms();
            GreedyAlgorithmCoding.Program[] programs = new GreedyAlgorithmCoding.Program[randomPrograms.size()];
            for(int i = 0;i<randomPrograms.size();i++){
                programs[i] = randomPrograms.get(i);
            }
            int process = GreedyAlgorithmCoding.process(programs, 0, 0);
            int process2 = GreedyAlgorithmCoding.process2(programs);
            if(process != process2){
                System.out.println("Oops:"+process+" "+process2);
            }
        }
        System.out.println("对比结束！");
    }
}

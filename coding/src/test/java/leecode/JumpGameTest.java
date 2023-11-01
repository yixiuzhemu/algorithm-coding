package leecode;

import com.ly.algorithm.coding.leecode.JumpGame;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/22 10:06
 * @desc
 **/
public class JumpGameTest {

    @Test
    public void jumpGame(){
        int[] arr = new int[]{3,1,4,2,1,1,5,2,1,1};
        System.out.println(JumpGame.jumpGame(arr));
        System.out.println(JumpGame.jumpGame_v2(arr));
        System.out.println(JumpGame.jumpGame_v3(arr));
    }

}

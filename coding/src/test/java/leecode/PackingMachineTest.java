package leecode;

import com.ly.algorithm.coding.leecode.PackingMachine;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/9/19 21:15
 * @desc
 **/
public class PackingMachineTest {

    @Test
    public void packingMachine(){
        int[] machines = new int[]{12,0,3};
        System.out.println(PackingMachine.packingMachine(machines));
    }

}

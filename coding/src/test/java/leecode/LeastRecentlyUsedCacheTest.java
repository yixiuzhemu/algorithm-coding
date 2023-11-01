package leecode;

import com.ly.algorithm.coding.leecode.LeastRecentlyUsedCache;
import org.junit.Test;

/**
 * @author Ly
 * @create 2023/10/14 10:28
 * @desc
 **/
public class LeastRecentlyUsedCacheTest {

    @Test
    public void lru(){
        LeastRecentlyUsedCache<Integer, Integer> lru = new LeastRecentlyUsedCache<>(10);
        for(int i = 0;i<100;i++){
            lru.put(i,i);
        }
        System.out.println(lru.get(97));
        System.out.println(lru.get(88));
    }

}

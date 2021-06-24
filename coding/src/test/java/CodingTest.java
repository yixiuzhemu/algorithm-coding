import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 获取测试方法
 * @author Ly
 * @create 2021/6/21 14:28
 * @desc
 **/
public class CodingTest {

    @Test
    public void getTestMethod(){
        Map<Integer,String> maps = Maps.newHashMap();
        Integer count = 0;
        maps.put(++count,"选择排序");
        maps.put(++count,"冒泡排序");
        maps.put(++count,"插入排序");
        maps.put(++count,"归并排序");
        maps.put(++count,"随机快排");
        maps.put(++count,"堆排序");
        maps.put(++count,"计数排序");
        maps.put(++count,"基数排序");
        maps.put(++count,"前缀树");
        maps.put(++count,"快慢指针（找中点）");
        maps.put(++count,"反转链表");
        maps.put(++count,"回文判断");
        maps.put(++count,"链表实现荷兰国旗");
        maps.put(++count,"复制rand链表");
        maps.put(++count,"获取有环链表相交点");
        maps.put(++count,"二叉树先序、中序、后序");
        maps.put(++count,"二叉树宽度、层级遍历");
        maps.put(++count,"二叉树序列化、反序列化");
        maps.put(++count,"ParentTree树结构获取前驱、后继节点");
        maps.put(++count,"二叉树最大距离");
        maps.put(++count,"搜索二叉树的头节点");
        maps.put(++count,"派对最大快乐值");
        Random random = new Random();
        Set<Integer> sets = Sets.newHashSet();
        Integer num = count / 3;
        while(sets.size()<num){
            sets.add(random.nextInt(count)+1);
        }
        Iterator<Integer> iterator = sets.iterator();
        while(iterator.hasNext()){
            Integer key = iterator.next();
            System.out.println(maps.get(key));
        }
    }



}

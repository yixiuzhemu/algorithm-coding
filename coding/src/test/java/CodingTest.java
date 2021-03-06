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
        maps.put(++count,"排序-选择排序");
        maps.put(++count,"排序-冒泡排序");
        maps.put(++count,"排序-插入排序");
        maps.put(++count,"排序-归并排序");
        maps.put(++count,"排序-随机快排");
        maps.put(++count,"排序-堆排序");
        maps.put(++count,"排序-计数排序");
        maps.put(++count,"排序-基数排序");
        maps.put(++count,"前缀树算法");
        maps.put(++count,"链表-快慢指针（找中点）");
        maps.put(++count,"链表-反转链表");
        maps.put(++count,"链表-回文判断");
        maps.put(++count,"链表-链表实现荷兰国旗");
        maps.put(++count,"链表-复制rand链表");
        maps.put(++count,"链表-获取有环链表相交点");
        maps.put(++count,"二叉树-先序、中序、后序");
        maps.put(++count,"二叉树-宽度、层级遍历");
        maps.put(++count,"二叉树-序列化、反序列化");
        maps.put(++count,"二叉树-ParentTree树结构获取前驱、后继节点");
        maps.put(++count,"二叉树-打印折痕");
        maps.put(++count,"二叉树的递归套路-二叉树最大距离");
        maps.put(++count,"二叉树的递归套路-搜索二叉树的头节点");
        maps.put(++count,"二叉树的递归套路-派对最大快乐值");
        maps.put(++count,"二叉树的递归套路-判断是否是满二叉树");
        maps.put(++count,"二叉树的递归套路-判断是否是完全二叉树");
        maps.put(++count,"二叉树的递归套路-两个节点最低公共祖先");
        maps.put(++count,"贪心算法-返回字符串拼接字典序最小的结果");
        maps.put(++count,"贪心算法-会议室宣讲");
        maps.put(++count,"贪心算法-照亮居民");
        maps.put(++count,"贪心算法-金币切割");
        maps.put(++count,"贪心算法-最大项目资金");
        maps.put(++count,"并查集-查询独立用户");
        maps.put(++count,"图-宽度优先遍历");
        maps.put(++count,"图-深度优先遍历");
        maps.put(++count,"图-拓扑排序算法");
        maps.put(++count,"图-最小生成树算法之Kruskal");
        maps.put(++count,"图-最小生成树算法之psim");
        maps.put(++count,"图-最小生成树算法之dijkstra及其优化写法");
        maps.put(++count,"暴力递归-汉诺塔问题");
        maps.put(++count,"暴力递归-逆序栈");
        maps.put(++count,"暴力递归-左右模型-字符串子序列");
        maps.put(++count,"暴力递归-左右模型-数字字符串转换为字符字符串");
        maps.put(++count,"暴力递归-左右模型-背包问题");
        maps.put(++count,"暴力递归-范围模型-拿牌问题");
        maps.put(++count,"暴力递归-N皇后问题(及优化)");
        maps.put(++count,"暴力递归-机器人移动");
        maps.put(++count,"动态规划-机器人移动");
        maps.put(++count,"动态规划-字符串的全部子序列");
        maps.put(++count,"动态规划-数字字符串转换为字符字符串");
        maps.put(++count,"动态规划-拿牌问题");
        maps.put(++count,"暴力递归-凑金额");
        maps.put(++count,"记忆化搜索-凑金额");
        maps.put(++count,"动态规划-凑金额");
        maps.put(++count,"记忆化搜索-贴纸");
        maps.put(++count,"动态规划-多样本位置全对应的尝试模型-两个字符串的最长公共子序列");
        maps.put(++count,"暴力递归-寻找业务限制的尝试模型-洗咖啡杯");
        maps.put(++count,"动态规划-洗咖啡杯");
        maps.put(++count,"暴力递归-象棋问题");
        maps.put(++count,"动态规划-象棋问题");
        maps.put(++count,"滑动窗口-获取子数组");
        maps.put(++count,"滑动窗口-获取最大值");
        maps.put(++count,"单调栈-获取左边以及右边比当前值小的位置");
        maps.put(++count,"单调栈-获取最大和");
        maps.put(++count,"斐波那契数列-暴力递归求解");
        maps.put(++count,"斐波那契数列-线性求解");
        maps.put(++count,"斐波那契数列-矩阵式求解");
        maps.put(++count,"类似斐波那契数列问题-达标字符串");
        maps.put(++count,"类似斐波那契数列问题-贴瓷砖");
        maps.put(++count,"蓄水池算法-接球入袋");
        maps.put(++count,"概率转化");
        maps.put(++count,"KMP算法-匹配子串");
        maps.put(++count,"KMP算法-旋转字符串");
        maps.put(++count,"KMP算法-相等结构树");
        maps.put(++count,"bfprt算法");
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

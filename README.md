> #com.ly.algorithm-coding
>>>some alorithm's coding

># 一、常见算法题
>>>* [(1).蓄水池算法-接球入袋（适用于抽奖场景，实现等概率）](/coding/src/main/java/com/ly/algorithm/coding/CommonCoding.java "蓄水池算法-接球入袋")
>>>* [(2).概率转化（将等概率的某个返回，转换成其它范围上的等概率）](/coding/src/main/java/com/ly/algorithm/coding/CommonCoding.java "概率转化")


>#  二、暴力递归
>>## 1、暴力递归就是尝试 
>>>* (1).把问题转化为规模缩小了的同类问题的子问题
>>>* (2).有明确的不需要进行递归的条件（base case）
>>>* (3).有当得到了子问题的结果之后的决策过程
>>>* (4).不记录每一个子问题的解 
  
>> ## 2、什么样的暴力递归可以优化
>>>* (1).有重复调用同一个子问题的解，这种递归可以优化
>>>* (2).如果每一个子问题都是不同的解，无法优化
  
>> ## 3、面试中设计暴力递归过程的原则
>>>* (1).每一个可变参数的类型，一定不要比int类型更加复杂
>>>* (2).原则1可以违法，让类型突破到一维线性结构，那必须是唯一可变参数
>>>* (3).如果发现原则1被违反，但不违反原则2，只需要做到记忆化搜索即可
>>>* (4).可变参数的个数，能少则少

>> ## 4、知道了面试中设计暴力递归过程的原则，然后呢？
>>>* (1).一定要逼自己找到不违反原则情况下的暴力尝试
>>>* (2).如果你找到的暴力尝试，不符合原则，马上舍弃，找新的
>>>* (3).如果某个提莫突破了设计原则， 一定极难极难，面试中出现概率低于5%
      
>> ## 5、如何分析有没有重复解
>>>* (1).列出调用过程，可以只列出前几层
>>>* (2).有没有重复解，一看便知
      
>> ## 6、暴力递归常见的4中模型
>>>* (1).从左往右的尝试模型
>>>* (2).范围上的尝试模型
>>>* (3).多样本位置全对应的尝试模型
>>>* (4).寻找业务限制的尝试模型

>> ## 7、暴力递归常见的题型
>>>* [(1).汉诺塔](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "hanoi1、hanoi2、hanoi3")
>>>* [(2).逆序栈](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "reverse")
>>>* [(3).打印一个字符串的全部子序列](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "printSubsequence")
>>>* [(4).数字字符串转换为字符字符串](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "convertString")
>>>* [(5).背包问题](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "getMaxPrice")
>>>* [(6).拿牌问题](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "getMaxScore")
>>>* [(7).N皇后及常数时间项优化](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "nQueen、nQueen2")
>>>* [(8).机器人移动](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "move")
>>>* [(9).凑金额](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "getPriceCount2")
>>>* [(10).暴力递归-寻找业务限制的尝试模型-洗咖啡杯](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "washCoffeeCup")
>>>* [(11).象棋问题](/coding/src/main/java/com/ly/algorithm/coding/ViolentRecursionCoding.java "horseJump")

> # 三、动态规划
>> ## 1、如何找到某个问题的动态规划方式
>>>* (1).设计暴力递归：重要原则+4中常见尝试模型
>>>* (2).分析有没有重复解：套路解决
>>>* (3).用记忆化搜索——>用严格表结构实现动态规划，套路解决
>>>* (4).看是否能继续优化，套路解决

>> ## 2、暴力递归和动态规划的关系
>>>* (1).某一个暴力递归，有解的重复调用，就可以把这个暴力递归优化成动态规划
>>>* (2).任何动态规划问题，都一定对应着某一个有解的重复调用的暴力递归
>>>* (3).但不是所有的暴力递归，都一定对应着动态规划

>> ## 3、面试题和动态规划的关系
>>>* (1).解决一个问题，可能有很多尝试方法
>>>* (2).可能在很多尝试方法中，又有若干个尝试方法有动态规划的方式
>>>* (3).一个问题可能有若干种动态规划的解法

>> ## 4、暴力递归到动态规划的套路
>>>* (1).你已经有了一个不违反原则的暴力递归，而且的确存在解的重复调用
>>>* (2).找到哪些参数的变化会影响返回值，对每一个参数列出变化范围
>>>* (3).参数间的所有的组合数量，意味着表大小
>>>* (4).记忆化搜索的方法就是傻缓存，非常容易得到
>>>* (5).规定好严格表的大小，分析位置的依赖顺序，然后从基础填写到最终解
>>>* (6).对于有枚举行为的决策过程，进一步优化

>> ## 5、动态规划的进一步优化
>>>* (1).空间压缩
>>>* (2).状态化简
>>>* (3).四边形不等式
>>>* (4).其它优化技巧略

>> ## 6、动态规划常见题型
>>>* [(1).机器人移动](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "move")
>>>* [(2).背包问题](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "getMaxPrice")
>>>* [(3).打印一个字符串的全部子序列](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "printSubsequence")
>>>* [(4).数字字符串转换为字符字符串](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "convertString")
>>>* [(5).拿牌问题](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "getMaxScore")
>>>* [(6).凑金额](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "getPriceCount2")
>>>* [(7).贴纸(记忆化搜索 最优解)](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "getStickerStr1")
>>>* [(8).两个字符串的最长公共子序列](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "longestPublicSubsequence")
>>>* [(9).洗咖啡杯](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "washCoffeeCup")
>>>* [(10).象棋问题](/coding/src/main/java/com/ly/algorithm/coding/DynamicProgrammingCoding.java "horseJump")

> # [四、图](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java")
>> ## [1、图](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "GraphCoding.java")
>>>* (1).由点的集合和边的集合构成
>>>* (2).虽然存在有向图和无向图的概念，但实际上都可以用有向图来表达
>>>* (3).边上可能带有权值
      
>>[2、图的宽度优先遍历](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "width")
>>>* (1).利用队列实现
>>>* (2).从源节点开始依次按照宽度进队列，然后弹出
>>>* (3).每弹出一个点，把该节点所有没有进过队列的邻接节点加入队列
>>>* (4).直到队列变空

>>[3、的深度优先遍历](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "height")
>>>* (1).利用栈实现
>>>* (2).从源节点开始把节点按照深度放入栈，然后弹出
>>>* (3).每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
>>>* (4).直到栈变空
       
>>[4、图的拓扑排序算法](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "sort")
>>>* (1).在图中找到所有入度为0 的点输出
>>>* (2)，把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
>>>* (3).图的所有的点都被删除后，依次输出的顺序就是拓扑排序
>>>* 要求：有向图切其中没有环
>>>* 应用：事件安排、编译顺序
  
>>[5、最小生成树算法之Kruskal](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "kruskal")
>>>* (1).总是从权值最小的边开始考虑，依次考察权值依次变大的边
>>>* (2).当前的边要么进入最小生成树的集合，要么丢弃
>>>* (3).如果当前的边进入最小生成树的集合中不会形成环，就要当前边
>>>* (4).如果当前的边进入最小生成树的集合中会形成环，就不要当前边
>>>* (5).考察完所有边之后，最小生成树的集合也得到了
  
>>[6、最小生成树算法之psim](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "psim") 
>>>* (1).任意从图中选择一个点出发
>>>* (2).解锁当前点的所有出度边
>>>* (3).从所有边中找到权重最小的一条边
>>>* (4).找到权重最小的那条边对应的点，并解锁这个点
>>>* (5).直到所有点都被解锁，再移除不需要的边
>>>* (6).[使用小根堆实现](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "psim2")
  
>>[7、迪瑞特斯拉算法](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "dijkstra")
>>>* (1).默认不允许出现权重为负数的边
>>>* (2).必须给出一个出发点
>>>* (3).如果存在某个点无法到达，则距离为正无穷
>>>* (4).将所有点先放入一个Map中，其中除了出发点的距离为0，其它点的距离都为正无穷
>>>* (5).从出发点开始往外跳，计算出能到达的点的距离，计算完成后，选出距离最小的一个节点为下一跳，并且当前节点不再使用
>>>* (6).直到整个Map遍历结束
>>>* (7).[迪瑞特斯拉算法利用定制化小根堆进行优化](/coding/src/main/java/com/ly/algorithm/coding/GraphCoding.java "dijkstra2")

># 五、二叉树
>>## 1、二叉树的递归套路
>>>* (1).假设以X节点为头，假设可以向X的左树和X的右树得到任何信息
>>>* (2).在上一步的假设下，讨论以X为头节点的树，得到答案的可能性(最重要)  常见的可能性 1.与X有关  2.与X无关
>>>* (3).列出所有可能性后，确定到底需求向左树和右树要什么样的信息
>>>* (4).把左树信息和右树信息求全集，就是任何一颗子树都需要返回的信息S
>>>* (5).递归函数返回S，每一颗子树都这么要求
>>>* (6).写代码，在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息
>>## 2、二叉树常见算法
>>>* [(1)先序遍历](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "pre、pre1")
>>>* [(2)中序遍历](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "mid、mid")
>>>* [(3)后序遍历](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "suf、suf1")
>>>* [(4)宽度遍历](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "width")
>>>* [(5)层级遍历](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "level")
>>>* [(6)获取二叉树的宽度](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "getWidth")
>>>* [(7)先序序列化](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "preSerial")
>>>* [(8)先序反序列化](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "buildTree")
>>>* [(9)层级序列化](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "levelSerial")
>>>* [(10)层级反序列化](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "buildLevelTree")
>>>* [(11)从拥有父节点的树中找到某一个节点的后继节点](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "getSuccessdingTree")
>>>* [(12)从拥有父节点的树中找到某一个节点的前驱节点](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "getPrecursorNode")
>>>* [(13)打印折痕](/coding/src/main/java/com/ly/algorithm/coding/TreeCoding.java "printAllCrease")


># 六、滑动窗口
>>## 1、滑动窗口
>>>* (1).滑动窗口是一种想象出来的数据结构
>>>* (2).滑动窗口有左边界L和右边界R
>>>* (3).在数组或者字符串或者一个序列上，记为S，窗口就是S[L..R]这一部分
>>>* (4).L往右滑意味着一个样本出了窗口，R往右滑意味着一个样本进了窗口
>>>* (5).L和R都只能往右滑
>>## 2、滑动窗口能做什么
>>>* (1).滑动窗口、首尾指针等技巧，说白了就是一种求解问题的流程设计
>>>* (2).想用滑动窗口，要想办法把具体的问题转化为滑动窗口的处理流程
>>>* (3).想用滑动窗口的最值的更新接口，就看看处理流程下，是否需要最值这个信息
>>## 3、常见题型
>>>* [(1)获取最大值](/coding/src/main/java/com/ly/algorithm/coding/SlideWindowCoding.java "slideMaxNum")
>>>* [(2)获取子数组](/coding/src/main/java/com/ly/algorithm/coding/SlideWindowCoding.java "getSubMember2")


># 七、单调栈
>>## 1、单调栈
>>>* (1).一种特别设计的栈结构，为了解决如下的问题：
>>>* 给定一个可能含有重复值的数组arr，i位置的树一定存在如下两个信息
>>>* arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪儿
>>>* arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪儿
>>>* 如果想得到arr钟所有位置的两个信息，怎么能让得到信息的过程尽量快
>>## 2、单调栈怎么使用
>>>* (1).想用单调栈，要想办法把具体的问题转化为单调栈塑哦解决的原问题
>>## 3、常见题型
>>>* [(1)最大值的单调栈实现](/coding/src/main/java/com/ly/algorithm/coding/MonotonousStackCoding.java "slideMaxNum2")
>>>* [(2)获取左边以及右边比当前值小的位置](/coding/src/main/java/com/ly/algorithm/coding/MonotonousStackCoding.java "getNearLess")
>>>* [(3)获取最大和](/coding/src/main/java/com/ly/algorithm/coding/MonotonousStackCoding.java "getMaxSum")


># 八、斐波那契数列及推导
>>## 1、斐波那契数列
>>>* 斐波那契数列（Fibonacci sequence），又称黄金分割数列，因数学家莱昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，故又称为“兔子数列”，指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、……在数学上，斐波那契数列以如下被以递推的方法定义：F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
>>## 2、斐波那契数列推导
>>>* 斐波那契满足严格的递推式(除了初始项，其它每一项不随条件转移)，所以每一步都拥有相同的矩阵
>>>* 类似于 F(n) = F(n-1) + f(n-2) + ....+ f(n-k)的问题
>>>* 都可以转换成常数项为k的矩阵问题
>>>* 即 f(n) * f(n-1)*....*f(n-k) = f(k) * f(k-1) *....*f(1) * K阶矩阵的（n-k）次方
>>>* 例如，斐波那契数列 = F(n) = F(n-1)+F(n-2) 属于一个2阶问题
>>>* 那么转换为 f(n) * f(n-1) = f(2) * f(1) * (一个二阶矩阵) ^ (n-2)
>>>* 通过代入给出的示例值，可以求出二阶矩阵为
>>>* {
>>>*     {1,1},
>>>*     {1,0}
>>>* }
>>>*
>>>* 例如，迈台阶问题，一个可以迈1阶，可以迈2阶，可以迈5阶，那么到n层台阶，一共有多少种迈法
>>>* f(n) = f(n-1)+f(n-2)+f(n-5) 5阶问题
>>>*
>>>* 例如，奶牛问题，10年后奶牛会死亡
>>>* f(n) = f(n-1)+f(n-3)-f(n-10) 10阶问题
>>## 3、常见题型
>>>* [(1)斐波那契数列-暴力递归求解](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "violent")
>>>* [(2)斐波那契数列-线性求解](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "linear")
>>>* [(3)斐波那契-矩阵式求解](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "recent")
>>>* [(4)如何让一个数的次方计算的最快（Math.pow的实现原理）](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "matrixPower")
>>>* [(5)矩阵相乘](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "multiMatrix")
>>>* [(6)斐波那契数列问题扩展-奶牛问题](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "cowRecent")
>>>* [(7)类似斐波那契数列问题-达标字符串](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "常数项不同，其余求解方法都与斐波那契-矩阵式求解相同")
>>>* [(8)类似斐波那契数列问题-贴瓷砖](/coding/src/main/java/com/ly/algorithm/coding/FibonacciProblemCoding.java "常数项不同，其余求解方法都与斐波那契-矩阵式求解相同")


># 九、排序算法
>>## 1、常见算法
>>>* [(1)选择排序](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "selectSort")
>>>* [(2)冒泡排序](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "bubblingSort")
>>>* [(3)插入排序](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "insertSort")
>>>* [(4)归并排序（递归版，非递归版）](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "mergeSort，commonMergeSort")
>>>* [(5)快速排序（快排1.0，快排2.0，快排3.0）](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "quickSort（process1，process2，process3）")
>>>* [(6)堆排序](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "heapSort")
>>>* [(7)计数排序](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "commonMergeSort")
>>>* [(8)基数排序](/coding/src/main/java/com/ly/algorithm/coding/SortCoding.java "quickSort")

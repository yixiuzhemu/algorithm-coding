package com.ly.algorithm.coding;

/**
 * KMP算法（字符串匹配算法）
 * 假设现在有俩个字符串 str、matcher，如果matcher属于str的子串，请输出第一次全匹配开始的位置
 * 例如：str: aaabc matcher: aa
 * 则返回 0
 * 暴力解法：
 * 依次str、matcher字符串，直到全匹配退出。时间复杂度为 O(MN) ,空间复杂度为O(1)
 *
 * KMP算法：
 * KMP算法-加速思路：
 * 创建一个长度为m的数组，记录matcher字符串每一个位置前 最大前缀后缀相等的值（且长度不超过当前位置，位置i的最大前缀后缀相等的值最多只能为i-1）
 * 例如：
 * str ：    a a b a a b
 * matcher:  a a b a a c
 *          -1 0 1 0 0 2
 * 其中，0位置，1位置默认为-1 和 0
 *
 * 计算出next数组后，创建两个指针X和Y，X指向str的开头，Y指向matcher的开头
 * 循环遍历str字符串，如果相同，则X,Y同时加一判断下一个值是否相同
 * 当值不相同时
 * X的位置不变，将Y位置回退到 next[Y],再从该位置进行匹配。
 *
 * 为什么是回退到next[Y]位置
 * 而不是X退回到起始位置+1的位置
 * Y退回到0位置
 * 1.Y位置存在next[Y]个字符，代表前缀后缀相同。回退前，X位置和Y位置之前所有值全匹配，Y位置的某一段前缀肯定等于X位置某一段位置的后缀
 * str ：    a a b a a b
 * matcher:  a a b a a c
 * 此时X = 5  Y = 5   next[5] = 2
 * 所以X保持不变，Y回退到next[5] 实际上等于  X回退到 X-next[5] Y回退到0位置
 *
 * 那么为什么是回退到 X-next[5]   而不是X退回到起始位置+1的位置
 * 2.因为起始位置+1的位置  到  X-next[5] 肯定没有任何一个位置让matcher全匹配
 * 假设 X回退到位置1 并且可以全匹配，
 * 即
 * str ：    a   a b a a   b
 * matcher:      a a b a   a c
 * 那么表示1位置之后的所有字符 都完全匹配matcher，那么此时c位置的前缀后缀相等的值 就变成了4
 * 此时我们知道c位置的最大前缀后缀匹配值为2 ，互相矛盾，所以起始位置+1的位置  到  X-next[5] 肯定没有任何一个位置让matcher全匹配
 *
 *时间复杂度
 *                X(最大值为N)             X-Y(最大值为N)
 *  第一个判断        增大                     不变
 *  第二个判断        增大                     增大
 *  第三个判断        不变                     增大
 *  所以整个循环的循环次数不会超过2N，所以时间复杂度为O(N)
 *
 *  所以KMP算法的时间复杂度为O(N)，空间复杂度是O(M)
 *
 *
 * @author Ly
 * @create 2021/8/19 11:27
 * @desc
 **/
public class KmpCoding {

    /**
     * 当目标字符串在源字符串匹配时，返回第一个索引的位置
     * @param str
     * @param match
     * @return
     */
    public static int search(String str,String match){
        if(match == null || str == null || match.length() > str.length()){
            return  -1;
        }
        int[] nextArray = getNextArray(match);
        int x = 0;
        int y = 0;
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        while(x < strChars.length && y < matchChars.length){
            if(strChars[x] == matchChars[y] ){
                x++;
                y++;
            }else if(y == 0){
                x++;
            }else{
                y = nextArray[y];
            }
        }
        return y == matchChars.length ? x - matchChars.length : -1;
    }

    /**
     * 计算next数组加速方法：
     *    假设我们需要求i位置的前缀后缀最大相等值。那么默认i-1位置的值已经求出并且正确，将i-1的值赋值给index
     *    所以i位置的值只需要判断match[index] 是否等于 match[next[index]] ,如果相等，则 i位置的 值等于 next[index] + 1
     *    如果match[index] 不等于 match[next[index]] 那么将 index 置为 next[index]，再进行值判断
     *    直到值相等，或者index 等于0
     *  例如： a a b a a c
     *  默认0位置和1位置为-1，0 ,此时 next = [-1,0]
     *  直接计算2位置的值,即b
     *  计算match[1] 等于 match[next[1]] ,  此时2位置的值等于1位置的值加1，即 next[2] = 1
     *
     *  再计算位置3的值，即a
     *  计算match[2] 不等于 match[next[2]] ，此时将index值赋值为next[2] 即 1
     *  计算match[2] 不等于 match[next[1]]，此时将index值赋值为next[1] 即0
     *  index已经为0，那么next[3] = 0
     *
     *  再计算位置4的值，即a
     *  计算match[3] 等于 match[next[3]] ，此时4位置的值等于3位置的值加1，即next[4] = 1
     *
     *  最后计算位置5的值，即c
     *  计算match[4] 等于 match[next[4]] ，此时5位置的值等于4位置的值加1，即next[4] = 2
     *
     * 至此，所有位置的值都计算完毕
     * 时间复杂度
     * 该方法的时间复杂度 与 i 和 index有关
     *             i(最大值为Match长度)  i - index(最大值为Match长度)
     * 第一个判断        增大                 不变
     * 第二个判断        不变                 增大
     * 第三个判断        增大                 不变
     * 所以整个循环的循环次数不会超过2M，所以时间复杂度为O(M)
     * @param match
     * @return
     */
    public static int[] getNextArray(String match){
        if(match.length() == 1){
            return new int[]{-1};
        }
        int[] next = new int[match.length()];
        next[0] = -1;
        next[1] = 0;
        int index = 0;
        char[] chars = match.toCharArray();
        int i = 2;
        while(i < chars.length){
            if(chars[i-1] == chars[index]){
                next[i++] = ++index;
            }else if(index > 0){
                index = next[index];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }

}

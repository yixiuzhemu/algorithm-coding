package com.ly.algorithm.coding.leecode;

/**
 * 有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。
 * 现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色，这个正方形将会被覆盖。
 * 目标是在完成染色之后，每个红色R都比每个绿色G距离最左侧近。返回最少需要涂染几个正方形。
 * @author Ly
 * @create 2023/9/11 20:52
 * @desc
 **/
public class ColorLeftRight {

    /**
     * 准备一个分界线，从-1位置开始，找左边有多少个G，右边有多少个R（需要把左边的G变成R，把右边的R变成G）
     *
     * 准备一个变量和一个预处理数组，一个记录从左到右每个位置G的数量（从左到右结算的，所以L不需要数组记录），一个记录从右到左R的数量
     *
     * 例如： RGRRGGGRRRGGG
     *
     * L =       0111234444567
     *
     * R =       6554333321000
     * @param s
     * @return
     */
    public static int minPaint(String s){
        if(s == null || s.length() < 2){
            return 0;
        }
        char[] chs = s.toCharArray();
        int right = 0;
        for(int i = 0;i< chs.length;i++){
            right += chs[i] == 'R' ? 1 : 0;
        }
        //分界线在-1时的结果,将右边的R都改成G
        int res = right;
        int left = 0;
        for(int i = 0;i < chs.length;i++){
            left += chs[i] == 'G' ? 1 : 0;
            //遇到一个R，就将right--，就知道右边还剩多少个R了
            right -= chs[i] == 'R' ? 1 : 0;
            res = Math.min(res,left+right);
        }
        return res;
    }

}

package com.ly.algorithm.coding;

/**
 * 类似斐波那契数列的递归
 * 斐波那契满足严格的递推式(除了初始项，其它每一项不随条件转移)，所以每一步都拥有相同的矩阵
 * 类似于 F(n) = F(n-1) + f(n-2) + ....+ f(n-k)的问题
 * 都可以转换成常数项为k的矩阵问题
 * 即 f(n) * f(n-1)*....*f(n-k) = f(k) * f(k-1) *....*f(1) * K阶矩阵的（n-k）次方
 * 例如，斐波那契数列 = F(n) = F(n-1)+F(n-2) 属于一个2阶问题
 * 那么转换为 f(n) * f(n-1) = f(2) * f(1) * (一个二阶矩阵) ^ (n-2)
 * 通过代入给出的示例值，可以求出二阶矩阵为
 * {
 *     {1,1},
 *     {1,0}
 * }
 *
 * 例如，迈台阶问题，一个可以迈1阶，可以迈2阶，可以迈5阶，那么到n层台阶，一共有多少种迈法
 * f(n) = f(n-1)+f(n-2)+f(n-5) 5阶问题
 *
 * 例如，奶牛问题，10年后奶牛会死亡
 * f(n) = f(n-1)+f(n-3)-f(n-10) 10阶问题
 *
 * @author Ly
 * @create 2021/8/16 15:27
 * @desc
 **/
public class FibonacciProblemCoding {

    /**
     * 斐波那契数列-暴力递归求解
     * @param n
     * @return
     */
     public static int violent(int n){
         if(n < 1){
             return 0;
         }
         if( n == 1 || n == 2){
             return 1;
         }
         return violent(n-1)+violent(n-2);
     }

    /**
     * 斐波那契数列-线性求解
     * @param n
     * @return
     */
     public static int linear(int n){
         if(n < 1){
             return 0;
         }
         if(n == 1 || n == 2){
             return 1;
         }
         int res = 1;
         int pre = 1;
         int tmp = 0;
         for(int i = 3;i <= n;i++){
             tmp = res;
             res = res + pre;
             pre = tmp;
         }
         return res;

     }

    /**
     * 斐波那契-矩阵式求解
     * 斐波那契数列满足严格的逻辑递推式，
     * 1,1,2,3,5,8......
     * 即，F(n)*F(n-1) = F(n-1) * F(n-2) * {{a,b},{c,d}}
     *
     * 带入F(3)*F(2) = F(2) * F(1) *{{a,b},{c,d}} 可得
     * a+c = 2 , b+d = 1
     * 带入F(4)*F(3) = F(3) * F(2) *{{a,b},{c,d}} 可得
     * 2a+c = 3  2b+d = 2
     * 可推算出矩阵为{{1,1},{1,0}}
     * 所以计算斐波那契数列N的结果值即计算
     * F(2)*F(1) * {{1,1},{1,0}}^(N-2) = {{1,1},{1,0}}^(N-2) = res[2][2]
     * 则F(N) = res[0][0]+res[0][1]
     *
     * @param n
     * @return
     */
     public static int recent(int n){
         if(n < 1){
             return 0;
         }
         if(n == 1 || n == 2){
             return 1;
         }
         int[][] base = {{1,1},{1,0}};
         int[][] res = matrixPower(base,n-2);
         return res[0][0] + res[1][0];
     }

    /**
     * 如何让一个数的次方计算的最快（Math.pow的实现原理）
     * 将次方数拆分为2进制
     * 例如10^75 = 10^64 * 10 ^ 8 * 10 ^ 2 * 10
     * 分别计算10 ~ 10^64的值，
     * 根据75每次右移，确定该位是否为1，来决定是否乘上该值。
     * 最终10^75次方需要进行的计算次数为 log2^75
     *
     * 同理{{1,1},{1,0}}^(N-2) 也是这样进行求解
     * @param m
     * @param p
     * @return
     */
     private static int[][] matrixPower(int[][] m,int p){
         int[][] res = new int[m.length][m[0].length];
         //把结果设置为单位矩阵（对角线全为1 的矩阵）
         for(int i = 0;i< res.length;i++){
             res[i][i] = 1;
         }
         int[][] tmp = m;
         for(;p != 0;p >>= 1){
             if((p&1) != 0){
                 res = multiMatrix(res,tmp);
             }
             tmp = multiMatrix(tmp,tmp);
         }
         return res;
     }

    /**
     * 矩阵相乘：arr1[a][b] * arr2[c][d]
     * 最终结果res 行数一定等于 a的行数，列数一定等于b的列数
     * res = arr1[a][d]
     * 求解res上每一个点的值
     * res[i][j] = (arr1[i][0] * arr2[0][j]) + (arr1[i][1] * arr2[1][j]) + .......
     * 即 矩阵1 i行上的每一个数乘以 矩阵2 j列每一个对应位置上的数 的总和
     * @param m1
     * @param m2
     * @return
     */
     private static int[][] multiMatrix(int[][] m1,int[][] m2){
         int[][] res =  new int[m1.length][m2[0].length];
         for(int i  = 0;i< m1.length;i++){
             for(int j = 0;j < m2[0].length;j++){
                 for(int k = 0;k<m2.length;k++){
                     res[i][j] += m1[i][k] * m2[k][j];
                 }
             }
         }
         return res;
     }

    /**
     * 斐波那契数列问题扩展-奶牛问题
     * 农场初始有1头牛，每一年可以生一头小牛，小牛需要3年后才能生牛，
     * 那么第n年，农场一共拥有多少头牛
     * 类似斐波那契数列的问题，通过规律可以知道
     * 1,2,3,4,6,9......
     * 即f(n) = f(n-1)+f(n-3) = 1 * f(n-1) + 0*f(n-2) + 1 * f(n-3)
     * 属于斐波那契数列3阶问题，那么同样可以转换成矩阵求解
     * 即 f(4)*f(3)*(f2) = f(3) * f(2) * f(1) * {{a,b,c},{d,e,f},{g,h,i}}
     * 通过代入求解，可得矩阵为
     * {
     *     {1,1,0},
     *     {0,0,1},
     *     {1,0,0}
     * }
     * 那么f(n)*f(n-1)*f(n-2) =  f(3) * f(2) * f(1) * {{a,b,c},{d,e,f},{g,h,i}} ^ (n-3)
     * @param n
     * @return
     */
     public static int cowRecent(int n){
         if(n < 1){
             return 0;
         }
         if(n == 1 || n == 2 || n == 3){
             return n;
         }
         int[][]  base = {{1,1,0},{0,0,1},{1,0,0}};
         int[][] res = matrixPower(base,n-3);
         return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
     }

    /**
     * 类似斐波那契数列问题-达标字符串
     * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串
     * 如果某个字符串，任何0字符串的左边都有1紧挨着，认为这个字符串达标，返回有多少达标的字符串
     * N = 1时，达标的字符串只有 "1"  结果为1
     * N = 2时，达标的字符串有"11","10" 结果为2
     * N = 3时，达标的字符串有"111","110","101" 结果为3
     * N = 4时，达标的字符串有"1111","1110","1101","1011","1010" 结果为5
     * 由上结果可知，如果字符串达标，那么第一位一定需要为1，
     * 那么将问题转换为，
     * 假设当前字符长度为N
     * 那么如果字符串要合规，最左边一定为1
     * 求f(N) 即等于求f(N-1)有多少种方案
     * 当f(N)为1 时，f(N-1)可以为1 也可以为0
     * 当f(N-1)为0时，N-2位置只能填1 (轮到求f(N-2)的值)
     * 当f(N-1)为1时，N-2位置可以随意
     * 那么f(N) = f(N-1) + f(N-2)
     * 属于一个2阶斐波那契问题
     *
     */

    /**
     * 类似斐波那契数列问题-贴瓷砖
     * 给定一个2*N的区域，现在有若干1*2大小的瓷砖
     * 请问全部铺满区域有多少种方法（只要有一步不一样，都属于不同方案）
     * 当N = 1时，贴法1种
     * 当N = 2时，贴法2种
     * 当N = 3时，贴法3种
     *
     * 假设第N块区域竖着摆，剩下的区域为N-1
     * 假设第N块区域横着摆，剩下的区域为N-2(空余出来的位置 只能横着摆)
     * 所以 F(N) = F(N-1)+F(N-2)
     * 属于一个2阶斐波那契问题
     */




}

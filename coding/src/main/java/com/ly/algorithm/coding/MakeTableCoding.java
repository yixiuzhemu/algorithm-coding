package com.ly.algorithm.coding;
/**
 * 打表法：
 *
 * 1、问题如果返回值不多，可以用hardcode的方式列出，作为程序的一部分。
 *
 * 2、一个大的问题解决时，底层频繁使用规模不大的小问题的解，如果小问题的返回值满足条件1，可以把小问题做成一张表，作为程序的一部分。
 *
 * 3、打表找规律
 *
 * 例如：给定一个正整数的数组，求数组中总的质数有多少个，已知数组中的数都是小于1000的，那么此时可以将1000以内所有的数的质数都先求出来，然后记录成一个hardcode，当代码处理每一个数时，直接从里面取出就可以了。
 * @author Ly
 * @create 2021/8/19 11:27
 * @desc
 **/
public class MakeTableCoding {

    /**
     * 由于需要最少的袋子，那么一定会优先选择装8个苹果的袋子，那么可以用N/8,再拿剩下的苹果去用6号袋子试，如果发现无法被6号袋子均分，那么就选择减一个8号袋子。如果N为奇数，那么是肯定无法被均分的。
     * @param N
     * @return
     */
    public static int minBagAwsome2(int N){
        if(N % 2 == 1){
            return -1;
        }
        int eight = N/8;
        if(eight * 8 == N){
            return eight;
        }
        int other = N - (eight * 8);
        while(eight >= 0){
            int six = other/6;
            if(six * 6 == other){
                return eight+six;
            }
            other = N - (--eight * 8);
        }
        return -1;
    }


    /**
     * 1、某个问题，输入参数很简单，并且只有一个实际参数
     *
     * 2、要求返回值类型也简单，并且只有一个
     *
     * 3、用暴力方法，把输入参数对应的返回值，打印出来，然后再进行优化code
     *
     * 观察规律，当苹果数量到达18之后，每往后推8位，奇数的袋子数一定是-1，偶数的袋子数一定是当前8位数的第一位数/8个袋子，所以可以对代码进行优化
     * @param N
     * @return
     */
    public static int minBagAwsome(int N){
        if(N % 2 == 1){
            return -1;
        }
        if(N < 18){
            return N == 0 ? 0 : (N == 6 || N == 8) ? 1 : (N == 12 || N == 14 || N == 16) ? 2 : -1;
        }
        return (N - 18)/8+3;
    }


    /**
     *给定一个正整数N，表示有N份青草统一放在仓库里，有一只牛和一只羊，牛先吃，羊后吃，他两轮流吃草，不管是牛还是羊，每一轮能吃的草亮必须是：1，4，16，64（4的某次方），谁最先把草吃完，谁获胜，假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定，根据唯一参数N，返回谁会赢
     * @param N
     * @param type true代表牛，false代表羊
     * @return
     */
    public static boolean fastEat(int N,boolean type){
        int count = N;
        //如果当前N为1或则4的某次方，那么此时type赢
        if(N == 1 || N == 4){
            return type;
        }
        while(count > 4){
            if(count % 4 == 0){
                count /= 4;
                if(count == 4){
                    return type;
                }
            }else{
                break;
            }
        }
        //此时不是4的次方
        //对于牛来说，当前吃的数量为m，吃掉m后，剩余的草的数量不能为4的某次方
        //如果牛此时吃1
        int pow = 0;
        while(Math.pow(4,pow) < N){
            //如果此时吃了4的某次方后，结果的返回等于当前类型，那么返回当前type
            if(fastEat((int)(N - Math.pow(4,pow++)),type?false:true) == type){
                return type;
            }
        }
        //无论type怎么吃，都不能返回他自己，那么另外一方赢
        return !type;
    }

    /**
     * 查看暴力解的输出结果后，会发现，发现当i>4 之后，规律永远是羊、牛、羊、牛、牛，那么代码可以优化成以下这样
     * @param N
     * @return
     */
    public static boolean fastEat(int N){
        if(N == 1 || N == 3 || N == 4){
            return true;
        }
        if(N == 2){
            return false;
        }
        //每5次为一轮
        int count = N % 5;
        if(count == 0 || count == 2){
            return false;
        }
        return true;
    }

    /**
     * 定义一种数：可以表示成若干（数量>1）连续正数和的数，比如：
     *
     * 5=2+3，5就是这样的数
     *
     * 12=3+4+5, 12就是这样的数
     *
     * 1不是这样的数，因为要求数量大于1个、连续的正数和
     *
     * 2=1+1，2也不是，因为等号右边不是连续的正数，给定一个参数N，返回是不是可以表示成若干连续正数和的数
     * @param N
     * @return
     */
    public static boolean isContinuationSum(int N){
        if(N < 3){
            return false;
        }
        int index = N % 2 == 0 ? N/2 : (N/2)+1;
        return process(index,N);
    }

    public static boolean process(int index,int N){
        int count = index;
        int temp = index;
        while(count < N){
            count += --index;
            if(count == N){
                return true;
            }
            if(count > N){
                return process(temp-1,N);
            }
            if(index == 0){
                return false;
            }
        }
        return false;
    }

    public static boolean isContinuationSum2(int N){
        //N 如果小于3
        if(N < 3 || (((~N)+1) & N ) == N){
            return false;
        }
        return true;
    }





}

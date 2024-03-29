package com.ly.algorithm.coding.leecode;

/**
 *
 * 给定一个正数N，表示你在纸上写下1~N所有的数字，返回在书写过程中，一共写下了多少个1
 *
 * @author Ly
 * @create 2023/11/3 22:03
 * @desc
 **/
public class OneNumber {

    /**
     * 针对1~N上的每个一数进行 num - (num/10)*10 取出个位、十位、百位，然后再获取每一位的1
     * @param N
     * @return
     */
    public static int oneNumber(int N){
        int count = 1;
        //求个位上的数
        for(int i = 2;i<=N;i++){
            int num = i;
            while(num != 0 && num / 10 >= 0){
                if(num - (num/10)*10 == 1){
                    count++;
                }
                num = num/10;
            }
        }
        return count;
    }

    /**
     * 动态规划：定义一个递归函数f(i),f(i)的含义是，求出1~i包含的所有的1
     *
     * 	对于i，将其进行数位分解，例如i=13625，首先使用非递归计算出3626~13625有多少个1，再调用f(3625)计算有多少个1.
     *
     * 	当i=3625时，使用非递归计算出626~3625有多少个1，再调用f(625)计算有多少个1
     *
     * 	。。。。
     *
     * 	当i<10时，f(i)返回1
     *
     * 	非递归求有多少个1
     *
     * 	对于i，当最高位=1时，对于最高位来说，一共有i%10^(k-1) + 1 个1
     *
     * 	对于其余位，对于任何一位，如果要让当前位为1，那么最高位只能1或者是0，而其他位可以随意变
     *
     * 	例如：13625
     *
     * 	假设要让千位为1，那么万位必须为1，才能落在3626~13625范围内，而对于百位、十位、个位可以是0~9的任意值，所以千位上1的个数为10^3
     *
     * 	假设要让百位位1，那么万位同样必须为1，才能落在3626~13625范围内，而对于千位、十位、个位可以是0~9的任意值，所以百位上1的个数为10^3 。。。
     *
     * 	所以当最高位为1时，1的总数量为10^(k-2) * (k-1)+i%10^(k-1) + 1
     *
     *
     *
     * 如果最高位=a时，且a大于1，对于最高位来说，此时一共有10(k^1)个1，例如626~3625，只有1000~1999,千位才为1
     *
     * 对于其余位，对于任何一位，如果要让当前位位1，那么最高位可以时0~a上的其中一个数，而其他位可以随意变
     *
     * 	例如：3625
     *
     * 	假设要让百位为1，那么千位可以是1或者2或者3，才能落在626~3625范围上，而对于十位、个位可以是0~9的任意值，所以百位上1的个数为10^2 * a
     *
     * 	假设要让十位为1，那么千位可以是0或者1或者2（这里不可能为3，因为如果千位为3，十位要是1，那么百位必须大于6，此时超出了范围），而对于百位、个位可以是0~9的任意值，所以十位上1的个数为10^2 * a
     *
     * 	所以对于最高位=a时，1的总数量为10^(k-2) * (k-1) * a + 10^(k-1)
     *
     * 	最后将递归结果返回累加， 就是最后1的数量
     *
     * 	时间复杂度：递归次数logN （以10为底），递归方法中需要求位数，logN（以10位底）所以总的时间复杂度为logN^2(以10为底)
     * @param N
     * @return
     */
    public static int oneNumber_v2(int N){
        return process(N);
    }

    public static int process(int i){
        //如果i<10
        if(i<10){
            return 1;
        }
        //将i进行数位分解
        int num = i;
        //计算位数
        int k=1;
        while(num >= 10){
            num /=  10;
            k++;
        }
        //数位拆分
        int less = i - num * (int)Math.pow(10,k-1);
        int res = process(less);
        //使用非递归计算less+1 ~ i 的1
        if(num == 1){
            //如果高位=1
            //最高位1的个数为 i%10^(k-1) + 1
            res += (i%(int)(Math.pow(10,k-1)))+1;
            //其余位1的个数为 10^(k-2) * (k-1)
            res += ((int)Math.pow(10,k-2)) * (k-1);
        }else{
            //如果最高位不为1
            //最高位1的格式为10^(k-1)
            res += (int)(Math.pow(10,k-1));
            //其余位1的个数为10^(k-2) * (k-1) * a
            res += (int)(Math.pow(10,k-2)) * (k-1) * num;
        }
        return res;
    }

}

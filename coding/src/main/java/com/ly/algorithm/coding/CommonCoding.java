package com.ly.algorithm.coding;

/**
 * @author Ly
 * @create 2021/8/17 9:58
 * @desc
 **/
public class CommonCoding {

    /**
     * 蓄水池算法-接球入袋（适用于抽奖场景）
     * 现在有一个机器，在源源不断的吐出每次序号+1的球
     * 有一个容量为10的袋子，需要决定当前球，是入袋子还是扔掉，如果扔掉了，那么将永远无法找回
     * 当机器吐出100个球时，需要保证每个球入袋的几率为10/100
     * 当机器吐出3762个球时，需要保证每个球入袋的几率为10/3762
     * 即，新吐一个球的时候，要保证前面所有球入袋的几率都是均等的
     * 1.1-10号球，每一个球都入袋子
     * 2.往后出现的第K号球（K>10），通过一个随机函数计算其是否落在1-10
     * 3.如果计算出的随机数在1-10，那么从原数组中，随机扔掉一个球
     * 4.如果计算出的随机数不在1-10，那么直接扔掉
     * 例如：当17号球入袋，那么3号球还在袋中的概率是多少
     * 当11号球来之前，3号球在袋中的概率为1
     * 11号球入袋的概率为 10/11
     * 而三号球被淘汰的概率为(1/10)*(10/11)
     * 那么三号球不被淘汰的概率就为 1 * (1-(1/10)*(10/11))) = 1 * (10/11)
     * 同理，12号球来时，3号球还在袋中的概率为1 * (10/11) * (11/12)
     * 所以当17号球来时，3号球还在袋中的概率为10/17
     *
     */

    /**
     * 概率转化
     * 给定一个函数f,等概率返回m~n 之间的数，现在根据该函数，实现等概率返回k~l的数
     * 1.根据原函数，改成成返回0，1二进制源的函数g()
     * 2.将k-l的范围 改成0~l-k 确定一共需要多少个二进制位
     * 3.在每个二进制位上使用g函数随机生成0或1
     * 4.淘汰超过l-k的值，知道生成的值在0~l-k之间，再加上k，即等概率返回每一个值
     *
     *
     * 给定一个函数f，不等概率返回0和1，返回0的概率为P，返回1的概率为1-P，实现等概率返回0，1
     * 1.根据原函数，同时调用两次，生成2个值，那么结果只会是 （0，0），（0，1），（1，0），（1，1）其中之一
     * 2.淘汰（0，0），（1，1）的结果，只保留（0，1），（1，0）
     * 3.视（0，1）为0，（1，0）为1 ，那么他们的生成概率都是P*（1-P），即等概率
     */

}

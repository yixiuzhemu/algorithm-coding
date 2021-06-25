package com.ly.algorithm.coding;

import com.google.common.collect.Lists;
import com.ly.algorithm.EmployeeInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @author Ly
 * @create 2021/6/24 16:49
 * @desc
 **/
public class EmployeeCoding {
    /**
     * 派对最大快乐值
     *
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2.派对的整体快乐值是所有到场员工快乐值的累加
     * 3.你点目标是让排队的整体快乐值尽量大
     * 给定一棵多叉树的头节点boss，请返回排队的最大快乐值
     *
     *
     * 分类
     * 1.X来，获取左子树和右子树的最大快乐值
     *  可以知道X的快乐值，再获取X的子节点不来的最大快乐值
     * 2.X不来，获取X的所有子节点的左子树和右子树的最大快乐值
     *  分别讨论X的子节点来不来的情况，并返回其中的最大值
     *
     * 需要的信息
     * 头节点来时的快乐值
     * 头节点不来时的快乐值
     */
    static class HappyInfo{
        /**
         * 头节点来时的快乐值
         */
        public int come;

        /**
         * 头节点不来时的快乐值
         */
        public int noCome;

        public HappyInfo(int come, int noCome) {
            this.come = come;
            this.noCome = noCome;
        }
    }

    private static HappyInfo getHappyInfo(EmployeeInfo employeeInfo){
        //如果是基层员工，则直接返回基层员工去时的快乐值
        if(CollectionUtils.isEmpty(employeeInfo.subordinates)){
            return new HappyInfo(employeeInfo.happy,0);
        }
        int come = employeeInfo.happy;
        int noCome = 0;
        List<EmployeeInfo> subordinates = employeeInfo.subordinates;
        for (EmployeeInfo subordinate : subordinates) {
            HappyInfo info = getHappyInfo(subordinate);
            //当前员工如果去，则直接加上下级员工不去的快乐值
            come += info.noCome;
            //当前员工不去，加上下级去或者不去的最大快乐值
            noCome += Math.max(info.come,info.noCome);
        }
        return new HappyInfo(come,noCome);
    }

    public static Integer getMaxHappyInfo(EmployeeInfo employeeInfo){
        HappyInfo comeInfo = getHappyInfo(employeeInfo);
        return Math.max(comeInfo.come,comeInfo.noCome);
    }
}

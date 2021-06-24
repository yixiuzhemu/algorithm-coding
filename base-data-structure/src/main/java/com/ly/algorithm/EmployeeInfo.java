package com.ly.algorithm;

import java.util.List;

/**
 *      * 公司的每个员工都符合Employee类的描述，整个人员结构可以看作是一棵标准的、没有环的多叉树，
 *      * 树的头节点是公司唯一的老板，除老板之外的每个员工都有唯一的直接上级。叶节点是没有任何下属的基础员工，下属列表为空
 *      * 除基层员工以外，每个员工都有一个或多个直接下级
 * @author Ly
 * @create 2021/6/24 16:28
 * @desc
 **/
public class EmployeeInfo {
    /**
     * 这名员工可以带来的快乐值
     */
    public int happy;

    /**
     * 这名员工的直接下级
     */
    public List<EmployeeInfo> subordinates;

    public EmployeeInfo(int happy) {
        this.happy = happy;
    }
}

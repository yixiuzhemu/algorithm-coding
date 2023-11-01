package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 括号有效配对是指
 *
 *  任何一个左括号都能找到和其正确配对的右括号
 *
 *  任何一个右括号都能找到和其正确配对的左括号
 *
 *  效的：（（））、（）（）、（（）（））等等
 *
 *  问题一：怎么判断一个括号字符串有效
 *
 *  问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 * @author Ly
 * @create 2023/9/10 9:01
 * @desc
 **/
public class NeedParentheses {

    /**
     * 怎么判断一个括号字符串有效
     * 准备一个遍历count，遇到（ 就++，遇到）就--
     *
     * 有效的情况：字符串遍历完成后，count=0
     *
     * 无效的情况：字符串遍历完成后，count不等于0，或者count在过程中出现负数，
     * @param str
     * @return
     */
    public static boolean valid(String str){
        char[] charArray = str.toCharArray();
        int count = 0;
        for (char c : charArray) {
            if(c == '('){
                count++;
            }else if(c == ')'){
                count--;
            }
            if(count < 0){
                return false;
            }
        }
        if(count == 0){
            return true;
        }
        return false;
    }

    /**
     * 在问题一的基础上，准备一个变量need
     *
     * 当遍历完成后，如果count大于0，那么need+=count
     *
     * 如果过程中，count出现负数，那么count=0,need++：
     * @param str
     * @return
     */
    public static int needParentheses(String str){
        char[] charArray = str.toCharArray();
        int count = 0;
        int need = 0;
        for (char c : charArray) {
            if(c == '('){
                count++;
            }else if(c == ')'){
                count--;
            }
            if(count < 0){
                count = 0;
                need++;
            }
        }
        if(count > 0){
            need += count;
        }
        return need;
    }

    /**
     * 括号有效配对是指：
     *
     *  任何一个左括号都能找到和其正确配对的右括号
     *
     *  任何一个右括号都能找到和其正确配对的左括号
     *
     * 返回一个括号字符串中，最长的括号有效子串的长度
     * @param str
     * @return
     */
    public static int maxLength(String str){
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int j = 0;
        int ans = 0;
        for (int i = 1;i<chars.length;i++) {
            if(chars[i] == ')'){
                j = i - dp[i-1]-1;
                if(j > 0 && chars[j] == '('){
                    dp[i] = dp[i-1] + 2 + (j > 0 ? dp[j-1]:0);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        //遍历do，返回最大值
        return ans;
    }

}

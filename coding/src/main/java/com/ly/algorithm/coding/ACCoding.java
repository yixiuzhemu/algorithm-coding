package com.ly.algorithm.coding;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *AC自动机
 *
 * 对于其他任意节点X
 *
 * 如果通过路径P到达孩子节点，那么对于孩子节点的右指针指向节点X的右指针路径上存在P的第一个节点。
 *
 * 例如上图中节点X通过路径c来到下一个节点XC，而节点X的下一个右指针上的节点XT有直接相连的路径c，所以就将XC的tail指针指向XT。如果一直找到右指针的为空都没有找到这样的路径，那么将XC节点的tail指针指向头节点。
 *
 * 通过这种方式，可以快速的找到匹配的字符串。
 * @author Ly
 * @create 2023/9/8 21:50
 * @desc
 **/
public class ACCoding {

    /**
     * 前缀树节点
     */
    public static class Node{
        //如果一个node，end为空，不是结尾
        //如果end不为空，表示这个节点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        //只有在上面的end变量不为空的时候，endUse才有意义
        //表示，之歌字符串之前有没有加入过答案
        public boolean endUse;

        /**
         * 最右指针
         */
        public Node tail;

        public Node[] nexts;

        public Node(){
            endUse =false;
            end = null;
            tail = null;
            //26个字母
            nexts = new Node[26];
        }
    }

    public static class ACAutomation{
        private Node root;

        public ACAutomation(){
            root = new Node();
        }

        public void insert(String s){
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for(int i = 0;i<str.length;i++){
                index = str[i] - 'a';
                if(cur.nexts[index] == null){
                    Node next = new Node();
                    cur.nexts[index] = next;
                }
                cur = cur.nexts[index];
            }
            //将当前字符串保存当结尾，取的时候就不用再遍历了
            cur.end = s;
        }

        public void build(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node ctail = null;
            while(!queue.isEmpty()){
                //当前节点弹出
                //当前节点的所有后代加入到队列中
                //当前节点给他的子节点取设置tail指针
                cur = queue.poll();
                for(int i = 0;i<26;i++){
                    //找到有效的路
                    if(cur.nexts[i] != null){
                        //如果当前节点的孩子找不到最右指针，那么最终就会指向root节点，那么可以先指向root节点，再反过来找目标节点
                        cur.nexts[i].tail = root;
                        //循着当前节点的最右指针去找子节点的最右指针应该指向的节点
                        ctail = cur.tail;
                        while(ctail != null){
                            //如果来到的当前最右指针上，也有i方向的路，那么将子节点的最右指针指向当前节点
                            if(ctail.nexts[i] != null){
                                cur.nexts[i].tail = ctail.nexts[i];
                                break;
                            }
                            ctail = ctail.tail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containsWord(String content){
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = Lists.newArrayList();
            for(int i = 0;i<str.length;i++){
                index = str[i] - 'a';
                while(cur.nexts[index] == null && cur != root){
                    //如果在当前链路上没有找到路，那么顺着最右指针去找
                    //可以跳到一个有路径的节点，或者当前跳到了根节点
                    cur = cur.tail;
                }
                //现在来到的路径，是可以继续匹配的
                //现在来到的节点，是前缀树的根节点
                //如果当前已经没有路了，那么最终会回到根节点，回到头节点后重新开始匹配
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                //能找到路径时，只通过右指针去收集答案，而不跟换路径
                follow = cur;
                while(follow != null){
                    //如果当前位置已经加入过了，那么直接跳过
                    if(follow.endUse){
                        break;
                    }
                    if(follow.end != null){
                        //代表来到了一个结束节点
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    //顺着右指针往下找
                    follow = follow.tail;
                }
            }
            return ans;
        }
    }
}

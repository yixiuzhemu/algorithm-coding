package com.ly.algorithm.coding.leecode;

import com.google.common.collect.Maps;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * 给你一个字符串类型的数组arr，例如：String[] arr = {"b\st","d\","a\d\e","a\b\c"};
 *
 * 	把这些路径中蕴含的目录结构打印出来，子目录在父目录下面，并比父目录右缩进两个，
 *
 * 	a
 *
 * 		b
 *
 * 			c
 *
 * 		d
 *
 * 			e
 *
 * 	b
 *
 * 		cst
 *
 * 	d
 * @author Ly
 * @create 2023/10/6 11:20
 * @desc
 **/
public class GetFolderTree {

    public static class Node{
        public String path;

        public TreeMap<String,Node> nextMap;

        public Node(String p){
            this.path = p;
            nextMap = Maps.newTreeMap();
        }
    }

    /**
     * 同一级的需要按字母顺序排列
     *
     * 前缀树，按照斜杠分割，将字符串分割成字符数组，再添加到前缀树中，再进行深度优先遍历
     * @param folderPaths
     */
    public static void print(List<String> folderPaths){
        Node head = generateFolderTree(folderPaths);
        print(head,-1);
    }

    public static void print(Node node,int level){
        if(level > -1){
            for(int i = 0;i<level;i++){
                System.out.print(" ");
            }
            System.out.println(node.path);
        }
        TreeMap<String, Node> nextMap = node.nextMap;
        Iterator<Map.Entry<String, Node>> iterator = nextMap.entrySet().iterator();
        while(iterator.hasNext()){
            print(iterator.next().getValue(),level+1);
        }
    }

    public static Node generateFolderTree(List<String> folderPaths){
        Node head = new Node(null);
        for (String folderPath : folderPaths) {
            String[] arr = folderPath.split("\\\\");
            if(!head.nextMap.containsKey(arr[0])){
                head.nextMap.put(arr[0],new Node(arr[0]));
            }
            Node curHead = head.nextMap.get(arr[0]);
            //后续的节点以当前节点为头
            for(int i = 1;i<arr.length;i++){
                if(!curHead.nextMap.containsKey(arr[i])){
                    curHead.nextMap.put(arr[i],new Node(arr[i]));
                }
                curHead = curHead.nextMap.get(arr[i]);
            }
        }
        return head;
    }

}

package com.ly.algorithm;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 前缀树
 * @author Ly
 * @create 2021/6/21 14:27
 * @desc
 **/
public class PreNode {

    private int p;

    private int e;

    private char value;

    private Map<Character,PreNode> maps;

    public PreNode() {
        maps = Maps.newHashMap();
    }

    public void add(String s){
        char[] chars = s.toCharArray();
        PreNode first = this;
        for(int i = 0;i<chars.length;i++){
            first.addP();
            Map<Character, PreNode> maps = first.getMaps();
            PreNode node = maps.get(chars[i]);
            if(node == null){
                node = new PreNode();
                maps.put(chars[i],node);
            }
            node.setValue(chars[i]);
            first = node;
        }
        first.addP();
        first.addE();
    }

    public Boolean search(String s){
        char[] chars = s.toCharArray();
        PreNode first = this;
        for (char aChar : chars) {
            Map<Character, PreNode> maps = first.getMaps();
            PreNode node = maps.get(aChar);
            if(node == null || node.getP() <= 0){
                return false;
            }
            first = node;
        }
        if(first.getE() <= 0){
            return false;
        }
        return true;
    }

    public int countString(String s){
        char[] chars = s.toCharArray();
        PreNode first = this;
        for (char aChar : chars) {
            Map<Character, PreNode> maps = first.getMaps();
            PreNode node = maps.get(aChar);
            if(node == null && node.getP()<0){
                return 0;
            }
            first = node;
        }
        return first.getP();
    }

    public void delete(String s){
        if(this.search(s)){
            char[] chars = s.toCharArray();
            PreNode first = this;
            for (char aChar : chars) {
                Map<Character, PreNode> maps = first.getMaps();
                PreNode node = maps.get(aChar);
                if(node == null){
                    return;
                }
                node.subP();
                if(node.getP() == 0){
                    maps.remove(aChar);
                    break;
                }
                first = node;
            }
            first.subE();
        }
    }

    public int getP() {
        return p;
    }

    public int getE() {
        return e;
    }

    public void addP() {
        this.p++;
    }

    public void subP() {
        this.p--;
    }

    public void  addE() {
        this.e++;
    }

    public void  subE() {
        this.e--;
    }

    public Map<Character, PreNode> getMaps() {
        return maps;
    }

    public void setValue(char value) {
        this.value = value;
    }
}

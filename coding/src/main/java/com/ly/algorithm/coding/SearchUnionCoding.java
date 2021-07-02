package com.ly.algorithm.coding;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 * 并查集
 * 1.有若干个样本a、b、c、d。。。类型假设是V
 * 2.在并查集中一开始认为每个样本都在单独的集合里
 * 3.用户可以在任何时候调用如下两个方法：
 *    boolean isSameSet(V x,V y)：查询样本x 和样本y是否属于一个集合
 *    void union(V x,V y): 把x和y各自所在的集合的所有样本合并成一个集合
 * 4.isSameSet和union方法的代价越低越好
 *
 *
 * 应用： 解决两大块区域合并的问题 、常用在图等领域中
 * @author Ly
 * @create 2021/7/1 15:17
 * @desc
 **/
public class SearchUnionCoding<V> {

    public static class Node<V>{

        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public Map<Node<V>,Node<V>> parentMap = Maps.newHashMap();

    public Map<Node<V>,Integer> sizeMap = Maps.newHashMap();

    public Map<V,Node<V>> valueMap = Maps.newHashMap();

    public SearchUnionCoding(List<V> values) {
        for (V value : values) {
            Node<V> vNode = new Node<>(value);
            valueMap.put(value, vNode);
            parentMap.put(vNode,vNode);
            sizeMap.put(vNode,1);
        }
    }

    public boolean isSampleSet(V v1,V v2){
        if(!valueMap.containsKey(v1) ||!valueMap.containsKey(v2)){
            return false;
        }
       return findFather(valueMap.get(v1)) == findFather(valueMap.get(v2));
    }
    public Node<V> findFather(Node<V> v){
        Stack<Node<V>> path = new Stack<>();
        while (v != null && v != parentMap.get(v)){
            path.push(v);
            v = parentMap.get(v);
        }
        while(!path.isEmpty()){
            Node<V> pop = path.pop();
            Integer size = sizeMap.get(v);
            //并查集优化，将并查集链扁平化，最终查询父节点的时间复杂度为0（1）
            parentMap.put(pop,v);
            size++;
            sizeMap.put(v,size);
        }
        return v;
    }

    public void union(V v1,V v2){
        if(!valueMap.containsKey(v1) || !valueMap.containsKey(v2)){
            return;
        }
        Node<V> v1Father = findFather( valueMap.get(v1));
        Node<V> v2Father = findFather(valueMap.get(v2));
        if(v1Father != v2Father){
            Integer v1Size = sizeMap.get(v1Father);
            Integer v2Size = sizeMap.get(v2Father);
            Node<V> big = v1Size >= v2Size ?v1Father : v2Father;
            Node<V> small = big == v1Father ? v2Father : v1Father;
            parentMap.put(small,big);
            sizeMap.remove(small);
            sizeMap.put(big,v1Size+v2Size);
        }
    }

    public static class Account{
        public String id;

        public String bilibiliId;

        public String gitHUbId;

        public Account(String id, String bilibiliId, String gitHUbId) {
            this.id = id;
            this.bilibiliId = bilibiliId;
            this.gitHUbId = gitHUbId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Account)) {
                return false;
            }
            Account account = (Account) o;
            return Objects.equals(id, account.id) ||
                    Objects.equals(bilibiliId, account.bilibiliId) ||
                    Objects.equals(gitHUbId, account.gitHUbId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, bilibiliId, gitHUbId);
        }
    }

    public static int getIndependentUsers(List<Account> accounts){
        SearchUnionCoding<Account> accountSearchUnionCoding = new SearchUnionCoding<>(accounts);
        Map<String,Account> idMaps = Maps.newHashMap();
        Map<String,Account> bilibiliIdMaps = Maps.newHashMap();
        Map<String,Account> gitHubIdMaps = Maps.newHashMap();
        for (Account account : accounts) {
            Account idAccount = idMaps.get(account.id);
            if(idAccount != null){
                accountSearchUnionCoding.union(idAccount,account);
            }else{
                idMaps.put(account.id,account);
            }
            Account bilibiliAccount = bilibiliIdMaps.get(account.bilibiliId);
            if(bilibiliAccount != null){
                accountSearchUnionCoding.union(bilibiliAccount,account);
            }else{
                bilibiliIdMaps.put(account.bilibiliId,account);
            }
            Account gitHubAccount = gitHubIdMaps.get(account.gitHUbId);
            if(gitHubAccount != null){
                accountSearchUnionCoding.union(gitHubAccount,account);
            }else{
                gitHubIdMaps.put(account.gitHUbId,account);
            }
        }
        return accountSearchUnionCoding.sizeMap.keySet().size();
    }

}

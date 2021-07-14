package com.ly.algorithm;

import lombok.Data;

/**
 * stack str
 * @author Ly
 * @create 2021/5/14 16:54
 * @desc
 **/
@Data
public class Stack {

    private int[] arr;

    private int index;

    private int limit;

    public Stack(int limit) {
        arr = new int[limit];
    }

    public Boolean isEmpty(){
        if(index == 0){
            return true;
        }
        return false;
    }

    public int pop(){
        if(isEmpty()){
            return  -1;
        }
        int n = arr[--index];
        arr[index] = 0;
        return n;
    }

    public Boolean isFull(){
        if(index >= limit){
            return true;
        }
        return false;
    }

    public void push(int value){
        if(isFull()){
            return;
        }
        arr[index++] = value;
    }
}

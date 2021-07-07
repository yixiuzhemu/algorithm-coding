package com.ly.algorithm;

public class Heap{
        public int[] arr;

        private int heapSize;

        private int limit;

        public Heap(int size) {
            this.limit = size;
            this.arr = new int[size];
        }

        public Boolean isEmpty(){
            if(arr == null || heapSize == 0){
                return  true;
            }
            return false;
        }

        public Boolean isFull(){
            if(heapSize>= limit){
                return  true;
            }
            return false;
        }

        public void push(int i){
          if(!isFull()){
            arr[heapSize] = i;
            heapInsert(arr,heapSize++);
         }
        }


        public int pop(){
            if(!isEmpty()){
                int i = arr[0];
                //将最后一个节点的值移到根节点，并且heapSize 减一
                swap(arr,0,--heapSize);
                heapify(arr,0,heapSize);
                return i;
            }
            return -1;
        }

        /**
         * 弹出后，最后一个堆节点，移动到了头位置，需要对堆进行重新规整
         * @param arr
         * @param index
         * @param heapSize
         */
        public void heapify(int[] arr,int index,int heapSize){
            //父节点的子节点计算方法 2*i+1 , 2*i+2
            int l = (index<<1) + 1;
            while(l < heapSize){
                //获取子节点最大值的位置
                int largest = l+1<heapSize && arr[l+1] < arr[l] ? l + 1:l;
                //判断子节点最小的值是否比父节点小
                largest = arr[largest] < arr[index] ? largest:index;
                if(largest == index){
                    break;
                }
                swap(arr,index,largest);
                index = largest;
                l = (index<<1) + 1;
            }
        }

        /**
         * 插入的位置 找到其父节点位置，(i-1)/2 ，对大小进行判断
         * @param arr
         * @param index
         */
        public void heapInsert(int[] arr,int index){
            while(arr[(index-1)>>1] > arr[index]){
                swap(arr,(index-1)>>1,index);
                index = (index-1)>>1;
            }
        }

    public static void swap(int[] arr,int i ,int j){
        if(i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
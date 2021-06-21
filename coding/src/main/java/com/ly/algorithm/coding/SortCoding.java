package com.ly.algorithm.coding;

import java.util.Random;

/**
 * @author Ly
 * @create 2021/4/21 9:26
 * @desc
 **/
public class SortCoding {

    /**
     * 选择排序(选一个最小指到0位置，破坏稳定性) 555555155555 ，第一个5会跑到后面去
     * @param arr
     */
    public static void selectSort(int[] arr){
        for (int i = 0;i<arr.length;i++) {
            int min = arr[i];
            int count = i;
            for(int j = i+1;j < arr.length;j++){
                if(arr[j] < min){
                    min = arr[j];
                    count = j;
                }
            }
            if(i == count){
                continue;
            }
            swap(arr,i,count);
        }
    }

    /**
     * 冒泡排序 相等时不交换，保留稳定性
     * @param arr
     */
    public static void bubblingSort(int[] arr){
        for (int i = 0;i<arr.length - 1;i++) {
            for(int j=0;j<arr.length - 1 - i;j++){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 插入排序，相等时不交换，保留稳定性，在当前数之前已经排好序的数组中找出应该插入的位置
     * @param arr
     */
    public static void insertSort(int[] arr){
        for(int i = 1;i<arr.length;i++){
            for(int j = 0;j<i;j++){
                if(arr[j] > arr[i]){
                    swap(arr,j,i);
                }
            }
        }
    }

    /**
     * 归并排序递归版，用于求小数和，大数和，等等问题，可以实现稳定性
     * @param arr
     */
    public static void mergeSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
    }

    public static void mergeSort(int[] arr,int L,int R){
        if(L==R){
            return;
        }
        int mid = L + ((R-L) >> 1);
        mergeSort(arr,L,mid);
        mergeSort(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr,int L,int mid ,int R){
        int lp = L;
        int rp = mid + 1;
        //R = 10 ,L = 0 数组长度为  11
        int[] help = new int[R-L + 1];
        int index = 0;
        //左指针到中点的位置，右指针到右边界的位置，谁小将谁移动到help数组中，指针向后移动，另一个指针不动，知道一个指针走完
        while(lp<=mid && rp<=R){
            help[index++] = arr[lp] >= arr[rp] ? arr[rp++]:arr[lp++];
        }
        //将剩下的部分全部放入help数组
        while(lp<=mid){
            help[index++] = arr[lp++];
        }
        //将剩下的部分全部放入help数组
        while(rp<=R){
            help[index++] = arr[rp++];
        }
        //将help数组覆盖到原数组中
        for(int i = 0;i<help.length;i++){
            arr[L++] = help[i];
        }
    }

    /**
     * 归并排序非递归版本，加入一个mergeSize变量，将数组根据mergeSize划分成左组右组
     * @param arr
     */
    public static void commonMergeSort(int arr[]){
        int mergeSize = 1;
        while(mergeSize<arr.length){
            int L = 0;
            while(L < arr.length){
                //中点位置，等于L 到mergeSize
                int mid = L + mergeSize - 1;
                if(mid >= arr.length){
                    break;
                }
                //如果不存在右组，则R 直接等于数组长度-1
                int R = Math.min(mid+mergeSize,arr.length-1);
                merge(arr,L,mid,R);
                L = R + 1;
            }
            if(mergeSize > arr.length >> 1 ){
                break;
            }
            mergeSize = mergeSize<<1;
        }
    }

    /**
     * 快速排序
     * @param arr
     */
    public static void quickSort(int[] arr){
        process1(arr,0,arr.length-1);
    }

    /**
     * 快排1.0，将数组划分成左右两个区域，以数组最后一个值作为划分值，左边小于划分值，右边大于划分值,一次只确定一个值
     * @param arr
     * @param L
     * @param R
     */
    public static void process1(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        int m = arr[R];
        int less = L - 1;
        int more = R + 1;
        int index = L;
        while(index < more){
            if(arr[index] < m){
                swap(arr,index++,++less);
            }else if(arr[index] > m){
                swap(arr,index,--more);
            }else{
                index++;
            }
        }
        int sureIndex = index - 1;
        process1(arr,L,sureIndex-1);
        process1(arr,sureIndex+1,R);
    }

    /**
     * 随机快排2.0 ,将数组分成3个区域，所以中间肯定是相等的，所以一次性搞定一批数
     * @param arr
     * @param L
     * @param R
     */
    public static void process2(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        int less = L - 1;
        int more = R + 1;
        int index = L;
        int m = arr[R];
        while(index < more){
            if(arr[index] < m){
                if(++less == index){
                    index++;
                    continue;
                }
                swap(arr,index++,less);
            }else if(arr[index] > m){
                if(--more == index){
                    continue;
                }
                swap(arr,index,more);
            }else{
                index++;
            }
        }
        process2(arr,L,less);
        process2(arr,index,R);
    }
    /**
     * 快排3.0 在2.0的基础上，选用随机值作为划分值
     * @param arr
     */
    public void process3(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        Random random = new Random();
        int m = arr[random.nextInt(R-L)+L];
        int less = L - 1;
        int more = R + 1;
        int index = L;
        while(index < more){
            if(arr[index] < m){
                if(index == ++less){
                    index++;
                    continue;
                }
                swap(arr,index++,less);
            }else if(arr[index] > m){
                if(index == --more){
                    continue;
                }
                swap(arr,index,more);
            }else{
                index++;
            }
        }
        process3(arr,L,less);
        process3(arr,index,R);
    }

    /**
     * 堆排序，重点在于heapInsert和heapify，入堆使用heapInsert，出堆使用heapify
     * 入堆时，比较当前插入的值的位置和它的父节点的值的大小，一直到根节点
     * 出堆时，弹出第一个数，然后将堆的最后一个节点放到根节点，然后进行heapify 进行规整
     * 堆的头节点从小到大 称为小根堆，从大到小，称为大根堆
     * 子节点等于 (2*i)+1 父节点的计算方式，（i-1）/2
     * @param arr
     */
    public static void heapSort(int[] arr){
        Heap heap = new Heap(arr.length);
        for (int i : arr) {
            heap.push(i);
        }
        for(int i = 0;i<arr.length;i++){
            arr[i] = heap.pop();
        }
    }

    static class Heap{
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
    }

    /**
     * 计数排序，适用场景很少，需要提前规划桶
     * @param arr
     */
    public static void countSort(int[] arr){
        int[] help = new int[10];
        for (int i : arr) {
            int m = i % 10;
            help[m]++;
        }
        int count = 0;
        for (int i = 0;i<help.length;i++) {
            for(int j = 0 ;j<help[i];j++){
                arr[count++] = i;
            }
        }
    }

    /**
     * 基数排序
     * @param arr
     */
    public static void radixSort(int[] arr){
        int bit = maxBit(arr);
        radixSort(arr,0,arr.length-1,bit);
    }

    public static int maxBit(int[] arr){
        int max = 0;
        for (int i : arr) {
            max = Math.max(max,i);
        }
        int count = 1;
        while((max = max / 10) > 0){
            count++;
        }
        return count;
    }

    public static void radixSort(int[] arr,int L,int R,int bit){
        int radix = 10;
        int[] help = new int[R-L+1];
        for(int i = 0;i<bit;i++){
            int[] count = new int[radix];
            int[] countB = new int[radix];
            int divideRadix = (int)Math.pow(radix,i);
            for(int j = 0;j<arr.length;j++){
                count[(arr[j]/divideRadix)%radix]++;
            }
            int sum = 0;
            for(int j = 0;j<count.length;j++){
                sum+=count[j];
                countB[j] = sum;
            }
            for(int j = arr.length-1;j>=0;j--){
                //从数组最后一个数开始算，在数组中应该出现的位置为 取余之后-1
                help[--countB[(arr[j]/divideRadix)%radix]] = arr[j];
            }
            for(int j = 0;j<arr.length;j++){
                arr[j] = help[j];
            }
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

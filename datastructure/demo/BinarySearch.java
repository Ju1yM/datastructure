package com.datastructure.demo;

public class BinarySearch {
    //在这里可以mid-1 或者mid+1换成mid 但是不可以过于膨胀将a[mid].compareTo(x)>0条件下的mid或者mid-1换成mid+1
    // 考虑寻找index=0的情况  当区间为[0,1] 每次mid=(0+1)/2 =0的时候 每次right有变成了mid+1=1  程序进入死循环
    public static <T extends Comparable<? super T>> boolean binarySearch(T a[], T x) {

        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid].compareTo(x) > 0) {
                right = mid-1;
            } else if (a[mid].compareTo(x) < 0) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Integer a[] = {1, 4,6};
        System.out.println(binarySearch(a, 1));
    }


}

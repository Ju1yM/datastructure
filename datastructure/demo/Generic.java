package com.datastructure.demo;

public class Generic {
    //使用泛型static来表示没有特定参数的表
//    注意这里用int就不可以 需要Integer
    public  static <E> boolean contains(E [] arr,E x)
    {
        for(E val : arr){
            if(x.equals(val))
                return true;
        }
        return  false;
    }

    public static void main(String[] args)
    {
        Integer arr[]={4,7,9};
        Integer x = 4;
        System.out.println(contains(arr,x));

    }
}

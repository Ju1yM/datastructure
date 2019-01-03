package com.className.demo;

import java.util.ArrayList;
import java.util.List;

//1.不能像下面这样直接利用泛型变量创建数组：
//
//                      T[] a = new T[];
//
//        但可以先创建一个Object类型的数组，再强制类型转化为泛型数组：
//
//                      T[] a = (T[]) new Object[length];
//
//        这条语句是对的，因为对于没有限定的类型变量，类型擦除后用Object代替T，上面语句变为：
//
//                      Object[] a = (Object[]) new Object[length];
//
//        上面这条语句很明显没有问题。

public class ReadAndWrite<T> {

    private T value;
   // List<T>[] arr = (ArrayList<T> [])new ArrayList[10]; //强制转换一个arraylist[]给new出来的
    //  T [] a = new T [10];  //会报错
   // T [] aa =(T[]) new Object[5];

    public T read() {

        return value;

    }

    public void write(T t) {
        value = t;
    }
}

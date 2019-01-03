package com.datastructure.demo;

import com.className.demo.ReadAndWrite;

public class TypeClear {
    //java会进行类型擦除
//1,2是运行时报错
//1.如果在arr2[0]处对cell.toString()之后 就会报Exception in thread "main" java.lang.ArrayStoreException: java.lang.String
//	at com.TestDeom.main(TestDeom.java:22)异常

    //2.如果没有toString()方法， arr2[]变成了ReadAndWrite[]类型 cell就可以传进去  但是下一行会报错
    // Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
    //	at com.TestDeom.main(TestDeom.java:26)

    //3.直接传cell到arr1[]里会在编译时报错

    public static void main(String[] args)
    {


        ReadAndWrite<String>[] arr1 = new ReadAndWrite[10];

        ReadAndWrite<Integer> cell = new ReadAndWrite<>();
        cell.write(15);

        Object [] arr2 =arr1;//arr2没有new一个对象 所以会和arr1指向同一个地址
        arr2[0]=cell;

        String s = arr1[0].read();


    }



}

package com.datastructure.demo;

import com.TestDeom;
import com.className.demo.person;

public class assistChange {
    //集合不可以协变 因此使用通配符来(Collection<? extends Number> arr)             <? super Number>
    //数组可以协变  继承的类可以当做参数传入
    public static void main(String[] args)
    {
        int a=1,b=2;
        System.out.println(Integer.sum(a,b));
        person ppp=new person("2",29);
        person[] arr = new em[5];
        arr[0] = new st("1",20);
        System.out.println(arr[0].toString());
        System.out.println(ppp);
    }

    public static class em extends person{
        public em(String name, int age) {
            super(name, age);
        }
    }
    public static class st extends em {

        public st(String name, int age) {
            super(name, age);
        }
    }
}

package com.datastructure.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
//首先 removelist2是错误的 对于增强for循环，如果要对list进行add remove操作，则迭代器是非法的 必须使用自己构造的迭代器
//注意removelist3中，Iterator<Integer> i=list.iterator(); while(i.hasNext()){if(i.next()%2==0)  i.remove();}
//三种方法remove的实例不一样
// removelist1删除的是list.get(i)之后的list.remove(i)
//remove2虽然是错误的 希望删除的是基础迭代器中的实例i  list.remove(i)
//remove3删除的是自己构造的迭代器中的实例i   i.remove();




//重要的是对于ArrayList和LinkedList(双向链表)查找 删除的时间复杂度
//对于removelist1  ArrayList remove效率不高 因为每次需要移动 程序花费二次时间  第一次找到位置到达 第二次需要删除移动
// LinkedList对于get的调用方法 每次需要从头走到该地方  remove也需要到达位置更改指针指向 因此是整个程序花费二次时间

//removelist2进行了思想上的优化 直接迭代Iterator对象
//对于ArrayList和LinkedList来说，不需要get到达， 但是每次remove时候 还需要搜索到达位置删除

//removelist3来说 LinkedList每次在到达的位置进行删除 整个过程只有遍历的时间 一次时间
//arraylist来说 遍历+移动 仍是二次时间

public class removeList {
    public static void main(String[] args) {
        List a=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8));
        removelist1(a);
        //  removelist2(a);  //增强for循环使用的基础迭代器非法
        removelist3(a);
    }


    public static void removelist1(List<Integer> list)
    {
        for (int i = 0; i <list.size(); i++) {
            if(list.get(i)%2==0)
            {list.remove(i);}

        }
        System.out.println(list);
    }

    public static void removelist2(List<Integer> list)
    {
        for(Integer i :list)
        {
            if(i%2==0)
               list.remove(i); //错误 i是基础迭代器中的实例
               // i.remove;   //这时候i没有remove()方法
        }
        System.out.println(list);
    }

    public static void removelist3(List<Integer> list)
    {
        Iterator<Integer> i = list.iterator();
        while(i.hasNext()) {
            Integer tmp=i.next();
            if(tmp%2==0) {
                i.remove();

            }

        }
        System.out.println(list);

    }

}



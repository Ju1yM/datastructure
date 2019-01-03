package com.datastructure.demo;

import com.className.demo.person;

import java.util.*;

public class comparable {
    public static void main(String[] args)
    {
        //---------------------------------------------
        //String

        String[] s={"Hello","zzzzz","AAAAAB","aaaa"};
        System.out.println(findmax3(s,new CaseIgnore()));//调用方法或者class需要static


        //---------------------------------------------
        //List
        List<person> p = new ArrayList<>();
        p.add(new person("UU",77));
        p.add( new person("AAA",10));
        p.add(new person("BBB",15));
        Collections.sort(p);
        for(person pp :p){
       //     System.out.println(pp.toString());
        }
        //---------------------------------------------
        //Integer
        Integer arr[] ={15,28,97,101,1,5};
        int arrint[] = {15,28,97,101,1,5};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1-o2);
            }
        });
        for (int i = 0; i < arr.length; i++) {
      //     System.out.println(arr[i]);
        }

    }




    public  static  Comparable findmax(Comparable arr[])
    {
        int maxindex = 0;
        for (int i = 0; i <arr.length ; i++) {
          if(arr[i].compareTo(arr[maxindex])>0) {
              maxindex = i;
          }
        }
        return arr[maxindex];
    }
//否则不能正确比较 因为编译器不知道对compareTo方法调用是否合法
    //所以找出所有继承Comparable接口的 并高于他的父类都可以使用这个方法
    public  static <T extends Comparable<? super  T>> T findmax2(T[] arr){
        int maxindex = 0;
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i].compareTo(arr[maxindex])>0) {
                maxindex = i;
            }
        }
        return arr[maxindex];
    }

    public static <T> T findmax3(T[] arr,Comparator<? super T>cmp){
        int maxindex = 0;
        for (int i = 0; i <arr.length ; i++) {
            if(cmp.compare(arr[i],arr[maxindex])>0) {
                maxindex = i;
            }
        }
        return arr[maxindex];
    }

   static class CaseIgnore implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }



}

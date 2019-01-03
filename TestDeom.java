package com;

import com.className.demo.ReadAndWrite;
import com.className.demo.person;
import sun.awt.util.IdentityArrayList;

import java.util.*;


public class TestDeom {

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
     List a=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8));
     removelist1(a);
   //  removelist2(a);
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
                    list.remove(i);
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

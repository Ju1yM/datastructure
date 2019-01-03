package com.datastructure.demo;
//本程序寻找一个数组中最大子序列 基本思想是最大子序列只有三种情况 左边一部分 右边一部分 中间一部分
// 因此采用递归的思想解决  每次寻找到部分进行查找 并进行对比
//注意重点是   int maxleft = maxArray(a, left, mid);
//             int maxright = maxArray(a, mid + 1, right);
// 之后mid已经发生变化
public class maxArray {
    public static int maxArray(int[] a, int left, int right) {

        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }
        int mid = (left + right) / 2;
        int maxleft = maxArray(a, left, mid);
        int maxright = maxArray(a, mid + 1, right);

        int maxlefttmp = 0;
        int maxleftsum = 0;
        for (int i = mid; i >= left; i--) {
            maxleftsum += a[i];
            if (maxleftsum > maxlefttmp)
                maxlefttmp = maxleftsum;
        }

        int maxrighttmp = 0;
        int maxrightsum = 0;
        for (int i = mid + 1; i <= right; i++) {
            maxrightsum += a[i];
            if (maxrightsum > maxrighttmp)
                maxrighttmp = maxrightsum;
        }


        return maxNum(maxleft, maxright, maxrighttmp + maxlefttmp);
    }

    public static int maxNum(int a, int b, int c) {
        int tmp = Math.max(a, b);
        return tmp > c ? tmp : c;
    }

    public static void main(String[] args) {
        int a[] = {5, -11, -10, 2};
        System.out.println(maxArray(a, 0, a.length - 1));
    }
}

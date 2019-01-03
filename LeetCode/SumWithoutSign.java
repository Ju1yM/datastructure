package com.LeetCode;

public class SumWithoutSign {
//a&b表示两个数中需要进位的位置，左移一位表示进位  a^b表示不需要进位的位置
    //当没有进位时 a^b就可以理解为加法
    public static void main(String[] args) {

        System.out.println( getSum(2,3));
    }

    public static int getSum(int a, int b) {
        while (b != 0) {
            int temp = a^b;
            b = (a & b) << 1;
            a = temp;

        }
        return a;
    }
}

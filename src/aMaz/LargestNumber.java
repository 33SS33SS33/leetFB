package aMaz;

import java.util.*;

/**
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead
 * of an integer.
 * Tags: Sort
 * 要使用cmp函数来排序 比较规则是x+y 和y+x的大小 而且要倒序
 * 同时要注意[0,0]这种特殊情况
 */
class LargestNumber {
    public static void main(String[] args) {
        LargestNumber ln = new LargestNumber();
        int[] num = {3, 30, 34, 5, 9, 0};
        System.out.println(ln.largestNumber(num));
    }

    /**
     * Create a comparator for sorting 最好的
     * Convert num to String and compare the concatenated result of them
     * Note {0, 0} is a special case
     */
    public String largestNumber(int[] num) {
        StringBuilder res = new StringBuilder();
        if (num == null || num.length == 0)
            return res.toString();
        String[] str = new String[num.length];
        for (int i = 0; i < num.length; i++)
            str[i] = num[i] + "";
        Comparator<String> comp = (s1, s2) -> Long.valueOf(s2 + s1).compareTo(Long.valueOf(s1 + s2));
        Arrays.sort(str, comp);
        if (str[0].equals("0"))
            return "0"; // deal with 0
        for (String s : str)
            res.append(s);
        return res.toString();
    }

}

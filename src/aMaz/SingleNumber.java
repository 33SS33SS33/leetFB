package aMaz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * Tags: Hashtable, Bit Manipulation
 * 可以用字典 如果字典没有key就插入  如果字典有key 就删除 最后剩下的key就是
 * 不用多余空间的话  就要用异或操作 异或操作有交换律还有结合律
 */
class SingleNumber {
    public static void main(String[] args) {
        int[] A = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};
        System.out.println(singleNumbera(A));
        System.out.println(singleNumberb(A));
        System.out.println(singleNumberc(A));
    }

    /**
     * Without extra space 最好的
     * XOR of two equal numbers is 0 : a^a=0. This is the main idea of the
     * algorithm.
     */
    public static int singleNumbera(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int res = A[0];
        for (int i = 1; i < A.length; i++) {
            res ^= A[i];
        }
        return res;
    }

    /**
     * hashtable, store the value and remove when appears second time
     * the only number left is the one
     */
    public static int singleNumberc(int[] A) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (!map.containsKey(A[i]))
                map.put(A[i], 1);
            else
                map.remove(A[i]);
        }
        int res = 0;
        for (Integer key : map.keySet())
            res = key;
        return res;
    }

    public static int singleNumberb(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int n : A) {
            if (!set.add(n))
                set.remove(n);
        }
        Iterator<Integer> it = set.iterator();
        return it.next();
    }

}

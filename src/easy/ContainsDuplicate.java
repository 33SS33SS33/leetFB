package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * <p/>
 * 用set
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] num = { 1, 3, 1, 5, 8 };
        System.out.println(containsDuplicate(num));
    }

    /**
     * 最好的
     */
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<Integer>();
        for (int n : nums) {
            if (s.contains(n)) {
                return true;
            }
            s.add(n);
        }
        return false;
    }
}

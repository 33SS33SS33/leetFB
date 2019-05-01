package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 * boyer-moore-majority-vote-algorithm 使用了这个算法
 * 首先要观察一下 符合要求的最多有几个众数  这道题是2个
 * 然后设置4个变量 两个变量存候选的众数 两个变量存对应候选数的出现次数
 * 然后遍历数组 碰见候选数就+1 没有就两个都-1 如果计数器变成0了 就把当前的数字当成候选数
 */
public class MajorityEle2 {
    public static void main(String[] args) {
        MajorityEle2 l = new MajorityEle2();
        int[] s = {1, 3, 1, 5, 2, 1};
        System.out.println(l.majorityElementA(s).toString());
        System.out.println(l.majorityElementB(s).toString());
    }

    /**
     * 使用map
     * creek not good ,Time = O(n) and Space = O(n)
     */
    public List<Integer> majorityElementB(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    /**
     * creek good Time = O(n) and Space = O(1)
     */
    public List<Integer> majorityElementA(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Integer n1 = null, n2 = null;
        int c1 = 0, c2 = 0;
        for (int i : nums) {
            if (n1 != null && i == n1) {
                c1++;
            } else if (n2 != null && i == n2) {
                c2++;
            } else if (c1 == 0) {
                c1 = 1;
                n1 = i;
            } else if (c2 == 0) {
                c2 = 1;
                n2 = i;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = c2 = 0;
        for (int i : nums) {
            if (i == n1) {
                c1++;
            } else if (i == n2) {
                c2++;
            }
        }
        if (c1 > nums.length / 3)
            result.add(n1);
        if (c2 > nums.length / 3)
            result.add(n2);

        return result;
    }

}

package medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GAOSHANSHAN835 on 2016/1/18.
 */

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */

public class MajorityEle2 {
    public static void main(String[] args) {
        MajorityEle2 l = new MajorityEle2();
        int[] s = { 1, 3, 1, 5, 2, 1 };
        System.out.println(l.majorityElementA(s));
        System.out.println(l.majorityElementB(s));
        System.out.println(l.majorityElementC(s));
    }

    /**
     * creek good Time = O(n) and Space = O(1)
     */
    public List<Integer> majorityElementA(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        Integer n1 = null, n2 = null;
        int c1 = 0, c2 = 0;
        for (int i : nums) {
            if (n1 != null && i == n1.intValue()) {
                c1++;
            } else if (n2 != null && i == n2.intValue()) {
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
            if (i == n1.intValue()) {
                c1++;
            } else if (i == n2.intValue()) {
                c2++;
            }
        }
        if (c1 > nums.length / 3)
            result.add(n1);
        if (c2 > nums.length / 3)
            result.add(n2);

        return result;
    }

    /**
     * creek not good ,Time = O(n) and Space = O(n)
     */
    public List<Integer> majorityElementB(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<Integer> result = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 3) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public List<Integer> majorityElementC(int[] nums) {
        List<Integer> rst = new ArrayList<Integer>();
        if (nums == null) {
            return rst;
        }
        int n1 = 0, n2 = 0;
        int c1 = 0, c2 = 0;
        for (int i = 0; i < nums.length; ++i) {
            int n3 = nums[i];
            if (c1 > 0 && c2 > 0) {
                if (n3 != n1 && n3 != n2) {
                    c1--;
                    c2--;
                } else if (n3 == n1) {
                    c1++;
                } else {
                    c2++;
                }
            } else if (c1 > 0) {
                if (n3 == n1) {
                    c1++;
                } else {
                    n2 = n3;
                    c2++;
                }
            } else if (c2 > 0) {
                if (n3 == n2) {
                    c2++;
                } else {
                    n1 = n3;
                    c1++;
                }
            } else {
                n1 = n3;
                c1++;
            }
        }
        c1 = c2 = 0;
        for (int i = 0;
             i < nums.length; ++i) { // n1, n2 are only candidates!!! need to count again!!!
            if (nums[i] == n1) {
                c1++;
            } else if (nums[i]
                    == n2) { //no worry if n1==n2 in some cases, because we only count once.
                c2++;
            }
        }
        if (c1 > nums.length / 3) {
            rst.add(n1);
        }
        if (c2 > nums.length / 3) {
            rst.add(n2);
        }
        return rst;
    }
    /*public List<Integer> majorityElement(int[] nums) {
        if(nums.length == 0) return Collections.emptyList();

        int n1 = nums[0];
        int n2 = nums[0];

        int c1 = 0;
        int c2 = 0;

        for(int i = 0; i < nums.length; i++){

            // put to an empty slot
            if(c1 <= 0 && nums[i] != n2){
                n1 = nums[i];
                c1 = 1;
                continue;
            }

            if(c2 <= 0 && nums[i] != n1){
                n2 = nums[i];
                c2 = 1;
                continue;
            }

            // add count
            if(nums[i] == n1){
                c1++;
                continue;
            }

            if(nums[i] == n2){
                c2++;
                continue;
            }

            // no match

            c1--;
            c2--;
        }

        // faint
        return new ArrayList<>(IntStream.of(n1, n2)
                .filter(n ->
                        Arrays.stream(nums).filter(i -> n == i).count() > nums.length / 3)
                .boxed()
                .collect(Collectors.toSet()));
    }*/

}

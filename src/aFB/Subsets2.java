package aFB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,2], a solution is:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * Tags: Array, Backtracking
 * 使用DFS来做
 * 每次都以不同的元素开始dfs 如果元素相同 则跳过
 * 因为元素相同的只需要在path上加上就行了
 * 应该也能用之前的办法做 待确认
 */
class Subsets2 {
    public static void main(String[] args) {
        int[] num = {1, 2, 2};
        List<List<Integer>> res = subsetsWithDup(num);
        for (List<Integer> l : res) {
            System.out.println(l.toString());
        }
        System.out.print("------------------");
        List<List<Integer>> res2 = subsetsWithDupb(num);
        for (List<Integer> l2 : res2) {
            System.out.println(l2.toString());
        }
    }

    /**
     * 最好的
     * Backtrack to generate all subsets
     * Add list to result
     * Backtrack from current position to the end of array
     * Skip duplicates first
     * Add number to list and pass list and i+1 to next backtrack
     * Reset list after that
     */
    public static List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (null == num || num.length == 0)
            return res;
        Arrays.sort(num); // sort first
        subsetsHelper(res, new ArrayList<Integer>(), num, 0);
        return res;
    }

    private static void subsetsHelper(List<List<Integer>> res, List<Integer> list, int[] num, int pos) {
        res.add(new ArrayList<Integer>(list));
        for (int i = pos; i < num.length; i++) {
            if (i != pos && num[i] == num[i - 1])
                continue; // skip dups
            list.add(num[i]);
            subsetsHelper(res, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }

    //不好懂。。
    public static List<List<Integer>> subsetsWithDupb(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList()); // add []
        for (int i = 0, prev = 0; i < nums.length; i++) {
            int size = ans.size();
            for (int j = (i == 0 || nums[i] != nums[i - 1]) ? 0 : prev; j < size; j++) {
                List<Integer> cur = new ArrayList(ans.get(j));
                cur.add(nums[i]);
                ans.add(cur);
            }
            prev = size;
        }
        return ans;
    }
}
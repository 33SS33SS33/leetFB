package sum;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a
 * + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 * <p/>
 * Note:
 * Elements in a triplet (a,b,c) must be in <strong>non-descending</strong>
 * order.
 * (ie, a ≤ b ≤ c)
 * The solution set must not contain <strong>duplicate</strong> triplets.
 * <p/>
 * For example, given array S = {-1 0 1 2 -1 -4},
 * <p/>
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 * <p/>
 * Tags: Array, Two Pointers
 */
class ThreeSum {
    public static void main(String[] args) {
        ThreeSum t = new ThreeSum();
        int[] s = { -1, 0, 1, 2, -1, -4 };
//        t.printResult(t.threeSum(s));
//        ArrayList<ArrayList<Integer>> res = threeSumB(s);
//        for (List<Integer> l : res) {
//            System.out.print(l.toString());
//        }
        ArrayList<List<Integer>> res2 = threeSumC(s);
            System.out.print(res2.toString());
//        ArrayList<ArrayList<Integer>> res3 = threeSumD(s);
//        System.out.print(res3.toString());
    }

    /**
     * Two Pointers.
     * Sort given array first.
     * Traverse the array with 1 pointer
     * Use 2 more pointers from both start(i + 1) and end to find target
     */
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue; // skip duplicate
            if (num[i] > 0)
                break; // stop at positive integers
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (j > i + 1 && num[j] == num[j - 1]) { // skip duplicate
                    j++;
                    continue;
                }
                if (num[i] + num[j] > 0)
                    break;// already bigger than 0
                if (num[i] + num[j] + num[k] < 0)
                    j++;
                else if (num[i] + num[j] + num[k] > 0)
                    k--;
                else { // num[i] + num[j] + num[k] == 0
                    List<Integer> triplets = new ArrayList<Integer>();
                    triplets.add(num[i]);
                    triplets.add(num[j]);
                    triplets.add(num[k]);
                    res.add(triplets);
                    j++; // move j ahead
                    k--;
                }
            }
        }
        return res;
    }

    /**
     * better---creek!!!!!---
     * A better solution is using two pointers instead of one. This makes time complexity of O(n^2).
     * To avoid duplicate, we can take advantage of sorted arrays, i.e., move pointers by >1 to use same element only once.
     */
    public static ArrayList<ArrayList<Integer>> threeSumB(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num.length < 3)
            return result;
        // sort array
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            // //avoid duplicate solutions
            if (i == 0 || num[i] > num[i - 1]) {
                int negate = -num[i];
                int start = i + 1;
                int end = num.length - 1;
                while (start < end) {
                    //case 1
                    if (num[start] + num[end] == negate) {
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[start]);
                        temp.add(num[end]);

                        result.add(temp);
                        start++;
                        end--;
                        //avoid duplicate solutions
                        while (start < end && num[end] == num[end + 1])
                            end--;
                        while (start < end && num[start] == num[start - 1])
                            start++;
                        //case 2
                    } else if (num[start] + num[end] < negate) {
                        start++;
                        //case 3
                    } else {
                        end--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * not good
     */
    public static ArrayList<List<Integer>> threeSumC(int[] num) {
        //sort array
        Arrays.sort(num);
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> each = new ArrayList<Integer>();
        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0)
                break;
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] + num[j] > 0 && num[j] > 0)
                    break;
                for (int k = j + 1; k < num.length; k++) {
                    if (num[i] + num[j] + num[k] == 0) {
                        each.add(num[i]);
                        each.add(num[j]);
                        each.add(num[k]);
                        result.add(each);
                        each.clear();
                    }
                }
            }
        }
        return result;
    }

    /**ganker----在这里为了避免重复结果，对于已经判断过的数会skip掉，这也是排序带来的方便
     * 时间复杂度为O(n^2+nlogn)=(n^2),空间复杂度是O(n)*/
    public static ArrayList<ArrayList<Integer>> threeSumD(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length <= 2)
            return res;
        Arrays.sort(num);
        for (int i = num.length - 1; i >= 2; i--) {
            if (i < num.length - 1 && num[i] == num[i + 1])
                continue;
            ArrayList<ArrayList<Integer>> curRes = twoSum(num, i - 1, -num[i]);
            for (int j = 0; j < curRes.size(); j++) {
                curRes.get(j).add(num[i]);
            }
            res.addAll(curRes);
        }
        return res;
    }
    private static ArrayList<ArrayList<Integer>> twoSum(int[] num, int end, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length <= 1)
            return res;
        int l = 0;
        int r = end;
        while (l < r) {
            if (num[l] + num[r] == target) {
                ArrayList<Integer> item = new ArrayList<Integer>();
                item.add(num[l]);
                item.add(num[r]);
                res.add(item);
                l++;
                r--;
                while (l < r && num[l] == num[l - 1])
                    l++;
                while (l < r && num[r] == num[r + 1])
                    r--;
            } else if (num[l] + num[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return res;
    }

    private void printResult(List<List<Integer>> result) {
        for (List<Integer> l : result) {
            System.out.print("{");
            for (Integer i : l) {
                System.out.print(" " + i);
            }
            System.out.println(" }");
        }
    }
}

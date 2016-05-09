package backTrac;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GAOSHANSHAN835 on 2016/3/4.
 */

/**Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can
 * be used and each combination should be a unique set of numbers.
 Ensure that numbers within the set are sorted in ascending order.

 Example 1: Input: k = 3, n = 7 Output: [[1,2,4]]
 Example 2: Input: k = 3, n = 9 Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
/**DFS即可*/
public class CombinationSum3 {
    public static void main(String[] args) {
        CombinationSum3 cs = new CombinationSum3();
        System.out.println(cs.combinationSum3A(3, 7));
        System.out.println(cs.combinationSum3B(3, 9));
    }

    public List<List<Integer>> combinationSum3A(int k, int n) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        backtrack(ans, new ArrayList<Integer>(), k, 1, n);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> comb, int k, int start, int n) {
        if (comb.size() > k)
            return; // no need to search in k+1 numbers
        if (comb.size() == k && n == 0) { // combination found
            List<Integer> res = new ArrayList<Integer>(comb); // make a copy of the list
            ans.add(res);
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (n - i >= 0) { // n < i can be skipped
                comb.add(i);
                backtrack(ans, comb, k, i + 1, n - i);
                comb.remove(comb.size() - 1);
            }
        }
    }

    /**creek*/
    public List<List<Integer>> combinationSum3B(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs(result, 1, n, list, k);
        return result;
    }
    public void dfs(List<List<Integer>> result, int start, int sum, List<Integer> list, int k){
        if(sum==0 && list.size()==k){
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(list);
            result.add(temp);
        }
        for(int i=start; i<=9; i++){
            if(sum-i<0) break;
            if(list.size()>k) break;
            list.add(i);
            dfs(result, i+1, sum-i, list, k);
            list.remove(list.size()-1);
        }
    }
}

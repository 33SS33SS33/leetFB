package sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note
 * that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have <strong>exactly one</strong> 
 * solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 * Tags: Array, HashTable
 */
class TwoSum {
    public static void main(String[] args) {
        TwoSum t = new TwoSum();

        int[] numbers = {4,2,7}; // 6 = 2+4
        int target = 6;
        
        int[] res = t.twoSum(numbers, target);
        int[] res1 = t.twoSumB(numbers, target);
        for (int i = 0; i < res.length; i++) {
            System.out.print(i == res.length - 1 ? res[i] : res[i] + ", ");
        }

        System.out.println();

        for (int i = 0; i < res1.length; i++) {
            System.out.print(i == res1.length - 1 ? res1[i] : res1[i] + ", ");
        }
    }

    /**
     * HashMap, O(n) time, O(n) space
     * key -> number, value -> index
     * Search new target in map and return index if not same
     * Otherwise return null
     */
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++) { // put all to map
            map.put(numbers[i], i);
        }
        
        for (int i = 0; i < numbers.length; i++) {
            int newTarget = target - numbers[i];
            // O(1) search
            if (map.containsKey(newTarget) && i != map.get(newTarget)) { // can't be same indices
                return new int[]{i + 1, map.get(newTarget) + 1};
            }
        }
        return null;
    }

    /**creek  Time complexity of this solution is O(n)*/
    public int[] twoSumB(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                int index = map.get(numbers[i]);
                result[0] = index+1 ;
                result[1] = i+1;
                break;
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return result;
    }

    /**Time complexity in worst case: O(n^2).*/
    public static int[] twoSumC(int[] numbers, int target) {
        int[] ret = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    ret[0] = i + 1;
                    ret[1] = j + 1;
                }
            }
        }
        return ret;
    }
}
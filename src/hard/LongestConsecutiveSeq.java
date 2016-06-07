package hard;

import java.util.*;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its
 * length: 4.
 * Your algorithm should run in O(n) complexity.
 * Tags: Array, HashTable
 */

/**
 * We can solve this problem in O(n) time using an Efficient Solution. The idea is to use Hashing.
 * We first insert all elements in a Hash. Then check all the possible starts of consecutive subsequences. Below is complete algorithm.
 * 1) Create an empty hash.
 * 2) Insert all array elements to hash.
 * 3) Do following for every element arr[i]
 * ....a) Check if this element is the starting point of a subsequence.
 * To check this, we simply look for arr[i] - 1 in hash, if not found, then this is
 * the first element a subsequence.
 * If this element is a first element, then count
 * number of elements in the consecutive starting with this element.
 * If count is more than current res, then updateres.
 */
class LongestConsecutiveSeq {
    public static void main(String[] args) {
        LongestConsecutiveSeq l = new LongestConsecutiveSeq();
        int[] a = { 100, 4, 200, 1, 3, 2 };
        System.out.println(l.longestConsecutive(a));
        System.out.println(l.longestConsecutiveB(a));
        System.out.println(l.longestConsecutiveB2(a));
    }

    /**
     * use a HashSet to add and remove elements. HashSet is implemented by using a hash table. 最好的
     * Elements are not ordered. The add, remove and contains methods have constant time complexity O(1).
     */
    public static int longestConsecutiveB(int[] num) {
        // if array is empty, return 0
        if (num.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<Integer>();
        int max = 1;
        for (int e : num)
            set.add(e);
        for (int e : num) {
            int left = e - 1;
            int right = e + 1;
            int count = 1;
            while (set.contains(left)) {
                count++;
                set.remove(left);
                left--;
            }
            while (set.contains(right)) {
                count++;
                set.remove(right);
                right++;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * Use a map to store ranges
     * Get lower bound with smaller value
     * Get upper bound with larger value
     * Update max length with new bound
     * Put all possible ranges into map
     * including num[i] ~ num[i], low ~ upp, upp ~ low
     */
    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0;
        for (int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i]))
                continue; // skip duplicates
            int low = num[i]; // initialize ranges
            int upp = num[i];
            if (map.containsKey(num[i] - 1))
                low = map.get(num[i] - 1);
            if (map.containsKey(num[i] + 1))
                upp = map.get(num[i] + 1);
            maxLen = Math.max(maxLen, upp - low + 1); // update length
            // put possible ranges into map for next iteration
            map.put(num[i], num[i]);
            map.put(low, upp);
            map.put(upp, low);
        }
        return maxLen;
    }

    /**
     * 其实这个题看起来是数字处理，排序的问题，但是如果要达到好的时间复杂度，还得从图的角度来考虑。
     * 思路是把这些数字看成图的顶点，而边就是他相邻的数字，然后进行深度优先搜索。通俗一点说就是先把数字放到一个集合中，
     * 拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，并且把用过的数字从集合中移除（因为连续的关系，一个数字不会出现在两个串中）。
     * 最后比较当前串是不是比当前最大串要长，是则更新。如此继续直到集合为空。如果我们用HashSet来存储数字，则可以认为访问时间是常量的，
     * 那么算法需要一次扫描来建立集合，第二次扫描来找出最长串，所以复杂度是O(2*n)=O(n)，空间复杂度是集合的大小，即O(n)
     */
    public int longestConsecutiveB2(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        HashSet<Integer> set = new HashSet<Integer>();
        int res = 1;
        for (int i = 0; i < num.length; i++) {
            set.add(num[i]);
        }
        while (!set.isEmpty()) {
            Iterator iter = set.iterator();
            int item = (Integer) iter.next();
            set.remove(item);
            int len = 1;
            int i = item - 1;
            while (set.contains(i)) {
                set.remove(i--);
                len++;
            }
            i = item + 1;
            while (set.contains(i)) {
                set.remove(i++);
                len++;
            }
            if (len > res)
                res = len;
        }
        return res;
    }

}

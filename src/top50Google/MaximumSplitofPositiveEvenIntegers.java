package top50Google;

import java.util.*;

public class MaximumSplitofPositiveEvenIntegers {
    /**
     * You are given an integer finalSum. Split it into a sum of a maximum number of unique positive even integers.
     * For example, given finalSum = 12, the following splits are valid (unique positive even integers summing up to finalSum): (12), (2 + 10), (2 + 4 + 6), and (4 + 8). Among them, (2 + 4 + 6) contains the maximum number of integers. Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers should be unique.
     * Return a list of integers that represent a valid split containing a maximum number of integers.
     * If no valid split exists for finalSum, return an empty list. You may return the integers in any order.
     * Example 1:
     * Input: finalSum = 12
     * Output: [2,4,6]
     * Explanation: The following are valid splits: (12), (2 + 10), (2 + 4 + 6), and (4 + 8).
     * (2 + 4 + 6) has the maximum number of integers, which is 3. Thus, we return [2,4,6].
     * Note that [2,6,4], [6,2,4], etc. are also accepted.
     */
    public List<Long> maximumEvenSplit(long f) {
        LinkedList<Long> ans = new LinkedList<>();
        if (f % 2 == 0) {
            long i = 2;
            while (i <= f) {
                ans.offer(i);
                f -= i;
                i += 2;
            }
            ans.offer(f + ans.pollLast());
        }
        return ans;
    }
}

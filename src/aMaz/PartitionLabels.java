package aMaz;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanshan on 8/27/18.
 */
public class PartitionLabels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }

    /**
     * A string S of lowercase letters is given. We want to palindromePartition this string into
     * as many parts as possible so that each letter appears in at most one part,
     * and return a list of integers representing the size of these parts.
     * Input: S = "ababcbacadefegdehijhklij"  Output: [9,7,8]
     * The palindromePartition is "ababcbaca", "defegde", "hijhklij".
     * This is a palindromePartition so that each letter appears in at most one part.
     * A palindromePartition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     * Note: S will have length in range [1, 500].
     * S will consist of lowercase letters ('a' to 'z') only.
     */
    public static List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }
}

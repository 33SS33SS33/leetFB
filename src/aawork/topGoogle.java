package aawork;

import java.util.LinkedList;
import java.util.List;

public class topGoogle {
// 68. Text Justification

    /**
     * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
     * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
     * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
     * For the last line of text, it should be left-justified and no extra space is inserted between words.
     * Note:A word is defined as a character sequence consisting of non-space characters only.
     * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
     * The input array words contains at least one word.
     * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
     * Output:
     * [
     * "This    is    an",
     * "example  of text",
     * "justification.  "
     * ]
     */
    public static List<String> textJustification(String[] words, int L) {
        List<String> list = new LinkedList<String>();
        for (int i = 0, w; i < words.length; i = w) {
            int len = -1;
            for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
                len += words[w].length() + 1;
            }
            StringBuilder strBuilder = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != words.length) { // not 1 char, not last line
                space = (L - len) / (w - i - 1) + 1;
                extra = (L - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) strBuilder.append(' ');
                if (extra-- > 0) strBuilder.append(' ');
                strBuilder.append(words[j]);
            }
            int strLen = L - strBuilder.length();
            while (strLen-- > 0) strBuilder.append(' ');
            list.add(strBuilder.toString());
        }
        return list;
    }

    /**
     You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
     You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
     For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
     ["Solution","pickIndex"]
     [[[1]],[]]
     Output[null,0]
     Explanation
     Solution solution = new Solution([1]);
     solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.*/

    class Solution {
        private int[] prefixSums;
        private int totalSum;

        public Solution(int[] w) {
            this.prefixSums = new int[w.length];

            int prefixSum = 0;
            for (int i = 0; i < w.length; ++i) {
                prefixSum += w[i];
                this.prefixSums[i] = prefixSum;
            }
            this.totalSum = prefixSum;
        }

        public int pickIndex() {
            double target = this.totalSum * Math.random();

            // run a binary search to find the target zone
            int low = 0, high = this.prefixSums.length;
            while (low < high) {
                // better to avoid the overflow
                int mid = low + (high - low) / 2;
                if (target > this.prefixSums[mid])
                    low = mid + 1;
                else
                    high = mid;
            }
            return low;
        }
    }
/**/
    /**/
//528. Random Pick with Weight
//
//394. Decode String
//
//366. Find Leaves of Binary Tree
//
//1293. Shortest Path in a Grid with Obstacles Elimination
//
//1048. Longest String Chain
//
//1146. Snapshot Array
//
//410. Split Array Largest Sum
//
//150. Evaluate Reverse Polish Notation
//
//329. Longest Increasing Path in a Matrix
//
//359. Logger Rate Limiter
//
//489. Robot Room Cleaner
//
//772. Basic Calculator III
//
//843. Guess the Word
//
//939. Minimum Area Rectangle
//
//1937. Maximum Number of Points with Cost
//
//2034. Stock Price Fluctuation
//
//1423. Maximum Points You Can Obtain from Cards
//
//833. Find And Replace in String
//
//1610. Maximum Number of Visible Points
//
//652. Find Duplicate Subtrees
//
//2007. Find Original Array From Doubled Array
//
//2096. Step-By-Step Directions From a Binary Tree Node to Another
//
//407. Trapping Rain Water II
//
//418. Sentence Screen Fitting
//
//539. Minimum Time Difference
//
//299. Bulls and Cows
//
//1834. Single-Threaded CPU
//
//1277. Count Square Submatrices with All Ones
//
//2013. Detect Squares
//
//777. Swap Adjacent in LR String
//
//792. Number of Matching Subsequences
//
//1218. Longest Arithmetic Subsequence of Given Difference
//
//384. Shuffle an Array
//
//388. Longest Absolute File Path
//
//745. Prefix and Suffix Search
//
//2128. Remove All Ones With Row and Column Flips
//
//2115. Find All Possible Recipes from Given Supplies
//
//1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
//
//818. Race Car
//
//794. Valid Tic-Tac-Toe State
//
//552. Student Attendance Record II
//
//690. Employee Importance
//
//664. Strange Printer
//
//954. Array of Doubled Pairs
//
//1554. Strings Differ by One Character
//
//2018. Check if Word Can Be Placed In Crossword
//
//2135. Count Words Obtained After Adding a Letter
//
//2158. Amount of New Area Painted Each Day

//302. Smallest Rectangle Enclosing Black Pixels

}

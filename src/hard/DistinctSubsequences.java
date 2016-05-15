package hard;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * Explain: the number of distinct subsequences of S equal to T
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.

 * Tags: DP, String
 */

/**使用动态规划 DP
 思考的时候 把T固定住比较容易想 比如 如果T是rab  那么S是rab的时候 然后S变长了 变成rabb/rabc的时候
 As a typical way to implement a dynamic programming algorithm, we construct a matrix dp, where each cell dp[i][j] represents the number of solutions of aligning substring T[0..i] with S[0..j];
 Rule 1). dp[0][j] = 1, since aligning T = "" with any substring of S would have only ONE solution which is to delete all characters in S.
 Rule 2). when i > 0, dp[i][j] can be derived by two cases:
 case 1). if T[i] != S[j], then the solution would be to ignore the character S[j] and align substring T[0..i] with S[0..(j-1)]. Therefore, dp[i][j] = dp[i][j-1].
 case 2). if T[i] == S[j], then first we could adopt the solution in case 1), but also we could match the characters T[i] and S[j] and align the rest of them (i.e. T[0..(i-1)] and S[0..(j-1)]. As a result, dp[i][j] = dp[i][j-1] + d[i-1][j-1]

 e.g. T = B, S = ABC
 dp[1][2]=1: Align T'=B and S'=AB, only one solution, which is to remove character A in S'.*/
class DistinctSubsequences {
    public static void main(String[] args) {
       String S = "rabbbit";
        String T = "rabbit";
        System.out.println(new DistinctSubsequences().numDistinct(S,T));
        System.out.println(new DistinctSubsequences().numDistinctOptimal(S,T));
        System.out.println(new DistinctSubsequences().numDistinct3(S,T));
    }
    
    /**
     * DP, 2d array as table, Time O(mn), Space O(mn)
     * We keep a m*n matrix and scanning through string S, while
     * m = T.length() + 1 and n = S.length() + 1
     * and each cell in matrix dp[i][j] means the number of distinct
     * subsequences of T.substr(1...i) in S(1...j)
     * 
     * Initialization, dp[0][0] = 1, 
     * dp[0][j] = 1, means T is empty, and there is always 1 substring
     * and dp[i][0] = 0, means S is empty
     * 
     * dp[i][j] = dp[i][j-1]        (from S[1...j - 1] no S[j])
     *           + (dp[i-1][j-1]    (S[j] == T[i] and we are going to use S[j])
     *               or 0)          (S[j] != T[i] so we could not use S[j])
     */
    public int numDistinct(String s, String t) {
        if (s == null || t == null)
            return 0;
        int m = t.length();
        int n = s.length();
        if (m > n)
            return 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++)
            dp[0][i] = 1;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                dp[i][j] = dp[i][j - 1] + (t.charAt(i - 1) == s.charAt(j - 1) ? dp[i - 1][j - 1] : 0);
        return dp[m][n];
    }
    
    /**
     * Space optimized, 1D array, build row by row
     */
    public int numDistinctOptimal(String s, String t) {
        if (s == null || t == null) return 0;
        int m = t.length();
        int n = s.length();
        if (m > n) return 0;
        
        int[] dp = new int[m + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= n; i++)
            for (int j = m; j >= 1; j--)
                // same: path[i] = path[i] + (T[i-1] == S[j-1] ? path[i-1] : 0);
                if (t.charAt(j - 1) == s.charAt(i - 1)) dp[j] += dp[j - 1];
        return dp[m];
    }
    public int numDistinct3(String S, String T) {
        char[] s = S.toCharArray();
        if(s.length == 0) return 0;
        char[] t = T.toCharArray();
        if(t.length == 0) return 0;
        if(t.length > s.length) return 0;
        int[][] P = new int[s.length][t.length];
        P[0][0] = s[0] == t[0] ? 1 : 0;
        for(int i = 1; i < s.length; i++){
            P[i][0] = s[i] == t[0] ? 1 + P[i - 1][0] : P[i - 1][0];
        }
        for(int j = 1; j < t.length; j++){
            for(int i = j; i < s.length; i++){
                if(t[j] == s[i]){
                    // sum choices
                    P[i][j] = P[i - 1][j] + P[i - 1][j - 1];
                } else {
                    // no choice
                    P[i][j] = P[i - 1][j];
                }
            }
        }
        return P[s.length - 1][t.length - 1];
    }

    /**错的 不要这样*/
    public int numDistinct4(String S, String T) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<Character, ArrayList<Integer>>();

        for (int i = 0; i < T.length(); i++) {
            if (map.containsKey(T.charAt(i))) {
                map.get(T.charAt(i)).add(i);
            } else {
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                map.put(T.charAt(i), temp);
            }
        }

        int[] result = new int[T.length() + 1];
        result[0] = 1;

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (map.containsKey(c)) {
                ArrayList<Integer> temp = map.get(c);
                int[] old = new int[temp.size()];

                for (int j = 0; j < temp.size(); j++)
                    old[j] = result[temp.get(j)];

                // the relation
                for (int j = 0; j < temp.size(); j++)
                    result[temp.get(j) + 1] = result[temp.get(j) + 1] + old[j];
            }
        }

        return result[T.length()];
    }
}
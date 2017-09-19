package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 * Tags: DP, String
 */
class DecodeWays {
    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.decodeWaysa("12"));
        System.out.println(d.decodeWaysb("12"));
        System.out.println(d.decodeWaysc("12"));
    }

    //http://blog.csdn.net/linhuanmars/article/details/24570759
    //最好的  不懂。。。
    public int decodeWaysa(String s) {
        int n = s.length();
        if (n == 0)
            return 0;
        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0')
                continue;
            else
                memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];
        return memo[0];
    }

    /**
     * Optimal, DP 一维动归
     * Reduce space to O(1)
     */
    public int decodeWaysb(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int len = s.length();
        int prev1 = 1;
        int prev2 = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int code1 = Integer.valueOf(s.substring(i - 1, i)); // 1 digit
            int code2 = Integer.valueOf(s.substring(i - 2, i)); // 2 digits
            int temp = prev2;
            prev2 = (code1 != 0 ? prev2 : 0) + (code2 <= 26 && code2 > 9 ? prev1 : 0);
            prev1 = temp;
        }
        return prev2;
    }

    /**
     * Time O(n), Space O(n) 二维动归
     * note that there can be zeros in s
     */
    public static int decodeWaysc(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int len = s.length();
        int[] ways = new int[len + 1];
        ways[0] = 1;
        ways[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int code1 = Integer.valueOf(s.substring(i - 1, i));
            int code2 = Integer.valueOf(s.substring(i - 2, i));
            // System.out.println(code2);
            ways[i] = (code1 != 0 ? ways[i - 1] : 0) + (code2 <= 26 && code2 > 9 ? ways[i - 2] : 0);
        }
        return ways[len];
    }

}

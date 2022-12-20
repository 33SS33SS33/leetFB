package aTop300microsoft;

/**
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 */
public class DecodeWays {
    public int numDecodingsa(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < n; i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += twoBack;
            }
            twoBack = oneBack;
            oneBack = current;
        }
        return oneBack;
    }

    public int numDecodings(String s) {
        return s.length() == 0 ? 0 : numDecodings(0, s);
    }

    private int numDecodings(int p, String s) {
        int n = s.length();
        if (p == n) return 1;
        if (s.charAt(p) == '0') return 0;
        int res = numDecodings(p + 1, s);
        if (p < n - 1 && (s.charAt(p) == '1' || s.charAt(p) == '2' && s.charAt(p + 1) < '7'))
            res += numDecodings(p + 2, s);
        return res;
    }

    public int numDecodingsb(String s) {
        int n = s.length();
        Integer[] mem = new Integer[n];
        return s.length() == 0 ? 0 : numDecodings(0, s, mem);
    }

    private int numDecodings(int p, String s, Integer[] mem) {
        int n = s.length();
        if (p == n) return 1;
        if (s.charAt(p) == '0') return 0;
        if (mem[p] != null) return mem[p];
        int res = numDecodings(p + 1, s, mem);
        if (p < n - 1 && (s.charAt(p) == '1' || s.charAt(p) == '2' && s.charAt(p + 1) < '7'))
            res += numDecodings(p + 2, s, mem);
        return mem[p] = res;
    }

    public int numDecodingsc(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--)
            if (s.charAt(i) != '0') {
                dp[i] = dp[i + 1];
                if (i < n - 1 && (s.charAt(i) == '1' || s.charAt(i) == '2' && s.charAt(i + 1) < '7'))
                    dp[i] += dp[i + 2];
            }
        return dp[0];
    }
}

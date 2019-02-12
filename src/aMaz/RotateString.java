package aMaz;

/**
 * Created by shanshan on 2/11/19.
 * We are given two strings, A and B.
 * A shift on A consists of taking string A and moving the leftmost character to the rightmost position.
 * For example, if A = 'abcde', then it will be 'bcdea' after one shift on A.
 * Return True if and only if A can become B after some number of shifts on A.
 * Input: A = 'abcde', B = 'cdeab' Output: true
 * Input: A = 'abcde', B = 'abced' Output: false
 */
public class RotateString {
    public boolean rotateString(String A, String B) {
        if (A == null || B == null || A.length() != B.length())
            return false;
        if (A.length() == 0)
            return true;
        char start = A.charAt(0);
        for (int i = 0; i < B.length(); i++) {
            int j = i;
            while (j < B.length() && B.charAt(j) != start)
                j++;
            if (j >= B.length())
                return false;
            for (int n = 0; n < B.length(); n++) {
                if (A.charAt(n) != B.charAt((n + j) % B.length()))
                    break;
                if (n == B.length() - 1)
                    return true;
            }
            i = j;
        }
        return false;
    }
}

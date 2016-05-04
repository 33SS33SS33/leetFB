package string;

/**
 * Created by GAOSHANSHAN835 on 2015/12/23.
 */

/**
 * ---- Hard
 * Implement wildcard pattern matching with support for '?' and '*'.
 */
public class IsMatch {
    public static void main(String[] args) {
        String s = "foo";
        String t = "f*";
        System.out.println(new IsMatch().isMatch(s, t));
        System.out.println();
    }

    /**
     * O(length(s)+length(p))
     * */
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;
        while (i < s.length()) {  // advancing both pointers
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') { // * found, only advancing pattern pointer
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {  // last pattern pointer was *, advancing string pointer
                j = starIndex + 1;
                i = iIndex + 1;
                iIndex++;

            } else {   //current pattern pointer is not star, last patter pointer was not *
                //characters do not match
                return false;
            }
        }
        //check for remaining characters in pattern
        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }
        return j == p.length();
    }
}

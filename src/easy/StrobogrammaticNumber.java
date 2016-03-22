package easy;

import java.util.Arrays;

/**
 * Created by GAOSHANSHAN835 on 2016/1/19.
 */
public class StrobogrammaticNumber {

    public static void main(String[] args) {
        StrobogrammaticNumber r = new StrobogrammaticNumber();
        String a = "8778";
        System.out.println(r.isStrobogrammatic(a));
    }

    static final char[][] GOOD_PATTERNS = { { '9', '6' }, { '6', '9' }, { '1', '1' }, { '8', '8' },
            { '0', '0' }, };

    boolean isStrobogrammatic(char l, char r) {
        char[] s = new char[] { l, r };
        for (char[] g : GOOD_PATTERNS) {
            if (Arrays.equals(g, s)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStrobogrammatic(String num) {
        char[] S = num.toCharArray();
        for (int i = 0; i <= S.length / 2; i++) {
            if (!isStrobogrammatic(S[i], S[S.length - 1 - i])) {
                return false;
            }
        }
        return true;
    }
}

package hard;

/**
 * Validate if a given string is numeric.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should
 * gather all requirements up front before implementing one.
 * LinkedIn
 * Tags: Math, String  (E) String to Integer (atoi)
 */

class ValidNumber {
    public static void main(String[] args) {
        String s = "1.33";
        String s2 = "1.33m";
        System.out.println(new ValidNumber().isNumber(s));
        System.out.println(new ValidNumber().isNumber(s2));
    }

    /**
     * Whitespace, +, -, digit, ., e
     */
    public boolean isNumber(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        // whitespace
        while (i <= e && Character.isWhitespace(s.charAt(i)))
            i++;
        if (i > len - 1)
            return false;
        while (e >= i && Character.isWhitespace(s.charAt(e)))
            e--;
        // skip leading +/-
        if (s.charAt(i) == '+' || s.charAt(i) == '-')
            i++;
        boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c))
                num = true;
            else if (c == '.') { // '.' appear
                if (exp || dot)
                    return false; // exp can't have '.' or dots
                dot = true;
            } else if (c == 'e') { // e appear
                if (exp || !num)
                    return false; // already e but not num
                exp = true; // is exp, see whether is num from now
                num = false;
            } else if (c == '+' || c == '-') { // +, - must appear after e
                if (s.charAt(i - 1) != 'e')
                    return false;
            } else
                return false;
            i++;
        }
        return num; // whether is num or not
    }

}
package aMaz;

/**
 * Implement atoi to zigZagConversion a string to an integer.
 * Hint: Carefully consider all possible input cases. If you want a challenge,
 * please do not see below and ask yourself what are the possible input cases.
 * Keys: Whitespaces, Additional chars, Signs, Out of range
 * Requirements for atoi:
 */
class StringtoInteger {
    public static void main(String[] args) {
        System.out.println("MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("2147483648: " + atoi("2147483648"));
        System.out.println("-2147483647: " + atoi("-2147483647"));
        System.out.println("-2147483648: " + atoi("-2147483648"));
    }

    /**
     * Whitespace, sign, out of range Trim the unnecessary whitespaces
     * initialize a variable as long to store the result, use a boolean as a flag to mark whether its negative
     * return MAX or MIN if its out of range
     */
    public static int atoi(String str) {
        /*validate input*/
        if (str == null || str.length() == 0)
            return 0;
        long longRes = 0; // result can be out of range
        /*whitespaces*/
        str = str.trim(); // remove front and trailing whitespaces
        /*sign*/
        boolean neg = false; // is negative or not
        if (str.charAt(0) == '-') {
            neg = true;
            str = str.substring(1, str.length());
        } else if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }
        /*calculation*/
        int i = 0;
        while (i < str.length()) { // calculate without sign
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                longRes = longRes * 10 + (c - '0');
            } else
                break; // break when not a digit
            i++;
        }
        longRes = neg ? longRes * (-1) : longRes; // add sign
        /*out of range*/
        if (longRes > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (longRes < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) longRes;
    }

}

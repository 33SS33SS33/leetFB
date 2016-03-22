package easy;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * <p/>
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 * <p/>
 * For example,
 * Given s = "Hello World",
 * return 5.
 * Tags: String
 */
class LengthOfLastWord {
    public static void main(String[] args) {
        String a = " ";
        String b = "Hello World";
        String c = "           ";
        String d = "a";
        System.out.println(lengthOfLastWord(a));
        System.out.println(lengthOfLastWordB(a));
        System.out.println(lengthOfLastWordC(a));
        System.out.println(lengthOfLastWordD(a));
        System.out.println(lengthOfLastWord(b));
        System.out.println(lengthOfLastWordB(b));
        System.out.println(lengthOfLastWordC(b));
        System.out.println(lengthOfLastWordD(b));
        System.out.println(lengthOfLastWord(c));
        System.out.println(lengthOfLastWordB(c));
        System.out.println(lengthOfLastWordC(c));
        System.out.println(lengthOfLastWordD(c));
        System.out.println(lengthOfLastWord(d));
        System.out.println(lengthOfLastWordB(d));
        System.out.println(lengthOfLastWordC(d));
        System.out.println(lengthOfLastWordD(d));
    }

    /**
     * Traverse backwards
     * Use count to remember length of word
     * Start counting from non-space char
     * Return when next space is met and length is not zero
     */
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int len = s.length();
        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ')
                count++;
            if (s.charAt(i) == ' ' && count != 0)
                return count;
        }
        return count;
    }

    // mine, trim and check from back
    public static int lengthOfLastWordB(String s) {
        s = s.trim(); // remove front and trailing spaces
        char space = ' ';
        if (s.indexOf(space) == -1)
            return s.length(); // dont have a space
        int len = s.length();
        for (int i = len - 1; i >= 0; i--) { // traverse backwards
            if (s.charAt(i) == ' ' && i != len - 1) { // is space and not last one
                return len - 1 - i;
            }
        }
        return 0;
    }

    public static int lengthOfLastWordC(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (s == null)
            return 0;
        char[] chars = s.toCharArray();
        int upper = chars.length - 1;
        while (upper >= 0 && chars[upper] == ' ')
            upper--;

        int len = 0;
        for (int i = 0; i <= upper; i++) {
            if (chars[i] == ' ')
                len = 0;
            else
                len++;
        }
        return len;
    }

    public static int lengthOfLastWordD(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int result = 0;
        int len = s.length();
        boolean flag = false;
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                flag = true;
                result++;
            } else {
                if (flag)
                    return result;
            }
        }

        return result;
    }
}

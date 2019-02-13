package aMaz;

/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 * Tags: Two pointers, String
 * 用l<r判断比较好 否则会越界 记得每个循环都加上l<r的判断
 */
class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(validPalindrome(""));
        System.out.println(validPalindromeb(""));
//        System.out.println(validPalindromec(""));
        System.out.println(validPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(validPalindromeb("A man, a plan, a canal: Panama"));
//        System.out.println(validPalindromec("A man, a plan, a canal: Panama"));
    }

    public static boolean validPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0, tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }
        return true;
    }

    /**
     * Two pointers
     */
    public static boolean validPalindromeb(String s) {
        if (s == null || s.length() == 0)
            return true;
        int start = 0;
        int end = s.length() - 1;
        s = s.toLowerCase();
        while (start < end) {
            // move start
            while (start <= end && !isValid(s.charAt(start)))
                start++;
            // for string without letters or digit
            if (start > end)
                return true;
            // move end
            while (start <= end && !isValid(s.charAt(end)))
                end--;
            if (s.charAt(start) != s.charAt(end))
                break;
            start++;
            end--;
        }
        return end <= start;
    }

    private static boolean isValid(char c) {
        return Character.isLetterOrDigit(c);
    }

    /**
     * Put letters and numbers in a new string and zigZagConversion to lowercase
     * O(2n) Time, O(n) Space
     */
 /*   public static boolean validPalindromec(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray())
            if (Character.isLetterOrDigit(c))
                sb.append(c);
        String copy = sb.toString().toLowerCase();
        int length = copy.length();
        for (int i = 0; i < length / 2; i++) {
            if (copy.charAt(i) != copy.charAt(length - (i + 1))) {
                return false;
            }
        }
        return true;
    }*/

}

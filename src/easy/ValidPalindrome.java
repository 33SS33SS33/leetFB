package easy;

import java.util.Stack;

/**
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 * <p/>
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * <p/>
 * Note:
 * Have you consider that the string might be empty? This is a good question to
 * ask during an interview.
 * <p/>
 * For the purpose of this problem, we define empty string as valid palindrome.
 * <p/>
 * Tags: Two pointers, String
 */
class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindromeC("A man, a plan, a canal: Panama"));
        System.out.println(isPalindromeB("A man, a plan, a canal: Panama"));
        System.out.println(isPalindromeD("A man, a plan, a canal: Panama"));
    }

    /**
     * ----Stack------
     */
    public static boolean isPalindromeB(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int len = s.length();
        if (len < 2)
            return true;
        Stack<Character> stack = new Stack<Character>();
        int index = 0;
        while (index < len / 2) {
            stack.push(s.charAt(index));
            index++;
        }
        if (len % 2 == 1)
            index++;
        while (index < len) {
            if (stack.empty())
                return false;
            char temp = stack.pop();
            if (s.charAt(index) != temp)
                return false;
            else
                index++;
        }
        return true;
    }

    /**
     * Two pointers
     */
    public static boolean isPalindromeC(String s) {
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
     * Put letters and numbers in a new string and convert to lowercase
     * O(2n) Time, O(n) Space
     */
    public static boolean isPalindromeD(String s) {
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
    }
}

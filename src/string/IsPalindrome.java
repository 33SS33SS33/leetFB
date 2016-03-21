package string;

/**
 * Created by GAOSHANSHAN835 on 2016/1/5.
 */
public class IsPalindrome {
    public static void main(String[] args) {
        String s = "foof";
        System.out.println(new IsPalindrome().isPalindrome(s));
        System.out.println(new IsPalindrome().isPalindromeB(s));
        System.out.println();
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (!isValid(s.charAt(l))) {
                l++;
                continue;
            }
            if (!isValid(s.charAt(r))) {
                r--;
                continue;
            }
            if (!isSame(s.charAt(l), s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    private boolean isValid(char c) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')
            return true;
        return false;
    }
    private boolean isSame(char c1, char c2) {
        if (c1 >= 'A' && c1 <= 'Z')
            c1 = (char) (c1 - 'A' + 'a');
        if (c2 >= 'A' && c2 <= 'Z')
            c2 = (char) (c2 - 'A' + 'a');
        return c1 == c2;

    }

    /**better*/
    public boolean isPalindromeB(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while (i < j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

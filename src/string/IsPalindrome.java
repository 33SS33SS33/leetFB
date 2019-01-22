package string;

/**
 * Created by GAOSHANSHAN835 on 2016/1/5.
 */
public class IsPalindrome {
    public static void main(String[] args) {
        String s = "foof";
        System.out.println(new IsPalindrome().isPalindromeB(s));
        System.out.println();
    }

    /**
     * better
     */
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

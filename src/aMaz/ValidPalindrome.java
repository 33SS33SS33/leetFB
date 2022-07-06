package aMaz;


class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(validPalindrome("race a car"));
        System.out.println(validPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }
        return true;
    }
}

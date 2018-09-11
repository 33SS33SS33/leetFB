package easy;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 * Keys: Negative integer?
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * If you are thinking of converting the integer to string, note the
 * restriction of using extra space.
 * You could also try reversing an integer. However, if you have solved the
 * problem "Reverse Integer", you know that the reversed integer might
 * overflow. How would you handle such case?
 * There is a more generic way of solving this problem.
 * Tags: Math
 * Thoughts
 * Problems related with numbers are frequently solved by / and %. No need of extra space is required.
 * This problem is similar with the Reverse Integer problem.
 * Note: no extra space here means do not convert the integer to string,
 * since string will be a copy of the integer and take extra space. The space take by div, left, and right can be ignored.
 */

class PalindromeNo {
    public static void main(String[] args) {
        System.out.println(isPalindromeB(1));
        System.out.println(isPalindromeB(12));
        System.out.println(isPalindromeB(32123));
        System.out.println(isPalindromeB(321123));
        System.out.println(isPalindromeB(-1));
        System.out.println(isPalindromeB(1234567));
        System.out.println(isPalindromeB(1000000021));
    }

    public static boolean isPalindromeB(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0))
            return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return (x == rev || x == rev / 10);
    }
}

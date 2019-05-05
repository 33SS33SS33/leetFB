package aMaz;

/**
 * Created by shanshan on 2/7/19.
 */
public class FindtheClosestPalindrome {
    public static void main(String[] args) {
        System.out.println(new FindtheClosestPalindrome().findtheClosestPalindrome("123"));
    }

    /**
     * Given an integer n, find the closest integer (not including itself), which is a palindrome.
     * The 'closest' is defined as absolute difference minimized between two integers.
     * Example 1:
     * Input: "123"
     * Output: "121"
     * Note:
     * The input n is a positive integer represented by string, whose length will not exceed 18.
     * If there is a tie, return the smaller one as answer.
     */
    public String findtheClosestPalindrome(String n) {
        long num = Long.parseLong(n);
        long newNum = num;
        long largePalin = 0;
        long smallPalin = 0;
        long unit = 1;
        for (int i = 0; i < n.length() / 2 - 1; i++) unit *= 10;
        do {
            largePalin = makePalindrome(newNum += unit);
        } while (largePalin <= num);
        newNum = num;
        do {
            if (newNum < unit) break;
            smallPalin = makePalindrome(newNum -= unit);
        } while (smallPalin >= num);

        return num - smallPalin <= largePalin - num ? String.valueOf(smallPalin) : String.valueOf(largePalin);
    }

    private long makePalindrome(long num) {
        char[] arr = String.valueOf(num).toCharArray();
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            arr[tail--] = arr[head++];
        }
        return Long.parseLong(new String(arr));
    }
}

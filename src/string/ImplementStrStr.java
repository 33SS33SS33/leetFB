package string;

/**
 * Created by GAOSHANSHAN835 on 2016/1/4.
 */

/**
 * Implement strStr().
  Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Tags: Two Pointers, String
 */

public class ImplementStrStr {
    public static void main(String[] args) {
        String haystack = "mississippi";
        String needle = "issi";
        System.out.println(strStr(haystack, needle));
        System.out.println(strStrB(haystack, needle));
        System.out.println();

        String str1 = "14531234";
        String str2 = "123";
        String str3 = "1123";
        String str4 = "1245";
        String str5 = "12121212123";
        System.out.println(strStr(str1, str2));
        System.out.println(strStr(str3, str2));
        System.out.println(strStr(str4, str2));
        System.out.println(strStr(str5, str2));
        System.out.println();
        System.out.println(strStrB(str1, str2));
        System.out.println(strStrB(str3, str2));
        System.out.println(strStrB(str4, str2));
        System.out.println(strStrB(str5, str2));
        System.out.println();
        System.out.println(strStrC(str1, str2));
        System.out.println(strStrC(str3, str2));
        System.out.println(strStrC(str4, str2));
        System.out.println(strStrC(str5, str2));
    }

    /*两个循环 大循环就是遍历长字符串
    然后在长字符串的每个起始位都开始小循环比对字符*/
    public static int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length())
                    return i;
                if (i + j == haystack.length())
                    return -1;
                if (needle.charAt(j) != haystack.charAt(i + j))
                    break;
            }
        }
    }

    /**
     * brute force
     */
    public static int strStrB(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() == 0)
            return 0;
        if (needle.length() > haystack.length())
            return -1;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean successFlag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    successFlag = false;
                    break;
                }
            }
            if (successFlag)
                return i;
        }
        return -1;
    }

    /**
     * ----------KMP----------
     */
    public static int strStrC(String haystack, String needle) {
        if (haystack == null || needle == null)
            return 0;
        int h = haystack.length();
        int n = needle.length();
        if (n > h)
            return -1;
        if (n == 0)
            return 0;
        int[] next = getNext(needle);
        int i = 0;
        while (i <= h - n) {
            int success = 1;
            for (int j = 0; j < n; j++) {
                if (needle.charAt(0) != haystack.charAt(i)) {
                    success = 0;
                    i++;
                    break;
                } else if (needle.charAt(j) != haystack.charAt(i + j)) {
                    success = 0;
                    i = i + j - next[j - 1];
                    break;
                }
            }
            if (success == 1)
                return i;
        }
        return -1;
    }
    //calculate KMP array
    public static int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = 0;
        for (int i = 1; i < needle.length(); i++) {
            int index = next[i - 1];
            while (index > 0 && needle.charAt(index) != needle.charAt(i)) {
                index = next[index - 1];
            }
            if (needle.charAt(index) == needle.charAt(i)) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }
        return next;
    }
}

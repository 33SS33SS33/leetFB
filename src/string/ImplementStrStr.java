package string;

class ImplementStrStr {
    public static void main(String[] args) {
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
        System.out.println(strStr2(str1, str2));
        System.out.println(strStr2(str3, str2));
        System.out.println(strStr2(str4, str2));
        System.out.println(strStr2(str5, str2));
        System.out.println();
        System.out.println(strStr3(str1, str2));
        System.out.println(strStr3(str3, str2));
        System.out.println(strStr3(str4, str2));
        System.out.println(strStr3(str5, str2));

    }

    /**
     * return null if anyone is null or haystack is shorter
     * return haystack if needle's length is 0
     * traverse fewer times by substract the length
     * compare each char with two pointers till needle's length runs out
     * if not returned during loop, return null
     */
    public static String strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length())
            return null;
        if (needle.length() == 0)
            return haystack;
        int len = haystack.length();
        int tarLen = needle.length();
        for (int i = 0; i < len - tarLen + 1; i++) { // iteration times reduced
            int j = 0;
            int k = i;
            while (k < len && haystack.charAt(k) == needle.charAt(j)) {
                k++;
                j++;
                if (j == needle.length())
                    return haystack.substring(k - j);
            }
        }
        return null;
    }

    public static String strStr2(String haystack, String needle) {
        if ((haystack == null) || (needle == null)) {
            return null;
        }
        int lengthOfStack = haystack.length();
        int lengthOfNeedle = needle.length();
        if (lengthOfStack < lengthOfNeedle) {
            return null;
        }
        for (int i = 0; i <= lengthOfStack - lengthOfNeedle; i++) {
            if (haystack.substring(i, i + lengthOfNeedle).equals(needle)) {
                return haystack.substring(i);
            }
        }
        return null;
    }

    public static String strStr3(String haystack, String needle) {
        if (haystack == null || needle == null)
            return null;
        if (haystack.length() == 0) {
            return needle.length() == 0 ? "" : null;
        }
        if (needle.length() == 0)
            return haystack;
        if (haystack.length() < needle.length())
            return null;

        int base = 29;
        long patternHash = 0;
        long tempBase = 1;

        for (int i = needle.length() - 1; i >= 0; i--) {
            patternHash += (int) needle.charAt(i) * tempBase;
            tempBase *= base;
        }

        long hayHash = 0;
        tempBase = 1;
        for (int i = needle.length() - 1; i >= 0; i--) {
            hayHash += (int) haystack.charAt(i) * tempBase;
            tempBase *= base;
        }
        tempBase /= base;

        if (hayHash == patternHash) {
            return haystack;
        }

        for (int i = needle.length(); i < haystack.length(); i++) {
            hayHash = (hayHash - (int) haystack.charAt(i - needle.length()) * tempBase) * base
                    + (int) haystack.charAt(i);
            if (hayHash == patternHash) {
                return haystack.substring(i - needle.length() + 1);
            }
        }
        return null;
    }

}

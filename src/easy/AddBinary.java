package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 * Tags: Math, String
 */

/**
 * 就是二进制加法 从后往前加也可以 记得str int转换
 */
class AddBinary {
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
        System.out.println(addBinaryB(a, b));
        System.out.println(addBinaryC(a, b));
    }

    /**
     * 最好的
     * Traverse the longest binary backwards
     * Use + to insert to front, turn digit sum to int and restore to binary
     */
    public static String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        int carry = 0;
        String res = "";
        int i = 0;
        while (i < m || i < n) {
            int p = i < m ? a.charAt(m - 1 - i) - '0' : 0;
            int q = i < n ? b.charAt(n - 1 - i) - '0' : 0;
            int temp = p + q + carry;
            carry = temp / 2;
            res = temp % 2 + res;
            i++;
        }
        return carry == 0 ? res : "1" + res;
    }

    //    最好的
    public static String addBinaryC(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        StringBuilder stb = new StringBuilder();
        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int aByte;
        int bByte;
        int carry = 0;
        int result;
        while (i > -1 || j > -1 || carry == 1) {
            aByte = (i > -1) ? Character.getNumericValue(aArray[i--]) : 0;
            bByte = (j > -1) ? Character.getNumericValue(bArray[j--]) : 0;
            result = aByte ^ bByte ^ carry;
            carry = ((aByte + bByte + carry) >= 2) ? 1 : 0;
            stb.append(result);
        }
        return stb.reverse().toString();
    }

    // add 0 and calculate one by one
    public static String addBinaryB(String a, String b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        StringBuilder result = new StringBuilder();
        int lenA = a.length();
        int lenB = b.length();
        int i = lenA - 1;
        int j = lenB - 1;
        boolean carry = false;
        while (i > -1 || j > -1) {
            char c1 = i > -1 ? a.charAt(i) : '0';
            char c2 = j > -1 ? b.charAt(j) : '0';
            if (c1 == '1' && c2 == '1') {
                if (carry)
                    result.append(1);
                else
                    result.append(0);
                carry = true; // set carry for next digit
            } else if (c1 == '1' || c2 == '1') {
                if (carry) {
                    result.append(0);
                    carry = true; // set carry for next digit
                } else {
                    result.append(1);
                    carry = false;
                }
            } else {
                if (carry)
                    result.append(1);
                else
                    result.append(0);
                carry = false;
            }
            i--;
            j--;
        }
        if (carry)
            result.append('1');
        return result.reverse().toString();
    }

}


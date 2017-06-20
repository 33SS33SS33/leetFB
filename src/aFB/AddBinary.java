package aFB;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 * Tags: Math, String
 * 就是二进制加法 从后往前加也可以 记得str int转换
 */
class AddBinary {
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(addBinary(a, b));
        System.out.println(addBinaryC(a, b));
    }

    //  最好的
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    /**
     * Traverse the longest binary backwards
     * Use + to insert to front, turn digit sum to int and restore to binary
     */
    public static String addBinaryb(String a, String b) {
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

}


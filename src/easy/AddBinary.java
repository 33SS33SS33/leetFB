package easy;

/**
 * Created by GAOSHANSHAN835 on 2016/1/7.
 */
/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 *
 * Tags: Math, String
 */
class AddBinary {
    public static void main(String[] args) {
        String a = "1111001100101111011111000";
        String b = "111001011011111010001";
        // String a = "1010";
        // String b = "1011";
        System.out.println(addBinary(a, b));
        System.out.println(addBinaryB(a, b));
        System.out.println(addBinaryC(a, b));
    }

    /**
     * Traverse the longest binary backwards
     *
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

    // add 0 and calculate one by one
    public static String addBinaryB(String a, String b) {
        if (a == null) return b;
        if (b == null) return a;

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
                else result.append(0);
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
                if (carry) result.append(1);
                else result.append(0);
                carry = false;
            }
            i--;
            j--;
        }
        if (carry) result.append('1');
        return result.reverse().toString();
    }

    public static String addBinaryC(String a, String b) {
        if(a==null || a.length()==0)
            return b;
        if(b==null || b.length()==0)
            return a;

        int pa = a.length()-1;
        int pb = b.length()-1;

        int flag = 0;
        StringBuilder sb = new StringBuilder();
        while(pa >= 0 || pb >=0){
            int va = 0;
            int vb = 0;

            if(pa >= 0){
                va = a.charAt(pa)=='0'? 0 : 1;
                pa--;
            }
            if(pb >= 0){
                vb = b.charAt(pb)=='0'? 0: 1;
                pb--;
            }

            int sum = va + vb + flag;
            if(sum >= 2){
                sb.append(String.valueOf(sum-2));
                flag = 1;
            }else{
                flag = 0;
                sb.append(String.valueOf(sum));
            }
        }

        if(flag == 1){
            sb.append("1");
        }

        String reversed = sb.reverse().toString();
        return reversed;
    }
}


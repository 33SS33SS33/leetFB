package medium;

/**
 * Given two numbers represented as strings, return multiplication of the
 * numbers as a string.
 * Note: The numbers can be arbitrarily large and are non-negative.
 *
 * Tags: Math, String
 */
class MultiplyStrings {
    public static void main(String[] args) {
        String num1 = "322";
        String num2 = "3";
        System.out.println(multiplyA(num1, num2));
        System.out.println(multiplyB(num1, num2));
        System.out.print(multiplyC(num1, num2));
    }
/*假设第一个数长度是n，第二个数长度是m，我们知道结果长度为m+n或者m+n-1（没有进位的情况）。
对于某一位i，要计算这个位上的数字，我们需要对所有能组合出这一位结果的位进行乘法，
即第1位和第i位，第2位和第i-1位，... ，然后累加起来，最后我们取个位上的数值，然后剩下的作为进位放到下一轮循环中。
这个算法两层循环，每层循环次数是O(m+n)，所以时间复杂度是O((m+n)^2)。
算法中不需要额外空间，只需要维护一个进位变量即可，所以空间复杂度是O(1)。*/
    /**
     * Use an integer array to store the result of each digit and build
     * Convert digit to integer to calculate res of each digit
     * Add carry to next res, and mod current res with 10
     * Note that the result digit is reversed in the array
     * Skip trailing zeros and build from behind
     */
    public static String multiplyA(String num1, String num2) {
        if (num1 == null || num2 == null)
            return "";
        if (num1.equals("0") || num2.equals("0"))
            return "0"; // if one is 0
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int m = c1.length;
        int n = c2.length;
        int[] res = new int[m + n]; // max length possible
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // note += below
                res[m + n - i - j - 2] += (c1[i] - '0') * (c2[j] - '0'); // reverse to keep zeros of result but skip unnecessary zeros
                res[m + n - i - j - 1] += res[m + n - i - j - 2] / 10;
                res[m + n - i - j - 2] %= 10;
            }
        }
        // build ans string
        StringBuilder ans = new StringBuilder();
        for (int i = m + n - 1; i >= 0; i--) {
            if (res[i] != 0) {
                for (int j = i; j >= 0; j--)
                    ans.append(res[j]); // note res[j]
                return ans.toString();
            }
        }
        return "0";
    }

    /**
     * creek
     * The key to solve this problem is multiplying each digit of the numbers at the corresponding positions and get the sum values at each position.
     * That is how we do multiplication manually.
     */
    public static String multiplyB(String num1, String num2) {
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();
        int[] d = new int[num1.length() + num2.length()];
        //multiply each digit and sum at the corresponding positions
        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                d[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
            }
        }
        StringBuilder sb = new StringBuilder();
        //calculate each digit
        for (int i = 0; i < d.length; i++) {
            int mod = d[i] % 10;
            int carry = d[i] / 10;
            if (i + 1 < d.length) {
                d[i + 1] += carry;
            }
            sb.insert(0, mod);
        }
        //remove front 0's
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static String multiplyC(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0)
            return "";
        if (num1.charAt(0) == '0')
            return "0";
        if (num2.charAt(0) == '0')
            return "0";
        StringBuilder res = new StringBuilder();
        int num = 0;
        for (int i = num1.length() + num2.length(); i > 0; i--) {
            for (int j = Math.min(i - 1, num1.length()); j > 0; j--) {
                if (i - j <= num2.length()) {
                    num += (int) (num1.charAt(j - 1) - '0') * (int) (num2.charAt(i - 1 - j) - '0');
                }
            }
            if (i != 1 || num > 0)
                res.append(num % 10);
            num = num / 10;
        }
        return res.reverse().toString();
    }
}

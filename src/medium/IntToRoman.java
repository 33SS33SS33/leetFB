package medium;

/**
 * Given an integer, convert it to a roman numeral.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * Tags: Math, String
 */
class IntToRoman {
    public static void main(String[] args) {
        System.out.println(new IntToRoman().intToRoman(3));
        System.out.println(new IntToRoman().intToRoman2(3));
        System.out.println(new IntToRoman().intToRomanC(3));
    }

    public static final int[] intDict = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public static final String[] romanDict = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * Recursion
     * Go through the dict, if num >= dict, insert it to head
     * Pass rest of the integer to next recursion
     */
    public String intToRoman(int num) {
        for (int i = 0; i < intDict.length; i++) {
            if (intDict[i] <= num) {
                return romanDict[i] + intToRoman(num - intDict[i]);
            }
        }
        return ""; // Note the return statement
    }
    
    /**
     * While loop
     * Go through the dict, if num >= intDict[i], append roman integer
     * num -= intDict[i]
     * if num < intDict[i], i++
     */
    public String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0 && i < intDict.length) {
            if (num >= intDict[i]) {
                sb.append(romanDict[i]);
                num -= intDict[i]; // update num
            } else {
                i++; // move to next roman
            }
        }
        return sb.toString();
    }

    static final int[]  N = { 1, 5, 10, 50, 100, 500, 1000 };
    static final char[] C = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
    public static String intToRomanC(int num) {
        String buff = "";
        char last = 0;
        int samecount = 0;
        while (num > 0) {
            for (int i = N.length - 1; i >= 0; i--) {
                if (num >= N[i]) {
                    num -= N[i];
                    buff += C[i];
                    if (last == C[i]) {
                        samecount++;
                    } else {
                        samecount = 0;
                    }
                    if (samecount == 3) {
                        int s = 5 * N[i];
                        buff = buff.substring(0, buff.length() - 4);
                        if (!buff.isEmpty() && buff.charAt(buff.length() - 1) == C[i + 1]) {
                            s += N[i + 1];
                            buff = buff.substring(0, buff.length() - 1);
                        }
                        return buff + C[i] + intToRomanC(s) + intToRomanC(num);
                    }
                    last = C[i];
                    break;
                }
            }
        }
        return buff;
    }
}